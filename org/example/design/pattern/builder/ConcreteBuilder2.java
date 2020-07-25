package org.example.design.pattern.builder;

/**
 * @author lhzlhz
 * @create 2020/7/23
 */
public class ConcreteBuilder2 implements Builder{
	//todo
	Product product;

	public ConcreteBuilder2() {
		product = new Product();
	}

	@Override
	public void buildPartA() {
		product.setPartA("Component A /2");
	}

	@Override
	public void buildPartB() {
		product.setPartB("Component B /2");

	}

	@Override
	public Product getResult() {
		return product;
	}

}
