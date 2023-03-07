import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class countingWordsWithStreamAPI {

    public static void main(String[] args) {

        new Scanner(System.in).useDelimiter("(?U)[^\\p{L}\\p{Digit}]+")
                .tokens()
                .map(String::toLowerCase)
                .collect(Collectors.toMap(word -> word, word -> 1, Integer::sum))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue()
                        .reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new))
                .entrySet()
                .stream()
                .limit(10)
                .forEach(k -> System.out.println(k.getKey()));

    }
}
