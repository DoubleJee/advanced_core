package design_pattern.iterator.component;

public class WaiterC {

    MenuComponent allMenus;

    public WaiterC(MenuComponent allMenus){
        this.allMenus = allMenus;
    }

    public void printMenu(){
        allMenus.print();
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
    }

}
