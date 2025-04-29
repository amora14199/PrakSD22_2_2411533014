package Pekan1;

import java.util.*;

public class ArrayListGetElemen {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(9);
		list.add(5);
		list.add(6);
		System.out.println(list);
		Integer n = list.get(1);
		System.out.println("Pada indeks ke 1 angkanya adalah:" + n);
	}

}