package apap.ti.silogistik2106702005.service;

import apap.ti.silogistik2106702005.model.PermintaanPengiriman;
import apap.ti.silogistik2106702005.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106702005.repository.PermintaanPengirimanBarangDb;
import apap.ti.silogistik2106702005.repository.PermintaanPengirimanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService {
    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;
    @Autowired
    PermintaanPengirimanBarangDb permintaanPengirimanBarangDb;
    @Autowired
    PermintaanPengirimanBarangService permintaanPengirimanBarangService;

    @Override
    public List<PermintaanPengiriman> getAllPermintaanPengiriman() { return permintaanPengirimanDb.findAll(); }

    @Override
    public PermintaanPengiriman getPermintaanPengirimanById(Long id) {
        Optional<PermintaanPengiriman> permintaan = permintaanPengirimanDb.findById(id);
        if (permintaan.isPresent()) {
            return permintaan.get();
        } return null;
    }

    @Override
    public void addPermintaanPengiriman(PermintaanPengiriman permintaan) {
        LocalDate tanggalPengiriman = permintaan.getTanggalPengiriman();
        LocalDateTime currentDateTime = LocalDateTime.now();
        String nomor = generateNomor(countJumlahBarang(permintaan), permintaan.getJenisLayanan(), currentDateTime);

        permintaan.setTanggalPengiriman(tanggalPengiriman);
        permintaan.setWaktuPermintaan(currentDateTime);
        permintaan.setIsCancelled(Boolean.FALSE);
        permintaan.setNomorPengiriman(nomor);

        permintaanPengirimanDb.save(permintaan);

        for (PermintaanPengirimanBarang permintaanPengirimanBarang : permintaan.getPermintaanPengirimanBarang()) {
            permintaanPengirimanBarang.setPermintaanPengiriman(permintaan);
            permintaanPengirimanBarangDb.save(permintaanPengirimanBarang);

//            var permintaanExisting = permintaanPengirimanBarangService.checkIfExist(permintaan.getId(), permintaanPengirimanBarang.getBarang().getSku());
//
//            if (permintaanExisting != null) {
//                System.out.println("masuk sinii");
//                permintaanExisting.setKuantitasPesanan(permintaanExisting.getKuantitasPesanan() + permintaanPengirimanBarang.getKuantitasPesanan());
//                permintaanPengirimanBarangDb.save(permintaanExisting);
//            } else {
//                System.out.println("masukkesini");
//                permintaanPengirimanBarangDb.save(permintaanPengirimanBarang);
//            }
        }
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
}
