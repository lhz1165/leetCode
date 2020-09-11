package org.design.pattern.exam2;

/**
 * @author lhzlhz
 * @create 2020/9/11
 */
//待补偿状态
public class CompensateRewardState extends RewardState {
	@Override
	public void doReward(RewardStateContext context, Request request) {
		compensateReward(context, request);  //返奖失败，需要对用户进行返奖补偿
	}

	private void compensateReward(RewardStateContext context, Request request) {

	}
}