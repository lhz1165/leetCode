package org.design.pattern.exam1;

/**
 * @author lhzlhz
 * @create 2020/9/11
 */
public class OldUserRewardStrategyA extends RewardStrategy {
	@Override
	public int reward(long userId) {
		System.out.println("老用户的奖励发放规则");
		return 50;
	}  //具体的计算逻辑，...
}
