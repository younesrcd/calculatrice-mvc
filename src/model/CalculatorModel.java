package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import controler.CalculatorControlerInterface;

/**
 * Modèle de la calculatrice en notation polonaise inverse.
 *
 * Il contient l'accumulateur (nombre en cours) et la pile des opérandes.
 * Règle des opérations : si l'accumulateur est plein, il sert de deuxième
 * opérande et la première est prise au sommet de la pile ; s'il est vide,
 * les deux opérandes sont prises sur la pile. Le résultat est placé dans
 * l'accumulateur.
 *
 * Après chaque modification, le modèle prévient le contrôleur (callback).
 */
public class CalculatorModel implements CalculatorModelInterface {

    private Double accu = null;                        // accumulateur (null = vide)
    private Stack<Double> memory = new Stack<>();      // pile des opérandes
    private CalculatorControlerInterface controler;    // prévenu à chaque changement

    // ===== Opérations arithmétiques =====

    @Override
    public void add() {
        if (accu != null) {
            // Accu plein : sommet de la pile + accu
            if (!memory.isEmpty()) {
                accu = memory.pop() + accu;
            }
        } else {
            // Accu vide : les deux éléments du haut de la pile
            if (memory.size() >= 2) {
                double b = memory.pop();   // dernier empilé
                double a = memory.pop();   // celui en dessous
                accu = a + b;
            }
        }
        notifyChange();
    }

    @Override
    public void substract() {
        if (accu != null) {
            // Accu plein : sommet de la pile - accu
            if (!memory.isEmpty()) {
                accu = memory.pop() - accu;
            }
        } else {
            // Accu vide : les deux éléments du haut de la pile
            if (memory.size() >= 2) {
                double b = memory.pop();   // dernier empilé
                double a = memory.pop();   // celui en dessous
                accu = a - b;
            }
        }
        notifyChange();
    }

    @Override
    public void multiply() {
        if (accu != null) {
            // Accu plein : sommet de la pile * accu
            if (!memory.isEmpty()) {
                accu = memory.pop() * accu;
            }
        } else {
            // Accu vide : les deux éléments du haut de la pile
            if (memory.size() >= 2) {
                double b = memory.pop();   // dernier empilé
                double a = memory.pop();   // celui en dessous
                accu = a * b;
            }
        }
        notifyChange();
    }

    @Override
    public void divide() {
        // Remarque : une division par zéro donne Infinity (pas de plantage)
        if (accu != null) {
            // Accu plein : sommet de la pile / accu
            if (!memory.isEmpty()) {
                accu = memory.pop() / accu;
            }
        } else {
            // Accu vide : les deux éléments du haut de la pile
            if (memory.size() >= 2) {
                double b = memory.pop();   // diviseur : dernier empilé
                double a = memory.pop();   // dividende : celui en dessous
                accu = a / b;
            }
        }
        notifyChange();
    }

    @Override
    public void opposite() {
        if (accu != null) {
            // Accu plein : on change son signe
            accu = -accu;
        } else if (!memory.isEmpty()) {
            // Accu vide : on prend le sommet de la pile
            accu = -memory.pop();
        }
        notifyChange();
    }

    // ===== Gestion de l'accumulateur et de la pile =====

    @Override
    public void push() {
        if (accu != null) {
            memory.push(accu);
            accu = null;
        }
        notifyChange();
    }

    @Override
    public void pop() {
        if (!memory.isEmpty()) {
            accu = memory.pop();
        }
        notifyChange();
    }

    @Override
    public void drop() {
        if (!memory.isEmpty()) {
            memory.pop();
        }
        notifyChange();
    }

    @Override
    public void swap() {
        if (memory.size() >= 2) {
            double top = memory.pop();      // ancien sommet
            double second = memory.pop();   // élément juste en dessous
            memory.push(top);
            memory.push(second);
        }
        notifyChange();
    }

    @Override
    public void clear() {
        accu = null;
        notifyChange();
    }

    @Override
    public void dropAll() {
        memory.clear();
        notifyChange();
    }

    @Override
    public void setAccu(double value) {
        this.accu = value;
        notifyChange();
    }

    // ===== Liaison avec le contrôleur =====

    /** Branche le contrôleur à prévenir lors des changements. */
    public void setControler(CalculatorControlerInterface controler) {
        this.controler = controler;
    }

    /** Renvoie une copie de la pile : la vraie pile ne peut pas être modifiée de l'extérieur. */
    public List<Double> getMemory() {
        return new ArrayList<>(memory);
    }

    /** Prévient le contrôleur que l'accumulateur et la pile ont pu changer. */
    private void notifyChange() {
        if (controler != null) {
            controler.change(accu == null ? "" : accu.toString());
            controler.change(getMemory());
        }
    }
}
