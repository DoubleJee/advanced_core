package design_pattern.iterator.component;

import java.util.Iterator;

public class WaiterC {

    MenuComponent allMenus;

    public WaiterC(MenuComponent allMenus){
        this.allMenus = allMenus;
    }

    public void printMenu(){
        allMenus.print();
    }

    public void printVegetarianMenu(){
        System.out.println("-----------素食-----------");
        Iterator iterator = allMenus.createIterator();
        while (iterator.hasNext()){
            MenuComponent menuComponent = (MenuComponent)iterator.next();
            if(menuComponent.isVegetarian()){
                menuComponent.print();
            }
        }
    }


    public static void main(String[] args) {
        MenuComponent pancakeHouseMenu = new Menu("馅饼店菜单","早餐店");
        MenuComponent dinnerHouseMenu = new Menu("晚餐菜单","晚上适合吃喔！");
        MenuComponent cafeHouseMenu = new Menu("咖啡店菜单","浓浓咖啡各式各样");

        MenuComponent allMenus = new Menu("所有的菜单","附近店的所有菜单");
        allMenus.add(pancakeHouseMenu);
        allMenus.add(dinnerHouseMenu);
        allMenus.add(cafeHouseMenu);

        pancakeHouseMenu.add(new MenuItem("杂粮煎饼","薄饼加上蛋，香味十足",true,5));
        dinnerHouseMenu.add(new MenuItem("馄饨","混沌，虾米带汤",true,10));
        cafeHouseMenu.add(new MenuItem("摩卡咖啡","很浓香",true,7));

        WaiterC waiterC = new WaiterC(allMenus);
        waiterC.printMenu();
        waiterC.printVegetarianMenu();

        //组合模式：允许你将对象组成树形结构来表现“整体/部分”的层次结构，组合能让客户以一直的方式来处理个别对象和对象组合

        //菜单就是整体、对象组合（组合节点）、菜单项就是部分、个别对象（叶节点）
    }

}
