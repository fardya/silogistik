package apap.ti.silogistik2106702005.service;

import apap.ti.silogistik2106702005.model.PermintaanPengiriman;

import java.time.LocalTime;
import java.util.List;

public interface PermintaanPengirimanService {
    List<PermintaanPengiriman> getAllPermintaanPengiriman();

    PermintaanPengiriman getPermintaanPengirimanById(Long id);

    void addPermintaanPengiriman(PermintaanPengiriman permintaan);

    void deletePermintaanPengiriman(PermintaanPengiriman permintaan);

    String generateNomor(int jumlah, int layanan, LocalTime waktu);
}
