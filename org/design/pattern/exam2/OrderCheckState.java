package org.design.pattern.exam2;

/**
 * @author lhzlhz
 * @create 2020/9/11
 * 等待校验状态这是最开始的状态
 */
public class OrderCheckState extends RewardState {
	@Override
	public void doReward(RewardStateContext context, Request request) {
		orderCheck(context, request);  //对进来的订单进行校验，判断是否用券，是否满足优惠条件等等
	}

	private boolean orderCheck(RewardStateContext context, Request request) {
		System.out.println("对进来的订单进行校验，判断是否用券，是否满足优惠条件等等");
		if (true) {
			context.setRewardState(new BeforeRewardCheckState());
			context.echo(context, request);
			return true;
		}else {
			context.setRewardState(new RewardFailedState());
			context.echo(context, request);
			return false;
		}

	}

}
