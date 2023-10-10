package apap.ti.silogistik2106702005.controller;

import apap.ti.silogistik2106702005.model.Gudang;
import apap.ti.silogistik2106702005.service.GudangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GudangController {
    @Autowired
    private GudangService gudangService;

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

        return "view-gudang";
    }
}
