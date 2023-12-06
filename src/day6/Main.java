package day6;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.Files.readAllLines;

public class Main {
    private final static String FILENAME = "src/" + Main.class.getPackageName() + "/input.txt";

    public static void main(String[] args) throws IOException {
        part1();
        //part2();
    }

    private static void part1() throws IOException {
        List<String> lines = readAllLines(Path.of(FILENAME));
        String[] timesStr = getNumbers(lines.get(0).split(":")[1]);
        String[] distanceStr = getNumbers(lines.get(1).split(":")[1]);

        List<String> times = new ArrayList<>(Arrays.asList(timesStr));
        times.removeAll(Arrays.asList("", null));

        List<String> distances = new ArrayList<>(Arrays.asList(distanceStr));
        distances.removeAll(Arrays.asList("", null));

        int result = 1;
        for (int i = 0; i < times.size(); i++) {
            int race_time = Integer.parseInt(times.get(i));
            int distance_to_beat = Integer.parseInt(distances.get(i));
            int hold = 0, record_beaten = 0;
            while (hold < race_time) {
                if (hold * (race_time - hold) > distance_to_beat) record_beaten++;
                hold++;
            }
            result *= record_beaten;
        }
        System.out.println(result);
    }

    private static String[] getNumbers(String input) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);
        List<String> numbers = new ArrayList<>();
        while (matcher.find()) {
            String number = matcher.group();
            numbers.add(number);
        }
        return numbers.toArray(new String[0]);
    }

    private static void part2() throws IOException {
        List<String> lines = readAllLines(Path.of(FILENAME));
        String timesStr = lines.get(0).split(":")[1].replaceAll("[^0-9]", "");
        String distanceStr = lines.get(1).split(":")[1].replaceAll("[^0-9]", "");

        long race_time = Long.parseLong(timesStr);
        long distance_to_beat = Long.parseLong(distanceStr);

        long hold = 0, record_beaten = 0;
        while (hold < race_time) {
            if (hold * (race_time - hold) > distance_to_beat) record_beaten++;
            hold++;
        }
        System.out.println(record_beaten);
    }

}
