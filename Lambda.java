/*
Задание:
    Дан предикат condition и две функции ifTrue и ifFalse. Напишите метод ternaryOperator, который из них построит новую функцию, возвращающую значение функции ifTrue, если предикат выполнен, и значение ifFalse иначе.

    Рассмотрим пример использования метода. Его можно было все свернуть в одну строчку, но для наглядности все элементы вынесены в отдельные переменные.


    Predicate<Object> condition = Objects::isNull;
    Function<Object, Integer> ifTrue = obj -> 0;
    Function<CharSequence, Integer> ifFalse = CharSequence::length;
    Function<String, Integer> safeStringLength = ternaryOperator(condition, ifTrue, ifFalse);
    Результирующая функция будет для нулевых ссылок на String возвращать 0, а для ненулевых ссылок возвращать длину строки.

    Все импорты объявлены за вас.

    Требования:
    1. Должен быть метод public static <T, U> Function<T, U> ternaryOperator(Predicate<? super T> condition, Function<? super T, ? extends U> ifTrue, Function<? super T, ? extends U> ifFalse)
    2. Метод должен возвращать функцию
    3. Используйте лямбда-выражение
*/



import java.util.Objects;
import java.util.function.*;

public class Lambda {

    public static void main(String[] args) {
        Predicate<Object> condition = Objects::isNull;
        Function<Object, Integer> ifTrue = obj -> 0;
        Function<CharSequence, Integer> ifFalse = CharSequence::length;
        Function<String, Integer> safeStringLength = ternaryOperator(condition, ifTrue, ifFalse);
    }
    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse) {
        return (T t) -> (condition.test(t)) ? ifTrue.apply(t) : ifFalse.apply(t);
    }
}
