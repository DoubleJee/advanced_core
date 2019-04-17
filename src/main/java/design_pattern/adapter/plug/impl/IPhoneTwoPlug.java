package design_pattern.adapter.plug.impl;

import design_pattern.adapter.plug.TwoPlug;
//苹果充电器两孔插头
public class IPhoneTwoPlug implements TwoPlug {
    @Override
    public void twoPlugDisPlay() {
        System.out.println("我是Iphone充电线，两孔插头");
    }
}
