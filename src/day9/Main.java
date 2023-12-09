package day9;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllLines;
import static java.util.Arrays.stream;

public class Main {
    private final static String FILENAME = "src/" + Main.class.getPackageName() + "/input.txt";

    public static void main(String[] args) throws IOException {
        part1();
        part2();
    }

    private static void part1() throws IOException {
        List<String> lines = readAllLines(Path.of(FILENAME));
        long sum = 0;
        for (String line : lines) {
            for (List<Integer> integers : buildSeries(line)) {
                sum += integers.getLast();
            }
        }
        System.out.println(sum);
    }

    private static void part2() throws IOException {
        List<String> lines = readAllLines(Path.of(FILENAME));

        long sum = 0;
        for (String line : lines) {
            List<List<Integer>> series = buildSeries(line);

            long tempSum = 0;
            for (int i = series.size() - 1; i > 0; i--) {
                tempSum = series.get(i - 1).getFirst() - tempSum;
            }
            sum += tempSum;
        }

        System.out.println(sum);
    }

    private static List<List<Integer>> buildSeries(String line) {
        List<Integer> numbers = stream(line.split(" ")).map(Integer::parseInt).toList();

        List<List<Integer>> series = new ArrayList<>();
        List<Integer> next = numbers.stream().toList();

        series.add(next);

        while (next.stream().mapToInt(Integer::intValue).sum() != 0) {
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < next.size() - 1; i++) {
                int difference = next.get(i + 1) - next.get(i);
                temp.add(difference);
            }
            next = temp.stream().toList();
            series.add(next);
        }
        return series;
    }


}
