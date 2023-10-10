package apap.ti.silogistik2106702005.dto.request;

import apap.ti.silogistik2106702005.model.GudangBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateGudangRequest {
    private String nama;
    private String alamatGudang;
    private List<GudangBarang> listGudangBarang;
}
