package design_pattern.proxy.house.impl;

import design_pattern.proxy.house.SellHouse;

public class MiddlemanProxy implements SellHouse {

    private SellHouse sellHouse;

    public MiddlemanProxy(SellHouse sellHouse){
        this.sellHouse = sellHouse;
    }

    @Override
    public void sellHouse() {
        System.out.println("把钱交给我把，我这里有房子");
        sellHouse.sellHouse();
    }
}
