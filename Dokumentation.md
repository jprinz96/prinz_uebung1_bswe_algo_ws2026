# Datensturktur
Für das Sudoku wurde ein zweidimensionales Array vom Typ `int[][]` gewählt. Die Größe wird beim Erstellen auf 9×9 festgelegt, da das Sudoku aus neun Zeilen und neun Spalten besteht.

Das Array speichert ganze Zahlen von 0 bis 9. Dabei steht 0 für ein leeres Feld.

Diese Datenstruktur eignet sich, weil sie den Aufbau des Sudoku-Rasters direkt abbildet. Über den Zeilen- und Spaltenindex kann auf jedes Feld zugegriffen werden. Die gespeicherten Zahlen können während des Lösens verändert werden, während die Größe des Arrays gleich bleibt.

# Backtracking
Das Sudoku wird von oben links zeilenweise von links nach rechts durchsucht. Beim ersten leeren Feld, das durch 0 gekennzeichnet ist, werden die Zahlen von 1 bis 9 aufsteigend ausprobiert.

Die Methode `isValid()` prüft mithilfe von `isNumberInRow()`, `isNumberInColumn() `und `isNumberInBox()`, ob die Zahl bereits in der Zeile, Spalte oder im 3×3-Block vorkommt. Wenn alle drei Methoden `false` zurückgeben, darf die Zahl eingesetzt werden.

Anschließend ruft sich die Methode `isSudokuSolvable()` rekursiv selbst auf, um das nächste leere Feld zu bearbeiten. Kann dort mit keiner Zahl eine Lösung gefunden werden, wird false zurückgegeben. Der vorherige Aufruf setzt seine eingesetzte Zahl wieder auf 0 und versucht die nächste Zahl.

Sind keine leeren Felder mehr vorhanden, wird `true` zurückgegeben. Wenn alle Möglichkeiten erfolglos ausprobiert wurden, liefert der `Solver` null zurück.

### Warum Backtracking?
Backtracking eignet sich für Sudoku, da für ein leeres Feld mehrere Zahlen infrage kommen können. Der Algorithmus probiert die erlaubten Zahlen systematisch aus. Führt eine Zahl später in eine Sackgasse, wird sie entfernt und die nächste Möglichkeit ausprobiert.

# Zeitkomplexität
Bei einem leeren Feld werden maximal neun Zahlen ausprobiert. Für jede Möglichkeit können beim nächsten leeren Feld wieder bis zu neun Zahlen ausprobiert werden. Bei `n` leeren Feldern ergeben sich somit im schlechtesten Fall bis zu `9ⁿ` Kombinationen. Die Zeitkomplexität wird daher mit `O(9ⁿ)` abgeschätzt.

Der Aufwand hängt also von der Anzahl der leeren Felder ab. In der Praxis schließt `isValid()` viele Möglichkeiten frühzeitig aus. Die tatsächliche Laufzeit hängt deshalb auch von der Anordnung der vorgegebenen Zahlen und davon ab, wie oft der Algorithmus zurückgehen muss.

# Platzkomplexität
Das Sudoku wird direkt im vorhandenen Array verändert, es werden keine Kopien erstellt. Die Größe des Arrays bleibt immer 9×9.

Bei jedem rekursiven Aufruf muss sich das Programm merken, wo es danach weitermachen soll. Je mehr leere Felder bearbeitet werden, desto mehr Aufrufe können gleichzeitig aktiv sein. Bei n leeren Feldern wächst der zusätzliche Speicherbedarf daher linear → O(n).

Benutze Hilfestellungen:
- https://www.intervue.io/top-coding-questions/java/sudoku-solver/
- https://www.geeksforgeeks.org/dsa/sudoku-backtracking-7/
- https://www.baeldung.com/java-sudoku
- https://www.youtube.com/watch?v=mcXc8Mva2bA&t=1081s
- https://www.youtube.com/watch?v=9I73elFoze8
