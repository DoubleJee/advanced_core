package design_pattern.template.impl;

import design_pattern.template.CaffeineBeverage;

import java.util.Scanner;

public class Tea extends CaffeineBeverage {
    /**
     * 浸泡
     */
    @Override
    public void brew() {
        System.out.println("用沸水侵泡茶叶");
    }

    /**
     * 加入调料
     */
    @Override
    public void addCondiments() {
        System.out.println("加柠檬");
    }

    @Override
    public boolean customerWantsCondiments() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请问你的茶是否要加点柠檬？(y/n)：");
        String next = scanner.next();
        if(next.toLowerCase().equals("y")){
            return true;
        }
        return false;
    }
}
