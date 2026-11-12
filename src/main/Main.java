package main;

import controler.CalculatorControler;
import model.CalculatorModel;
import view.ConsoleView;

/**
 * Programme de test du modèle, avec la vue console.
 * Simule les actions d'un utilisateur en passant par le contrôleur.
 */
public class Main {

    public static void main(String[] args) {
        // 1. Création des trois composants MVC
        CalculatorModel model = new CalculatorModel();
        ConsoleView view = new ConsoleView();
        CalculatorControler controler = new CalculatorControler(model, view);

        // 2. Branchement du retour : le modèle prévient le contrôleur
        model.setControler(controler);

        // 3. Calcul de (10 + 3) * 2 -> attendu : Accu 26.0
        view.affiche();
        controler.handleNumber(10);
        controler.handleAction("push");
        controler.handleNumber(3);
        controler.handleAction("+");
        controler.handleAction("push");
        controler.handleNumber(2);
        controler.handleAction("*");

        // 4. Ordre des opérandes : 10 push 3 push - -> attendu : Accu 7.0
        controler.handleAction("clear");
        controler.handleNumber(10);
        controler.handleAction("push");
        controler.handleNumber(3);
        controler.handleAction("push");
        controler.handleAction("-");

        // 5. Cas limites : aucune opération ne doit planter
        controler.handleAction("clear");
        controler.handleAction("dropAll");
        controler.handleAction("+");       // pas assez d'opérandes
        controler.handleAction("pop");     // pile vide
        controler.handleAction("swap");    // pile vide
    }
}
