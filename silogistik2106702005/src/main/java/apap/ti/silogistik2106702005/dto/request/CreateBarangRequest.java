package apap.ti.silogistik2106702005.dto.request;

import apap.ti.silogistik2106702005.model.GudangBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBarangRequest {
    private int tipeBarang;
    private String merk;
    private Long hargaBarang;
    private List<GudangBarang> listGudangBarang;
}