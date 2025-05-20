package Pekan5;

public class TambahSLL {
	 public static NodeSLL insertAtFront(NodeSLL head, int value) {
	        NodeSLL new_node = new NodeSLL(value);
	        new_node.next = head;
	        return new_node;
	    }

	    // fungsi menambahkan di akhir
	    public static NodeSLL insertAtEnd(NodeSLL head, int value) {
	        // buat sebuah node dengan sebuah nilai
	        NodeSLL newNode = new NodeSLL(value);

	        // jika list kosong maka node jadi head
	        if (head == null)
	            return newNode;

	        // simpan head ke variabel sementara
	        NodeSLL last = head;

	        // telusuri ke node akhir
	        while (last.next != null) {
	            last = last.next;
	        }

	        // ubah pointer
	        last.next = newNode;
	        return head;
	    }

	    static NodeSLL GetNode(int data) {
	        return new NodeSLL(data);}
	static NodeSLL insertPos(NodeSLL headNode, int position, int value) {
	    NodeSLL head = headNode;
	    if (position < 1) {
	        System.out.print("Invalid position");
	    }
	    if (position == 1) {
	        NodeSLL new_node = new NodeSLL(value);
	        new_node.next = head;
	        return new_node;
	    } else {
	        while (position-- != 0) {
	            if (position == 1) {
	                NodeSLL newNode = GetNode(value);
	                newNode.next = headNode.next;
	                headNode.next = newNode;
	                break;
	            }
	            headNode = headNode.next;
	        }
	        if (position != 1)
	            System.out.print("Posisi di luar jangkauan");
	        
	        return head;
	    }
	}

	public static void printList(NodeSLL head) {
	    NodeSLL curr = head;
	    while (curr.next != null) {
	        System.out.print(curr.data+"-->");
	        curr = curr.next;
	    }
	    if (curr.next==null) {
	    System.out.println(curr.data);
	    System.out.println();
	    }
	}
	public static void main(String[] args) {
	    // buat linked list 2 -> 3 -> 4 -> 5
	    NodeSLL head = new NodeSLL(2);
	    head.next = new NodeSLL(3);
	    head.next.next = new NodeSLL(5);
	    head.next.next.next = new NodeSLL(6);

	    // cetak list asli
	    System.out.print("Senarai berantai awal: ");
	    printList(head);

	    // tambahkan node baru di depan
	    System.out.print("tambah 1 simpul di depan: ");
	    int data = 1;
	    head = insertAtFront(head, data);
	    // cetak update list
	    printList(head);

	    // tambahkan node baru di belakang
	    System.out.print("tambah 1 simpul di belakang: ");
	    int data2 = 7;
	    head = insertAtEnd(head, data2);
	    // cetak update list
	    printList(head);

	    System.out.print("tambah 1 simpul ke data 4: ");
	    int data3 = 4;
	    int pos = 4;
	    head = insertPos(head, pos, data3);
	    // cetak update list
	    printList(head);
	}
}