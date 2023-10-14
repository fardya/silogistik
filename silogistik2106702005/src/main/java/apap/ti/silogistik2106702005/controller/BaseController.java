package apap.ti.silogistik2106702005.controller;

import apap.ti.silogistik2106702005.service.BarangService;
import apap.ti.silogistik2106702005.service.GudangService;
import apap.ti.silogistik2106702005.service.KaryawanService;
import apap.ti.silogistik2106702005.service.PermintaanPengirimanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class BaseController {
    @Autowired
    GudangService gudangService;

    @Autowired
    BarangService barangService;

    @Autowired
    PermintaanPengirimanService permintaanService;

    @Autowired
    KaryawanService karyawanService;

    @GetMapping("/")
    public String home(Model model) {
        int jumlahGudang = gudangService.getAllGudang().size();
        int jumlahBarang = barangService.getAllBarang().size();
        int jumlahPermintaan = permintaanService.getAllPermintaanPengiriman().size();
        int jumlahKaryawan = karyawanService.getAllKaryawan().size();

        model.addAttribute("gudang", jumlahGudang);
        model.addAttribute("barang", jumlahBarang);
        model.addAttribute("permintaan", jumlahPermintaan);
        model.addAttribute("karyawan", jumlahKaryawan);

        return "home";
    }
}
