package org.design.pattern.exam1;

/**
 * @author lhzlhz
 * @create 2020/9/11
 * 策略模式在不同用户返回奖励的实战
 */
public class InviteRewardImpl {
	public static void main(String[] args) {
		InviteRewardImpl t = new InviteRewardImpl();
		t.sendReward(1);
	}

	//返奖主流程
	public void sendReward(long userId) {
		FactorRewardStrategyFactory strategyFactory = new FactorRewardStrategyFactory();  //创建工厂
		//Invitee invitee = getInviteeByUserId(userId);  //根据用户id查询用户信息
		int userid = 0;
		//if (invitee.userType == UserTypeEnum.NEW_USER) {  //新用户返奖策略
			newUserRewardStrategyA newUserBasicReward = (newUserRewardStrategyA) strategyFactory.createStrategy(newUserRewardStrategyA.class);
			RewardContext rewardContext = new RewardContext(newUserBasicReward);
			rewardContext.doStrategy(userId); //执行返奖策略
		//}if(invitee.userType == UserTypeEnum.OLD_USER){}  //老用户返奖策略，...
	}
}
