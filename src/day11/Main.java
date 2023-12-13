package day11;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class Main {
    private final static String FILENAME = "src/" + Main.class.getPackageName() + "/sample.txt";

    public static void main(String[] args) throws IOException {
        //part1();
        part2();
    }

    private static void part1() throws IOException {
        List<String> lines = readAllLines(Path.of(FILENAME));
        char[][] space = new char[lines.size() * 2][];

        int i = 0;
        for (String line : lines) {
            space[i++] = line.toCharArray();
            if (!line.contains("#")) space[i++] = line.toCharArray(); // expand row
        }

        char[][] transposed = transpose(space, i);

        space = new char[transposed.length * 2][]; // reset space
        i = 0;
        for (char[] chars : transposed) {
            space[i++] = chars;
            if (!String.valueOf(chars).contains("#")) space[i++] = chars; // expand row
        }

        space = transpose(space, i);

        List<Galaxy> galaxies = new ArrayList<>();
        for (int j = 0; j < space.length; j++) {
            for (int k = 0; k < space[0].length; k++) {
                if (space[j][k] == '#') {
                    galaxies.add(new Galaxy(galaxies.size(), j, k));
                }
            }
        }

        findPairs(galaxies);
    }

    public static int expansion_factor = 10 - 1;
    public static List<Integer> expansion_col = new ArrayList<>();
    public static List<Integer> expansion_row = new ArrayList<>();
    private static void part2() throws IOException {
        List<String> lines = readAllLines(Path.of(FILENAME));

        char[][] space = new char[lines.size()][];

        int i = 0;
        for (String line : lines) {
            space[i++] = line.toCharArray();
            if (!line.contains("#")) {
                expansion_row.add(i);
            }
        }

        char[][] transposed = transpose(space, i);

        space = new char[transposed.length][]; // reset space
        i = 0;
        for (char[] chars : transposed) {
            space[i++] = chars;
            if (!String.valueOf(chars).contains("#")) {
                expansion_col.add(i);
            }
        }

        space = transpose(space, i);

        List<Galaxy> galaxies = new ArrayList<>();
        for (int j = 0; j < space.length; j++) {
            for (int k = 0; k < space[0].length; k++) {
                if (space[j][k] == '#') {
                    galaxies.add(new Galaxy(galaxies.size(), j, k));
                }
            }
        }

        findPairs(galaxies);
    }


    private static void findPairs(List<Galaxy> galaxies) {
        long sum = 0;
        for (int i = 0; i < galaxies.size(); i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                sum += galaxies.get(i).distance(galaxies.get(j));
            }
        }
        System.out.println(sum);
    }

    private static char[][] transpose(char[][] space, int i) {
        char[][] transposed = new char[space[0].length][i];
        for (int j = 0; j < space[0].length; j++) {
            for (int k = 0; k < i; k++) {
                transposed[j][k] = space[k][j];
            }
        }
        return transposed;
    }


}
