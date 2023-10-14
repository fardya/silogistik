package apap.ti.silogistik2106702005.service;

import apap.ti.silogistik2106702005.model.PermintaanPengiriman;
import apap.ti.silogistik2106702005.model.Barang;
import apap.ti.silogistik2106702005.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106702005.repository.PermintaanPengirimanBarangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermintaanPengirimanBarangServiceImpl implements PermintaanPengirimanBarangService {
    @Autowired
    PermintaanPengirimanBarangDb permintaanBarangDb;

    @Override
    public void savePermintaanPengirimanBarang(PermintaanPengirimanBarang permintaanBarang) {
        permintaanBarangDb.save(permintaanBarang);
    }

//    @Override
//    public void saveFromPermintaan(PermintaanPengiriman permintaan) {
//
//
//        for (PermintaanPengirimanBarang permintaanBarang : permintaan.getPermintaanPengirimanBarang()) {
//
//        }
//    }

    @Override
    public PermintaanPengirimanBarang checkIfExist(Long idPermintaan, String skuBarang) {
        for (PermintaanPengirimanBarang permintaanPengirimanBarang : permintaanBarangDb.findAll()) {
            if (permintaanPengirimanBarang.getBarang().getSku().equals(skuBarang) && permintaanPengirimanBarang.getPermintaanPengiriman().getId().equals(idPermintaan)) {
                return permintaanPengirimanBarang;
            }
        }

        return null;
    }
}
