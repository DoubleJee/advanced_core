package design_pattern.adapter.plug.impl;

import design_pattern.adapter.plug.ThreePlug;

public class ComputerThreePlug implements ThreePlug {
    @Override
    public void threePlugDisPlay() {
        System.out.println("我是电脑电源线，三孔插头");
    }
}
