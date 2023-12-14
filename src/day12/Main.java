package day12;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class Main {
    private final static String FILENAME = "src/" + Main.class.getPackageName() + "/sample.txt";

    public static void main(String[] args) throws IOException {
        part1();
        //part2();
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

    /**
     * count arrangements with recursion
     * @param holder a hashmap to store the results of the recursive calls
     * @param spring a full line in the input file
     * @param record_index the index of the current spring record status (the map)
     * @param group_index the index of the current contiguous group
     * @param group_tracker the number of records in the current contiguous group
     * @return the number of arrangements
     */
    private static long countArrangements(HashMap<String, Long> holder, Spring spring, int record_index, int group_index, int group_tracker) {
        var key = String.join("-", record_index + "", group_index + "", group_tracker + "");
        if (holder.containsKey(key)) {
            return holder.get(key);
        }
        if (record_index == spring.map().length()) {
            return (group_index == spring.groupsList().size() && group_tracker == 0) // if we are at the end of the map and we have used all the groups
                    || (group_index == (spring.groupsList().size() - 1) && spring.groupsList().get(group_index) == group_tracker) ? 1 : 0;
        }
        long total = 0;
        char c = spring.map().charAt(record_index);

        if ((c == '.' || c == '?') && group_tracker == 0) {
            // move on to the next record
            total += countArrangements(holder, spring, record_index + 1, group_index, 0);
        } else if ((c == '.' || c == '?')
                && group_tracker > 0
                && group_index < spring.groupsList().size()
                && spring.groupsList().get(group_index) == group_tracker) {
            // if we are in a group, we can either skip this record or end the group
            total += countArrangements(holder, spring, record_index + 1, group_index + 1, 0);
        }

        if (c == '#' || c == '?') {
            // start a new group
            total += countArrangements(holder, spring, record_index + 1, group_index, group_tracker + 1);
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
