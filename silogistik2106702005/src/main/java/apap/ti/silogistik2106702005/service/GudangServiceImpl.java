package apap.ti.silogistik2106702005.service;

import apap.ti.silogistik2106702005.model.Gudang;
import apap.ti.silogistik2106702005.model.GudangBarang;
import apap.ti.silogistik2106702005.repository.GudangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GudangServiceImpl implements GudangService {
    @Autowired
    GudangDb gudangDb;

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
    public Gudang updateGudang(Gudang gudangFromDto) {
        Gudang gudang = getGudangById(gudangFromDto.getId());
        if (gudang != null) {
            gudang.setListGudangBarang(gudangFromDto.getListGudangBarang());
            gudangDb.save(gudang);
        }
        return gudang;
    }
}
