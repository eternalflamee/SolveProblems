//The ComplexNumber class is given. Redefine the equals() and hashCode() methods in it
// so that equals() compares ComplexNumber instances by the contents of the re and im fields,
// and hashCode() would be consistent with the implementation of equals().
//An implementation of hashCode() that returns a constant or does not take into account
// the fractional part of re and im will not be counted.


public class EqualsAndHashcode {

    public static void main(String[] args) {

    }

    public static class ComplexNumber {
        private double re;
        private double im;

        public ComplexNumber() {
        }

        public ComplexNumber(double re, double im) {
            this.re = re;
            this.im = im;
        }

        public double getRe() {
            return re;
        }

        public double getIm() {
            return im;
        }


        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }

            if (o == this) {
                return true;
            }

            if (getClass() != o.getClass()) {
                return false;
            }
            return Double.compare(this.re, ((ComplexNumber) o).re) == 0 && Double.compare(this.im, ((ComplexNumber) o).im) == 0;
        }


        public int hashCode() {
            return java.util.Objects.hash(re, im);
        }

    }
}
