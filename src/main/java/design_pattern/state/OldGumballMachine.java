package design_pattern.state;
//糖果机
public class OldGumballMachine {

    /**
     * 状态：售罄
     */
    final static int SOLD_OUT = 0;

    /**
     * 状态：没有硬币
     */
    final static int NO_QUARTER = 1;

    /**
     * 状态：有硬币
     */
    final static int HAS_QUARTER = 2;

    /**
     * 状态：售卖中
     */
    final static int SOLD = 3;

    /**
     * 糖果机当前状态
     */
    int state = SOLD_OUT;

    /**
     * 糖果数目
     */
    int count = 0;

    public OldGumballMachine(int count){
        this.count = count;
        if(count > 0){
            //从售罄状态到没有硬币状态
            state = NO_QUARTER;
        }
    }

    /**
     * 投入硬币
     */
    public void insertQuarter(){
        if(state == HAS_QUARTER){
            System.out.println("已经有硬币了，你不能再投入硬币了！");
        }else if(state == NO_QUARTER){
            //修改为有硬币状态
            state = HAS_QUARTER;
            System.out.println("投入硬币，现在有硬币了");
        }else if(state == SOLD_OUT){
            System.out.println("不要再投硬币了，糖果已经卖完了");
        }else if(state == SOLD){
            System.out.println("请稍等一下，我们将把糖果售出给你");
        }
    }

    /**
     * 退回硬币
     */
    public void ejectQuarter(){
        if(state == HAS_QUARTER){
            System.out.println("退回成功！");
            //修改为没有硬币状态
            state = NO_QUARTER;
        }else if(state == NO_QUARTER){
            System.out.println("你没有投入硬币！");
        }else if(state == SOLD_OUT){
            System.out.println("无法退出，你没有投入硬币！");
        }else if(state == SOLD){
            System.out.println("不好意思，糖果即将出机，不可以退币了");
        }
    }

    /**
     * 转动把柄
     */
    public void turnCrank(){
        if(state == HAS_QUARTER){
            System.out.println("你转动了扳机");
            state = SOLD;
            dispense();
        }else if(state == NO_QUARTER){
            System.out.println("你转动了扳机，但是你没有投入硬币！");
        }else if(state == SOLD_OUT){
            System.out.println("已经没有糖果了。");
        }else if(state == SOLD){
            System.out.println("转两圈也不会多得到糖果！");
        }
    }

    /**
     * 分配糖果
     */
    public void dispense(){
        if(state == HAS_QUARTER){
            System.out.println("没有糖果！");
        }else if(state == NO_QUARTER){
            System.out.println("你需要先付钱！");
        }else if(state == SOLD_OUT){
            System.out.println("没有糖果！");
        }else if(state == SOLD){
            System.out.println("糖果已经出来了！");
            count --;
            if(count == 0){
                state = SOLD_OUT;
                System.out.println("糖果已经售罄了！");
            }else {
                state = NO_QUARTER;
            }
        }
    }

    @Override
    public String toString() {
        String stateStr = null;
        if(state == HAS_QUARTER){
            stateStr = "已有硬币";
        }else if(state == NO_QUARTER){
            stateStr = "没有硬币";
        }else if(state == SOLD_OUT){
            stateStr = "售罄";
        }else if(state == SOLD){
            stateStr = "售卖中";
        }
        return "--------------------糖果机现在的状态：" + stateStr + "--------------------";
    }

    public static void main(String[] args) {
        OldGumballMachine oldGumballMachine = new OldGumballMachine(5);
        System.out.println(oldGumballMachine);

        oldGumballMachine.insertQuarter();
        oldGumballMachine.turnCrank();
        System.out.println(oldGumballMachine);

        oldGumballMachine.insertQuarter();
        oldGumballMachine.ejectQuarter();
        oldGumballMachine.turnCrank();
        System.out.println(oldGumballMachine);

        oldGumballMachine.insertQuarter();
        oldGumballMachine.turnCrank();
        oldGumballMachine.insertQuarter();
        oldGumballMachine.turnCrank();
        oldGumballMachine.ejectQuarter();
        System.out.println(oldGumballMachine);

        oldGumballMachine.insertQuarter();
        oldGumballMachine.insertQuarter();
        oldGumballMachine.turnCrank();
        oldGumballMachine.insertQuarter();
        oldGumballMachine.turnCrank();
        oldGumballMachine.insertQuarter();
        oldGumballMachine.turnCrank();
        System.out.println(oldGumballMachine);
    }

}
