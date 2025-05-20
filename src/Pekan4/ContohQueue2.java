package Pekan4;

import java.util.LinkedList;
import java.util.Queue;

public class ContohQueue2 {

	 public static void main(String[] args) {
	        Queue<Integer> q = new LinkedList<>();

	        // Tambahkan elemen ke antrian
	        for (int i = 0; i < 6; i++) {
	            q.add(i);
	        }

	        // Menampilkan isi antrian
	        System.out.println("Elemen Antrian = " + q);

	        // Menghapus kepala antrian
	        int hapus = q.remove();
	        System.out.println("Hapus elemen = " + hapus);
	        System.out.println(q);

	        // Untuk melihat antrian terdepan
	        int depan = q.peek();
	        System.out.println("Kepala Antrian = " + depan);

	        // Ukuran antrian
	        int banyak = q.size();
	        System.out.println("Size Antrian = " + banyak);
	    }
	}