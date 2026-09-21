# Sudoku Solver

Dieses Projekt implementiert einen Sudoku-Solver in Java.

## Regeln

Ein gültiges 9x9-Sudoku muss folgende Bedingungen erfüllen:

- Jede Zahl von 1 bis 9 darf in einer Zeile nur einmal vorkommen.
- Jede Zahl von 1 bis 9 darf in einer Spalte nur einmal vorkommen.
- Jede Zahl von 1 bis 9 darf in einem 3x3-Block nur einmal vorkommen.
- `0` steht für ein leeres Feld.

## Eingabe

Das Sudoku kann über ein Menü eingegeben oder aus der Datei `input.csv` eingelesen werden.

Die CSV-Datei enthält neun Zeilen mit jeweils neun durch Kommas getrennten Zahlen.

Beispiel:

```text
5,3,0,0,7,0,0,0,0
6,0,0,1,9,5,0,0,0
0,9,8,0,0,0,0,6,0
```

## Funktion

Das Programm:

1. liest ein Sudoku ein,
2. überprüft, ob Zahlen an einer bestimmten Position gültig sind,
3. löst das Sudoku mithilfe eines Lösungsalgorithmus,
4. gibt das gelöste Sudoku aus oder meldet, wenn keine Lösung existiert.

Der Einstiegspunkt des Solvers ist:

```java
public static int[][] solve(int[][] sudoku)
```