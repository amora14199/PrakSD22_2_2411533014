package pekan6;

public class Node {
	 ItemBelanja data;
	    Node prev;
	    Node next;

	    public Node(ItemBelanja data) {
	        this.data = data;
	        this.prev = null;
	        this.next = null;
	    }
	}