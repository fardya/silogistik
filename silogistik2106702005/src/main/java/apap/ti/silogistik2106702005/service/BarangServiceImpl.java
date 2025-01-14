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
        return barangDb.findAllByOrderByMerkLowerAsc();
    }

    @Override
    public void addBarang (Barang barang) {
        String sku = generateSku(barang.getTipeBarang());
        barang.setSku(sku);
        barang.setMerkLower(barang.getMerk().toLowerCase());

        barangDb.save(barang);
    }

    @Override
    public Barang getBarangBySku(String sku) {
        Optional<Barang> barang = barangDb.findById(sku);
        if (barang.isPresent()) {
            return barang.get();
        }
        return null;
    }

    @Override
    public Barang updateBarang(Barang barangFromDto) {
        Barang barang = getBarangBySku(barangFromDto.getSku());
        if (barang != null) {
            barang.setMerk(barangFromDto.getMerk());
            barang.setMerkLower(barangFromDto.getMerk().toLowerCase());
            barang.setHargaBarang(barangFromDto.getHargaBarang());
            barangDb.save(barang);
        }
        return barang;
    }

    @Override
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

    @Override
    public int countStok(Barang barang) {
        int total = 0;
        for (GudangBarang gudangBarang : barang.getListGudangBarang()) {
            total += gudangBarang.getStok();
        }
        return total;
    }

    @Override
    public boolean merkExists(String merk) {
        return getAllBarang().stream().anyMatch(b -> b.getMerk().equals(merk));
    }

    @Override
    public boolean merkExists(String merk, String sku) {
        return getAllBarang().stream().anyMatch(b -> b.getMerk().equals(merk) && !b.getSku().equals(sku));
    }
}
