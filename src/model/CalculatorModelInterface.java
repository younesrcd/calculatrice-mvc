package model;

/**
 * Contrat du modèle de la calculatrice.
 * Les opérations ne prennent pas de paramètre : en notation polonaise inverse,
 * les opérandes sont déjà dans l'accumulateur et sur la pile.
 */
public interface CalculatorModelInterface {

    // ===== Opérations arithmétiques (résultat dans l'accumulateur) =====
    void add();        // a + b
    void substract();  // a - b
    void multiply();   // a * b
    void divide();     // a / b
    void opposite();   // -a (une seule opérande)

    // ===== Gestion de l'accumulateur et de la pile =====
    void push();       // accumulateur -> sommet de la pile
    void pop();        // sommet de la pile -> accumulateur
    void drop();       // supprime le sommet de la pile
    void swap();       // échange les deux éléments du haut de la pile
    void clear();      // vide l'accumulateur
    void dropAll();    // vide toute la pile

    void setAccu(double value);  // saisie d'un nombre dans l'accumulateur
}
