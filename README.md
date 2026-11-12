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
