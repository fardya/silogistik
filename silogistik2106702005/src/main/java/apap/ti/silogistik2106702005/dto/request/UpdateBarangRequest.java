package apap.ti.silogistik2106702005.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateBarangRequest extends CreateBarangRequest {
    private String sku;
}
