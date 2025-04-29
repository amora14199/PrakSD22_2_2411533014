package Pekan1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class MahasiswaMain {
	private Object nim;

	public static void main (String[] args) {
		ArrayList<MahasiswaMain> mahasiswaList = new ArrayList<>();
		Scanner scanner = new Scanner (System.in);
		int choice;
		do {
			System.out.println("Menu: ");
			System.out.println("1. Tambah Mahasiswa");
			System.out.println("2. Tampilkan Semua Mahasiswa");
			System.out.println("3. Hapus Mahasiswa Berdasarkan NIM");
			System.out.println("4. Cari Mahasiswa Berdasarkan NIM");
			System.out.println("5. Keluar");
			System.out.println("Pelih Menu: ");
			choice = scanner.nextInt();
			scanner.nextLine(); //Consume newLine
			switch (choice) {
				case 1:
					System.out.println("Masukan NIM: ");
					String nim = scanner.nextLine();
					System.out.println("Masukan Nama: ");
					String nama = scanner.nextLine();
					System.out.println("Masukan Prodi: ");
					String prodi = scanner.nextLine();
					mahasiswaList.add(new MahasiswaMain());
					break;
				case 2:
					System.out.println("Data Mahasiswa: ");
					for (MahasiswaMain mhs : mahasiswaList)	{
						System.out.println(mhs);
					}
					break;
				case 3:
					System.out.println("Masukan NIM yang akan di hapus: ");
					String nimHapus = scanner.nextLine();
					mahasiswaList.removeIf(mhs ->mhs.nim.equals(nimHapus));
					break;
				case 4:
					System.out.println("Masukan NIM yang di cari: ");
					String nimCari = scanner.nextLine();
					for (MahasiswaMain mhs : mahasiswaList) {
						if (mhs.nim.equals(nimCari)) {
							System.out.println(mhs);
							break;
						} else {
							System.out.println("nim tidak ada");
						}
					}
					break;
				case 5:
					System.out.println("Keluar dari program.");
					break;
					default:
						System.out.println("Pilihan tidak valid.");
			}
		} while (choice != 5);
		
		scanner.close();
	}

}