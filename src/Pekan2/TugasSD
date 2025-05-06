package pekan2;
import java.util.ArrayList;
import java.util.Scanner;

class Mobil {
    String platNomor;

    Mobil(String platNomor) {
        this.platNomor = platNomor;
    }

    public String toString() {
        return platNomor;
    }
}

class Parkiran {
    ArrayList<Mobil> daftarMobil = new ArrayList<>();

    void tambahMobil(String platNomor) {
        daftarMobil.add(new Mobil(platNomor));
        System.out.println("Mobil dengan plat " + platNomor + " ditambahkan.");
    }

    void keluarkanMobil(String platNomor) {
        for (int i = 0; i < daftarMobil.size(); i++) {
            if (daftarMobil.get(i).platNomor.equalsIgnoreCase(platNomor)) {
                daftarMobil.remove(i);
                System.out.println("Mobil dengan plat " + platNomor + " telah dikeluarkan.");
                return;
            }
        }
        System.out.println("Mobil dengan plat " + platNomor + " tidak ditemukan.");
    }

    void tampilkanParkiran() {
        if (daftarMobil.isEmpty()) {
            System.out.println("Parkiran kosong.");
        } else {
            System.out.println("Daftar mobil di parkiran:");
            for (Mobil m : daftarMobil) {
                System.out.println("- " + m.platNomor);
            }
        }
    }

    void cariMobil(String platNomor) {
        for (Mobil m : daftarMobil) {
            if (m.platNomor.equalsIgnoreCase(platNomor)) {
                System.out.println("Mobil dengan plat " + platNomor + " masih terparkir.");
                return;
            }
        }
        System.out.println("Mobil dengan plat " + platNomor + " tidak ditemukan di parkiran.");
    }
}

public class ParkiranMobil {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parkiran parkiran = new Parkiran();

        String[] platAwal = {"BA1111AA", "BA2222BB", "BA3333CC", "BA4444DD", "BA5555EE", "BA6666FF", "BA7777GG"};
        for (String plat : platAwal) {
            parkiran.tambahMobil(plat);
        }

        int pilihan;
        do {
            System.out.println("\n=== Menu Parkiran Mobil ===");
            System.out.println("1. Tambah mobil ke Parkiran");
            System.out.println("2. Keluarkan mobil dari Parkiran");
            System.out.println("3. Tampilkan isi Parkiran");
            System.out.println("4. Cari mobil di Parkiran");
            System.out.println("5. Keluar");
            System.out.print("Pilih Opsi: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan plat nomor: ");
                    String platTambah = scanner.nextLine();
                    parkiran.tambahMobil(platTambah);
                    break;
                case 2:
                    System.out.print("Masukkan plat nomor yang akan dikeluarkan: ");
                    String platKeluar = scanner.nextLine();
                    parkiran.keluarkanMobil(platKeluar);
                    break;
                case 3:
                    parkiran.tampilkanParkiran();
                    break;
                case 4:
                    System.out.print("Masukkan plat nomor yang dicari: ");
                    String platCari = scanner.nextLine();
                    parkiran.cariMobil(platCari);
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan program parkiran.");
                    break;
                default:
                    System.out.println("Opsi tidak valid. Coba lagi.");
            }
        } while (pilihan != 5);

        scanner.close();
    }
}
