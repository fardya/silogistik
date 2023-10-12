package apap.ti.silogistik2106702005.repository;

import apap.ti.silogistik2106702005.model.Gudang;
import apap.ti.silogistik2106702005.model.Barang;
import apap.ti.silogistik2106702005.model.GudangBarang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GudangBarangDb extends JpaRepository<GudangBarang, Long> {
    List<GudangBarang> findByGudang(Gudang gudang);

    List<GudangBarang> findByBarang(Barang barang);
}
