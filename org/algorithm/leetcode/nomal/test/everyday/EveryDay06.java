package org.algorithm.leetcode.nomal.test.everyday;

import javafx.util.Pair;
import org.algorithm.leetcode.specified.unifind.ConnectingGraphIII;

import java.util.*;

/**
 * @author: lhz
 * @date: 2021/1/5
 **/
public class EveryDay06 {
    public static void main(String[] args) {
        EveryDay06 e = new EveryDay06();
//        List<List<String>> equations = new ArrayList<>();
//        List<String> a = new ArrayList<>(Arrays.asList("a","e"));
//        List<String> a1 = new ArrayList<>(Arrays.asList("b","e"));
//        equations.add(a);
//        equations.add(a1);
//        double[] values = new double[]{4.0,3.0};
//        List<List<String>> queries = new ArrayList<>();
//        List<String> b = new ArrayList<>(Arrays.asList("a","b"));
//        List<String> b1 = new ArrayList<>(Arrays.asList("e","e"));
//        List<String> b4 = new ArrayList<>(Arrays.asList("x","x"));
//        queries.add(b);
//        queries.add(b1);
//
//        queries.add(b4);
//        e.calcEquation(equations, values, queries);
        //[1,0,0],[0,1,0],[0,0,1]
//        int[][] ar = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
//        System.out.println(e.findCircleNum(ar));
        e.findUnsortedSubarray(new int[]{2,6,4,8,10,9,15});


    }

    /**
     * 399. 除法求值
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        List<PairVal> vals = new ArrayList<>();
        List<PairVal> results = new ArrayList<>();
        Map<String, Double> map = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            String fenzi = equations.get(i)
                    .get(0);
            String fenmu = equations.get(i)
                    .get(1);
            PairVal par = new PairVal();
            par.fenzi = par.fenzi + fenzi;
            par.fenmu = par.fenmu + fenmu;
            par.val = values[i];
            vals.add(par);
        }

        for (int i = 0; i < vals.size(); i++) {
            Queue<PairVal> queue = new LinkedList<>();
            queue.offer(vals.get(i));
            results.add(vals.get(i));
            int index = i + 1;
            while (index < vals.size()) {
                int num = queue.size();
                for (int j = 0; j < num; j++) {
                    PairVal curPair = queue.poll();
                    PairVal cal = vals.get(index);
                    PairVal newVal1 = new PairVal();
                    newVal1.val = cal.val * curPair.val;
                    newVal1.fenzi = curPair.fenzi + cal.fenzi;
                    newVal1.fenmu = curPair.fenmu + cal.fenmu;
                    yuefen(newVal1);
                    results.add(newVal1);
                    queue.offer(newVal1);
                    PairVal newVal2 = new PairVal();
                    newVal2.val = curPair.val / cal.val;
                    newVal2.fenzi = curPair.fenzi + cal.fenmu;
                    newVal2.fenmu = curPair.fenmu + cal.fenzi;
                    yuefen(newVal2);
                    results.add(newVal2);
                    queue.offer(newVal2);
                }
                index++;
            }

        }

        for (PairVal pairVal : results) {
            map.put(pairVal.fenzi + "/" + pairVal.fenmu, pairVal.val);
            map.put(pairVal.fenmu + "/" + pairVal.fenzi, 1 / pairVal.val);
        }
        List<Double> list = new ArrayList<>();
        for (List<String> query : queries) {
            String fenzi = query.get(0);
            String fenmu = query.get(1);
            boolean is = true;
            if (fenzi.equals(fenmu)) {
                for (List<String> equation : equations) {
                    if (equation.contains(fenmu)) {
                        list.add(1.0);
                        is = false;
                        break;
                    }
                }
                if (is) {
                    list.add(-1.0);
                }
            } else {
                String key = fenzi + "/" + fenmu;
                list.add(map.getOrDefault(key, -1.0));
            }

        }
        double[] res = new double[list.size()];
        int k = 0;
        for (Double aDouble : list) {
            res[k++] = aDouble;
        }

        return res;


    }

    public void yuefen(PairVal pairVal) {
        String a = pairVal.fenzi;
        String b = pairVal.fenmu;
        StringBuilder sa = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        List<Character> al = new ArrayList<>();
        List<Character> bl = new ArrayList<>();
        char[] ca = a.toCharArray();
        char[] cb = b.toCharArray();
        for (char c : ca) {
            al.add(c);
        }
        for (char c : cb) {
            bl.add(c);
        }

        Iterator<Character> iterator = al.iterator();
        while (iterator.hasNext()) {
            Character c = iterator.next();
            if (bl.contains(c)) {
                iterator.remove();
                bl.remove(c);
            }
        }
        for (Character c : al) {
            sa.append(c);
        }
        for (Character c : bl) {
            sb.append(c);
        }
        pairVal.fenzi = sa.toString();
        pairVal.fenmu = sb.toString();

    }

    /**
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0 || isConnected[0].length == 0) {
            return 0;
        }
        int m = isConnected.length;//行
        int n = isConnected[0].length;//列
        ConnectingGraphIII c = new ConnectingGraphIII(m);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (isConnected[i][j] == 1) {
                    c.connect(i, j);
                }
            }
        }
        return c.query();

    }


    public int findUnsortedSubarray(int[] nums) {
        Stack < Integer > stack = new Stack < Integer > ();
        int l = nums.length, r = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
                l = Math.min(l, stack.pop());
            stack.push(i);
        }
        stack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                r = Math.max(r, stack.pop());
            stack.push(i);
        }
        return r - l > 0 ? r - l + 1 : 0;
    }


    public  Pair<Integer, Integer> findMin(int[] nums, int start, int end) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = start; i < end + 1; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        return new Pair<>(min,max);
    }





}

class PairVal {
    String fenzi = "";
    String fenmu = "";
    Double val = 0.0;

}