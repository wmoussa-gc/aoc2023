package day5;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;
import static java.nio.file.Files.readAllLines;

public class Main {
    private final static String FILENAME = "src/" + Main.class.getPackageName() + "/input.txt";

    private static List<Range> seedToSoilRanges = new ArrayList<>();
    private static List<Range> soilToFertilizerRanges = new ArrayList<>();
    private static List<Range> fertilizerToWaterRanges = new ArrayList<>();
    private static List<Range> waterToLightRanges = new ArrayList<>();
    private static List<Range> lightToTemperatureRanges = new ArrayList<>();
    private static List<Range> temperatureToHumidityRanges = new ArrayList<>();
    private static List<Range> humidityToLocationRanges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        part1();
        //part2();
    }

    private static void part1() throws IOException {
        Result result = getRanges();

        seedToSoilRanges = result.ranges.stream().filter(r -> r.getType() == Range.Type.SEED_TO_SOIL).toList();
        soilToFertilizerRanges = result.ranges.stream().filter(r -> r.getType() == Range.Type.SOIL_TO_FERTILIZER).toList();
        fertilizerToWaterRanges = result.ranges.stream().filter(r -> r.getType() == Range.Type.FERTILIZER_TO_WATER).toList();
        waterToLightRanges = result.ranges.stream().filter(r -> r.getType() == Range.Type.WATER_TO_LIGHT).toList();
        lightToTemperatureRanges = result.ranges.stream().filter(r -> r.getType() == Range.Type.LIGHT_TO_TEMPERATURE).toList();
        temperatureToHumidityRanges = result.ranges.stream().filter(r -> r.getType() == Range.Type.TEMPERATURE_TO_HUMIDITY).toList();
        humidityToLocationRanges = result.ranges.stream().filter(r -> r.getType() == Range.Type.HUMIDITY_TO_LOCATION).toList();

        List<Seed> seeds = new ArrayList<>();
        for (String seedStr : result.lines().get(0).split(":")[1].trim().split(" ")) {
            if (!seedStr.isBlank()) {
                Seed seed = new Seed(parseLong(seedStr));
                seeds.add(seed);
            }
        }

        long min_location = Long.MAX_VALUE;
        for (Seed seed : seeds) {
            long location = getLocation(seed.seedNumber());
            if (location < min_location) min_location = location;
        }

        System.out.println(min_location);

    }

    private static void part2() throws IOException {
        Result result = getRanges();

        seedToSoilRanges = result.ranges.stream().filter(r -> r.getType() == Range.Type.SEED_TO_SOIL).toList();
        soilToFertilizerRanges = result.ranges.stream().filter(r -> r.getType() == Range.Type.SOIL_TO_FERTILIZER).toList();
        fertilizerToWaterRanges = result.ranges.stream().filter(r -> r.getType() == Range.Type.FERTILIZER_TO_WATER).toList();
        waterToLightRanges = result.ranges.stream().filter(r -> r.getType() == Range.Type.WATER_TO_LIGHT).toList();
        lightToTemperatureRanges = result.ranges.stream().filter(r -> r.getType() == Range.Type.LIGHT_TO_TEMPERATURE).toList();
        temperatureToHumidityRanges = result.ranges.stream().filter(r -> r.getType() == Range.Type.TEMPERATURE_TO_HUMIDITY).toList();
        humidityToLocationRanges = result.ranges.stream().filter(r -> r.getType() == Range.Type.HUMIDITY_TO_LOCATION).toList();

        String seedPairs = result.lines().get(0).split(":")[1].trim();
        String[] pairs = seedPairs.split("(?<!\\G\\d+)\\s");
        long min_location = Long.MAX_VALUE;
        for (String pair : pairs) {
            System.out.println(pair);
            String[] rangePair = pair.split(" ");
            long seedStart = Long.parseLong(rangePair[0]);
            long range = Long.parseLong(rangePair[1]);
            for (long i = seedStart; i < seedStart + range; i++) {
                long location = getLocation(i);
                if (location < min_location) min_location = location;
            }
        }

        System.out.println(min_location);

    }

    private static long getLocation(long seedId) {
        Seed seed = new Seed(seedId);
        long soil = seed.converter(seed.seedNumber(), seedToSoilRanges);
        long fertilizer = seed.converter(soil, soilToFertilizerRanges);
        long water = seed.converter(fertilizer, fertilizerToWaterRanges);
        long light = seed.converter(water, waterToLightRanges);
        long temperature = seed.converter(light, lightToTemperatureRanges);
        long humidity = seed.converter(temperature, temperatureToHumidityRanges);
        return seed.converter(humidity, humidityToLocationRanges);
    }

    private static Result getRanges() throws IOException {
        List<String> lines = readAllLines(Path.of(FILENAME));

        List<Range> ranges = new ArrayList<>();
        boolean seedToSoil = false, soilToFertilizer = false, fertilizerToWater = false, waterToLight = false, lightToTemperature = false, temperatureToHumidity = false, humidityToLocation = false;
        for (String rest : lines.subList(1, lines.size())) {
            if (rest.isBlank()) continue;
            if (rest.equals("seed-to-soil map:")) {
                seedToSoil = true;
                continue;
            }
            if (rest.equals("soil-to-fertilizer map:")) {
                seedToSoil = false;
                soilToFertilizer = true;
                continue;
            }

            if (rest.equals("fertilizer-to-water map:")) {
                soilToFertilizer = false;
                fertilizerToWater = true;
                continue;
            }

            if (rest.equals("water-to-light map:")) {
                fertilizerToWater = false;
                waterToLight = true;
                continue;
            }

            if (rest.equals("light-to-temperature map:")) {
                waterToLight = false;
                lightToTemperature = true;
                continue;
            }

            if (rest.equals("temperature-to-humidity map:")) {
                lightToTemperature = false;
                temperatureToHumidity = true;
                continue;
            }

            if (rest.equals("humidity-to-location map:")) {
                temperatureToHumidity = false;
                humidityToLocation = true;
                continue;
            }

            if (seedToSoil) {
                String[] values = rest.trim().split(" ");
                ranges.add(new Range(parseLong(values[1]), parseLong(values[0]), parseLong(values[2]), Range.Type.SEED_TO_SOIL));
            }

            if (soilToFertilizer) {
                String[] values = rest.trim().split(" ");
                ranges.add(new Range(parseLong(values[1]), parseLong(values[0]), parseLong(values[2]), Range.Type.SOIL_TO_FERTILIZER));
            }

            if (fertilizerToWater) {
                String[] values = rest.trim().split(" ");
                ranges.add(new Range(parseLong(values[1]), parseLong(values[0]), parseLong(values[2]), Range.Type.FERTILIZER_TO_WATER));
            }

            if (waterToLight) {
                String[] values = rest.trim().split(" ");
                ranges.add(new Range(parseLong(values[1]), parseLong(values[0]), parseLong(values[2]), Range.Type.WATER_TO_LIGHT));
            }

            if (lightToTemperature) {
                String[] values = rest.trim().split(" ");
                ranges.add(new Range(parseLong(values[1]), parseLong(values[0]), parseLong(values[2]), Range.Type.LIGHT_TO_TEMPERATURE));
            }

            if (temperatureToHumidity) {
                String[] values = rest.trim().split(" ");
                ranges.add(new Range(parseLong(values[1]), parseLong(values[0]), parseLong(values[2]), Range.Type.TEMPERATURE_TO_HUMIDITY));
            }

            if (humidityToLocation) {
                String[] values = rest.trim().split(" ");
                ranges.add(new Range(parseLong(values[1]), parseLong(values[0]), parseLong(values[2]), Range.Type.HUMIDITY_TO_LOCATION));
            }
        }
        return new Result(lines, ranges);
    }

    private record Result(List<String> lines, List<Range> ranges) {
    }

}
