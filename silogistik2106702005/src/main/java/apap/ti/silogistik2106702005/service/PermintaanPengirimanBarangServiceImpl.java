package apap.ti.silogistik2106702005.service;

import apap.ti.silogistik2106702005.model.PermintaanPengiriman;
import apap.ti.silogistik2106702005.model.Barang;
import apap.ti.silogistik2106702005.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106702005.repository.PermintaanPengirimanBarangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermintaanPengirimanBarangServiceImpl implements PermintaanPengirimanBarangService {
    @Autowired
    PermintaanPengirimanBarangDb permintaanBarangDb;

    @Override
    public void savePermintaanPengirimanBarang(PermintaanPengirimanBarang permintaanBarang) {
        permintaanBarangDb.save(permintaanBarang);
    }

    @Override
    public List<PermintaanPengirimanBarang> getAllPermintaanPengirimanBarang() {
        return permintaanBarangDb.findAll();
    }

    @Override
    public PermintaanPengirimanBarang checkIfExists(Long idPermintaan, String skuBarang) {
        for (PermintaanPengirimanBarang permintaanBarang : getAllPermintaanPengirimanBarang()) {
            if (permintaanBarang.getBarang().getSku().equals(skuBarang) && permintaanBarang.getPermintaanPengiriman().getId().equals(idPermintaan)) {
                return permintaanBarang;
            }
        }
        return null;
    }
}
