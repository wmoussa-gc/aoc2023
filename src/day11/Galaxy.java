package day11;

import static day11.Main.*;

public record Galaxy(int id, int x, int y) {
    public long distance(Galaxy galaxy) {
        long x_t = Math.abs(x - galaxy.x), y_t = Math.abs(y - galaxy.y);
        if (expansion_col.contains(x) || expansion_col.contains(galaxy.x)) {
            x_t += expansion_factor;
        }
        if (expansion_row.contains(y) || expansion_row.contains(galaxy.y)) {
            y_t += expansion_factor;
        }
        return x_t + y_t;
    }
}