package day4;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.file.Files.readAllLines;

public class Main {
    private final static String FILENAME = "src/" + Main.class.getPackageName() + "/input.txt";

    public static void main(String[] args) throws IOException {
        //part1();
        part2();
    }

    private static void part1() throws IOException {
        List<String> lines = readAllLines(Path.of(FILENAME));
        int sum = 0;
        for (String line : lines) {
            int matches = 0;
            String[] cardNumbers = line.split(":")[1].split("\\|");
            for (String winningNumber : cardNumbers[0].trim().split(" ")) {
                for (String userCard : cardNumbers[1].trim().split(" ")) {
                    if (!winningNumber.isBlank() && !userCard.isBlank() && winningNumber.equals(userCard)) {
                        matches = matches == 0 ? 1 : matches << 1;
                    }
                }
            }
            sum += matches;
        }
        System.out.println(sum);
    }

    static Map<Integer, Integer> cardIdCopies = new HashMap<>();

    /**
     * not optimized, very slow
     */
    private static void part2() throws IOException {
        List<String> lines = readAllLines(Path.of(FILENAME));
        buildFirstSet(lines);
        for (String line : lines) {
            System.out.println(line);
            String card = line.split(":")[0];
            int cardId = Integer.parseInt(card.replace("Card ", "").trim());
            for (int copy = 0; copy < cardIdCopies.get(cardId); copy++) {
                int matches = 0;
                String[] cardNumbers = line.split(":")[1].split("\\|");
                for (String winningNumber : cardNumbers[0].trim().split(" ")) {
                    for (String userCard : cardNumbers[1].trim().split(" ")) {
                        if (!winningNumber.isBlank() && !userCard.isBlank() && winningNumber.equals(userCard)) {
                            matches++;
                        }
                    }
                }
                for (int i = 1; i <= matches; i++) {
                    Integer count = cardIdCopies.get(cardId + i);
                    cardIdCopies.put(cardId + i, count + 1);
                }
            }
        }
        System.out.println(cardIdCopies.values().stream().reduce(0, Integer::sum));
    }

    private static void buildFirstSet(List<String> lines) {
        for (String line : lines) {
            String cardId = line.split(":")[0].replace("Card ", "");
            cardIdCopies.put(Integer.parseInt(cardId.trim()), 1);
        }
    }


}
