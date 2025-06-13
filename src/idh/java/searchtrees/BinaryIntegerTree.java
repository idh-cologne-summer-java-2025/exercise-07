package idh.java.searchtrees;

/*Ich denke, dass wir eine rekursiv iterierte Methode implentieren sollen, die automatisch die entstandenen Bedingungen konzipiert.
 * Um es visuell zu vereinfachen, habe ich eine kleine Methode ergänzt, mit welcher wir sehen können wo wir uns gerade aufhalten und wie der Baum im ist Zustand weilt.*/

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
		 * Interne rekursive Methode zum Löschen eines Knotens mit bestimmtem Wert.
		 * @param valueToDelete Der zu löschende Wert
		 * @return Wurzel des (möglicherweise geänderten) Teilbaums
		 */
		public BinaryIntegerTreeNode deleteNode(int valueToDelete) {
			if (valueToDelete < this.value) {
				if (left != null) {
					left = left.deleteNode(valueToDelete);
				}
			} else if (valueToDelete > this.value) {
				if (right != null) {
					right = right.deleteNode(valueToDelete);
				}
			} else {
				// Knoten mit passendem Wert gefunden
				// Fall 1: keine Kinder
				if (left == null && right == null) {
					return null;
				}
				// Fall 2: ein Kind
				if (left == null) {
					return right;
				} else if (right == null) {
					return left;
				}
				// Fall 3: zwei Kinder
				// Finde kleinsten Wert im rechten Teilbaum
				BinaryIntegerTreeNode minNode = right;
				while (minNode.left != null) {
					minNode = minNode.left;
				}
				// Ersetze aktuellen Wert mit dem kleinsten Nachfolgerwert
				this.value = minNode.value;
				// Lösche den Nachfolgerwert rekursiv aus rechtem Teilbaum
				right = right.deleteNode(minNode.value);
			}
			return this; // Gib die (möglicherweise geänderte) Wurzel zurück
		}

        /**
         * Gibt den Baum als ASCII-Baumstruktur aus (für Debug und Visualisierung)
         * @param prefix Einrückung
         * @param isLeft true, wenn linkes Kind
         */
        public void printPretty(String prefix, boolean isLeft) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + value);
            if (left != null) {
                left.printPretty(prefix + (isLeft ? "│   " : "    "), true);
            }
            if (right != null) {
                right.printPretty(prefix + (isLeft ? "│   " : "    "), false);
            }
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
	 * Gibt die Baumstruktur als ASCII-Baum aus
	 */
	public void printPretty() {
		if (root != null) {
			System.out.println("\ud83c\udf33 Baumstruktur:");
			root.printPretty("", false);
		} else {
			System.out.println("\ud83c\udf31 Der Baum ist leer.");
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
	 * @param value Der zu löschende int-Wert
	 * @return true, wenn der Wert gefunden und gelöscht wurde, sonst false
	 */
	public boolean delete(int value) {
		if (root == null) {
			return false;
		}
		if (!contains(value)) {
			return false; // Wert nicht im Baum
		}
		root = root.deleteNode(value); // setze ggf. neue Wurzel
		return true;
	}
}
