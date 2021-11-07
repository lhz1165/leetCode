package org.algorithm.third.no4;

/**
 * @author lhzlhz
 * @create 2/10/2021
 */
/**
 * @author Cremains
 * @date 2021/9/25 17:32
 * @description
 */
public class SingleLinkedListDemo {

	public static void main(String[] args) {
		// 先创建节点
		HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");

		HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
		HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");

		// 创建一个链表
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		// 加入
		singleLinkedList.add(heroNode1);
		singleLinkedList.add(heroNode2);
		singleLinkedList.add(heroNode3);
		singleLinkedList.add(heroNode4);
		/**
		 * 结果:
		 * HeroNode{no=1, name='宋江', nickName='及时雨'}
		 * HeroNode{no=2, name='卢俊义', nickName='玉麒麟'}
		 * HeroNode{no=3, name='吴用', nickName='智多星'}
		 * HeroNode{no=4, name='林冲', nickName='豹子头'}
		 */
		/**
		 * 结果
		 * 准备插入的英雄的编号[3]已存在,不能加入!
		 * HeroNode{no=1, name='宋江', nickName='及时雨'}
		 * HeroNode{no=2, name='卢俊义', nickName='玉麒麟'}
		 * HeroNode{no=3, name='吴用', nickName='智多星'}
		 * HeroNode{no=4, name='林冲', nickName='豹子头'}
		 */
		// 显示
		singleLinkedList.list();

		// 测试 修改节点
		HeroNode newHeroNode = new HeroNode(2, "小卢", "小麒麟");
		HeroNode newHeroNode1 = new HeroNode(5, "小卢", "小麒麟");
		singleLinkedList.update(newHeroNode);
		singleLinkedList.update(newHeroNode1);
		/**
		 * 结果
		 * HeroNode{no=1, name='宋江', nickName='及时雨'}
		 * HeroNode{no=2, name='卢俊义', nickName='玉麒麟'}
		 * HeroNode{no=3, name='吴用', nickName='智多星'}
		 * HeroNode{no=4, name='林冲', nickName='豹子头'}
		 * 没有找到编号为[5]的节点
		 * 修改后的链表情况
		 *
		 * HeroNode{no=1, name='宋江', nickName='及时雨'}
		 * HeroNode{no=2, name='小卢', nickName='小麒麟'}
		 * HeroNode{no=3, name='吴用', nickName='智多星'}
		 * HeroNode{no=4, name='林冲', nickName='豹子头'}
		 */
		// 修改后的显示
		System.out.println("修改后的链表情况");
		System.out.println();
		singleLinkedList.list();

		System.out.println("删除一个节点");
		singleLinkedList.delete(1);
		singleLinkedList.delete(2);
		singleLinkedList.delete(3);
		singleLinkedList.delete(4);
		singleLinkedList.list();
		/**
		 * 结果
		 * 删除一个节点
		 * 链表为空
		 */
	}
}

// 定义一个单向链表 管理我们的英雄
class SingleLinkedList {
	// 初始化一下头结点  头结点不要动 不存放具体的数据
	private HeroNode head = new HeroNode(0, null, null);

	// 添加方法到单向链表
	// 思路 当不考虑编号顺序时
	// 1. 找到当前链表的最后节点
	// 2. 将最后的这个节点的next 指向 新节点

	public void add(HeroNode heroNode) {
		// 因为 head节点不能动 我们需要一个辅助节点 temp
		HeroNode temp = head;
		// 遍历链表找到最后
		while (true) {
			// 找到链表的最后
			if (temp.next == null) {
				break;
			}
			// 如果没有找到 就将temp后移
			temp = temp.next;
		}
		// 当退出white循环时, temp 就指向了链表的最后
		temp.next = heroNode;
		//heroNode.next = null;

	}

	// 第二种添加英雄的方式, 根据排名将英雄插入到指定位置
	// 如果有这个排名, 则添加失败 并给出提示
	public void addByOrder(HeroNode heroNode) {
		// 因为头结点不能动 因此我们任然通过一个辅助指针(变量) 来帮助找到添加的位置
		// 因为是单列表 temp是要找到添加节点的前一个节点, 否则插入不了
		HeroNode temp = head;
		boolean flag = false; // 标志添加的编号是否存在 默认为false
		while (true) {
			if (temp.next == null) { // 说明已经到了链表的最后了
				break;
			}
			if (temp.next.no > heroNode.no) { // 位置找到了 在temp和temp.next之间插入该节点
				break;
			} else if (temp.next.no == heroNode.no) { // 说明希望添加的heroNode的编号已经存在 不能添加
				flag = true; // 说明编号存在
				break;
			}
			temp = temp.next;
		}
		// 判断 flag的值
		if (flag) { // 不能添加 说明编号存在
			System.out.println("准备插入的英雄的编号[" + heroNode.no + "]已存在,不能加入!");
		} else {
			// 插入链表中 temp 中
			heroNode.next = temp.next;
			temp.next = heroNode;
		}
	}

	// 显示链表 遍历
	public void list() {
		// 判断链表为空
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		// 因为头结点不能动我们需要一个复制节点 temp
		HeroNode temp = head.next;
		while (true) {
			// 输出这个节点的信息
			System.out.println(temp);
			if (temp.next == null) {
				break;
			}
			// 将temp 后移 不然是一个死循环
			temp = temp.next;
		}

	}

	// 修改节点的信息 根据编号来修改 编号不能修改
	public void update(HeroNode newHeroNode) {
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		// 找到需要修改的节点 很具no查询
		HeroNode temp = head.next;
		boolean flag = false; // 表示是否找到该节点
		while (true) {
			if (temp == null) {
				break; // 表示链表已经遍历结束了 (不是最后一个节点 已经出到外面去了)
			}
			if (temp.no == newHeroNode.no) {
				// 找到要修改的节点
				flag = true;
				break;
			}
			temp = temp.next;
		}
		// 根据flag判断是否找到要修改的节点
		if (!flag) {
			System.out.println("没有找到编号为[" + newHeroNode.no + "]的节点");
		} else {
			temp.name = newHeroNode.name;
			temp.nickName = newHeroNode.nickName;
		}

	}

	// 删除节点
	//思路 1.head节点不能动 因此我们需要一个temp辅助节点找到删除节点的前一个节点
	//    2. 我们需要以temp节点的后一个节点的标号进行比较
	public void delete(int no) {
		HeroNode temp = head;
		boolean flag = false; // 标志是否找到待删除节点的
		while (true) {
			if (temp.next == null) { // temp已经到链表的最后
				break;
			}
			if (temp.next.no == no) {
				// 找到了待删除节点的前一个节点
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if (flag) {
			// 可以删除
			HeroNode t = temp.next;
			temp.next = t.next;
			t.next = null;
		} else {
			System.out.println("没有找到该no=[" + no + "]节点,无法删除");
		}


	}

}

// 定义一个HeroNode , 每个HeroNode 对象就是一个节点
class HeroNode {
	public int no;
	public String name;
	public String nickName;
	public HeroNode next; // 指向下一个节点 null

	// 构造器
	public HeroNode(int hNo, String hName, String hNickName) {
		this.no = hNo;
		this.name = hName;
		this.nickName = hNickName;
	}

	// 为了显示方便 重写toString方法


	@Override
	public String toString() {
		return "HeroNode{" +
				"no=" + no +
				", name='" + name + '\'' +
				", nickName='" + nickName + '\'' +
				'}';
	}
}


