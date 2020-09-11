package org.design.pattern.exam2;

/**
 * @author lhzlhz
 * @create 2020/9/11
 */
public abstract class RewardState {
  abstract void doReward(RewardStateContext context, Request request);
}
