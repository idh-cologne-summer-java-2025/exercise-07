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
		
		public boolean delete(int value) {
			
			/**
			 * Ich hab hier den Delete-Abschnitt für den linken Suchbaum und für den rechten Suchbaum
			 * geschrieben, da man ja den current-Value nicht mit ner Null-Deklaration löschen kann.
			 * Deshalb muss glaube ich der Baum in links und rechts aufgeteilt werden, um danut arbeiten zu können.
			 * */
			if (left.value>=value) {
				if(left.left==null&&left.right==null) {
					left=null; 
					return true;
					/**Ich bin mir grad noch nicht ganz sicher,
					ob man damit bis in die hinterste Ecke des Baumes kommt.
					Aber mit this.value kommt man ja nicht weiter, da value in primitiver Datentyp ist*/
				}
				/**hiermit wird Case 2 abgedeckt*/
				else if(left.left==null&&left.right!=null&&left.value==value) {
					left=left.right;
					return true;
				}
				else if(left.left!=null&&left.right==null&&left.value==value) {
					left=left.left;
					return true;
				}
				//Kommen wir nun zu Case 3:...
				else if(left.left!=null&&left.right!=null&&left.value==value){
					
				}
				//Wenn wir auf dieser Ebene nicht auf unseren gesuchten Value gestoßen sind,
				//diggen wir eine Ebene tiefer in das Konstrukt.
				left.delete(value);
				
			}
			//UND NUN DEN GAAAAAANZEN SPAß AUCH FÜR DIE ANDERE SEITE, YEHAAAAAW
			else if (right.value<=value) {
				if(right.left==null&&right.right==null&&right.value==value) {
					right=null;
					return true;
					//Same gilt wie bei dieser Bedingung vom linken Zweig
				}
				else if(right.left==null&&right.right!=null&&right.value==value) {
					right=right.right;
					return true;
				}
				else if(right.left!=null&&right.right==null&&right.value==value) {
					right=right.left;
					return true;
				}
				else if(right.left!=null&&right.right!=null&&right.value==value) {
					
				}
				right.delete(value);
			}
			
			
			return false;
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
		if (root.value==value) {
			if (root.left==null && root.right==null) {
				root = null;
				return true;
			} else {
				root.delete(value);
			}
		}
		if (contains(value)==true) {
			root.delete(value);
		}
		return false;
	}

}
