package apap.ti.silogistik2106702005.service;

import apap.ti.silogistik2106702005.model.Karyawan;

import java.util.List;

public interface KaryawanService {
    void addKaryawan(Karyawan karyawan);

    List<Karyawan> getAllKaryawan();
}
