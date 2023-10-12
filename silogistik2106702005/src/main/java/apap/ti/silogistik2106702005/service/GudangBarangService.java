package apap.ti.silogistik2106702005.service;

import apap.ti.silogistik2106702005.model.Gudang;
import apap.ti.silogistik2106702005.model.Barang;
import apap.ti.silogistik2106702005.model.GudangBarang;

import java.util.List;

public interface GudangBarangService {
    void saveGudangBarang(GudangBarang gudangBarang);

    void updateGudangBarang(Gudang gudangFromDTO);

    List<GudangBarang> getGudangBarangByGudang(Gudang gudang);

    List<GudangBarang> getGudangBarangByBarang(Barang barang);
}
