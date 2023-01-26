//Task: "Write a static Ascii Char Sequence class that implements compact storage of a sequence
//of ASCII characters(their codes fit into one byte)in an array of bytes. Compared to the String class from Java 8,
//storing each character as a char, Ascii Char Sequence will take up half as much memory."

//Solving:

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public static class AsciiCharSequence implements CharSequence {
        //Твой код здесь
        public byte[] bytes;

        public AsciiCharSequence(byte[] bytes) {
            this.bytes = bytes.clone();
        }

        public char charAt(int index) {
            return (char) bytes[index];
        }

        public int length() {
            return bytes.length;
        }

        public AsciiCharSequence subSequence(int start, int end) {
            byte[] sub = new byte[end - start];
            int j = 0;
            for (int i = start; i < end; i++) {
                sub[j] = bytes[i];
                j++;
            }
            return new AsciiCharSequence(sub);
        }

        public String toString() {
            return new String(bytes);
        }

    }

}