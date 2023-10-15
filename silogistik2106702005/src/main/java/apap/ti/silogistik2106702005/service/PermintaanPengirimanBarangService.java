package apap.ti.silogistik2106702005.service;

import apap.ti.silogistik2106702005.model.PermintaanPengiriman;
import apap.ti.silogistik2106702005.model.Barang;
import apap.ti.silogistik2106702005.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106702005.repository.PermintaanPengirimanBarangDb;

import java.util.List;

public interface PermintaanPengirimanBarangService {
    void savePermintaanPengirimanBarang(PermintaanPengirimanBarang permintaanBarang);

    List<PermintaanPengirimanBarang> getAllPermintaanPengirimanBarang();
}
