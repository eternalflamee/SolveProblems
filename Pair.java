//Implement a generic Pair class similar to Optional, but containing a pair of elements of different types
// and not prohibiting elements from taking the null value.

//Implement the getFirst(), getSecond(), equals() and hashCode() methods, and the static method of(). The constructor must be private.
//The following code should compile and work successfully with a correct Pair class:
//Pair<Integer, String> pair = Pair.of(1, "hello");
//Integer i = pair.getFirst(); // 1
//String s = pair.getSecond(); // "hello"
//Pair<Integer, String> pair2 = Pair.of(1, "hello");
//boolean mustBeTrue = pair.equals(pair2); // true!
//boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!

//Please do not change the access modifier of the Pair class. For correct validation, the class must have package visibility.


import java.util.Objects;


public class Pair<T, S> {
    private T value1;
    private S value2;

    private Pair(T value1, S value2) {
        this.value1 = value1;
        this.value2 = value2;
    }
    public static <T, S> Pair<T, S> of(T value1, S value2) {
        return new Pair<>(value1, value2);
    }

    public T getFirst() {
        return value1;
    }

    public S getSecond() {
        return value2;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(value1, pair.value1) && Objects.equals(value2, pair.value2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value1, value2);
    }
}
