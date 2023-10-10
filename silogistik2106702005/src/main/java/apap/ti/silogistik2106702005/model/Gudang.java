package apap.ti.silogistik2106702005.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gudang")
public class Gudang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 255)
    @Column(name = "alamat_gudang", nullable = false)
    private String alamatGudang;

    @OneToMany(mappedBy = "gudang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<GudangBarang> listGudangBarang;
}
