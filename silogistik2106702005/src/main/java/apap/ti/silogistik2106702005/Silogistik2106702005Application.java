package apap.ti.silogistik2106702005;

import apap.ti.silogistik2106702005.dto.GudangMapper;
import apap.ti.silogistik2106702005.dto.KaryawanMapper;
import apap.ti.silogistik2106702005.dto.request.CreateGudangRequest;
import apap.ti.silogistik2106702005.dto.request.CreateKaryawanRequest;
import apap.ti.silogistik2106702005.service.GudangService;
import apap.ti.silogistik2106702005.service.KaryawanService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

import java.util.Locale;

@SpringBootApplication
public class Silogistik2106702005Application {

	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106702005Application.class, args);
	}

	// CommandLineRunner digunakan untuk execute kode saat spring pertama kali start up
	@Bean
	@Transactional
	CommandLineRunner run(GudangService gudangService, GudangMapper gudangMapper,
						  KaryawanService karyawanService, KaryawanMapper karyawanMapper) {
		return args -> {
			var faker = new Faker(new Locale("in-ID"));

			// Membuat fake data Gudang
			for (int i= 0; i < 3; i++) {
				String namaGudang = "Gudang " + faker.company().name();
				var fakeAddress = faker.address();
				String alamatGudang = fakeAddress.streetAddress() + ", " + fakeAddress.city();

				var gudangDTO = new CreateGudangRequest();
				gudangDTO.setNama(namaGudang);
				gudangDTO.setAlamatGudang(alamatGudang);

				var gudang = gudangMapper.createGudangRequestToGudang(gudangDTO);
				gudangService.addGudang(gudang);
			}

			// Membuat fake data Karyawan
			for (int i= 0; i < 5; i++) {
				String nama = faker.name().fullName();
				int gender = faker.number().numberBetween(1, 3);
				var birthdate = faker.date().birthday(20, 40);

				var karyawanDTO = new CreateKaryawanRequest();
				karyawanDTO.setNama(nama);
				karyawanDTO.setJenisKelamin(gender);
				karyawanDTO.setTanggalLahir(birthdate);

				var karyawan = karyawanMapper.createKaryawanRequestToKaryawan(karyawanDTO);
				karyawanService.addKaryawan(karyawan);
			}
		};
	}
}
