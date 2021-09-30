package algorithms.back_tracking;

// 八皇后问题-位运算位图优化空间复杂度
public class Queen8_Opt {

    // 皇后摆放位置，数组索引是行号，数组元素的bit位是列号
    byte[] queens;

    // 用位标记某列是否有皇后
    byte cols;

    // 用位标记某一左斜线是否有皇后 （左上角 -> 右下角）
    short leftTop;

    // 用位标记某一右斜线是否有皇后 （右上角 -> 左下角）
    short rightTop;

    // 合理摆法
    int ways;


    public static void main(String[] args) {
        new Queen8_Opt().place8Queues();
    }

    void place8Queues() {
        queens = new byte[8];
        place(0);
        System.out.println("8皇后一共有" + ways + "种摆法");
    }

    void place(int row) {
        // 如果摆好了最后一层则成功，计数
        if (row == 8){
            ways++;
            show();
            return;
        }

        // 求值的第n位bit的公式为：值 & (1 << n)，也就是 值 与上 1左移n位，得出的值等于0，这个bit = 0，不等于0，则这个bit == 1
        for (int col = 0; col < 8; col++) {
            // 剪枝
            // 列操作位
            int co = 1 << col;
            if ((cols & co) != 0) continue;
            // 所在左斜线操作位
            int lo = 1 << (row - col + 8 - 1);
            if ((leftTop & lo) != 0) continue;
            // 所在右斜线操作位
            int ro = 1 << (row + col);
            if ((rightTop & ro) != 0) continue;

            // 摆放皇后
            // 指定列设为1，其余为0
            queens[row] = (byte) ((queens[row] | co) & co);
            // 指定bit设为1，其余不变
            cols |= co;
            leftTop |= lo;
            rightTop |= ro;

            place(row + 1);

            // 回溯
            // 指定bit设为0，其余不变
            cols ^= co;
            leftTop ^= lo;
            rightTop ^= ro;
        }
        // 回溯
    }

    void show(){
        // 每行每列打印，根据bit位，摆了皇后的输出1，没摆输出0
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if ((queens[row] & (1 << col)) != 0) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println("------------------------");
    }


}
