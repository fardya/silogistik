package apap.ti.silogistik2106702005.dto.request;

import apap.ti.silogistik2106702005.model.Karyawan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePermintaanPengirimanRequest {
    private Boolean isCancelled;
    private String namaPenerima;
    private String alamatPenerima;
    private Date tanggalPengiriman;
    private int biayaPengiriman;
    private int jenisLayanan;
    private LocalDateTime waktuPermintaan;
    private Karyawan karyawan;
}
