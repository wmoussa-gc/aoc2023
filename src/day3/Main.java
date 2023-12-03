package day3;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Character.isDigit;
import static java.nio.file.Files.readAllLines;

public class Main {
    private final static String FILENAME = "src/" + Main.class.getPackageName() + "/input.txt";

    public static void main(String[] args) throws IOException {
        //part1();
        part2();
    }

    private static void part1() throws IOException {
        List<String> lines = readAllLines(Path.of(FILENAME));
        int number_of_rows = lines.size();
        int number_of_columns = lines.get(0).length();
        char[][] matrix = new char[number_of_rows][number_of_columns];
        int i = 0;
        for (String line : lines) {
            matrix[i++] = line.toCharArray();
        }

        long sum = 0;
        for (int row = 0; row < number_of_rows; row++) {
            boolean is_surrounded = false;
            String part = "";
            for (int col = 0; col < number_of_columns; col++) {
                if (isDigit(matrix[row][col])) {
                    part += matrix[row][col];
                    if (isSurrounded(matrix, row, col, number_of_rows))
                        is_surrounded = true;
                } else if (!part.isEmpty() && is_surrounded) {
                    System.out.println(part);
                    sum += Integer.parseInt(part);
                    part = ""; //reset
                    is_surrounded = false;
                } else if (!part.isEmpty()) {
                    part = "";
                }
            }

            // last col
            if (!part.isEmpty() && is_surrounded) {
                System.out.println(part);
                sum += Integer.parseInt(part);
            }

        }
        System.out.println("total= " + sum);
    }

    private static boolean isSurrounded(char[][] matrix, int row, int col, int size) {
        if (row != 0 && col != 0 && row != size - 1 && col != size - 1) {
            if (!isDigit(matrix[row + 1][col + 1]) && matrix[row + 1][col + 1] != '.') return true;
            else if (!isDigit(matrix[row - 1][col - 1]) && matrix[row - 1][col - 1] != '.') return true;
            else if (!isDigit(matrix[row - 1][col + 1]) && matrix[row - 1][col + 1] != '.') return true;
            else if (!isDigit(matrix[row + 1][col - 1]) && matrix[row + 1][col - 1] != '.') return true;
            else if (!isDigit(matrix[row][col - 1]) && matrix[row][col - 1] != '.') return true;
            else if (!isDigit(matrix[row][col + 1]) && matrix[row][col + 1] != '.') return true;
            else if (!isDigit(matrix[row - 1][col]) && matrix[row - 1][col] != '.') return true;
            else return !isDigit(matrix[row + 1][col]) && matrix[row + 1][col] != '.';
        } else if (row == 0 && col == 0) {
            if (!isDigit(matrix[row + 1][col + 1]) && matrix[row + 1][col + 1] != '.') return true;
            else if (!isDigit(matrix[row][col + 1]) && matrix[row][col + 1] != '.') return true;
            else return !isDigit(matrix[row + 1][col]) && matrix[row + 1][col] != '.';
        } else if (row == 0 && col == size - 1) {
            if (!isDigit(matrix[row + 1][col - 1]) && matrix[row + 1][col - 1] != '.') return true;
            else if (!isDigit(matrix[row][col - 1]) && matrix[row][col - 1] != '.') return true;
            else return !isDigit(matrix[row + 1][col]) && matrix[row + 1][col] != '.';
        } else if (row == size - 1 && col == 0) {
            if (!isDigit(matrix[row - 1][col + 1]) && matrix[row - 1][col + 1] != '.') return true;
            else if (!isDigit(matrix[row][col + 1]) && matrix[row][col + 1] != '.') return true;
            else return !isDigit(matrix[row - 1][col]) && matrix[row - 1][col] != '.';
        } else if (row == size - 1 && col == size - 1) {
            if (!isDigit(matrix[row - 1][col - 1]) && matrix[row - 1][col - 1] != '.') return true;
            else if (!isDigit(matrix[row][col - 1]) && matrix[row][col - 1] != '.') return true;
            else return !isDigit(matrix[row - 1][col]) && matrix[row - 1][col] != '.';
        } else if (row == 0 && col != size - 1) {
            if (!isDigit(matrix[row][col + 1]) && matrix[row][col + 1] != '.') return true;
            else if (!isDigit(matrix[row][col - 1]) && matrix[row][col - 1] != '.') return true;
            else if (!isDigit(matrix[row + 1][col]) && matrix[row + 1][col] != '.') return true;
            else if (!isDigit(matrix[row + 1][col + 1]) && matrix[row + 1][col + 1] != '.') return true;
            else return !isDigit(matrix[row + 1][col - 1]) && matrix[row + 1][col - 1] != '.';
        } else if (col == 0) {
            if (!isDigit(matrix[row][col + 1]) && matrix[row][col + 1] != '.') return true;
            else if (!isDigit(matrix[row - 1][col]) && matrix[row - 1][col] != '.') return true;
            else if (!isDigit(matrix[row + 1][col]) && matrix[row + 1][col] != '.') return true;
            else if (!isDigit(matrix[row + 1][col + 1]) && matrix[row + 1][col + 1] != '.') return true;
            else return !isDigit(matrix[row - 1][col + 1]) && matrix[row - 1][col + 1] != '.';
        } else if (row == size - 1) {
            if (!isDigit(matrix[row][col + 1]) && matrix[row][col + 1] != '.') return true;
            else if (!isDigit(matrix[row][col - 1]) && matrix[row][col - 1] != '.') return true;
            else if (!isDigit(matrix[row - 1][col]) && matrix[row - 1][col] != '.') return true;
            else if (!isDigit(matrix[row - 1][col + 1]) && matrix[row - 1][col + 1] != '.') return true;
            else return !isDigit(matrix[row - 1][col - 1]) && matrix[row - 1][col - 1] != '.';
        } else if (col == size - 1 && row != 0) {
            if (!isDigit(matrix[row][col - 1]) && matrix[row][col - 1] != '.') return true;
            else if (!isDigit(matrix[row + 1][col - 1]) && matrix[row + 1][col - 1] != '.') return true;
            else if (!isDigit(matrix[row - 1][col - 1]) && matrix[row - 1][col - 1] != '.') return true;
            else if (!isDigit(matrix[row - 1][col]) && matrix[row - 1][col] != '.') return true;
            else return !isDigit(matrix[row + 1][col]) && matrix[row + 1][col] != '.';
        }


        return false;
    }

    private static void part2() throws IOException {
        List<String> lines = readAllLines(Path.of(FILENAME));
        int number_of_rows = lines.size();
        int number_of_columns = lines.get(0).length();
        char[][] matrix = new char[number_of_rows][number_of_columns];
        int i = 0;
        for (String line : lines) {
            matrix[i++] = line.toCharArray();
        }
        Map<String, List<Integer>> gears_and_part = new HashMap<>();
        for (int row = 0; row < number_of_rows; row++) {
            String is_surrounded_by_gear = null;
            String part = "";
            for (int col = 0; col < number_of_columns; col++) {
                if (isDigit(matrix[row][col])) {
                    part += matrix[row][col];
                    is_surrounded_by_gear = is_surrounded_by_gear != null ? is_surrounded_by_gear : isSurroundedByGear(matrix, row, col, number_of_rows);
                } else if (!part.isEmpty() && is_surrounded_by_gear != null) {
                    System.out.println(part);
                    List<Integer> parts = gears_and_part.getOrDefault(is_surrounded_by_gear, new ArrayList<>());
                    parts.add(Integer.parseInt(part));
                    gears_and_part.put(is_surrounded_by_gear, parts);
                    part = ""; //reset
                    is_surrounded_by_gear = null;
                } else if (!part.isEmpty()) {
                    part = "";
                }
            }

            // last col
            if (!part.isEmpty() && is_surrounded_by_gear != null) {
                System.out.println(part);
                List<Integer> parts = gears_and_part.getOrDefault(is_surrounded_by_gear, new ArrayList<>());
                parts.add(Integer.parseInt(part));
                gears_and_part.put(is_surrounded_by_gear, parts);
            }

        }

        long sum = 0;
        for (List<Integer> s : gears_and_part.values()) {
            if (s.size() == 2) sum += s.get(0) * s.get(1);
        }
        System.out.println("total gears= " + gears_and_part);
        System.out.println("sum= " + sum);

    }

    private static String isSurroundedByGear(char[][] matrix, int row, int col, int size) {
        if (row != 0 && col != 0 && row != size - 1 && col != size - 1) {
            if (matrix[row + 1][col + 1] == '*') return (row + 1) + "," + (col + 1);
            else if (matrix[row - 1][col - 1] == '*') return (row - 1) + "," + (col - 1);
            else if (matrix[row - 1][col + 1] == '*') return (row - 1) + "," + (col + 1);
            else if (matrix[row + 1][col - 1] == '*') return (row + 1) + "," + (col - 1);
            else if (matrix[row][col - 1] == '*') return row + "," + (col - 1);
            else if (matrix[row][col + 1] == '*') return row + "," + (col + 1);
            else if (matrix[row - 1][col] == '*') return (row - 1) + "," + col;
            else if (matrix[row + 1][col] == '*') return (row + 1) + "," + col;
        } else if (row == 0 && col == 0) {
            if (matrix[row + 1][col + 1] == '*') return (row + 1) + "," + (col + 1);
            else if (matrix[row][col + 1] == '*') return row + "," + (col + 1);
            else if (matrix[row + 1][col] == '*') return (row + 1) + "," + col;
        } else if (row == 0 && col == size - 1) {
            if (matrix[row + 1][col - 1] == '*') return (row + 1) + "," + (col - 1);
            else if (matrix[row][col - 1] == '*') return row + "," + (col - 1);
            else if (matrix[row + 1][col] == '*') return (row + 1) + "," + col;
        } else if (row == size - 1 && col == 0) {
            if (matrix[row - 1][col + 1] == '*') return (row - 1) + "," + (col + 1);
            else if (matrix[row][col + 1] == '*') return row + "," + (col + 1);
            else if (matrix[row - 1][col] == '*') return (row - 1) + "," + col;
        } else if (row == size - 1 && col == size - 1) {
            if (matrix[row - 1][col - 1] == '*') return (row - 1) + "," + (col - 1);
            else if (matrix[row][col - 1] == '*') return row + "," + (col - 1);
            else if (matrix[row - 1][col] == '*') return (row - 1) + "," + col;
        } else if (row == 0 && col != size - 1) {
            if (matrix[row][col + 1] == '*') return row + "," + (col + 1);
            else if (matrix[row][col - 1] == '*') return row + "," + (col - 1);
            else if (matrix[row + 1][col] == '*') return (row + 1) + "," + col;
            else if (matrix[row + 1][col + 1] == '*') return (row + 1) + "," + (col + 1);
            else if (matrix[row + 1][col - 1] == '*') return (row + 1) + "," + col;
        } else if (col == 0) {
            if (matrix[row][col + 1] == '*') return row + "," + col;
            else if (matrix[row - 1][col] == '*') return (row - 1) + "," + col;
            else if (matrix[row + 1][col] == '*') return (row + 1) + "," + col;
            else if (matrix[row + 1][col + 1] == '*') return (row + 1) + "," + (col + 1);
            else if (matrix[row - 1][col + 1] == '*') return (row - 1) + "," + (col + 1);
        } else if (row == size - 1) {
            if (matrix[row][col + 1] == '*') return row + "," + col;
            else if (matrix[row][col - 1] == '*') return row + "," + (col - 1);
            else if (matrix[row - 1][col] == '*') return (row - 1) + "," + col;
            else if (matrix[row - 1][col + 1] == '*') return (row - 1) + "," + (col + 1);
            else if (matrix[row - 1][col - 1] == '*') return (row - 1) + "," + (col - 1);
        } else if (col == size - 1 && row != 0) {
            if (matrix[row][col - 1] == '*') return row + "," + col;
            else if (matrix[row + 1][col - 1] == '*') return (row + 1) + "," + (col - 1);
            else if (matrix[row - 1][col - 1] == '*') return (row - 1) + "," + (col - 1);
            else if (matrix[row - 1][col] == '*') return (row - 1) + "," + col;
            else if (matrix[row + 1][col] == '*') return (row + 1) + "," + col;
        }

        return null;
    }


}
