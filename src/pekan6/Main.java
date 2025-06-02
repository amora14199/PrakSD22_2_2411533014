package pekan6;

import java.util.Scanner;

public class Main {
	 public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        DaftarBelanja daftar = new DaftarBelanja();
	        int pilihan;

	        do {
	            System.out.println("\n=== MENU DAFTAR BELANJA ===");
	            System.out.println("1. Tambah Item");
	            System.out.println("2. Hapus Item");
	            System.out.println("3. Tampilkan Semua Item");
	            System.out.println("4. Tampilkan Item Berdasarkan Kategori");
	            System.out.println("5. Cari Item");
	            System.out.println("6. Keluar");
	            System.out.print("Pilih menu: ");
	            pilihan = scanner.nextInt();
	            scanner.nextLine(); // consume newline

	            switch (pilihan) {
	                case 1:
	                    System.out.print("Masukkan nama item: ");
	                    String nama = scanner.nextLine();
	                    System.out.print("Masukkan kuantitas: ");
	                    int kuantitas = scanner.nextInt();
	                    scanner.nextLine(); // consume newline
	                    System.out.print("Masukkan kategori: ");
	                    String kategori = scanner.nextLine();
	                    daftar.tambahItem(nama, kuantitas, kategori);
	                    System.out.println("Item berhasil ditambahkan!");
	                    break;
	                case 2:
	                    System.out.print("Masukkan nama item yang ingin dihapus: ");
	                    String namaHapus = scanner.nextLine();
	                    daftar.hapusItem(namaHapus);
	                    System.out.println("Item berhasil dihapus.");
	                    break;
	                case 3:
	                    daftar.tampilkanSemuaItem();
	                    break;
	                case 4:
	                    System.out.print("Masukkan kategori: ");
	                    String kategoriCari = scanner.nextLine();
	                    daftar.tampilkanPerKategori(kategoriCari);
	                    break;
	                case 5:
	                    System.out.print("Masukkan nama item yang dicari: ");
	                    String namaCari = scanner.nextLine();
	                    daftar.cariItem(namaCari);
	                    break;
	                case 6:
	                    System.out.println("Terima kasih telah menggunakan program.");
	                    break;
	                default:
	                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
	            }
	        } while (pilihan != 6);

	        scanner.close();
	    }
	}