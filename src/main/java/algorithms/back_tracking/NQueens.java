package algorithms.back_tracking;

// N皇后问题
public class NQueens {

    // 皇后摆放位置，数组索引是行号，数组元素是列号
    int[] queens;

    // 合理摆法
    int ways;


    public static void main(String[] args) {
        new NQueens().placeQueens(8);
    }


    void placeQueens(int n){
        if (n < 1) return;
        // n个皇后
        queens = new int[n];
        place(0);
        System.out.println(n + "皇后一共有" + ways + "种摆法");
    }

    /**
     * 从第row行开始摆放皇后
     */
    void place(int row) {
        // 如果摆到了最后一层则成功，计数
        if (row == queens.length) {
            ways++;
            show();
            return;
        }

        for (int col = 0; col < queens.length; col++) {
            // 选择岔路 （剪枝）
            if (isValid(row, col)) {
                // 摆放皇后
                queens[row] = col;
                // 从row + 1开始摆放
                place(row + 1);
                // 回溯
            }
            // 回溯
        }
    }


    /**
     * 检查第row行col列，是否能摆放皇后 （剪枝函数）
     */
    boolean isValid(int row, int col) {
        // 遍历前面每行皇后的位置
        for (int i = 0; i < row; i++) {
            // 在同一列，第col列已经皇后
            if (queens[i] == col) return false;
            // 在同一条对角线（两个斜线），第i行的皇后跟第row行第col列格子处在同一对角线上
            // 公式就是 x1 - x2 / y1 - y2 = 1或者-1，代表在一个对角线上，也就是x1 - x2的绝对值 = y1 - y2的绝对值
            if (row - i == Math.abs(col - queens[i])) return false;
    }
        return true;
    }

    void show() {
        // 每行每列打印，摆了皇后的输出1，没摆输出0
        for (int row = 0; row < queens.length; row++) {
            for (int col = 0; col < queens.length; col++) {
                if (queens[row] == col) {
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
