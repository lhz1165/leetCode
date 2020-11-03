package org.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lhzlhz
 * @create 2020/10/30
 */
public class Test {
	public static void main(String[] args) {
		BorrowerDatabase bd = new BorrowerDatabase();
		//1 2 57
		//Borrowers  ----> int
		List<Integer> hasIds = bd.getBorrowers().stream().map(b -> b.getId()).collect(Collectors.toList());

		List<Integer> ids = new ArrayList<>();
		//3.....56...100
		for (int i = 1; i <100 ; i++) {
			if (!hasIds.contains(i)) {
				ids.add(i);
			}
		}
		int index = 0;
		Borrower b = new Borrower();
		b.setId(ids.get(index++));
	}



}
