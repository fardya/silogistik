package apap.ti.silogistik2106702005.controller;

import apap.ti.silogistik2106702005.dto.PermintaanPengirimanMapper;
import apap.ti.silogistik2106702005.dto.request.CreatePermintaanPengirimanRequest;
import apap.ti.silogistik2106702005.model.PermintaanPengiriman;
import apap.ti.silogistik2106702005.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106702005.service.BarangService;
import apap.ti.silogistik2106702005.service.KaryawanService;
import apap.ti.silogistik2106702005.service.PermintaanPengirimanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PermintaanPengirimanController {
    @Autowired
    private PermintaanPengirimanMapper permintaanMapper;

    @Autowired
    private PermintaanPengirimanService permintaanService;

    @Autowired
    private BarangService barangService;

    @Autowired
    private KaryawanService karyawanService;

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

        model.addAttribute("listKaryawan", karyawanService.getAllKaryawan());
        model.addAttribute("listBarang", barangService.getAllBarang());

        return "form-add-permintaan";
    }

    @PostMapping(value = "/permintaan-pengiriman/tambah", params = {"tambahBarang"})
    public String addRowBarang(@ModelAttribute CreatePermintaanPengirimanRequest permintaanDTO, Model model) {
        if (permintaanDTO.getPermintaanPengirimanBarang() == null || permintaanDTO.getPermintaanPengirimanBarang().size() == 0) {
            permintaanDTO.setPermintaanPengirimanBarang(new ArrayList<>());;
        }

        permintaanDTO.getPermintaanPengirimanBarang().add(new PermintaanPengirimanBarang());

        var listBarang = barangService.getAllBarang();

        model.addAttribute("listKaryawan", karyawanService.getAllKaryawan());
        model.addAttribute("permintaanDTO", permintaanDTO);
        model.addAttribute("listBarang", listBarang);

        return "form-add-permintaan";
    }

    @PostMapping("/permintaan-pengiriman/tambah")
    public String addPermintaan(@ModelAttribute CreatePermintaanPengirimanRequest permintaanDTO, Model model) {
        var permintaan = permintaanMapper.createPermintaanPengirimanRequestToPermintaanPengiriman(permintaanDTO);
        permintaanService.addPermintaanPengiriman(permintaan);

        model.addAttribute("id", permintaan.getId());

        return "success-add-permintaan";
    }
}
