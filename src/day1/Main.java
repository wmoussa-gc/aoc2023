package day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        part1();
        part2();
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

    private static boolean isNumeric(String str) {
        return str != null && str.matches("[0-9.]+");
    }

}
