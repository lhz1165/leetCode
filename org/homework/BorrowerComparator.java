package org.homework;

import java.util.Comparator;

/**
 * @author lhzlhz
 * @create 2020/10/30
 */
public class BorrowerComparator implements Comparator<Borrower> {

	@Override
	public int compare(Borrower o1, Borrower o2) {
		return o1.getId()-o2.getId();
	}
}
