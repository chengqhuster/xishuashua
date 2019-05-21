package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/employee-importance/
 *
 * 思路简述：dfs
 *
 */

import java.util.HashMap;
import java.util.List;

public class EmployeeImportance {
    public int getImportance(List<Employee> employees, int id) {
        if (employees == null || employees.size() == 0) {
            return 0;
        }
        HashMap<Integer, Employee> map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        return dfs(id, map);
    }

    private int dfs(int id, HashMap<Integer, Employee> map) {
        Employee employee = map.get(id);
        int importance = employee.importance;
        for (Integer sub : employee.subordinates) {
            importance += dfs(sub, map);
        }
        return importance;
    }

    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }
}
