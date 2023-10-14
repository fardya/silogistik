package apap.ti.silogistik2106702005.dto;

import apap.ti.silogistik2106702005.dto.request.CreateKaryawanRequest;
import apap.ti.silogistik2106702005.model.Karyawan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KaryawanMapper {
    Karyawan createKaryawanRequestToKaryawan(CreateKaryawanRequest createKaryawanRequest);
}
