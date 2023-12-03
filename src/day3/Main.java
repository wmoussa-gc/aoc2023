package day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        part1();
        //part2();
    }

    private static void part1() throws IOException {
        String fileName = "src/day3/sample.txt";
        List<String> lines = Files.readAllLines(Path.of(fileName));

        for (String line : lines) {

        }
    }

    private static void part2() throws IOException {
        String fileName = "src/day2/input.txt";
        List<String> lines = Files.readAllLines(Path.of(fileName));
        int sum = 0;
        for (String line : lines) {
            String[] reveals = line.split(":")[1].split(";");
            int min_green = 0;
            int min_red = 0;
            int min_blue = 0;
            for (String reveal : reveals) {
                String[] balls = reveal.split(",");
                for (String ball : balls) {
                    String[] temp = ball.trim().split(" ");
                    int x = Integer.parseInt(temp[0]);
                    String color = temp[1];
                    if (color.equals("red") && x > min_red) min_red = x;
                    if (color.equals("green") && x > min_green) min_green = x;
                    if (color.equals("blue") && x > min_blue) min_blue = x;
                }
            }
            sum += min_green * min_blue * min_red;
        }
        System.out.println(sum);
    }

}
