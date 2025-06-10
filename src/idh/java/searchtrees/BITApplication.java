package idh.java.searchtrees;

public class BITApplication {

    public static void main(String[] args) {
        BinaryIntegerTree bit = new BinaryIntegerTree();

        // Baum aufbauen
        bit.addValue(5);
        bit.addValue(7);
        bit.addValue(9);
        bit.addValue(6);
        bit.addValue(2);
        bit.addValue(1);

        System.out.print("Initialer Baum: ");
        bit.printInOrder();

        System.out.println("Enthält 7? " + bit.contains(7)); // true

        System.out.println("Lösche 7...");
        bit.delete(7);
        System.out.println("Enthält 7? " + bit.contains(7)); // false

        System.out.println("Enthält 9? " + bit.contains(9)); // true

        System.out.println("Lösche 5...");
        bit.delete(5);
        System.out.println("Enthält 5? " + bit.contains(5)); // false

        // Finaler Baum
        System.out.print("Finaler Baum: ");
        bit.printInOrder();
    }
}
