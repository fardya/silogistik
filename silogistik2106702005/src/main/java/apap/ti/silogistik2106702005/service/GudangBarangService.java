package apap.ti.silogistik2106702005.service;

import apap.ti.silogistik2106702005.model.Gudang;
import apap.ti.silogistik2106702005.model.GudangBarang;

import java.util.List;

public interface GudangBarangService {
    List<GudangBarang> getGudangBarangByGudang(Gudang gudang);
}
