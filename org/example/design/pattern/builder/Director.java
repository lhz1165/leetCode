package org.example.design.pattern.builder;

/**
 * @author lhzlhz
 * @create 2020/7/23
 */
public class Director {
	Builder builder;

	public Director(Builder builder) {
		this.builder = builder;
	}


	//
	public void construct() {
		//todo
		builder.buildPartA();
		builder.buildPartB();

		Product result = builder.getResult();

		System.out.println(result.partA);
		System.out.println(result.partB);
	}

}
