package view;

import java.util.List;

import controler.CalculatorControlerInterface;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculatorGUI implements CalculatorGUIInterface {

    private Stage stage;                              // la fenêtre
    private CalculatorControlerInterface controler;   // à qui envoyer les clics

    private ListView<String> stackView = new ListView<>();  // affichage de la pile
    private Label accuLabel = new Label("");                // affichage de l'accu
    private String saisie = "";                             // nombre en cours de frappe

    public CalculatorGUI(Stage stage) {
        this.stage = stage;
    }

    public void setControler(CalculatorControlerInterface controler) {
        this.controler = controler;
    }

    // ===== Construction de la fenêtre =====
    @Override
    public void affiche() {
        // Zone de l'accumulateur
        accuLabel.setMaxWidth(Double.MAX_VALUE);
        accuLabel.setAlignment(Pos.CENTER_RIGHT);
        accuLabel.setStyle("-fx-font-size: 20; -fx-border-color: gray; -fx-padding: 5;");

        // Zone de la pile
        stackView.setPrefHeight(150);

        // Grille de boutons
        GridPane grille = new GridPane();
        grille.setHgap(5);
        grille.setVgap(5);

        String[][] touches = {
            {"7",     "8",       "9",    "/"},
            {"4",     "5",       "6",    "*"},
            {"1",     "2",       "3",    "-"},
            {"0",     ".",       "+/-",  "+"},
            {"push",  "pop",     "drop", "swap"},
            {"clear", "dropAll", "",     ""}
        };

        for (int ligne = 0; ligne < touches.length; ligne++) {
            for (int col = 0; col < touches[ligne].length; col++) {
                String texte = touches[ligne][col];
                if (texte.isEmpty()) {
                    continue;                       // case vide : pas de bouton
                }
                Button bouton = new Button(texte);
                bouton.setPrefSize(70, 40);
                bouton.setOnAction(e -> clic(texte));  // que faire quand on clique
                grille.add(bouton, col, ligne);
            }
        }

        // Empilement vertical : pile, accu, boutons
        VBox racine = new VBox(10, stackView, accuLabel, grille);
        racine.setPadding(new Insets(10));

        stage.setScene(new Scene(racine));
        stage.setTitle("Calculatrice RPN");
        stage.show();
    }

    // ===== Gestion des clics =====
    private void clic(String touche) {
        if (touche.matches("[0-9]") || touche.equals(".")) {
            // Chiffre ou virgule : on construit le nombre à l'écran
            if (touche.equals(".") && saisie.contains(".")) {
                return;                             // une seule virgule
            }
            saisie = saisie + touche;
            accuLabel.setText(saisie);
        } else {
            // Opération : on envoie d'abord le nombre tapé, puis l'action
            envoyerSaisie();
            controler.handleAction(touche);
        }
    }

    private void envoyerSaisie() {
        if (!saisie.isEmpty() && !saisie.equals(".")) {
            controler.handleNumber(Double.parseDouble(saisie));
        }
        saisie = "";
    }

    // ===== Mises à jour demandées par le contrôleur =====
    @Override
    public void change(String accu) {
        accuLabel.setText(accu);
    }

    @Override
    public void change(List<Double> stackData) {
        stackView.getItems().clear();
        for (Double valeur : stackData) {
            stackView.getItems().add(valeur.toString());
        }
    }
}