package day2;

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
        String fileName = "src/day2/input.txt";
        List<String> lines = Files.readAllLines(Path.of(fileName));
        for (String line : lines) {

        }
    }

    private static void part2() throws IOException {
        String fileName = "src/day2/input.txt";
        List<String> lines = Files.readAllLines(Path.of(fileName));
        for (String line : lines) {

        }
    }

    private static boolean isNumeric(String str) {
        return str != null && str.matches("[0-9.]+");
    }

}
