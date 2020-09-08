package org.algorithm.leetcode300.specified.dynamic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author lhzlhz
 * @create 2020/9/3
 */
public class SigninController {
	/**
	 * TODO 请从这里开始补充代码
	 * <p>
	 * 测试1：138 1234 1234
	 * 结果：通过此手机号注册成功
	 * <p>
	 * 测试2：13812345abc
	 * 结果：通知本手机号无法注册，提示为非法手机号
	 * <p>
	 * 测试3：13812345678
	 * 结果：通知此手机号注册成功
	 * <p>
	 * 测试4：138 1234 5678
	 * 结果：提示此手机号已经被其他用户注册
	 * <p>
	 * 测试5：98765432101
	 * 结果：此手机号码为中国大陆非法手机号码
	 */
	public static void main(String[] args) throws InterruptedException {
		SigninController controller = new SigninController();
		List<String> db = new ArrayList<>();
		controller.register("158 8161 3068",db);
		controller.register("1554asdadas",db);
		controller.register("15881613068",db);
		controller.register("15881613068111",db);
		controller.register("15881613062",db);


	}

	public void register(String phone, List<String> phones) throws InterruptedException {
		String realPhone=phone.replaceAll(" +","") ;
		String valid = valid(realPhone,phones);
		if ("通过此手机号注册成功".equalsIgnoreCase(valid)) {
			phones.add(realPhone);
		}
		System.out.println(valid);


	}

	/**
	 * 开启三个线程 异步验证，只要有一项不合格就返回错误提示
	 * @param phone
	 * @param phones
	 * @return
	 * @throws InterruptedException
	 */
	public String valid(String phone,List<String> phones) throws InterruptedException {
		ExecutorService executor =
				Executors.newFixedThreadPool(3);
		CompletionService<String> cs =
				new ExecutorCompletionService<>(executor);
		List<Future<String>> futures =
				new ArrayList<>(3);
		futures.add(
				cs.submit(() -> {
					if (phones.contains(phone)) {
						return "提示此手机号已经被其他用户注册";
					}
					return "通过此手机号注册成功";
				}));

		futures.add(
				cs.submit(() ->{
					String mobileRegex = "^[0-9]*$";
					if (phone.matches(mobileRegex)){
						futures.add(
								cs.submit(() -> {
									String mobileRegex2 = "^1(3|4|5|7|8)\\d{9}$";
									if (phone.matches(mobileRegex2)){
										return "通过此手机号注册成功";
									}else {
										return "此手机号码为中国大陆非法手机号码";
									}

								}));
						return "通过此手机号注册成功";
					}else {

						return "通知本手机号无法注册，提示为非法手机号";
					}
				}));

		String r = "";
		try {
			// 只要有一个成功返回false，则break
			for (int i = 0; i < 3; ++i) {
				r = cs.take().get();

				if (!"通过此手机号注册成功".equals(r)) {
					break;
				}
			}
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally {
			//取消所有任务
			for (Future<String> f : futures) {
				f.cancel(true);

			}
			executor.shutdown();
		}
		// 返回结果
		return r;

	}


}
