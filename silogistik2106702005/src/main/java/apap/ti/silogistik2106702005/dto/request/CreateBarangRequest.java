package apap.ti.silogistik2106702005.dto.request;

import apap.ti.silogistik2106702005.model.GudangBarang;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBarangRequest {
    private String sku;

    private int tipeBarang;

    @NotBlank(message = "Merk barang tidak boleh kosong")
    private String merk;

    private String merkLower;

    @PositiveOrZero(message = "Harga tidak boleh berupa bilangan negatif")
    private Long hargaBarang;

    private List<GudangBarang> listGudangBarang;
}
