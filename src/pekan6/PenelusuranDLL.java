package Pekan6;

public class PenelusuranDLL {
	// Nama : Sasya Zamora
	// NIM  : 2411533014
		//fungsi penelusuran maju
	         static void forwardTraversal (NodeDLL head) {
	        	 //memulai penelusuran dari head
	        	 NodeDLL curr = head;
	        	 //lanjutkan sampai akhir
	        	 while (curr != null) {
	        		 //print data
	        		 System.out.print(curr.data + " <-> ");
	        		 //pindah ke node berikutnya
	        		 curr = curr.next;
	        	 }
	        	 //print spasi
	        	 System.out.println();
	         }
	         //fungsi penelusuran mundur\
	         static void backwardTraversal (NodeDLL tail) {
	        	 //mulai dari akhir
	        	 NodeDLL curr = tail;
	        	 //lanjut sampai head
	        	 while (curr != null) {
	        		 //cetak data
	        		 System.out.print(curr.data + " <-> ");
	        		 //pindah ke node sebelumnya
	        		 curr = curr.prev;
	        	 }
	        	 //cetak spasi
	        	 System.out.println();
	         }
	         public static void main(String[] args) {
	        	 System.out.println("Nama : Sasya Zamora");
	        	 System.out.println("NIM  : 2411533014");
	        	    // cetak DLL
	        	    NodeDLL head = new NodeDLL(1);
	        	    NodeDLL second = new NodeDLL(2);
	        	    NodeDLL third = new NodeDLL(3);

	        	    head.next = second;
	        	    second.prev = head;
	        	    second.next = third;
	        	    third.prev = second;

	        	    System.out.println("Penelusuran maju:");
	        	    forwardTraversal(head);

	        	    System.out.println("Penelusuran mundur:");
	        	    backwardTraversal(third);
	        	}
	}