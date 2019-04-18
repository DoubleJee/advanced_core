package design_pattern.proxy.house.impl;

import design_pattern.proxy.house.SellHouse;

//房东
public class Landlord implements SellHouse {

    @Override
    public void sellHouse() {
        System.out.println("收到钱了，这是你的房间钥匙，这个房子归你了！");
    }
}
