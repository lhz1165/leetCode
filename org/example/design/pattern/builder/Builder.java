package org.example.design.pattern.builder;

/**
 * @author lhzlhz
 * @create 2020/7/23
 */
public interface Builder {
	void  buildPartA();

	void buildPartB();

	Product getResult();
}
