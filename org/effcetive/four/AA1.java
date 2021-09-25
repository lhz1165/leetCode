package org.effcetive.four;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lhzlhz
 * @create 20/9/2021
 */
public class AA1 extends AA{
	@Override
	public void aa() {

	}

	public static void main(String[] args) {
		List<Integer> a = new ArrayList<>();
		a.add(1);
		List<? extends Integer> b = a;
		b.forEach(i->{
			System.out.println(i);
		});

	}

}
