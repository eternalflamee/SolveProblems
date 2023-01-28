//Task: "Write a static Ascii Char Sequence class that implements compact storage of a sequence
//of ASCII characters(their codes fit into one byte)in an array of bytes. Compared to the String class from Java 8,
//storing each character as a char, Ascii Char Sequence will take up half as much memory."

//Solving:

import java.util.Arrays;

public class AsciiCharSequence implements CharSequence {
    public byte[] bytes;

    public AsciiCharSequence(byte[] bytes) {
        this.bytes = bytes.clone();
    }

    @Override
    public char charAt(int index) {
        return (char) bytes[index];
    }

    @Override
    public int length() {
        return bytes.length;
    }

    @Override
    public AsciiCharSequence subSequence(int start, int end) {
        return new AsciiCharSequence(Arrays.copyOfRange(bytes, start, end));
    }

    @Override
    public String toString() {
        return new String(bytes);
    }

}

