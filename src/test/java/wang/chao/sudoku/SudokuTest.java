package wang.chao.sudoku;

import org.junit.jupiter.api.Test;

class SudokuTest {

    private static void runTest(String sudokuInput) {
        Sudoku sudoku = new Sudoku(sudokuInput);
        System.out.println("原始数独盘面：");
        sudoku.print();

        if (sudoku.solve()) {
            System.out.println("\n解出的数独盘面：");
            sudoku.print();
        } else {
            System.out.println("无解。");
        }
    }

    /**
     * 测试数独solve方法
     */
    @Test
    void testSolve() {
        // 测试1：简单数独
        System.out.println("测试1：简单数独");
        String easySudoku = "530070000600195000098000060800060003400803001700020006060000280000419005000080079";
        runTest(easySudoku);

        // 测试2：已解决的数独
        System.out.println("\n测试2：已解决数独");
        String solvedSudoku = "534678912672195348198342567859761423426853791713924856961537284287419635345286179";
        runTest(solvedSudoku);

        // 测试3：无解数独
        System.out.println("\n测试3：无解数独");
        String unsolvableSudoku = "530570000600195000098000060800060003400803001700020006060000280000419005000080079";
        runTest(unsolvableSudoku);

        // 测试4：几乎空的数独
        System.out.println("\n测试4：几乎空数独");
        String nearlyEmptySudoku = "000000000000000000000000000000000000000000000000000000000000000000000000000020003";
        runTest(nearlyEmptySudoku);

        // 测试5：无效数独-长度不足
        System.out.println("\n测试5：无效数独");
        String invalidSudokuToLongOrShort = "53007000060019500098000060800060003400803001700020006060000280000419005000080079";
        try {
            runTest(invalidSudokuToLongOrShort);
        } catch (Exception e) {
            assert e instanceof IllegalArgumentException;
        }

        // 测试6：无效数独-包含非数字字符
        System.out.println("\n测试6：无效数独");
        String invalidSudokuWithABC = "530070000600AV5000098000060800060003400803001700020006060000280000419005000080079";
        try {
            runTest(invalidSudokuWithABC);
        } catch (Exception e) {
            assert e instanceof IllegalArgumentException;
        }
    }

    /**
     * 测试数独print方法
     */
    @Test
    void testPrint() {
        Sudoku sudokuUnderTest = new Sudoku("530070000600195000098000060800060003400803001700020006060000280000419005000080079");

        sudokuUnderTest.print();
    }
}
