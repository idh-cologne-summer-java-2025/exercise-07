Fortgeschrittene Programmierung (Java 2)

# Übung 7

Klonen Sie dieses Repository direkt in Eclipse und importieren Sie das Projekt. Legen Sie einen neuen Branch an, den Sie nach Ihrem GitHub-Benutzernamen benennen.

----
Im src-Ordner finden Sie das Beispiel des BinaryIntegerTree, das wir heute im Unterricht besprochen haben.
Dieser Klasse sollen Sie eine weitere Methode hinzufügen: `boolean delete(int value)`
Das liest sich zunächst vielleicht einfacher, als es sich am Ende darstellt. Wenn man nämlich Knoten aus Bäumen löscht, muss man aufpassen, dass der Baum nicht auseinanderfällt und den gelöschten Knoten ggfs. ersetzen.
Erst einmal muss man den relevanten Knoten aufspüren. Dabei sind drei Fälle zu unterscheiden:
1. Der Knoten hat keine Kindknoten (ist also ein Blatt), dann kann man ihn einfach löschen.
2. Der Knoten hat nur einen Knidknoten, dann kann man ihn durch den Kindknoten ersetzen.
3. Der Knoten hat zwei Kindknoten, dann wird es estwas komplizierter :)

Der Fall 3 ist aus dem Grunde nicht elementar, weil der Baum neu angeordnet werden muss. Dafür muss man einen geeigneten Kandidaten unter den Nachfolgerknoten identifizieren. Hierfür schon einmal ein Tipp: Man braucht entweder den Knoten mit dem höchsten Wert aus dem linken Teilbaum oder den Knoten mit dem niedrigsten Wert aus dem rechten Teilbaum. Den Wert dieses Knotens muss man an die Stelle setzen, wo man den Knoten gelöscht hat. Danch muss man dafür sorgen, dass der entsprechde Wert weiter unten im Baum gelöscht wird (sonst ist er ja zweimal da). Das nacht man am besten wieder rekursiv...

Bei der Aufgabe erzeugt man relativ wenig Code, aber muss viel Gehirnschmalz investieren. Wenn man sie aber eigenständig gelöst hat, ist man dem tieferen Verständnis der Rekursion aber definitv näher gekommen!

----

Wenn Sie fertig sind, committen Sie alle Ihre Änderungen am Quellcode, und pushen Sie den neuen Branch auf das remote namens `origin` (= GitHub). 
