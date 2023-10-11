package apap.ti.silogistik2106702005.dto;

import apap.ti.silogistik2106702005.dto.request.CreateGudangRequest;
import apap.ti.silogistik2106702005.dto.request.UpdateGudangRequest;
import apap.ti.silogistik2106702005.model.Gudang;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GudangMapper {
    Gudang createGudangRequestToGudang(CreateGudangRequest createGudangRequest);

    Gudang updateGudangRequestToGudang(UpdateGudangRequest updateGudangRequest);

    UpdateGudangRequest gudangToUpdateGudangRequest(Gudang gudang);
}
