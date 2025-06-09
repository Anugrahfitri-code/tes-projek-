import java.util.ArrayList;
import java.util.Scanner;

abstract class OverRiddingMahasiswa {
    protected String nama;
    protected String nim;

    public OverRiddingMahasiswa(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    public abstract void belajar();
    public abstract void tugas();

    public String perkenalan() {
        return "Halo, saya " + nama + " dengan NIM " + nim;
    }
}

class MahasiswaReguler extends OverRiddingMahasiswa {
    public MahasiswaReguler(String nama, String nim) {
        super(nama, nim);
    }

    @Override
    public void belajar() {
        System.out.println(nama + " sedang belajar di kampus.");
    }

    @Override
    public void tugas() {
        System.out.println(nama + " mengerjakan tugas kuliah biasa. dan bermain");
    }
}

class MahasiswaTransfer extends OverRiddingMahasiswa {
    private String universitasAsal;

    public MahasiswaTransfer(String nama, String nim, String universitasAsal) {
        super(nama, nim);
        this.universitasAsal = universitasAsal;
    }

    @Override
    public void belajar() {
        System.out.println(nama + " belajar sambil menyesuaikan diri dari transfer.");
    }

    @Override
    public void tugas() {
        System.out.println(nama + " mengerjakan tugas tambahan untuk adaptasi.");
    }

    public void infoTransfer() {
        System.out.println(nama + " berasal dari Perguruan Tinggi " + universitasAsal + ".");
    }
}

public class MainOverRidding {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<OverRiddingMahasiswa> daftarMahasiswa = new ArrayList<>();

        int pilihan;
        do {
            System.out.println("\n===== Menu Mahasiswa =====");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Lihat Daftar Mahasiswa");
            System.out.println("3. Keluar dari menu");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); 

            switch (pilihan) {
                case 1:
                    System.out.print("Nama: ");
                    String nama = scanner.nextLine();

                    System.out.print("NIM: ");
                    String nim = scanner.nextLine();

                    System.out.print("Apakah mahasiswa transfer? (ya/tidak): ");
                    String jenis = scanner.nextLine().toLowerCase();

                    if (jenis.equals("ya")) {
                        System.out.print("Asal universitas: ");
                        String asal = scanner.nextLine();
                        daftarMahasiswa.add(new MahasiswaTransfer(nama, nim, asal));
                    } else {
                        daftarMahasiswa.add(new MahasiswaReguler(nama, nim));
                    }
                    System.out.println("Data mahasiswa berhasil ditambahkan.");
                    break;

                case 2:
                    if (daftarMahasiswa.isEmpty()) {
                        System.out.println("Belum ada data mahasiswa.");
                    } else {
                        System.out.println("\n=== Informasi Mahasiswa ===");
                        for (OverRiddingMahasiswa mhs : daftarMahasiswa) {
                            System.out.println(mhs.perkenalan());
                            mhs.belajar();
                            mhs.tugas();
                            if (mhs instanceof MahasiswaTransfer) {
                                ((MahasiswaTransfer) mhs).infoTransfer();
                            }
                            System.out.println();
                        }
                    }
                    break;

                case 3:
                    System.out.println("Terima kasih! Program selesai.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Coba lagi.");
            }

        } while (pilihan != 3);

        scanner.close();
    }
}
