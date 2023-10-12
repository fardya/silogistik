package apap.ti.silogistik2106702005.service;

import apap.ti.silogistik2106702005.model.Barang;
import apap.ti.silogistik2106702005.model.Gudang;
import apap.ti.silogistik2106702005.model.GudangBarang;
import apap.ti.silogistik2106702005.repository.GudangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GudangServiceImpl implements GudangService {
    @Autowired
    GudangDb gudangDb;

    @Autowired
    BarangService barangService;

    @Override
    public List<Gudang> getAllGudang() {
        return gudangDb.findAll();
    }

    @Override
    public void addGudang(Gudang gudang) {
        if (gudang.getListGudangBarang() != null) {
            for (GudangBarang gudangBarang : gudang.getListGudangBarang()) {
                gudangBarang.setGudang(gudang);
            }
        }
        gudangDb.save(gudang);
    }

    @Override
    public Gudang getGudangById(Long id) {
        Optional<Gudang> gudang = gudangDb.findById(id);
        if (gudang.isPresent()) {
            return gudang.get();
        } else return null;
    }

    @Override
    public List<Gudang> getGudangByBarang(String sku) {
        Barang barang = barangService.getBarangBySku(sku);

        List<Gudang> listGudang = new ArrayList<>();
        if (barang != null) {
            if (barang.getListGudangBarang() != null || barang.getListGudangBarang().size() != 0) {
                for (GudangBarang gudangBarang : barang.getListGudangBarang()) {
                    listGudang.add(gudangBarang.getGudang());
                }
            }
        }

        return listGudang;
    }
}
