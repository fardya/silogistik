package apap.ti.silogistik2106702005.controller;

import apap.ti.silogistik2106702005.dto.BarangMapper;
import apap.ti.silogistik2106702005.dto.request.CreateBarangRequest;
import apap.ti.silogistik2106702005.dto.request.UpdateBarangRequest;
import apap.ti.silogistik2106702005.model.Barang;
import apap.ti.silogistik2106702005.service.BarangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BarangController {
    @Autowired
    private BarangMapper barangMapper;
    @Autowired
    private BarangService barangService;

    @GetMapping("/barang")
    public String listBarang(Model model) {
        List<Barang> listBarang = barangService.getAllBarang();
        model.addAttribute("listBarang", listBarang);

        List<Integer> totalStok = new ArrayList<>();
        for (Barang barang : listBarang) {
            totalStok.add(barangService.countStok(barang));
        }
        model.addAttribute("totalStok", totalStok);

        return "viewall-barang";
    }

    @GetMapping("barang/tambah")
    public String formAddBarang(Model model) {
        var barangDTO = new CreateBarangRequest();
        model.addAttribute("barangDTO", barangDTO);

        return "form-add-barang";
    }

    @PostMapping("barang/tambah")
    public String addBarang(@Valid @ModelAttribute CreateBarangRequest barangDTO,
                            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();

            String errorMessage = "";
            for (ObjectError error : errors) {
                errorMessage += error.getDefaultMessage() + ", ";
            }
            model.addAttribute("errorMessage", errorMessage.substring(0, errorMessage.length()-2));

            return "error-view-barang";
        }

        if (barangService.merkExists(barangDTO.getMerk())) {
            String errorMessage = "Barang dengan merk yang sama sudah terdaftar";
            model.addAttribute("errorMessage", errorMessage);

            return "error-view-barang";
        }

        var barang = barangMapper.createBarangRequestToBarang(barangDTO);
        barangService.addBarang(barang);

        model.addAttribute("sku", barang.getSku());

        return "success-add-barang";
    }

    @GetMapping("/barang/{idBarang}")
    public String detailBarang(@PathVariable("idBarang") String sku, Model model) {
        var barang = barangService.getBarangBySku(sku);
        int totalStok = barangService.countStok(barang);

        model.addAttribute("barang", barang);
        model.addAttribute("totalStok", totalStok);

        return "view-barang";
    }

    @GetMapping("/barang/{idBarang}/ubah")
    public String formUpdateBarang(@PathVariable("idBarang") String sku, Model model) {
        var barang = barangService.getBarangBySku(sku);
        var barangDTO = barangMapper.barangToUpdateBarangRequest(barang);

        model.addAttribute("barangDTO", barangDTO);

        return "form-update-barang";
    }

    @PostMapping("/barang/ubah")
    public String updateBarang(@ModelAttribute UpdateBarangRequest barangDTO, Model model) {
        if (barangService.merkExists(barangDTO.getMerk(), barangDTO.getSku())) {
            String errorMessage = "Barang dengan merk yang sama sudah terdaftar";
            model.addAttribute("errorMessage", errorMessage);

            return "error-view-barang";
        }

        var barangFromDto = barangMapper.updateBarangRequestToBarang(barangDTO);
        var barang = barangService.updateBarang(barangFromDto);

        model.addAttribute("sku", barang.getSku());

        return "success-update-barang";
    }

}
