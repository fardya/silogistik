package apap.ti.silogistik2106702005.repository;

import apap.ti.silogistik2106702005.model.PermintaanPengiriman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PermintaanPengirimanDb extends JpaRepository<PermintaanPengiriman, Long> {
    List<PermintaanPengiriman> findAllByOrderByWaktuPermintaanDesc();

    List<PermintaanPengiriman> findAllByWaktuPermintaanBetweenOrderByWaktuPermintaanDesc(
            LocalDateTime startDate, LocalDateTime endDate);
}
