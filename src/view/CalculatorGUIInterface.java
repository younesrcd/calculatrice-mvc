package view;

import java.util.List;

/**
 * Contrat de toute vue de la calculatrice (console, JavaFX...).
 * Le contrôleur ne connaît la vue qu'à travers cette interface.
 */
public interface CalculatorGUIInterface {

    void affiche();                        // affiche l'interface
    void change(String accu);              // met à jour l'affichage de l'accumulateur
    void change(List<Double> stackData);   // met à jour l'affichage de la pile
}
