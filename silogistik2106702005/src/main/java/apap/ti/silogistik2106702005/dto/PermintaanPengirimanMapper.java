package apap.ti.silogistik2106702005.dto;

import apap.ti.silogistik2106702005.dto.request.CreatePermintaanPengirimanRequest;
import apap.ti.silogistik2106702005.dto.response.PermintaanPengirimanResponse;
import apap.ti.silogistik2106702005.model.PermintaanPengiriman;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface PermintaanPengirimanMapper {
    PermintaanPengiriman createPermintaanPengirimanRequestToPermintaanPengiriman(CreatePermintaanPengirimanRequest createPermintaanPengirimanRequest);

    @Mapping(target = "waktuPermintaan", ignore = true)
    @Mapping(target = "jenisLayanan", ignore = true)
    @Mapping(target = "tanggalPengiriman", ignore = true)
    @Mapping(target = "namaKaryawan", ignore = true)
    PermintaanPengirimanResponse permintaanToPermintaanPengirimanResponse(PermintaanPengiriman permintaan);

    @AfterMapping
    default void formatPermintaanResponse(PermintaanPengiriman permintaan,
                                          @MappingTarget PermintaanPengirimanResponse permintaanResponse) {

        if (permintaan.getWaktuPermintaan() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm");
            String formatted = permintaan.getWaktuPermintaan().format(formatter);

            permintaanResponse.setWaktuPermintaan(formatted);
        } else {
            permintaanResponse.setWaktuPermintaan("-");
        }

        if (permintaan.getKaryawan() != null) {
            String namaKaryawan = permintaan.getKaryawan().getNama();

            permintaanResponse.setNamaKaryawan(namaKaryawan);
        } else {
            permintaanResponse.setNamaKaryawan("-");
        }

        int jenis = permintaan.getJenisLayanan();
        if (jenis == 1) {
            permintaanResponse.setJenisLayanan("Same Day");
        } else if (jenis == 2) {
            permintaanResponse.setJenisLayanan("Kilat");
        } else if (jenis == 3) {
            permintaanResponse.setJenisLayanan("Reguler");
        } else {
            permintaanResponse.setJenisLayanan("Hemat");
        }

        if (permintaan.getTanggalPengiriman() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formatted = permintaan.getTanggalPengiriman().format(formatter);

            permintaanResponse.setTanggalPengiriman(formatted);
        } else {
            permintaanResponse.setTanggalPengiriman("-");
        }
    }
}
