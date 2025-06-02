package pekan6;

public class DaftarBelanja {
	 private Node head;
	    private Node tail;
	    private int size;

	    public DaftarBelanja() {
	        head = null;
	        tail = null;
	        size = 0;
	    }

	    // a. Tambah item ke akhir list
	    public void tambahItem(String nama, int kuantitas, String kategori) {
	        ItemBelanja newItem = new ItemBelanja(nama, kuantitas, kategori);
	        Node newNode = new Node(newItem);

	        if (head == null) {
	            head = newNode;
	            tail = newNode;
	        } else {
	            tail.next = newNode;
	            newNode.prev = tail;
	            tail = newNode;
	        }
	        size++;
	    }

	    // b. Hapus item berdasarkan nama
	    public void hapusItem(String nama) {
	        Node current = head;
	        
	        while (current != null) {
	            if (current.data.nama.equalsIgnoreCase(nama)) {
	                if (current == head && current == tail) {
	                    head = null;
	                    tail = null;
	                } else if (current == head) {
	                    head = head.next;
	                    head.prev = null;
	                } else if (current == tail) {
	                    tail = tail.prev;
	                    tail.next = null;
	                } else {
	                    current.prev.next = current.next;
	                    current.next.prev = current.prev;
	                }
	                size--;
	                return;
	            }
	            current = current.next;
	        }
	    }

	    // c. Tampilkan semua item
	    public void tampilkanSemuaItem() {
	        if (head == null) {
	            System.out.println("Daftar belanja kosong.");
	            return;
	        }

	        System.out.println("--- Daftar Belanja ---");
	        Node current = head;
	        while (current != null) {
	            System.out.println(current.data);
	            current = current.next;
	        }
	    }

	    // d. Tampilkan item berdasarkan kategori
	    public void tampilkanPerKategori(String kategori) {
	        System.out.println("--- Item dalam kategori '" + kategori + "' ---");
	        Node current = head;
	        boolean found = false;

	        while (current != null) {
	            if (current.data.kategori.equalsIgnoreCase(kategori)) {
	                System.out.println("- " + current.data.nama + " (" + current.data.kuantitas + ")");
	                found = true;
	            }
	            current = current.next;
	        }

	        if (!found) {
	            System.out.println("Tidak ada item dalam kategori ini.");
	        }
	    }

	    // e. Cari item berdasarkan nama
	    public void cariItem(String nama) {
	        Node current = head;
	        boolean found = false;

	        while (current != null) {
	            if (current.data.nama.equalsIgnoreCase(nama)) {
	                System.out.println("Item ditemukan:");
	                System.out.println(current.data);
	                found = true;
	                break;
	            }
	            current = current.next;
	        }

	        if (!found) {
	            System.out.println("Item tidak ditemukan.");
	        }
	    }
	}