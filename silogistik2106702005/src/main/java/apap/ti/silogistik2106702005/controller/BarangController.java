package apap.ti.silogistik2106702005.controller;

import apap.ti.silogistik2106702005.dto.BarangMapper;
import apap.ti.silogistik2106702005.dto.request.CreateBarangRequest;
import apap.ti.silogistik2106702005.dto.request.UpdateBarangRequest;
import apap.ti.silogistik2106702005.model.Barang;
import apap.ti.silogistik2106702005.service.BarangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.*;

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

        return "viewall-barang";
    }

    @GetMapping("barang/tambah")
    public String formAddBarang(Model model) {
        var barangDTO = new CreateBarangRequest();
        model.addAttribute("barangDTO", barangDTO);

        return "form-add-barang";
    }

    @PostMapping("barang/tambah")
    public void addBarang(@ModelAttribute CreateBarangRequest barangDTO, Model model) {
        String sku = barangService.generateSku(barangDTO.getTipeBarang());
        barangDTO.setSku(sku);

        var barang = barangMapper.createBarangRequestToBarang(barangDTO);
        barangService.addBarang(barang);
    }

    @GetMapping("/barang/{idBarang}")
    public String detailBarang(@PathVariable("idBarang") String sku, Model model) {
        var barang = barangService.getBarangBySku(sku);
        model.addAttribute("barang", barang);

        return "view-barang";
    }

    @GetMapping("/barang/{idBarang}/ubah")
    public String formUpdateBarang(@PathVariable("idBarang") String sku, Model model) {
        var barang = barangService.getBarangBySku(sku);

        model.addAttribute("barang", barang);

        return "form-update-barang";
    }

    @PostMapping("/barang/ubah")
    public void updateBarang(@ModelAttribute UpdateBarangRequest barangDTO, Model model) {
        var barangFromDto = barangMapper.updateBarangRequestToBarang(barangDTO);
        Barang updatedBarang = barangService.updateBarang(barangFromDto);
    }

}
