package org.design.pattern.exam2;

/**
 * @author lhzlhz
 * @create 2020/9/12
 */
public class RewardFailedState extends RewardState{
	@Override
	void doReward(RewardStateContext context, Request request) {
		System.out.println("2 预返奖状态检测-----");
	}
}
