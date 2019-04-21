package design_pattern.state;

public interface State {
    /**
     * 投入硬币
     */
    void insertQuarter();

    /**
     * 退回硬币
     */
    void ejectQuarter();

    /**
     * 转动扳机
     */
    void turnCrank();

    /**
     * 分配糖果
     */
    void dispense();
}
