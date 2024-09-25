package wang.chao.sudoku;

import java.io.Serializable;
import java.util.Objects;

public class Sudoku implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    // 数独盘面
    private Grid grid;

    /**
     * 构造函数，接受数独输入字符串
     *
     * @param input 输入字符串
     */
    public Sudoku(String input) {
        grid = new Grid(input);
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    /**
     * 回溯法-解数独
     *
     * @return 是否有解
     */
    public boolean solve() {
        for (int row = 0; row < Grid.GRID_SIZE; row++) {
            for (int col = 0; col < Grid.GRID_SIZE; col++) {
                // 找到空格，尝试填入数字
                if (grid.getRow(row)[col] == 0) {
                    for (int num = 1; num <= Grid.GRID_SIZE; num++) {
                        if (isValid(row, col, num)) {
                            grid.getRow(row)[col] = num;
                            if (solve()) {
                                return true;
                            }
                            grid.getRow(row)[col] = 0;
                        }
                    }
                    // 无解
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 检查填入数字是否合法
     *
     * @param row 行号
     * @param col 列号
     * @param num 所填数字
     * @return 是否合法
     */
    private boolean isValid(int row, int col, int num) {
        // 检查行
        for (int i : grid.getRow(row)) {
            if (i == num) return false;
        }
        // 检查列
        for (int i : grid.getColumn(col)) {
            if (i == num) return false;
        }
        // 检查3x3小格
        for (int i : grid.getBox(row, col)) {
            if (i == num) return false;
        }
        return true;
    }

    /**
     * 打印数独盘面
     */
    public void print() {
        grid.printGrid();
    }

    /**
     * 克隆Sudoku对象
     */
    @Override
    public Sudoku clone() throws CloneNotSupportedException {
        Sudoku clone = (Sudoku) super.clone();
        clone.setGrid(new Grid(this.grid.toString()));
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sudoku sudoku = (Sudoku) o;
        return this.grid.equals(sudoku.grid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grid);
    }
}
