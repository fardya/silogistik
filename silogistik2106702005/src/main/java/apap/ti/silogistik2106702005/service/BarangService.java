package apap.ti.silogistik2106702005.service;

import apap.ti.silogistik2106702005.model.Barang;

import java.util.List;

public interface BarangService {
    List<Barang> getAllBarang();

    void addBarang(Barang barang);

    Barang getBarangBySku(String sku);

    Barang updateBarang(Barang barang);

    String generateSku(int tipe);

    int countStok(Barang barang);
}
