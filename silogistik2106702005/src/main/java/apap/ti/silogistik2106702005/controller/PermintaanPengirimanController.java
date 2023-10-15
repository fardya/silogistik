package apap.ti.silogistik2106702005.controller;

import apap.ti.silogistik2106702005.dto.PermintaanPengirimanMapper;
import apap.ti.silogistik2106702005.dto.request.CreatePermintaanPengirimanRequest;
import apap.ti.silogistik2106702005.dto.response.PermintaanPengirimanResponse;
import apap.ti.silogistik2106702005.model.PermintaanPengiriman;
import apap.ti.silogistik2106702005.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106702005.service.BarangService;
import apap.ti.silogistik2106702005.service.KaryawanService;
import apap.ti.silogistik2106702005.service.PermintaanPengirimanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

        List<PermintaanPengirimanResponse> listPermintaanDTO = new ArrayList<>();
        for (PermintaanPengiriman permintaan : listPermintaan) {
            listPermintaanDTO.add(permintaanMapper.permintaanToPermintaanPengirimanResponse(permintaan));
        }
        model.addAttribute("listPermintaan", listPermintaanDTO);

        return "viewall-permintaan";
    }

    @GetMapping("/permintaan-pengiriman/{idPermintaanPengiriman}")
    public String detailPermintaan(@PathVariable("idPermintaanPengiriman") Long id, Model model) {
        var permintaan = permintaanService.getPermintaanPengirimanById(id);

        var permintaanDTO = permintaanMapper.permintaanToPermintaanPengirimanResponse(permintaan);

        model.addAttribute("pp", permintaanDTO);

        return "view-permintaan";
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
    public String addPermintaan(@Valid @ModelAttribute CreatePermintaanPengirimanRequest permintaanDTO,
                                BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();

            String errorMessage = "";
            for (ObjectError error : errors) {
                errorMessage += error.getDefaultMessage() + ", ";
            }
            model.addAttribute("errorMessage", errorMessage.substring(0, errorMessage.length()-2));

            return "error-view-permintaan";
        }


        var permintaanFromDto = permintaanMapper.createPermintaanPengirimanRequestToPermintaanPengiriman(permintaanDTO);
        permintaanService.addPermintaanPengiriman(permintaanFromDto);

        model.addAttribute("nomor", permintaanFromDto.getNomorPengiriman());

        return "success-add-permintaan";
    }

    @GetMapping("/permintaan-pengiriman/{idPermintaanPengiriman}/cancel")
    public String cancelPermintaan(@PathVariable("idPermintaanPengiriman") Long id, Model model) {
        var permintaan = permintaanService.getPermintaanPengirimanById(id);

        var nomorPengiriman = permintaan.getNomorPengiriman();
        model.addAttribute("nomor", nomorPengiriman);

        Duration duration = Duration.between(permintaan.getWaktuPermintaan(), LocalDateTime.now());
        if (duration.toHours() > 24) {
            model.addAttribute("id", permintaan.getId());
            return "fail-cancel-permintaan";
        }

        permintaanService.deletePermintaanPengiriman(permintaan);
        return "success-cancel-permintaan";
    }

    @GetMapping("/filter-permintaan-pengiriman")
    public String filterPermintaan(
            @RequestParam(value = "start-date", required = false) LocalDate startDate,
            @RequestParam(value = "end-date", required = false) LocalDate endDate,
            @RequestParam(value = "sku", required = false) String sku,
            Model model
    ) {
        var listBarang = barangService.getAllBarang();
        model.addAttribute("listBarang", listBarang);

        if (startDate != null && endDate != null && sku != null) {
            var barang = barangService.getBarangBySku(sku);
            var list1 = permintaanService.filterByWaktuPermintaan(
                    startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
            var list2 = permintaanService.getPermintaanByBarang(barang);

            List<PermintaanPengirimanResponse> filteredPermintaan = new ArrayList<>();
            for (PermintaanPengiriman permintaan : list1) {
                if (list2.contains(permintaan)) {
                    var permintaanDTO = permintaanMapper.permintaanToPermintaanPengirimanResponse(permintaan);
                    filteredPermintaan.add(permintaanDTO);
                }
            }

            model.addAttribute("listPermintaan", filteredPermintaan);
        }

        return "filter-permintaan";
    }
}
