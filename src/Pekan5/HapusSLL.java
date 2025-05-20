package Pekan5;

public class HapusSLL {
	 // Fungsi untuk menghapus head
    public static NodeSLL deleteHead(NodeSLL head) {
        // Jika SLL kosong
        if (head == null)
            return null;
        // Pindahkan head ke node berikutnya
        head = head.next;
        // Return head baru
        return head;
    }
    // Fungsi menghapus node terakhir SLL
    public static NodeSLL removeLastNode(NodeSLL head) {
        // Jika list kosong, return null
        if (head == null)
            return null;
        // Jika list satu node, hapus node dan return null
        if (head.next == null)
            return null;
        // Temukan node terakhir ke dua
        NodeSLL secondLast = head;
        while (secondLast.next.next != null)
            secondLast = secondLast.next;
        // Hapus node terakhir
        secondLast.next = null;
        return head;
    }
 // fungsi menghapus node di posisi tertentu
    public static NodeSLL deleteNode(NodeSLL head, int position) {
        NodeSLL temp = head;
        NodeSLL prev = null;
        // jika linked list null
        if (temp == null)
            return head;
        // kasus 1: head dihapus
        if (position == 1) {
            head = temp.next;
            return head;
        }
        // kasus 2: menghapus node di tengah
        // telusuri ke node yang dihapus
        for (int i = 1; temp != null && i < position; i++) {
            prev = temp;
            temp = temp.next;
        }
        // jika ditemukan, hapus node
        if (temp != null) {
            prev.next = temp.next;
        } else {
            System.out.println("Data tidak ada");
        }
        return head;
    }
    // fungsi mencetak SLL
    public static void printList(NodeSLL head) {
        NodeSLL curr = head;
        while (curr.next != null) {
            System.out.print(curr.data + "-->");
            curr = curr.next;
        }
        if (curr.next == null) {
            System.out.print(curr.data);
        }
        System.out.println();
    }
     // kelas main
        public static void main(String[] args) {
            // buat SLL 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
            NodeSLL head = new NodeSLL(1);
            head.next = new NodeSLL(2);
            head.next.next = new NodeSLL(3);
            head.next.next.next = new NodeSLL(4);
            head.next.next.next.next = new NodeSLL(5);
            head.next.next.next.next.next = new NodeSLL(6);

            // cetak list awal
            System.out.println("List awal: ");
            printList(head);

            // hapus head
            head = deleteHead(head);
            System.out.println("List setelah head dihapus: ");
            printList(head);

            // hapus node terakhir
            head = removeLastNode(head);
            System.out.println("List setelah simpul terakhir dihapus: ");
            printList(head);

            // hapus simpul pada posisi ke-2
            int position = 2;
            head = deleteNode(head, position);
            System.out.println("List setelah posisi 2 dihapus: ");
            printList(head);
    }
}