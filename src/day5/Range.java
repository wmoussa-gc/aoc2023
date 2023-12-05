package day5;

public class Range {
    private long source = 0;
    private long destination = 0;
    private long range = 0;

    private final Type type;

    public enum Type {
        SEED_TO_SOIL,
        SOIL_TO_FERTILIZER,
        FERTILIZER_TO_WATER,
        WATER_TO_LIGHT,
        LIGHT_TO_TEMPERATURE,
        TEMPERATURE_TO_HUMIDITY,
        HUMIDITY_TO_LOCATION;
    }

    private long difference = 0;

    public Range(long source, long destination, long range, Type type) {
        this.source = source;
        this.destination = destination;
        this.range = range;
        this.difference = Math.abs(this.source - this.destination);
        this.type = type;
    }

    public long getMappingValueFor(long number) {
        if (number >= source && number < source + range) {
            return source > destination ? number - difference : number + difference;
        }
        return number;
    }

    public Type getType() {
        return type;
    }
}
