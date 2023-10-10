package apap.ti.silogistik2106702005.controller;

import apap.ti.silogistik2106702005.dto.GudangMapper;
import apap.ti.silogistik2106702005.dto.request.UpdateGudangRequest;
import apap.ti.silogistik2106702005.model.Gudang;
import apap.ti.silogistik2106702005.model.GudangBarang;
import apap.ti.silogistik2106702005.model.Barang;
import apap.ti.silogistik2106702005.service.GudangService;
import apap.ti.silogistik2106702005.service.BarangService;
import apap.ti.silogistik2106702005.service.GudangBarangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GudangController {
    @Autowired
    private GudangMapper gudangMapper;

    @Autowired
    private GudangService gudangService;

    @Autowired
    private BarangService barangService;

    @Autowired
    private GudangBarangService gudangBarangService;

    @GetMapping("/gudang")
    public String listGudang(Model model) {
        List<Gudang> listGudang = gudangService.getAllGudang();
        model.addAttribute("listGudang", listGudang);

        return "viewall-gudang";
    }

    @GetMapping("/gudang/{idGudang}")
    public String detailGudang(@PathVariable("idGudang") Long id, Model model) {
        var gudang = gudangService.getGudangById(id);
        model.addAttribute("gudang", gudang);

        var listGudangBarang = gudangBarangService.getGudangBarangByGudang(gudang);

        return "view-gudang";
    }

    @GetMapping("/gudang/{idGudang}/restock-barang")
    public String formRestockGudang(@PathVariable("idGudang") Long id, Model model) {
        var gudang = gudangService.getGudangById(id);
        var listBarang = barangService.getAllBarang();

        model.addAttribute("gudang", gudang);
        model.addAttribute("listBarangExisting", listBarang);

        return "form-restock-gudang";
    }

    @PostMapping(value = "/gudang/restock-barang", params = {"tambahBarang"})
    public String addRowBarang(@ModelAttribute UpdateGudangRequest updateGudangRequest, Model model) {
        if (updateGudangRequest.getListGudangBarang() == null || updateGudangRequest.getListGudangBarang().size() == 0) {
            updateGudangRequest.setListGudangBarang((new ArrayList<>()));
        }

        updateGudangRequest.getListGudangBarang().add(new GudangBarang());
        List<Barang> listBarang = barangService.getAllBarang();

        model.addAttribute("gudangDTO", updateGudangRequest);
        model.addAttribute("listBarangExisting", listBarang);

        return "form-restock-gudang";
    }

    @PostMapping("/gudang/restock-barang")
    public String restockBarang(@ModelAttribute UpdateGudangRequest updateGudangRequest, Model model) {
        var gudang = gudangMapper.updateGudangRequestToGudang(updateGudangRequest);
        gudangService.updateGudang(gudang);

        model.addAttribute("gudang", updateGudangRequest);

        return "success-restock-gudang";
    }
}
