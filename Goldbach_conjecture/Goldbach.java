import javax.swing.JOptionPane;

public class Goldbach {

    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Enter an even number greater than two");
        int evenNumber = Integer.parseInt(input);

        if (evenNumber % 2 != 0 || evenNumber <= 2) {
            JOptionPane.showMessageDialog(null, "Enter an even number greater than two");
            return;
        }

        JOptionPane.showMessageDialog(null, findPrimePairs(evenNumber));
    }

    private static String findPrimePairs(int evenNumber) {
        String primePairs = "";

        for (int first = 2; first <= evenNumber / 2; first++) {
            int second = evenNumber - first;

            if (isPrime(first) && isPrime(second)) {
                primePairs += first + " + " + second + "\n";
            }
        }

        return primePairs;
    }

    private static boolean isPrime(int number) {
        for (int divisor = 2; divisor * divisor <= number; divisor++) {
            if (number % divisor == 0) {
                return false;
            }
        }

        return number >= 2;
    }
}
