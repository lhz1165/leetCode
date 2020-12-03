package org.algorithm.leetcode300.nomal.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: lhz
 * @date: 2020/12/3
 * 690 员工的重要性
 **/
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;

    public Employee(int id, int importance, List<Integer> subordinates) {
        this.id = id;
        this.importance = importance;
        this.subordinates = subordinates;
    }

    public static void main(String[] args) {
        Employee e1 = new Employee(1,5,new ArrayList<>(Arrays.asList(2,3)));
        Employee e2 = new Employee(2,3,new ArrayList<>());
        Employee e3 = new Employee(3,3,new ArrayList<>());

        System.out.println(e1.getImportance(new ArrayList<>(Arrays.asList(e1, e2, e3)), 1));
    }


    /**
     * 输入: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
     * 输出: 11
     * @param employees
     * @param id
     * @return
     */

    public int getImportance(List<Employee> employees, int id) {
        int result = 0;
        for (Employee employee : employees) {
            if (employee.id == id) {
                result = getResult(employee,  employees);
                break;
            }
        }
        return result;
    }

    public int  getResult(Employee employee,List<Employee> employees) {
        if (employee.subordinates == null || employee.subordinates.isEmpty()) {
            return employee.importance;
        }
        int res = 0;
        res += employee.importance;
        for (Integer subordinate : employee.subordinates) {
            for (Employee employee1 : employees) {
                if (employee1.id == subordinate) {
                    res+=getResult(employee1,employees);
                }
            }
        }
        return res;
    }




};