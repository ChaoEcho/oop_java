package wang.chao.sudoku;

import java.io.Serializable;
import java.util.Arrays;

public class Grid implements Cloneable, Serializable {
    // 常量，数独的长/宽，固定为9
    public static final int GRID_SIZE = 9;
    // 常量，3x3小格的长/宽，固定为3
    public static final int BOX_SIZE = 3;
    private static final long serialVersionUID = 1;
    // 数独盘面，只能为1-9的数字，0表示空格
    private int[][] grid;


    /**
     * 构造函数，接受数独输入字符串
     *
     * @param input 输入字符串
     */
    public Grid(String input) {
        // 1.检查输入字符串长度是否为81
        if (input.length() != GRID_SIZE * GRID_SIZE) {
            throw new IllegalArgumentException("输入字符串长度必须为 " + (GRID_SIZE * GRID_SIZE) + "。");
        }
        grid = new int[GRID_SIZE][GRID_SIZE];
        // 2.解析输入字符串到grid数组
        parse(input);
    }

    /**
     * 解析输入字符串到grid数组
     *
     * @param input 输入字符串
     */
    private void parse(String input) {
        for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
            char ch = input.charAt(i);
            // 检查输入字符串只能包含数字（0-9）
            if (ch < '0' || ch > '9') {
                throw new IllegalArgumentException("输入字符串只能包含数字（0-9）。");
            }
            grid[i / GRID_SIZE][i % GRID_SIZE] = (ch == '0') ? 0 : ch - '0';
        }
    }

    /**
     * 获取某一行
     *
     * @param row 行号
     * @return 该行的数组
     */
    public int[] getRow(int row) {
        return grid[row];
    }

    /**
     * 获取某一列
     *
     * @param col 列号
     * @return 该列的数组
     */
    public int[] getColumn(int col) {
        int[] column = new int[GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            column[i] = grid[i][col];
        }
        return column;
    }

    /**
     * 获取某一3x3小格
     *
     * @param row 行号
     * @param col 列号
     * @return 该3x3小格的数组
     */
    public int[] getBox(int row, int col) {
        int[] box = new int[GRID_SIZE];
        // 计算3x3小格的起始行号和列号
        int boxRowStart = (row / BOX_SIZE) * BOX_SIZE;
        int boxColStart = (col / BOX_SIZE) * BOX_SIZE;
        int index = 0;
        for (int i = boxRowStart; i < boxRowStart + BOX_SIZE; i++) {
            for (int j = boxColStart; j < boxColStart + BOX_SIZE; j++) {
                box[index++] = grid[i][j];
            }
        }
        return box;
    }

    /**
     * 打印当前数独盘面
     */
    public void printGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public Grid clone() throws CloneNotSupportedException {
        Grid clone = (Grid) super.clone();
        clone.grid = new int[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            System.arraycopy(grid[i], 0, clone.grid[i], 0, GRID_SIZE);
        }
        return clone;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                stringBuilder.append(grid[i][j]);
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grid grid1 = (Grid) o;
        for (int i = 0; i < GRID_SIZE; i++) {
            if (!Arrays.equals(grid[i], grid1.grid[i])) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(grid);
    }
}