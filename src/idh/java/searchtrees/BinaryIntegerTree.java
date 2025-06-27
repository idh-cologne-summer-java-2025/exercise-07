package idh.java.searchtrees;

/**
 * Klasse zum Speichern von int-Werten in einem sortierten Binärbaum
 */
public class BinaryIntegerTree {

	/**
	 * Innere Klasse, die einen int-Binärbaumknoten repräsentiert
	 */
	class BinaryIntegerTreeNode {

		public BinaryIntegerTreeNode(int value) {
			this.value = value;
		}

		BinaryIntegerTreeNode left;
		BinaryIntegerTreeNode right;
		int value;

		/**
		 * Rekursive Methode für das Hinzufügen von int-Werten
		 * 
		 * @param value int-Wert
		 * @return true, wenn Wert hinzugefügt wurde, false, wenn er schon vorhandem war
		 */
		public boolean addValue(int newValue) {
			if (newValue == value) {
				return false; // Wert bereits vorhanden
			} else if (newValue < value) {
				if (left == null) {
					left = new BinaryIntegerTreeNode(newValue);
					return true;
				} else {
					return left.addValue(newValue);
				}
			} else { // newValue > value
				if (right == null) {
					right = new BinaryIntegerTreeNode(newValue);
					return true;
				} else {
					return right.addValue(newValue);
				}
			}
		}

		public void printInOrder() {
			if (left != null) {
				left.printInOrder();
			}
			System.out.print(value + " ");
			if (right != null) {
				right.printInOrder();
			}
		}

		public boolean contains(int searchValue) {
			if (searchValue == value) {
				return true;
			} else if (searchValue < value) {
				return (left != null) && left.contains(searchValue);
			} else {
				return (right != null) && right.contains(searchValue);
			}
		}

		/**
		 * Sorry, die Methode brauchte tatsächlich eine andere Signatur als von mir
		 * angegeben (weil ich übersehen hatte, dass root selbst der gesuchte Knoten
		 * sein konnte).
		 * 
		 * @param value
		 * @return
		 */
		public BinaryIntegerTreeNode deleteNode(int value) {
			// Müssen wir links abbiegen?
			if (value < this.value) {
				// Biege links ab (da muss was sein, weil contains geprüft wurde)
				left = left.deleteNode(value);
	
			}
			// Müssen wir rechts abbiegen?
			else if (value > this.value) {
				// Biege rechts ab
				right = right.deleteNode(value);
				
			} else {
				if (left == null) {
					// Falls links nichts ist, gib den rechten Knoten zurück
					return right;
				} 
				else if (right == null) {
					// Falls rechts nichts ist, gib den linken Knoten zurück
					return left;
				} 
				else {
					// sonst suche den am weitesten links stehenden Knoten rechts
					// (Alternative wäre, den am weitesten rechts stehenden Knoten links zu liefern)
					BinaryIntegerTreeNode successor = right;
					while (successor.left != null) {
						successor = successor.left;
					}
					this.value = successor.value;
					// Und jetzt noch den Knoten löschen, den man als Ersatz genommen hat!
					right = right.deleteNode(successor.value);
				}
			}
			return this;
		}
	}

	/**
	 * Die Wurzel des Baums
	 */
	private BinaryIntegerTreeNode root;

	/**
	 * Methode für das Hinzufügen von int-Werten in den Baum
	 * 
	 * @param value int-Wert
	 * @return true, wenn Wert hinzugefügt wurde, false, wenn er schon vorhandem war
	 */
	public boolean addValue(int value) {
		if (root == null) {
			root = new BinaryIntegerTreeNode(value);
			return true;
		} else {
			return root.addValue(value);
		}
	}

	/**
	 * Soll den Baum in der sortierten Reihenfolge ausgeben
	 */
	public void printInOrder() {
		if (root != null) {
			root.printInOrder();
		} else {
			System.out.println("Der Baum ist leer.");
		}
	}

	/**
	 * Überprüft, ob der spezifizierte Wert enthalten ist.
	 */
	public boolean contains(int value) {
		return root != null && root.contains(value);
	}

	/**
	 * Löscht den übergebenen Wert aus dem Baum.
	 */
	public boolean delete(int value) {
		if (root == null || !contains(value)) {
			return false;
		}
		root = root.deleteNode(value);
		return true;
	}

}
