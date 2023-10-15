package apap.ti.silogistik2106702005.dto.request;

import apap.ti.silogistik2106702005.model.Karyawan;
import apap.ti.silogistik2106702005.model.PermintaanPengirimanBarang;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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

    @NotBlank(message = "Nama penerima tidak boleh kosong")
    private String namaPenerima;

    @NotBlank(message = "Alamat penerima tidak boleh kosong")
    private String alamatPenerima;

    @NotNull(message = "Tanggal pengiriman tidak boleh kosong")
    private LocalDate tanggalPengiriman;

    @PositiveOrZero(message = "Biaya tidak boleh berupa bilangan negatif")
    private int biayaPengiriman;

    private int jenisLayanan;

    private LocalDateTime waktuPermintaan;

    private Karyawan karyawan;

    @NotNull(message = "Perlu mengisi barang yang dipesan")
    private List<PermintaanPengirimanBarang> permintaanPengirimanBarang;
}
