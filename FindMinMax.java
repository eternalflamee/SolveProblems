/*
Напишите метод, находящий в стриме минимальный и максимальный элементы в соответствии порядком, заданным Comparator'ом.

    Найденные минимальный и максимальный элементы передайте в minMaxConsumer следующим образом:

        minMaxConsumer.accept(min, max);

    Если стрим не содержит элементов, то вызовите:

        minMaxConsumer.accept(null, null);
*/


import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class FindMinMax {

    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(123,54325,456,567,768,7689,78,35,132);
        Comparator<? super Integer> comparator = Integer::compare;
        BiConsumer<? super Integer, ? super Integer> biConsumer = (x1, x2) -> System.out.println("min: " + x1 + "\nmax: " + x2);
        findMinMax(stream, comparator, biConsumer);
    }

    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {
        List<? extends T> list = stream.toList();
        minMaxConsumer.accept(list.stream().min(order).orElse(null), list.stream().max(order).orElse(null));
    }


}
