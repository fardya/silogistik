package apap.ti.silogistik2106702005.service;

import apap.ti.silogistik2106702005.model.Gudang;
import apap.ti.silogistik2106702005.model.GudangBarang;
import apap.ti.silogistik2106702005.repository.GudangBarangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GudangBarangServiceImpl implements GudangBarangService {
    @Autowired
    GudangBarangDb gudangBarangDb;

    @Override
    public List<GudangBarang> getGudangBarangByGudang(Gudang gudang) {
        List<GudangBarang> listGudangBarang = gudangBarangDb.findByGudang(gudang);
        return listGudangBarang;
    }
}
