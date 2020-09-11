package org.design.pattern.exam2;

/**
 * @author lhzlhz
 * @create 2020/9/11
 */
public class RewardStateContext {

	private RewardState rewardState;

	public void setRewardState(RewardState currentState) {
		this.rewardState = currentState;
	}

	public RewardState getRewardState() {
		return rewardState;
	}

	public void echo(RewardStateContext context, Request request) {
		rewardState.doReward(context, request);
	}

	public boolean isResultFlag() {

		return true;
	}
}
