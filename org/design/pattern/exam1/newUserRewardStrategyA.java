package org.design.pattern.exam1;

/**
 * @author lhzlhz
 * @create 2020/9/11
 */
public class newUserRewardStrategyA extends RewardStrategy {
	@Override
	public int reward(long userId) {
		System.out.println("新用户的奖励发放");
		return 100;
	}  //具体的计算逻辑，...
}
