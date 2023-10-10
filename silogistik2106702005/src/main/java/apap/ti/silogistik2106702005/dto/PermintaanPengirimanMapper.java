package apap.ti.silogistik2106702005.dto;

import apap.ti.silogistik2106702005.dto.request.CreatePermintaanPengirimanRequest;
import apap.ti.silogistik2106702005.model.PermintaanPengiriman;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermintaanPengirimanMapper {
    PermintaanPengiriman createPermintaanPengirimanRequestToPermintaanPengiriman(CreatePermintaanPengirimanRequest createPermintaanPengirimanRequest);
}
