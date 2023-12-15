package day14;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class Main {
    private final static String FILENAME = "src/" + Main.class.getPackageName() + "/sample.txt";

    public static void main(String[] args) throws IOException {
        //part1();
        part2();
    }

    private static void part2() throws IOException {
        List<String> lines = readAllLines(Path.of(FILENAME));
        char[][] grid = new char[lines.size()][lines.getFirst().length()];

        for (String line : lines) {
            grid[lines.indexOf(line)] = line.toCharArray();
        }
        long cycle = 1;
        while (cycle != 0) {
            // tilt north
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '.') {
                        // roll next rock
                        int next_rock_index = findRockRollingFromSouth(grid, i, j);
                        if (next_rock_index != -1) {
                            grid[i][j] = 'O';
                            grid[next_rock_index][j] = '.';
                        }
                    }
                }
            }

            // tilt west
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '.') {
                        // roll next rock
                        int next_rock_index = findRockRollingFromEast(grid, i, j);
                        if (next_rock_index != -1) {
                            grid[i][j] = 'O';
                            grid[j][next_rock_index] = '.';
                        }
                    }
                }
            }

            // tilt south
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '.') {
                        // roll next rock
                        int next_rock_index = findRockRollingFromNorth(grid, i, j);
                        if (next_rock_index != -1) {
                            grid[i][j] = 'O';
                            grid[next_rock_index][j] = '.';
                        }
                    }
                }
            }

            // tilt east
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '.') {
                        // roll next rock
                        int next_rock_index = findRockRollingFromWest(grid, i, j);
                        if (next_rock_index != -1) {
                            grid[i][j] = 'O';
                            grid[j][next_rock_index] = '.';
                        }
                    }
                }
            }
            System.out.println(cycle);
            cycle--;
        }

        computeLoad(grid);
    }

    private static void part1() throws IOException {
        List<String> lines = readAllLines(Path.of(FILENAME));
        char[][] grid = new char[lines.size()][lines.getFirst().length()];

        for (String line : lines) {
            grid[lines.indexOf(line)] = line.toCharArray();
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '.') {
                    // roll next rock
                    int next_rock_index = findRockRollingFromSouth(grid, i, j);
                    if (next_rock_index != -1) {
                        grid[i][j] = 'O';
                        grid[next_rock_index][j] = '.';
                    }
                }
            }
        }
        computeLoad(grid);
    }

    private static void computeLoad(char[][] grid) {
        int max = grid.length;
        long sum = 0;
        for (char[] chars : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                if (chars[j] == 'O') {
                    sum += max;
                }
            }
            max--;
        }
        System.out.println(sum);
    }

    private static int findRockRollingFromSouth(char[][] grid, int i, int j) {
        for (int k = i; k < grid.length; k++) {
            if (grid[k][j] == '#') {
                return -1;
            }
            if (grid[k][j] == 'O') {
                return k;
            }
        }
        return -1;
    }

    private static int findRockRollingFromNorth(char[][] grid, int i, int j) {
        for (int k = grid.length - 1; k >= i; k--) {
            if (grid[k][j] == '#') {
                return -1;
            }
            if (grid[k][j] == 'O') {
                return k;
            }
        }
        return -1;
    }

    private static int findRockRollingFromEast(char[][] grid, int i, int j) {
        for (int k = j; k < grid[0].length; k++) {
            if (grid[k][i] == '#') {
                return -1;
            }
            if (grid[k][i] == 'O') {
                return k;
            }
        }
        return -1;
    }

    private static int findRockRollingFromWest(char[][] grid, int i, int j) {
        for (int k = grid[0].length - 1; k > j; k--) {
            if (grid[k][i] == '#') {
                return -1;
            }
            if (grid[k][i] == 'O') {
                return k;
            }
        }
        return -1;
    }


}
