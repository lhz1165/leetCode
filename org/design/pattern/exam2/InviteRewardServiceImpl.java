package org.design.pattern.exam2;

/**
 * @author lhzlhz
 * @create 2020/9/11
 *
 * 在我们的实践中，一个状态的下游不会涉及特别多的状态装换，所以我们简化了状态模式
 * 当前的状态只负责当前状态要处理的事情，状态的流转则由第三方类负责
 */
public class InviteRewardServiceImpl {
	public boolean sendRewardForInvitee(long userId, long orderId) {
		Request request = new Request(userId, orderId);
		RewardStateContext rewardContext = new RewardStateContext();
		rewardContext.setRewardState(new OrderCheckState());
		//执行这个状态的事件
		rewardContext.echo(rewardContext, request);  //开始返奖，订单校验


		//此处的if-else逻辑只是为了表达状态的转换过程，并非实际的业务逻辑
		if (rewardContext.isResultFlag()) {  //如果订单校验成功，进入预返奖状态
			rewardContext.setRewardState(new BeforeRewardCheckState());
			rewardContext.echo(rewardContext, request);
		} else {//如果订单校验失败，进入返奖失败流程，...
			rewardContext.setRewardState(new RewardFailedState());
			rewardContext.echo(rewardContext, request);
			return false;
		}

		if (rewardContext.isResultFlag()) {  //返奖成功，进入返奖结束流程，...
			rewardContext.setRewardState(new RewardSuccessState());
			rewardContext.echo(rewardContext, request);
		} else {  //返奖失败，进入返奖补偿阶段，...
			rewardContext.setRewardState(new CompensateRewardState());
			rewardContext.echo(rewardContext, request);
		}
		if (rewardContext.isResultFlag()) {  //补偿成功，进入返奖完成阶段，...
			rewardContext.setRewardState(new RewardSuccessState());
			rewardContext.echo(rewardContext, request);
		} else {
			//补偿失败，仍然停留在当前态，直至补偿成功（或多次补偿失败后人工介入处理）
			rewardContext.setRewardState(new CompensateRewardState());
			rewardContext.echo(rewardContext, request);
		}
		return true;
	}
}
