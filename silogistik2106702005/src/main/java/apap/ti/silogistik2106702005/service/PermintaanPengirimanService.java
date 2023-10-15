package apap.ti.silogistik2106702005.service;

import apap.ti.silogistik2106702005.model.Barang;
import apap.ti.silogistik2106702005.model.PermintaanPengiriman;

import java.time.LocalDateTime;
import java.util.List;

public interface PermintaanPengirimanService {
    List<PermintaanPengiriman> getAllPermintaanPengiriman();

    PermintaanPengiriman getPermintaanPengirimanById(Long id);

    void addPermintaanPengiriman(PermintaanPengiriman permintaan);

    void deletePermintaanPengiriman(PermintaanPengiriman permintaan);

    int countJumlahBarang(PermintaanPengiriman permintaan);

    String generateNomor(int jumlah, int layanan, LocalDateTime waktu);

    List<PermintaanPengiriman> filterByWaktuPermintaan(LocalDateTime startDate, LocalDateTime endDate);

    List<PermintaanPengiriman> getPermintaanByBarang(Barang barang);
}
