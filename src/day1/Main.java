package day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        part1();
        part2_alter();
    }

    private static void part1() throws IOException {
        String fileName = "src/day1/input.txt";
        List<String> lines = Files.readAllLines(Path.of(fileName));
        int total = 0;
        for (String line : lines) {
            List<Integer> number = new ArrayList<>();
            for (String character : line.split("")) {
                if (isNumeric(character)) {
                    number.add(Integer.parseInt(character));
                }
            }
            total += number.get(0) * 10 + number.get(number.size() - 1);
        }
        System.out.println(total);
    }

    private static void part2() throws IOException {
        String fileName = "src/day1/input.txt";
        List<String> lines = Files.readAllLines(Path.of(fileName));
        int total = 0;
        for (String line : lines) {
            line = line.replaceAll("one", "one1one");
            line = line.replaceAll("two", "two2two");
            line = line.replaceAll("three", "three3three");
            line = line.replaceAll("four", "four4four");
            line = line.replaceAll("five", "five5five");
            line = line.replaceAll("six", "six6six");
            line = line.replaceAll("seven", "seven7seven");
            line = line.replaceAll("eight", "eight8eight");
            line = line.replaceAll("nine", "nine9nine");

            List<Integer> number = new ArrayList<>();
            for (String character : line.split("")) {
                if (isNumeric(character)) {
                    number.add(Integer.parseInt(character));
                }
            }
            int temp = number.get(0) * 10 + number.get(number.size() - 1);
            total += temp;
        }
        System.out.println(total);
    }

    /**
     * credit to <a href="https://github.com/vuryss/aoc-java/blob/master/src/main/java/com/vuryss/aoc/solutions/event2023/Day1.java">...</a>
     * @throws IOException
     */
    private static void part2_alter() throws IOException {
        var digitWords = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
        String fileName = "src/day1/input.txt";
        List<String> lines = Files.readAllLines(Path.of(fileName));
        long sum = 0L;

        for (var line: lines) {
            var numbers = new LinkedList<Integer>();

            for (int i = 0; i < line.length(); i++) {
                int index = i;
                Stream.of(line.charAt(i)).filter(Character::isDigit).forEach(c -> numbers.add(Character.getNumericValue(c)));
                digitWords.stream().filter(e -> line.startsWith(e, index)).forEach(e -> numbers.add(digitWords.indexOf(e) + 1));
            }

            sum += numbers.getFirst() * 10 + numbers.getLast();
        }

        System.out.println(sum);
    }

    private static boolean isNumeric(String str) {
        return str != null && str.matches("[0-9.]+");
    }

}
