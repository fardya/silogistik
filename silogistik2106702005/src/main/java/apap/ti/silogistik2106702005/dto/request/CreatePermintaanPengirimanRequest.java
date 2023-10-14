package apap.ti.silogistik2106702005.dto.request;

import apap.ti.silogistik2106702005.model.Karyawan;
import apap.ti.silogistik2106702005.model.PermintaanPengirimanBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePermintaanPengirimanRequest {
    private Boolean isCancelled;
    private String namaPenerima;
    private String alamatPenerima;
    private LocalDate tanggalPengiriman;
    private int biayaPengiriman;
    private int jenisLayanan;
    private LocalDateTime waktuPermintaan;
    private Karyawan karyawan;
    private List<PermintaanPengirimanBarang> permintaanPengirimanBarang;
}
