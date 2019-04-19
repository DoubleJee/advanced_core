package design_pattern.iterator;


import design_pattern.iterator.inlay.CafeMenu;
import design_pattern.iterator.inlay.Menu;

public class Store {
    public static void main(String[] args) {
        //早餐菜单
        PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
        //午餐菜单
        DinnerHouseMenu dinnerHouseMenu = new DinnerHouseMenu();
        //咖啡菜单
        Menu CafeMenu = new CafeMenu();
        Iterator dinnerHouseMenuIterator = dinnerHouseMenu.createIterator();
        Waiter waiter = new Waiter(dinnerHouseMenu,pancakeHouseMenu);
        waiter.printMenu();

        //迭代器模式：提供一种方法顺序访问一个聚合对象（collection）中的各个元素，而又不暴露其内部的表示。

        //迭代器给我们一个统一的方法访问聚合中的每一个对象、遍历，不需要关注那种容器存储的，只需要获得元素

        //服务员不需要知道，我们使用哪一个实现（哪一个容器存储菜单项），反正她都是使用相同的接口，也就是只需要使用迭代器的接口来遍历菜单项（解耦了）
    }
}
