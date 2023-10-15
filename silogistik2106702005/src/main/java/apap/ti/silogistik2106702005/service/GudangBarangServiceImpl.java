package apap.ti.silogistik2106702005.service;

import apap.ti.silogistik2106702005.model.Gudang;
import apap.ti.silogistik2106702005.model.Barang;
import apap.ti.silogistik2106702005.model.GudangBarang;
import apap.ti.silogistik2106702005.repository.GudangBarangDb;
import apap.ti.silogistik2106702005.repository.GudangDb;
import apap.ti.silogistik2106702005.service.GudangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GudangBarangServiceImpl implements GudangBarangService {
    @Autowired
    GudangBarangDb gudangBarangDb;

    @Autowired
    GudangDb gudangDb;

    @Autowired
    GudangService gudangService;

    @Override
    public void saveGudangBarang(GudangBarang gudangBarang) {
        gudangBarangDb.save(gudangBarang);
    }

    @Override
    public void updateGudangBarang(Gudang gudangFromDto) {
        var gudang = gudangService.getGudangById(gudangFromDto.getId());

        for (GudangBarang gudangBarangDto : gudangFromDto.getListGudangBarang()) {
            for (GudangBarang gudangBarang : gudang.getListGudangBarang()) {
                if (gudangBarangDto.getBarang().getSku().equals(gudangBarang.getBarang().getSku())) {
                    gudangBarang.setStok(0);
                    break;
                }
            }
        }

        for (GudangBarang gudangBarangDto : gudangFromDto.getListGudangBarang()) {
            boolean flag = Boolean.FALSE;
            for (GudangBarang gudangBarang : gudang.getListGudangBarang()) {
                if (gudangBarangDto.getBarang().getSku().equals(gudangBarang.getBarang().getSku())) {
                     int currentStok = gudangBarang.getStok();
                     gudangBarang.setStok(currentStok + gudangBarangDto.getStok());
                    // gudangBarang.setStok(gudangBarangDto.getStok());
                    flag = Boolean.TRUE;
                    gudangBarangDb.save(gudangBarang);
                    break;
                }
            }
            if (flag == Boolean.FALSE) {
                gudang.getListGudangBarang().add(gudangBarangDto);
                gudangBarangDto.setGudang(gudang);
                gudangBarangDb.save(gudangBarangDto);
            }

            gudangDb.save(gudang);
        }

    }

    @Override
    public List<GudangBarang> getGudangBarangByGudang(Gudang gudang) {
        List<GudangBarang> listGudangBarang = gudangBarangDb.findByGudang(gudang);
        return listGudangBarang;
    }

    @Override
    public List<GudangBarang> getGudangBarangByBarang(Barang barang) {
        List<GudangBarang> listGudangBarang = gudangBarangDb.findByBarang(barang);
        return listGudangBarang;
    }
}
