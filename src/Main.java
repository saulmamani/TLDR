import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        sortByValue(
                        read(
                                new Scanner(new File("words.txt")),
                                new Scanner(new File("words.txt")),
                                new HashMap<>()
                            )
                    ).forEach((k, v)-> System.out.printf("%s %d\n", k, v));
    }

    private static Map<String,Integer> read(Scanner input, Scanner originalInput, Map<String,Integer> words) throws FileNotFoundException {
        if (input.hasNext()) {
            words.put(originalInput.next(), countWord(input.next(), new Scanner(new File("words.txt"))));
            return read(input, originalInput, words);
        }
        return words;
    }

    private static int countWord(String word, Scanner input) {
        if (input.hasNext()) {
            return (word.equals(input.next()) ? 1 : 0) + countWord(word, input);
        } else {
            return 0;
        }
    }

    public static Map<String, Integer> sortByValue(final Map<String, Integer> wordCounts) {
        return wordCounts.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
