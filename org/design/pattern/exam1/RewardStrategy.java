package org.design.pattern.exam1;

/**
 * @author lhzlhz
 * @create 2020/9/11
 */
public abstract class RewardStrategy {
	public abstract int reward(long userId);

	public void insertRewardAndSettlement(long userId, int reward) {} ; //更新用户信息以及结算
}
