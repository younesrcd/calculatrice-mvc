package main;

import controler.CalculatorControler;
import javafx.application.Application;
import javafx.stage.Stage;
import model.CalculatorModel;
import view.CalculatorGUI;

public class CalculatorApp extends Application {

    @Override
    public void start(Stage stage) {
        // 1. Création des trois composants
        CalculatorModel model = new CalculatorModel();
        CalculatorGUI view = new CalculatorGUI(stage);
        CalculatorControler controler = new CalculatorControler(model, view);

        // 2. Branchements
        model.setControler(controler);   // modèle → contrôleur
        view.setControler(controler);    // vue → contrôleur

        // 3. Affichage
        view.affiche();
    }

    public static void main(String[] args) {
        launch(args);
    }
}