package org.design.pattern.exam2;

/**
 * @author lhzlhz
 * @create 2020/9/12
 */
public class BeforeRewardCheckState extends RewardState{
	@Override
	void doReward(RewardStateContext context, Request request) {
		check(context, request);
	}

	boolean check(RewardStateContext rewardContext, Request request) {
		//返奖成功，进入返奖结束流程，...
		if (true) {
			rewardContext.setRewardState(new RewardSuccessState());
			rewardContext.echo(rewardContext, request);
			return true;
		} else {  //返奖失败，进入返奖补偿阶段，...
			rewardContext.setRewardState(new CompensateRewardState());
			rewardContext.echo(rewardContext, request);
			return false;
		}
	}
}
