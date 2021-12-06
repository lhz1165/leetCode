package org.algorithm.dy;

import org.algorithm.leetcode.basic.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

    }
    public int findKthNumber(int n, int k) {
        k = k - 1; // 从1出发开始往后按字典序从小到大的顺序走k-1步到达的就是 字典序的第K小数字

        long cur = 1; // 当前遍历到的数字，从1（根）出发

            while(k > 0){
            int nodes = getNodes(n, cur);
            if(k >= nodes){ // 向右侧节点走：字典序上升nodes位
                k =  k - nodes;
                cur++;  // 当前数字 cur = cur + 1
            }else{ // 向下往最左孩子节点走:字典序上升1位
                k = k - 1;
                cur *= 10;  // 当前数字 cur = cur * 10
            }
        }
        return (int)cur; // 最后cur停在的数字就是字典序的第K小数字
    }

    private int getNodes(int n, long cur){
        long next = cur + 1; // 当前节点右侧右边节点的值
        long totalNodes = 0; // 记录子树中的全部节点数目
        while(cur <= n){
            totalNodes += Math.min(n - cur + 1, next - cur);
            next *= 10;
            cur *= 10;
        }
        return (int)totalNodes;
    }




    //25分、10分、5分和1分 总数
    public int waysToChange(int n) {
        int[] dp = new int[n + 1];
        int[] coins = new int[]{1,5,10,25};
        dp[0] = 1;
        for(int coin : coins) {
            for(int i = coin; i <= n; i++) {
                dp[i] = (dp[i] + dp[i - coin]);
            }
        }
        return dp[n];
    }

//    public ListNode sortList(ListNode head) {
//        if(head == null || head.next == null){
//            return head;
//        }
//        ListNode midNode = findMid(head);
//        ListNode rightHead = sortList(midNode.next);
//        midNode.next = null;
//        ListNode leftHead = sortList(head);
//        return mergeTwoList(leftHead, rightHead);
//    }

    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        while (pre != null) {
            //部分反转
            //例如1->2->3->4 ===> 2->1->3->4 ==>2->1->4->3
            pre=reversePreK(pre, k);
        }

        return dummyNode.next;
    }
    //k=2 先反转前两个
    //1-2-3-4===》2-1-3-4
    public  ListNode reversePreK(ListNode head, int k) {
        //cur =0(dummy)
        ListNode cur = head;
        //n1 = 1
        ListNode n1 = head.next;
        for (int i = 0; i < k; i++) {
            cur = cur.next;
            if (cur == null) {
                return null;
            }
        }
        //设定边界
        //cur = 2
        //nkplus = 3
        ListNode nk = cur;
        ListNode nkplus = cur.next;

        //reverse
        //pre = 0(dummy)
        //cur = 1
        ListNode pre = head;
        cur = head.next;
        while (cur != nkplus) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        //2-1-0 ,3-4再组合在一起
        //head =0 ,n1 =1,nkplus=3,nk=2
        head.next = nk;
        n1.next = nkplus;
        //0-2-1-3-4
        //返回下一个前驱
        return n1;

    }



}
