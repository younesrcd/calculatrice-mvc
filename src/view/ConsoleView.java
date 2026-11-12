package view;

import java.util.List;

/**
 * Vue minimale qui affiche l'état de la calculatrice dans la console.
 * Sert à tester le modèle sans interface graphique.
 */
public class ConsoleView implements CalculatorGUIInterface {

    @Override
    public void affiche() {
        System.out.println("=== Calculatrice (vue console) ===");
    }

    @Override
    public void change(String accu) {
        System.out.println("Accu : " + accu);
    }

    @Override
    public void change(List<Double> stackData) {
        System.out.println("Pile : " + stackData);
    }
}
