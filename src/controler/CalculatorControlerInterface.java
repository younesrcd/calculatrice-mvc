package controler;

import java.util.List;

/**
 * Contrat du contrôleur, intermédiaire entre la vue et le modèle.
 */
public interface CalculatorControlerInterface {

    // ===== Modèle -> Contrôleur (callbacks) =====
    void change(String accu);              // l'accumulateur a changé
    void change(List<Double> stackData);   // la pile a changé

    // ===== Vue -> Contrôleur (actions de l'utilisateur) =====
    void handleNumber(double value);       // un nombre a été saisi
    void handleAction(String action);      // une touche d'opération a été pressée
}
