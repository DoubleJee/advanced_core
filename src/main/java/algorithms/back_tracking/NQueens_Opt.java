package algorithms.back_tracking;


// N皇后问题-剪枝优化
public class NQueens_Opt extends NQueens {

    // 标记某列是否有皇后
    boolean[] cols;

    // 标记某一左斜线是否有皇后 （左上角 -> 右下角）
    boolean[] leftTop;

    // 标记某一右斜线是否有皇后 （右上角 -> 左下角）
    boolean[] rightTop;


    public static void main(String[] args) {
        new NQueens_Opt().placeQueens(8);
    }


    @Override
    void placeQueens(int n) {
        if (n < 1) return;
        // n个皇后
        queens = new int[n];
        // n列
        cols = new boolean[n];
        // 斜线数量是 2 * n - 1 条
        leftTop = new boolean[(n << 1) - 1];
        rightTop = new boolean[leftTop.length];

        place(0);
        System.out.println(n + "皇后一共有" + ways + "种摆法");
    }

    @Override
    void place(int row) {
        // 如果摆好了最后一层则成功，计数
        if (row == queens.length) {
            ways++;
            show();
            return;
        }

        for (int col = 0; col < cols.length; col++) {
            // 剪枝
            // 当前列是否有皇后
            if (cols[col]) continue;
            // 左斜线索引 计算公式为 row - col + (n - 1)
            int ltIndex = row - col + cols.length - 1;
            // 所在左斜线是否有皇后
            if (leftTop[ltIndex]) continue;
            // 右斜线索引 计算公式为 row + col
            int rtIndex = row + col;
            // 所在右斜线是否有皇后
            if (rightTop[rtIndex]) continue;

            // 摆放皇后
            queens[row] = col;
            cols[col] = true;
            leftTop[ltIndex] = true;
            rightTop[rtIndex] = true;

            // 从row + 1开始摆放
            place(row + 1);

            // 回溯，取消摆放皇后
            cols[col] = false;
            leftTop[ltIndex] = false;
            rightTop[rtIndex] = false;
        }
        // 回溯
    }
}
