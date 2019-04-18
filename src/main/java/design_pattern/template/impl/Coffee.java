package design_pattern.template.impl;

import design_pattern.template.CaffeineBeverage;

import java.util.Scanner;

public class Coffee extends CaffeineBeverage {
    /**
     * 浸泡
     */
    @Override
    public void brew() {
        System.out.println("用沸水冲泡咖啡粉");
    }

    /**
     * 加入调料
     */
    @Override
    public void addCondiments() {
        System.out.println("加糖和牛奶");
    }

    @Override
    public boolean customerWantsCondiments() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请问你的咖啡是否要加糖和牛奶？(y/n)：");
        String next = scanner.next();
        if(next.toLowerCase().equals("y")){
            return true;
        }
        return false;
    }
}
