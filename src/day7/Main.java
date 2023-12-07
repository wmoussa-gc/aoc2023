package day7;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.file.Files.readAllLines;

public class Main {
    private final static String FILENAME = "src/" + Main.class.getPackageName() + "/sample.txt";

    public static void main(String[] args) throws IOException {
        //part1();
        part2();
    }

    private static void part1() throws IOException {
        List<String> lines = readAllLines(Path.of(FILENAME));

        List<Hand> five_of_kind = new ArrayList<>();
        List<Hand> four_of_kind = new ArrayList<>();
        List<Hand> full_house = new ArrayList<>();
        List<Hand> three_of_kind = new ArrayList<>();
        List<Hand> two_pairs = new ArrayList<>();
        List<Hand> one_pair = new ArrayList<>();
        List<Hand> high_card = new ArrayList<>();

        for (String line : lines) {
            String card = line.split(" ")[0];
            Integer bid = Integer.parseInt(line.split(" ")[1]);
            Map<Character, Integer> count = new HashMap<>();
            for (char c : card.toCharArray()) {
                count.put(c, count.getOrDefault(c, 0) + 1);
            }
            if (isFiveOfKind(count)) {
                five_of_kind.add(new Hand(card, bid));
            } else if (isFourOfKind(count)) {
                four_of_kind.add(new Hand(card, bid));
            } else if (isFullHouse(count)) {
                full_house.add(new Hand(card, bid));
            } else if (isThreeOfKind(count)) {
                three_of_kind.add(new Hand(card, bid));
            } else if (isTwoPair(count)) {
                two_pairs.add(new Hand(card, bid));
            } else if (isOnePair(count)) {
                one_pair.add(new Hand(card, bid));
            } else if (isHighCard(count)) {
                high_card.add(new Hand(card, bid));
            }
        }
        List<Hand> sorted_five_of_kind = five_of_kind.stream().sorted(Main::compareHands).toList();
        List<Hand> sorted_four_of_kind = four_of_kind.stream().sorted(Main::compareHands).toList();
        List<Hand> sorted_full_house = full_house.stream().sorted(Main::compareHands).toList();
        List<Hand> sorted_three_of_kind = three_of_kind.stream().sorted(Main::compareHands).toList();
        List<Hand> sorted_two_pair = two_pairs.stream().sorted(Main::compareHands).toList();
        List<Hand> sorted_one_pair = one_pair.stream().sorted(Main::compareHands).toList();
        List<Hand> sorted_high_card = high_card.stream().sorted(Main::compareHands).toList();

        long rank = 1;
        long sum = 0;
        for (Hand hand : sorted_high_card) {
            System.out.printf("%s|%d|%s\n", hand.card, hand.bid, rank);

            sum += rank * hand.bid;
            rank++;
        }
        for (Hand hand : sorted_one_pair) {
            System.out.printf("%s|%d|%s\n", hand.card, hand.bid, rank);

            sum += rank * hand.bid;
            rank++;
        }
        for (Hand hand : sorted_two_pair) {
            System.out.printf("%s|%d|%s\n", hand.card, hand.bid, rank);

            sum += rank * hand.bid;
            rank++;
        }
        for (Hand hand : sorted_three_of_kind) {
            System.out.printf("%s|%d|%s\n", hand.card, hand.bid, rank);

            sum += rank * hand.bid;
            rank++;
        }
        for (Hand hand : sorted_full_house) {
            System.out.printf("%s|%d|%s\n", hand.card, hand.bid, rank);

            sum += rank * hand.bid;
            rank++;
        }
        for (Hand hand : sorted_four_of_kind) {
            System.out.printf("%s|%d|%s\n", hand.card, hand.bid, rank);

            sum += rank * hand.bid;
            rank++;
        }
        for (Hand hand : sorted_five_of_kind) {
            System.out.printf("%s|%d|%s\n", hand.card, hand.bid, rank);

            sum += rank * hand.bid;
            rank++;
        }
        System.out.println(sum);

    }

    /**
     * reverse order
     */
    private static int compareHands(Hand x, Hand y) {
        for (int i = 0; i < x.card.length(); i++) {
            if (x.card.charAt(i) != y.card.charAt(i)) {
                char x_c = x.card.charAt(i);
                char y_c = y.card.charAt(i);
                if (x_c == 'J') x_c = 'R'; // give it a weight between Q & T
                if (y_c == 'J') y_c = 'R'; // give it a weight between Q & T

                if (Character.isDigit(x_c) && !Character.isDigit(y_c)) {
                    return -1;
                }
                if (!Character.isDigit(x_c) && Character.isDigit(y_c)) {
                    return 1;
                }
                if (Character.isDigit(x_c) && Character.isDigit(y_c)) {
                    return x_c - y_c;
                }
                return y_c - x_c;
            }
        }
        return 0;
    }

    private static boolean isTwoPair(Map<Character, Integer> card) {
        return card.values().stream().filter(x -> x == 2).count() == 2;
    }

    private static boolean isOnePair(Map<Character, Integer> card) {
        return card.values().stream().anyMatch(x -> x == 2);
    }

    private static boolean isHighCard(Map<Character, Integer> card) {
        return card.values().stream().allMatch(x -> x == 1);
    }

    private static boolean isFullHouse(Map<Character, Integer> card) {
        return card.values().stream().anyMatch(x -> x == 3) && card.values().stream().anyMatch(x -> x == 2);
    }

    private static boolean isFiveOfKind(Map<Character, Integer> card) {
        return card.values().stream().anyMatch(x -> x == 5);
    }

    private static boolean isFourOfKind(Map<Character, Integer> card) {
        return card.values().stream().anyMatch(x -> x == 4);
    }

    private static boolean isThreeOfKind(Map<Character, Integer> card) {
        return card.values().stream().anyMatch(x -> x == 3);
    }

    private static record Hand(String card, Integer bid) {
    }


    private static void part2() throws IOException {
        List<String> lines = readAllLines(Path.of(FILENAME));

        List<Hand> five_of_kind = new ArrayList<>();
        List<Hand> four_of_kind = new ArrayList<>();
        List<Hand> full_house = new ArrayList<>();
        List<Hand> three_of_kind = new ArrayList<>();
        List<Hand> two_pairs = new ArrayList<>();
        List<Hand> one_pair = new ArrayList<>();
        List<Hand> high_card = new ArrayList<>();

        for (String line : lines) {
            String card = line.split(" ")[0];
            Integer bid = Integer.parseInt(line.split(" ")[1]);
            Map<Character, Integer> count = new HashMap<>();
            for (char c : card.toCharArray()) {
                count.put(c, count.getOrDefault(c, 0) + 1);
            }

            // J treatment
            if (count.get('J') != null) {
                Map.Entry<Character, Integer> topCard = count.entrySet().stream()
                        .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed()).toList().get(0);
                if (topCard.getKey() != 'J'){
                    count.put(topCard.getKey(), topCard.getValue() + count.get('J'));
                    count.put('J', 0);
                }
            }

            if (isFiveOfKind(count)) {
                five_of_kind.add(new Hand(card, bid));
            } else if (isFourOfKind(count)) {
                four_of_kind.add(new Hand(card, bid));
            } else if (isFullHouse(count)) {
                full_house.add(new Hand(card, bid));
            } else if (isThreeOfKind(count)) {
                three_of_kind.add(new Hand(card, bid));
            } else if (isTwoPair(count)) {
                two_pairs.add(new Hand(card, bid));
            } else if (isOnePair(count)) {
                one_pair.add(new Hand(card, bid));
            } else if (isHighCard(count)) {
                high_card.add(new Hand(card, bid));
            } else {
                System.out.println("ERROR " + card);
            }
        }
        List<Hand> sorted_five_of_kind = five_of_kind.stream().sorted(Main::compareHandsSpecial).toList();
        List<Hand> sorted_four_of_kind = four_of_kind.stream().sorted(Main::compareHandsSpecial).toList();
        List<Hand> sorted_full_house = full_house.stream().sorted(Main::compareHandsSpecial).toList();
        List<Hand> sorted_three_of_kind = three_of_kind.stream().sorted(Main::compareHandsSpecial).toList();
        List<Hand> sorted_two_pair = two_pairs.stream().sorted(Main::compareHandsSpecial).toList();
        List<Hand> sorted_one_pair = one_pair.stream().sorted(Main::compareHandsSpecial).toList();
        List<Hand> sorted_high_card = high_card.stream().sorted(Main::compareHandsSpecial).toList();

        long rank = 1;
        long sum = 0;
        for (Hand hand : sorted_high_card) {
            System.out.printf("%s|%d|%s|%s\n", hand.card, hand.bid, rank, "sorted_high_card");

            sum += rank * hand.bid;
            rank++;
        }
        for (Hand hand : sorted_one_pair) {
            System.out.printf("%s|%d|%s|%s\n", hand.card, hand.bid, rank, "sorted_one_pair");

            sum += rank * hand.bid;
            rank++;
        }
        for (Hand hand : sorted_two_pair) {
            System.out.printf("%s|%d|%s|%s\n", hand.card, hand.bid, rank, "sorted_two_pair");

            sum += rank * hand.bid;
            rank++;
        }
        for (Hand hand : sorted_three_of_kind) {
            System.out.printf("%s|%d|%s|%s\n", hand.card, hand.bid, rank, "sorted_three_of_kind");

            sum += rank * hand.bid;
            rank++;
        }
        for (Hand hand : sorted_full_house) {
            System.out.printf("%s|%d|%s|%s\n", hand.card, hand.bid, rank, "sorted_full_house");

            sum += rank * hand.bid;
            rank++;
        }
        for (Hand hand : sorted_four_of_kind) {
            System.out.printf("%s|%d|%s|%s\n", hand.card, hand.bid, rank, "sorted_four_of_kind");

            sum += rank * hand.bid;
            rank++;
        }
        for (Hand hand : sorted_five_of_kind) {
            System.out.printf("%s|%d|%s|%s\n", hand.card, hand.bid, rank, "sorted_five_of_kind");

            sum += rank * hand.bid;
            rank++;
        }
        System.out.println(sum);

    }

    private static int compareHandsSpecial(Hand x, Hand y) {
        for (int i = 0; i < x.card.length(); i++) {
            if (x.card.charAt(i) != y.card.charAt(i)) {
                char x_c = x.card.charAt(i);
                char y_c = y.card.charAt(i);
                if (x_c == 'J') x_c = '1'; // lowest weight
                if (y_c == 'J') y_c = '1'; // lowest weight

                if (Character.isDigit(x_c) && !Character.isDigit(y_c)) {
                    return -1;
                }
                if (!Character.isDigit(x_c) && Character.isDigit(y_c)) {
                    return 1;
                }
                if (Character.isDigit(x_c) && Character.isDigit(y_c)) {
                    return x_c - y_c;
                }
                return y_c - x_c;
            }
        }
        return 0;
    }

}

