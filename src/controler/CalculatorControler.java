package controler;

import java.util.List;

import model.CalculatorModelInterface;
import view.CalculatorGUIInterface;

/**
 * Contrôleur : traduit les actions de l'utilisateur en appels au modèle
 * et transmet les changements du modèle à la vue.
 */
public class CalculatorControler implements CalculatorControlerInterface {

    private CalculatorModelInterface model;
    private CalculatorGUIInterface view;

    public CalculatorControler(CalculatorModelInterface model, CalculatorGUIInterface view) {
        this.model = model;
        this.view = view;
    }

    // ===== Modèle -> Vue : on transmet les changements =====

    @Override
    public void change(String accu) {
        view.change(accu);
    }

    @Override
    public void change(List<Double> stackData) {
        view.change(stackData);
    }

    // ===== Vue -> Modèle : on traduit les actions de l'utilisateur =====

    @Override
    public void handleNumber(double value) {
        model.setAccu(value);
    }

    @Override
    public void handleAction(String action) {
        switch (action) {
            case "+":       model.add();       break;
            case "-":       model.substract(); break;
            case "*":       model.multiply();  break;
            case "/":       model.divide();    break;
            case "+/-":     model.opposite();  break;
            case "push":    model.push();      break;
            case "pop":     model.pop();       break;
            case "drop":    model.drop();      break;
            case "swap":    model.swap();      break;
            case "clear":   model.clear();     break;
            case "dropAll": model.dropAll();   break;
            default: System.out.println("Action inconnue : " + action);
        }
    }
}
