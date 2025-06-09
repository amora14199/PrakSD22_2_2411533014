package Pekan7;

//TugasSorting.java
public class TugasSorting {

 // Fungsi Bubble Sort
 public static void bubbleSort(char[] array, int n) {
     for (int i = 0; i < n - 1; i++) {
         for (int j = 0; j < n - i - 1; j++) {
             if (array[j] > array[j + 1]) {
                 // swap array[j] dan array[j+1]
                 char temp = array[j];
                 array[j] = array[j + 1];
                 array[j + 1] = temp;
             }
         }
     }
 }

 public static void main(String[] args) {

     // Inisialisasi array dari z ke a
     char[] huruf = {
         'z', 'y', 'x', 'w', 'v', 'u', 't', 's', 'r', 'q', 'p', 'o', 'n', 'm',
         'l', 'k', 'j', 'i', 'h', 'g', 'f', 'e', 'd', 'c', 'b', 'a'
     };

     // Karena 2 digit terakhir NIM = 14, dan jalur masuk ganjil → 14 elemen pertama
     int jumlahElemen = 14;

     // Lakukan Bubble Sort pada 14 elemen pertama
     bubbleSort(huruf, jumlahElemen);

     // Print seluruh array sesuai format
     for (int i = 0; i < huruf.length; i++) {
         System.out.print(huruf[i]);
         if (i != huruf.length - 1) {
             System.out.print(" - ");
         }
     }
 }
}