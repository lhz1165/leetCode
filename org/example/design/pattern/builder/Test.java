package org.example.design.pattern.builder;

/**
 * @author lhzlhz
 * @create 2020/7/23
 */
public class Test {
	public static void main(String[] args) {
		//子类声明 父类接收
		Builder builder1 = new ConcreteBuilder2();

		Director director1 = new Director(builder1);

		director1.construct();

	}
}
