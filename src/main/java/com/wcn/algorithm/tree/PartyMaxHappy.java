package com.wcn.algorithm.tree;

import java.util.List;

/**
 * 派对最大快乐值
 * 公司现在要办party，你可以决定哪些员工来，哪些员工不来，规则如下：
 * 1. 如果过某个员工来了，那么这个员工的所有直接下级都不能来
 * 2. 排队的整体快乐值是所有到场员工快乐值的累加
 * 3. 你的目标是让排队的整体快乐值尽量大
 * 给定一棵多叉树的头结点boss，请返回派对的最大快乐值
 * 第一层boss不来的情况下，boss的下一级都来不一定是最优解
 */
public class PartyMaxHappy {
    static class Employee{
        int happy;//该员工自己的快乐值
        List<Employee> list;//该员工所有的直接下属
    }

    public static int max(Employee boss){
        Result result = process(boss);
        return Math.max(result.happyNo, result.happyYes);
    }

    private static Result process(Employee employee){
        if(employee.list==null || employee.list.isEmpty()){
            //最基层员工，不再有下属了
            return new Result(0, employee.happy);
        }
        //每个员工都可以分为来和不来两种情况

        int happyNo = 0;//本次循环的员工不来的情况下最大值
        int happyYes = employee.happy;//本次员工来的情况下最大值

        for(Employee e:employee.list){
            Result result = process(e);
            happyNo += Math.max(result.happyNo, result.happyYes); //本员工不来，所以其下级可以来也可以不来，取结果最大的
            happyYes += result.happyNo;//本员工来，其下级智能不来，只能取用happyNo
        }
        return new Result(happyNo, happyYes);
    }

    static class Result{
        int happyNo;//某个人不来的情况下最大快乐值
        int happyYes;//某个人来的情况下最大快乐值

        public Result(int happyNo, int happyYes) {
            this.happyNo = happyNo;
            this.happyYes = happyYes;
        }
    }
}
