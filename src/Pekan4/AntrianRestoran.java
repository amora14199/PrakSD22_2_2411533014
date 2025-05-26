package Pekan4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Kelas Pelanggan
class Pelanggan {
    String id;
    int jumlahPesanan;

    Pelanggan(String id, int jumlahPesanan) {
        this.id = id;
        this.jumlahPesanan = jumlahPesanan;
    }
}

// Kelas utama
public class AntrianRestoran {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue<Pelanggan> queue = new LinkedList<>();

        System.out.print("Masukkan jumlah pelanggan: ");
        int N = scanner.nextInt();

        for (int i = 0; i < N; i++) {
            System.out.print("Masukkan ID pelanggan ke-" + (i + 1) + ": ");
            String id = scanner.next();
            System.out.print("Masukkan jumlah pesanan untuk " + id + ": ");
            int jumlahPesanan = scanner.nextInt();
            queue.add(new Pelanggan(id, jumlahPesanan));
        }

        System.out.println("\n=== Hasil Pelayanan ===");
        int waktuKumulatif = 0;

        while (!queue.isEmpty()) {
            Pelanggan p = queue.poll();
            waktuKumulatif += p.jumlahPesanan;
            System.out.println(p.id + " selesai dalam " + waktuKumulatif + " menit");
        }

        scanner.close();
    }
}