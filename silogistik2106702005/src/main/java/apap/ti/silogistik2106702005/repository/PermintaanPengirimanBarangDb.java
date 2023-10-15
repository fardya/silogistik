package apap.ti.silogistik2106702005.repository;

import apap.ti.silogistik2106702005.model.Barang;
import apap.ti.silogistik2106702005.model.PermintaanPengiriman;
import apap.ti.silogistik2106702005.model.PermintaanPengirimanBarang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermintaanPengirimanBarangDb extends JpaRepository<PermintaanPengirimanBarang, Long> {
    List<PermintaanPengirimanBarang> findByBarang(Barang barang);
}
