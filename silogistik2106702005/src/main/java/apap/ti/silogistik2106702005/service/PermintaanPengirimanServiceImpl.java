package apap.ti.silogistik2106702005.service;

import apap.ti.silogistik2106702005.model.Barang;
import apap.ti.silogistik2106702005.model.PermintaanPengiriman;
import apap.ti.silogistik2106702005.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106702005.repository.PermintaanPengirimanBarangDb;
import apap.ti.silogistik2106702005.repository.PermintaanPengirimanDb;
import com.github.javafaker.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService {
    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;

    @Autowired
    PermintaanPengirimanBarangService permintaanBarangService;

    @Autowired
    PermintaanPengirimanBarangDb permintaanBarangDb;

    @Override
    public List<PermintaanPengiriman> getAllPermintaanPengiriman() { return permintaanPengirimanDb.findAllByOrderByWaktuPermintaanDesc(); }

    @Override
    public PermintaanPengiriman getPermintaanPengirimanById(Long id) {
        Optional<PermintaanPengiriman> permintaan = permintaanPengirimanDb.findById(id);
        if (permintaan.isPresent()) {
            return permintaan.get();
        } return null;
    }

    @Override
    public void addPermintaanPengiriman(PermintaanPengiriman permintaanFromDto) {
        LocalDate tanggalPengiriman = permintaanFromDto.getTanggalPengiriman();
        LocalDateTime currentDateTime = LocalDateTime.now();
        String nomor = generateNomor(countJumlahBarang(permintaanFromDto), permintaanFromDto.getJenisLayanan(), currentDateTime);

        permintaanFromDto.setTanggalPengiriman(tanggalPengiriman);
        permintaanFromDto.setWaktuPermintaan(currentDateTime);
        permintaanFromDto.setIsCancelled(Boolean.FALSE);
        permintaanFromDto.setNomorPengiriman(nomor);

        permintaanPengirimanDb.save(permintaanFromDto);

        List<PermintaanPengirimanBarang> permintaanNoDouble = new ArrayList<>();

        for (PermintaanPengirimanBarang permintaanBarangDto : permintaanFromDto.getPermintaanPengirimanBarang()) {
            boolean flag = Boolean.FALSE;
            for (PermintaanPengirimanBarang permintaanExisting : permintaanNoDouble) {
                if (permintaanExisting.getBarang().getSku().equals(permintaanBarangDto.getBarang().getSku())) {
                    int currentKuantitas = permintaanExisting.getKuantitasPesanan();
                    permintaanExisting.setKuantitasPesanan(currentKuantitas + permintaanBarangDto.getKuantitasPesanan());
                    flag = Boolean.TRUE;
                    break;
                }
            }
            if (flag == Boolean.FALSE) {
                permintaanNoDouble.add(permintaanBarangDto);
            }
        }

        for (PermintaanPengirimanBarang permintaanBarangDto : permintaanNoDouble) {
            permintaanBarangDto.setPermintaanPengiriman(permintaanFromDto);
            permintaanBarangService.savePermintaanPengirimanBarang(permintaanBarangDto);
        }

        permintaanFromDto.setId(permintaanFromDto.getId());
        permintaanPengirimanDb.save(permintaanFromDto);
    }

    @Override
    public void deletePermintaanPengiriman(PermintaanPengiriman permintaan) { permintaanPengirimanDb.delete(permintaan); }

    @Override
    public int countJumlahBarang(PermintaanPengiriman permintaan) {
        int total = 0;
        for (PermintaanPengirimanBarang barang : permintaan.getPermintaanPengirimanBarang()) {
            total += barang.getKuantitasPesanan();
        }

        return total;
    }

    @Override
    public String generateNomor(int jumlah, int layanan, LocalDateTime waktu) {
        String nomor = "REQ";

        int newJumlah = jumlah % 100;
        nomor += String.format("%02d", newJumlah);

        if (layanan == 1) {
            nomor += "SAM";
        } else if (layanan == 2) {
            nomor += "KIL";
        } else if (layanan == 3) {
            nomor += "REG";
        } else {
            nomor += "HEM";
        }

        nomor += waktu.toString().substring(11, 19);

        return nomor;
    }

    @Override
    public List<PermintaanPengiriman> filterByWaktuPermintaan(LocalDateTime startDate, LocalDateTime endDate) {
        return permintaanPengirimanDb.findAllByWaktuPermintaanBetweenOrderByWaktuPermintaanDesc(startDate, endDate);
    }

    @Override
    public List<PermintaanPengiriman> getPermintaanByBarang(Barang barang) {
        var listPermintaanBarang = permintaanBarangDb.findByBarang(barang);
        LinkedHashSet<PermintaanPengiriman> setHasil = new LinkedHashSet<>();

        for (PermintaanPengirimanBarang i : listPermintaanBarang) {
            if (i.getPermintaanPengiriman() != null) {
                var permintaan = getPermintaanPengirimanById(i.getPermintaanPengiriman().getId());
                setHasil.add(permintaan);
            }
        }

        return (new ArrayList<>(setHasil));
    }
}
