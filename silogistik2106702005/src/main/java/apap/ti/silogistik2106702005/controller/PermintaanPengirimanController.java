package apap.ti.silogistik2106702005.controller;

import apap.ti.silogistik2106702005.dto.PermintaanPengirimanMapper;
import apap.ti.silogistik2106702005.dto.request.CreatePermintaanPengirimanRequest;
import apap.ti.silogistik2106702005.model.PermintaanPengiriman;
import apap.ti.silogistik2106702005.service.PermintaanPengirimanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PermintaanPengirimanController {
    @Autowired
    private PermintaanPengirimanMapper permintaanMapper;

    @Autowired
    private PermintaanPengirimanService permintaanService;

    @GetMapping("/permintaan-pengiriman")
    public String listPermintaan(Model model) {
        List<PermintaanPengiriman> listPermintaan = permintaanService.getAllPermintaanPengiriman();
        model.addAttribute("listPermintaan", listPermintaan);

        return "viewall-permintaan";
    }

    @GetMapping("/permintaan-pengiriman/tambah")
    public String formAddPermintaan(Model model) {
        var permintaanDTO = new CreatePermintaanPengirimanRequest();
        model.addAttribute("permintaanDTO", permintaanDTO);

        return "form-add-permintaan";
    }

    @PostMapping("/permintaan-pengiriman/tambah")
    public void addPermintaan(@ModelAttribute CreatePermintaanPengirimanRequest permintaanDTO, Model model) {
    }
}
