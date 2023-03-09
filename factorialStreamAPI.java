/*
Релизуйте метод, вычисляющий факториал, с помощью Stream API.
В тестовую систему импортирован весь пакет java.util.stream

Пример ввода 1: 1
Пример возвращаемого значения 1: 1

Пример ввода 2: 3
Пример возвращаемого значения 2: 6
 */

import java.math.BigInteger;
import java.util.stream.*;
public class factorialStreamAPI {

    public static BigInteger factorial(int value) {
        if (value < 2) {
            return BigInteger.valueOf(1);
        } else {
            return IntStream.rangeClosed(2, value).mapToObj(BigInteger::valueOf).reduce(BigInteger::multiply).get();
        }
    }

}
