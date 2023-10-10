package apap.ti.silogistik2106702005.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateKaryawanRequest {
    private String nama;
    private int jenisKelamin;
    private Date tanggalLahir;
}
