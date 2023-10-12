package apap.ti.silogistik2106702005.service;

import apap.ti.silogistik2106702005.model.Gudang;

import java.util.List;

public interface GudangService {
    List<Gudang> getAllGudang();

    void addGudang(Gudang Gudang);

    Gudang getGudangById(Long id);

    List<Gudang> getGudangByBarang(String sku);
}
