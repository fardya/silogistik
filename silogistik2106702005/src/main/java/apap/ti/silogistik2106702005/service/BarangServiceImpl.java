package apap.ti.silogistik2106702005.service;

import apap.ti.silogistik2106702005.model.Barang;
import apap.ti.silogistik2106702005.model.GudangBarang;
import apap.ti.silogistik2106702005.repository.BarangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarangServiceImpl implements BarangService {
    @Autowired
    BarangDb barangDb;

    @Override
    public List<Barang> getAllBarang() {
        return barangDb.findAll();
    }

    @Override
    public void addBarang (Barang barang) {
        barangDb.save(barang);
    }

    @Override
    public Barang getBarangBySku(String sku) {
        Optional<Barang> barang = barangDb.findById(sku);
        if (barang.isPresent()) {
            return barang.get();
        } else return null;
    }

    public Barang updateBarang(Barang barang) {
        barangDb.save(barang);
        return barang;
    }

    public String generateSku(int tipe) {
        String sku = "";
        if (tipe == 1) {
            sku += "ELEC";
        } else if (tipe == 2) {
            sku += "CLOT";
        } else if (tipe == 3) {
            sku += "FOOD";
        } else if (tipe == 4) {
            sku += "COSM";
        } else {
            sku += "TOOL";
        }
        int number = getAllBarang().size() + 1;
        sku += String.format("%03d", number);
        return sku;
    }

    public int countStok(Barang barang) {
        int total = 0;
        for (GudangBarang gudangBarang : barang.getListGudangBarang()) {
            total += gudangBarang.getStok();
        }
        return total;
    }
}
