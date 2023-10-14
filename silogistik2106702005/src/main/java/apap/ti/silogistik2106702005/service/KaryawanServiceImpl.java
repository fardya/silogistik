package apap.ti.silogistik2106702005.service;

import apap.ti.silogistik2106702005.model.Karyawan;
import apap.ti.silogistik2106702005.repository.KaryawanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KaryawanServiceImpl implements KaryawanService {
    @Autowired
    KaryawanDb karyawanDb;

    @Override
    public void addKaryawan(Karyawan karyawan) {
        karyawanDb.save(karyawan);
    }

    @Override
    public List<Karyawan> getAllKaryawan() {
        return karyawanDb.findAll();
    }
}
