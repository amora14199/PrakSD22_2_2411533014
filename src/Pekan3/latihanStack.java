package Pekan3;

import java.util.Stack;

public class latihanStack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack <Integer> s = new Stack<Integer>();
		s.push(42);
		s.push(-3);
		s.push(17);
		System.out.println("nilai stack= "+s);
		System.out.println("nilai pop = "+s.pop());
		System.out.println("nilai stack setelah pop="+s);
		System.out.println("nilai peek"+s.peek());
		System.out.println("nilai stack setelah peek="+s);
		
	}

}
