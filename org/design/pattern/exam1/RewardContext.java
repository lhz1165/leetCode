package org.design.pattern.exam1;

/**
 * @author lhzlhz
 * @create 2020/9/11
 */
public class RewardContext {
	private RewardStrategy strategy;

	public RewardContext(RewardStrategy strategy) {
		this.strategy = strategy;
	}

	public void doStrategy(long userId) {
		int reward = strategy.reward(userId);
		insertRewardAndSettlement(userId,reward);
	}

	public void insertRewardAndSettlement(long userId, int reward) {
		//insertReward(userId, rewardMoney);
		System.out.println("数据库插入奖励");
		//settlement(userId);
		System.out.println("处理结算");
	}
}
