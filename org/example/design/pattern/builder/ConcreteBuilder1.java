package org.example.design.pattern.builder;

/**
 * @author lhzlhz
 * @create 2020/7/23
 */
public class ConcreteBuilder1 implements Builder{
	//null
	 Product product;

	public ConcreteBuilder1() {
		//object
		product = new Product();
	}


	public Product getResult() {
		return product;
	}

	@Override
	public void buildPartA() {
		//todo
		product.setPartA("Component A");

	}


	@Override
	public void buildPartB() {
		//todo
		product.setPartB("Component B");
	}

}
