package org.design.pattern.exam1;

/**
 * @author lhzlhz
 * @create 2020/9/11
 */
public abstract class StrategyFactory<T> {
	abstract RewardStrategy createStrategy(Class<T> c);
}
