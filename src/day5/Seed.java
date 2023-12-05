package day5;

import java.util.List;

public record Seed(long seedNumber) {

    public long converter(final long value, List<Range> rangeToUse) {
        for (Range range : rangeToUse) {
            long tmp = range.getMappingValueFor(value);
            if (tmp != value) return tmp;
        }
        return value;
    }
}
