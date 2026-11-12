# RPN Calculator – Java / JavaFX (MVC)

A desktop calculator using **Reverse Polish Notation** (RPN), written in Java with a JavaFX interface and a strict **Model-View-Controller** architecture.
It was a 2nd-year software engineering project at IMT Mines Alès (France), built in a team of two in autumn 2026.

## Features
- Basic operations in RPN: `+ − × ÷` and sign change
- Operand stack implemented by us (no library stack), with **push**, **drop** and **clear**
- Previous results can be reused from the stack
- Two interchangeable views behind the same interface: a JavaFX GUI (`CalculatorGUI`) and a console view (`ConsoleView`) used for testing

## My part
I wrote the **calculation engine** (`model/CalculatorModel`) and the **controller** (`controler/CalculatorControler`) that connects it to the view.

## Architecture
```
src/
├── model/      CalculatorModel, CalculatorModelInterface     – stack and operations
├── controler/  CalculatorControler, CalculatorControlerInterface – user actions → model
├── view/       CalculatorGUI (JavaFX), ConsoleView, CalculatorGUIInterface
└── main/       CalculatorApp (GUI launcher), Main (console test of the model)
```
The view only knows the controller through its interface. Class names follow the spelling in the course's UML diagram (`controler`, `substract`).

## Run
Requires the JavaFX 21 SDK. VM options:
```
--module-path "<path>/javafx-sdk-21.0.13/lib" --add-modules javafx.controls
```
- `main.CalculatorApp` starts the graphical calculator
- `main.Main` runs the console checks of the model

## Known limitations
- Division by zero returns `Infinity`, with no error message
- An operation is ignored when the stack holds too few operands
- No automated unit tests yet: the model is checked manually through `Main` and the console view

---

*Version française : voir plus bas (« Écarts par rapport à la consigne »).*


# Calculatrice RPN (MVC, Java / JavaFX)

## Écarts par rapport à la consigne

- **`dropAll()` dans `CalculatorModelInterface`** : la méthode est citée dans le texte du sujet mais absente du diagramme. Nous l'avons ajoutée.
- **`setAccu(double)` dans `CalculatorModelInterface`** : cette méthode n'est pas sur le diagramme. Elle sert à placer le nombre saisi dans l'accumulateur.
- **`handleNumber()` et `handleAction()` dans `CalculatorControlerInterface`** : la vue ne connaît le contrôleur que par son interface. Il fallait donc que ces deux méthodes y figurent, en plus de `change()`.
- **Méthodes publiques en plus** : `CalculatorModel` a `setControler()` et `getMemory()`, et `CalculatorGUI` a `setControler()`. Elles servent à brancher les composants entre eux et à transmettre la pile. `getMemory()` renvoie une copie de la pile.
- **Orthographe** : nous avons conservé `substract` et `controler`, comme dans le diagramme du sujet.
- **Opérandes manquantes** : si la pile ne contient pas assez de nombres, l'opération est ignorée. Il n'y a pas d'erreur et rien n'est affiché.
- **Division par zéro** : le résultat vaut `Infinity`. Il n'y a ni blocage ni message d'erreur.
- **Accumulateur vide** : il s'affiche comme une zone vide, et non `0.0` comme sur l'image du sujet.
- **Saisie après un résultat** : un nouveau nombre remplace le contenu de l'accumulateur. Il faut faire `push` avant pour garder le résultat.
- **Saisie des chiffres** : le nombre est construit dans la vue, puis envoyé au modèle au moment où l'on appuie sur une opération ou sur `push`.
- **Deux vues** : `ConsoleView` (tests) et `CalculatorGUI` (JavaFX) implémentent toutes les deux `CalculatorGUIInterface`.
- **Deux programmes principaux** : `Main` teste le modèle dans la console, et `CalculatorApp` lance l'interface graphique.
- **Extensions** : le traceur de fonctions et le calcul sur les polynômes ne sont pas réalisés.

## Correction après la présentation

- **Ordre des opérandes de `/` et `-`** : quand les deux nombres étaient pris sur la pile, l'ordre était inversé. Par exemple, `10 push 2 push /` donnait 0.2 au lieu de 5. C'est corrigé : le premier nombre saisi est toujours le dividende (ou le terme dont on soustrait). Un test a été ajouté dans `Main`.

## Lancement

- JavaFX 21 SDK, avec l'option VM suivante :
  `--module-path "<chemin>/javafx-sdk-21.0.13/lib" --add-modules javafx.controls`
