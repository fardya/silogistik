package apap.ti.silogistik2106702005.repository;

import apap.ti.silogistik2106702005.model.Gudang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GudangDb extends JpaRepository<Gudang, Long> {
}
