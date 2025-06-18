package idh.java.searchtrees;

public class BITApplication {

	public static void main(String[] args) {

		// Wie gehabt Binärbaum anlegen und füllen
		BinaryIntegerTree bit = new BinaryIntegerTree();
		bit.addValue(5);
		bit.addValue(7);
		bit.addValue(9);
		bit.addValue(6);
		bit.addValue(2);
		bit.addValue(1);

		bit.printInOrder();
		
		System.out.println(bit.contains(7)); // sollte true sein
		
		// Löschen eines Knotens
		bit.deleteFromTree(7);
		
		System.out.println(bit.contains(7)); // sollte nun false sein
		System.out.println(bit.contains(9)); // sollte noch true sein
		
		// Jetzt Probe auf's Exempel: Löschen der Wurzel
		bit.deleteFromTree(5);
		
		System.out.println(bit.contains(5)); // sollte nun false sein
		System.out.println(bit.contains(9)); // sollte noch true sein

	}
}
