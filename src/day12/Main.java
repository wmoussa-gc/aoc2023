package day12;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class Main {
    private final static String FILENAME = "src/" + Main.class.getPackageName() + "/input.txt";

    public static void main(String[] args) throws IOException {
        part1();
        part2();
    }

    private static void part1() throws IOException {
        List<String> lines = readAllLines(Path.of(FILENAME));
        long sum = lines.stream().map(Main::readString).mapToLong(spring ->
                countArrangements(new HashMap<>(), spring, 0, 0, 0)

        ).sum();
        System.out.println(sum);
    }

    private static void part2() throws IOException {
        List<String> lines = readAllLines(Path.of(FILENAME));
        long sum = lines.stream().map(Main::readString).mapToLong(spring ->
                countArrangements(new HashMap<>(), spring.copyForPart2(), 0, 0, 0)

        ).sum();
        System.out.println(sum);
    }

    private static long countArrangements(HashMap<String, Long> holder, Spring spring, int record_index, int j, int cur) {
        var key = String.join("-", record_index + "", j + "", cur + "");
        if (holder.containsKey(key)) {
            return holder.get(key);
        }
        if (record_index == spring.map().length()) {
            return (j == spring.groupsList().size() && cur == 0) || (j == (spring.groupsList().size() - 1) && spring.groupsList().get(j) == cur) ? 1 : 0;
        }
        long total = 0;
        char c = spring.map().charAt(record_index);

        if ((c == '.' || c == '?') && cur == 0) {
            total += countArrangements(holder, spring, record_index + 1, j, 0);
        } else if ((c == '.' || c == '?') && cur > 0 && j < spring.groupsList().size() && spring.groupsList().get(j) == cur) {
            total += countArrangements(holder, spring, record_index + 1, j + 1, 0);
        }

        if (c == '#' || c == '?') {
            total += countArrangements(holder, spring, record_index + 1, j, cur + 1);
        }
        holder.put(key, total);
        return total;
    }

    private static Spring readString(String s) {
        String[] split = s.split(" ");
        String map = split[0];
        String[] groups = split[1].split(",");
        List<Integer> groupsList = new ArrayList<>();
        for (String group : groups) {
            groupsList.add(Integer.parseInt(group));
        }
        return new Spring(map, groupsList);
    }


}
