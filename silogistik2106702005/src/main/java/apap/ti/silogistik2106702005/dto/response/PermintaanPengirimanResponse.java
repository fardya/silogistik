package apap.ti.silogistik2106702005.dto.response;

import apap.ti.silogistik2106702005.model.PermintaanPengirimanBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PermintaanPengirimanResponse {
    private Long id;
    private String nomorPengiriman;
    private String namaPenerima;
    private String alamatPenerima;
    private int biayaPengiriman;
    private String jenisLayanan;
    private String waktuPermintaan;
    private String tanggalPengiriman;
    private String namaKaryawan;
    private List<PermintaanPengirimanBarang> permintaanPengirimanBarang;
}
