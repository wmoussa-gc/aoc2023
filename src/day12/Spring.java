package day12;

import java.util.Collections;
import java.util.List;

import static java.lang.String.join;

public record Spring(String map, List<Integer> groupsList) {
    public Spring copyForPart2() {
        return new Spring(join("?", map, map, map, map, map), Collections.nCopies(5, groupsList).stream().flatMap(List::stream).toList());
    }
}
