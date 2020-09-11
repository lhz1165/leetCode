package org.design.pattern.exam1;

/**
 * @author lhzlhz
 * @create 2020/9/11
 */
public class FactorRewardStrategyFactory extends StrategyFactory {
	@Override
	RewardStrategy createStrategy(Class c) {
		RewardStrategy product = null;
		try {
			product = (RewardStrategy) Class.forName(c.getName()).newInstance();
		} catch (Exception e) {}
		return product;
	}
}
