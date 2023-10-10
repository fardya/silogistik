package apap.ti.silogistik2106702005.dto;

import apap.ti.silogistik2106702005.dto.request.CreateBarangRequest;
import apap.ti.silogistik2106702005.dto.request.UpdateBarangRequest;
import apap.ti.silogistik2106702005.model.Barang;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BarangMapper {
    Barang createBarangRequestToBarang(CreateBarangRequest createBarangRequest);

    Barang updateBarangRequestToBarang(UpdateBarangRequest updateBarangRequest);
}
