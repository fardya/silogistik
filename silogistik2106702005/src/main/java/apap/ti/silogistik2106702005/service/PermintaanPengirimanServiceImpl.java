package apap.ti.silogistik2106702005.service;

import apap.ti.silogistik2106702005.model.PermintaanPengiriman;
import apap.ti.silogistik2106702005.repository.PermintaanPengirimanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService {
    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;

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
    public void addPermintaanPengiriman(PermintaanPengiriman permintaan) { permintaanPengirimanDb.save(permintaan); }

    @Override
    public void deletePermintaanPengiriman(PermintaanPengiriman permintaan) { permintaanPengirimanDb.delete(permintaan); }

    @Override
    public String generateNomor(int jumlah, int layanan, LocalTime waktu) {
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

        nomor += waktu.toString();

        return nomor;
    }
}
