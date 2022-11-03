/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.*;
import modele.Accumulateur;

import java.util.ArrayList;

// Tout ce qui est lié aux cliques ou passages de souris
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

//Tout ce qui est lié à l'interface graphique "pure"
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class InterfaceGraphique implements IView{

    private Stage st;
    private Controleur control;
    private Accumulateur accu;
    
    public InterfaceGraphique(Stage st, Controleur controler) throws Exception {

        this.st = st;
        this.control = controler;
        this.accu = control.getAccu();
        
    }

    @Override
    public void affiche(){
        
        //Partie de la calculatrice qui contiendra les boutons, la partie "fixe"
        VBox calculatrice = new VBox(); 
        //On vient définir la largeur de notre calculatrice
        calculatrice.setPrefWidth(170);
        
        //On découpe le canvas en grille pour les boutons
        GridPane grilleBoutons = new GridPane();
        //On vient charger une image en arrière plan de la grille de bouton
        grilleBoutons.setStyle("-fx-border-width: 15");
        grilleBoutons.setStyle("-fx-border-color: pink");
        grilleBoutons.setStyle("-fx-background-image: url('https://www.le-monde-du-stickers.fr/5444-large_default/sticker-licorne.jpg')");
        
        
        
        //Partie de la calculatrice qui va afficher le résultat des opérations
        VBox ecranCalculatrice = new VBox();
        //On vient charger une image en arrière plan de la zone d'affichage
        ecranCalculatrice.setStyle("-fx-background-image: url('https://www.le-monde-du-stickers.fr/5444-large_default/sticker-licorne.jpg')");
        
        Scene scene = new Scene(calculatrice); 
        
        //Pour afficher le "résultat" tout en le stylisant notamment avec une bordure
        Label texteCalculatrice = new Label();
        texteCalculatrice.setStyle("-fx-border-width: 15");
        texteCalculatrice.setStyle("-fx-border-color: blue");
        
        Label texteErreur = new Label();
        texteErreur.setStyle("-fx-border-width: 15");
        texteErreur.setStyle("-fx-border-color: red");
        
        //Ces zones de textes non modifiables par l'utilisateur vont permettre d'afficher le stockage de la pile de l'accumulateur
        Label textePile0 = new Label();
        Label textePile1 = new Label();
        Label textePile2 = new Label();
        Label textePile3 = new Label();
              
        //On construit les boutons à implémenter dans la grille
        Button bt0 = new Button("0");
        Button bt1 = new Button("1");
        Button bt2 = new Button("2");
        Button bt3 = new Button("3");
        Button bt4 = new Button("4");
        Button bt5 = new Button("5");
        Button bt6 = new Button("6");
        Button bt7 = new Button("7");
        Button bt8 = new Button("8");
        Button bt9 = new Button("9");

        Button btPointDecimal = new Button(".");
        Button btAdd = new Button("+");
        Button btSub = new Button("-");
        Button btMult = new Button("x");
        Button btDiv = new Button("÷");     
        Button btNeg = new Button("(-)");
	
	//Après vérification, seul les caractères ASCII sont utilisables, on tente donc de représenter au mieux l'action du bouton
        Button btPush = new Button("⏎");
        Button btReset = new Button("AC");
        Button btBackspace = new Button("⌫");
        Button btSwap = new Button("↔"); 
        Button btDrop = new Button("▼");
        
        
        
        /*
        ICI, ON VIENT AFFECTER LA COULEUR DE L'ARRIERE PLAN DES BOUTONS
        */
        
        //Background pour les boutons de 1 à 9
        CornerRadii coin = new CornerRadii(10);
        Background couleurBouton1 = new Background(new BackgroundFill(Color.PINK, coin, Insets.EMPTY));
        
        //On applique le background associé
        bt0.setBackground(couleurBouton1);
        bt1.setBackground(couleurBouton1);
        bt2.setBackground(couleurBouton1);
        bt3.setBackground(couleurBouton1);
        bt4.setBackground(couleurBouton1);
        bt5.setBackground(couleurBouton1);
        bt6.setBackground(couleurBouton1);
        bt7.setBackground(couleurBouton1);
        bt8.setBackground(couleurBouton1);
        bt9.setBackground(couleurBouton1);
        
        //Background pour tous les autres boutons
        Background couleurBouton2 = new Background(new BackgroundFill(Color.PURPLE, coin, Insets.EMPTY));
        
        //On applique le background associé
        btDrop.setBackground(couleurBouton2);
        btSwap.setBackground(couleurBouton2);
        btBackspace.setBackground(couleurBouton2);
        btReset.setBackground(couleurBouton2);
        btPush.setBackground(couleurBouton2);
        btNeg.setBackground(couleurBouton2);
        btDiv.setBackground(couleurBouton2);
        btAdd.setBackground(couleurBouton2);
        btSub.setBackground(couleurBouton2);
        btMult.setBackground(couleurBouton2);
        btAdd.setBackground(couleurBouton2);
        btPointDecimal.setBackground(couleurBouton2);
        
        
        /*
        ICI, ON VIENT AFFECTER LA COULEUR DE L ECRITURE DES BOUTONS
        */
        
        Color couleurEcritureBouton = Color.WHITE;
        
        bt0.setTextFill(couleurEcritureBouton);
        bt1.setTextFill(couleurEcritureBouton);
        bt2.setTextFill(couleurEcritureBouton);
        bt3.setTextFill(couleurEcritureBouton);
        bt4.setTextFill(couleurEcritureBouton);
        bt5.setTextFill(couleurEcritureBouton);
        bt6.setTextFill(couleurEcritureBouton);
        bt7.setTextFill(couleurEcritureBouton);
        bt8.setTextFill(couleurEcritureBouton);
        bt9.setTextFill(couleurEcritureBouton);
        
        btDrop.setTextFill(couleurEcritureBouton);
        btAdd.setTextFill(couleurEcritureBouton);
        btSwap.setTextFill(couleurEcritureBouton);
        btBackspace.setTextFill(couleurEcritureBouton);
        btPointDecimal.setTextFill(couleurEcritureBouton);
        btSub.setTextFill(couleurEcritureBouton);
        btDiv.setTextFill(couleurEcritureBouton);
        btMult.setTextFill(couleurEcritureBouton);
        btNeg.setTextFill(couleurEcritureBouton);
        btPush.setTextFill(couleurEcritureBouton);
        btReset.setTextFill(couleurEcritureBouton);
        
        
        
        /*
        ICI, ON VIENT AFFECTER UNE DIMENSION CORRECTE A NOS BOUTONS
        */
        
        bt0.setMinWidth(calculatrice.getPrefWidth());
        bt1.setMinWidth(calculatrice.getPrefWidth());
        bt2.setMinWidth(calculatrice.getPrefWidth());
        bt3.setMinWidth(calculatrice.getPrefWidth());
        bt4.setMinWidth(calculatrice.getPrefWidth());
        bt5.setMinWidth(calculatrice.getPrefWidth());
        bt6.setMinWidth(calculatrice.getPrefWidth());
        bt7.setMinWidth(calculatrice.getPrefWidth());
        bt8.setMinWidth(calculatrice.getPrefWidth());
        bt9.setMinWidth(calculatrice.getPrefWidth());
        btPointDecimal.setMinWidth(calculatrice.getPrefWidth());
        btSub.setMinWidth(calculatrice.getPrefWidth());
        btAdd.setMinWidth(calculatrice.getPrefWidth());
        btMult.setMinWidth(calculatrice.getPrefWidth());
        btDiv.setMinWidth(calculatrice.getPrefWidth());
        btNeg.setMinWidth(calculatrice.getPrefWidth());
        btPush.setMinWidth(calculatrice.getPrefWidth());
        btReset.setMinWidth(calculatrice.getPrefWidth());
        btBackspace.setMinWidth(calculatrice.getPrefWidth());
        btSwap.setMinWidth(calculatrice.getPrefWidth());
        btDrop.setMinWidth(calculatrice.getPrefWidth());   
        
            
        
        /*
	ICI, ON ATTRIBUE LES BOUTONS ET LEUR EMPLACEMENT A LA GRILLE
 	*/
	
	//Groupe de boutons 1
        grilleBoutons.add(bt0,1,4);
        grilleBoutons.add(btPointDecimal,0,4);   
        grilleBoutons.add(btNeg,2,4);
        grilleBoutons.add(bt1,0,3);
        grilleBoutons.add(bt2,1,3);
        grilleBoutons.add(bt3,2,3);
        grilleBoutons.add(bt4,0,2);
        grilleBoutons.add(bt5,1,2);
        grilleBoutons.add(bt6,2,2);
        grilleBoutons.add(bt7,0,1);
        grilleBoutons.add(bt8,1,1);
        grilleBoutons.add(bt9,2,1);
        
	//Groupe de boutons 2
        grilleBoutons.add(btDiv,3,1);
        grilleBoutons.add(btMult,3,2);
        grilleBoutons.add(btAdd,3,4);
        grilleBoutons.add(btSub,3,3);
		
	//Groupe de boutons 3
        grilleBoutons.add(btPush,5,3);
        grilleBoutons.add(btReset,4,4);
        grilleBoutons.add(btBackspace,4,1);
        grilleBoutons.add(btSwap,4,3);
        grilleBoutons.add(btDrop,4,2);
		
        

        /*
        ICI ON VIENT CREER L'ENSEMBLE DES ACTIONS SUR LES BOUTONS
        */

        bt0.setOnAction (new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                control.appuyerSurCalculatrice('0'); //On indique au controleur qu'on appuie sur le bouton "0"
                texteCalculatrice.setText(accu.getEcran()); //On vient ajouter à l'affichage en "temps réel" de la calculatrice le bouton sur lequel on vient d'appuyer (ici "0")
            }
        });
       
        bt1.setOnAction (new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                control.appuyerSurCalculatrice('1');
                texteCalculatrice.setText(accu.getEcran());
            }
        });

        bt2.setOnAction (new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                control.appuyerSurCalculatrice('2');
                texteCalculatrice.setText(accu.getEcran());
            }
        });

        bt3.setOnAction (new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                control.appuyerSurCalculatrice('3');
                texteCalculatrice.setText(accu.getEcran());
            }
        });

        bt4.setOnAction (new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                control.appuyerSurCalculatrice('4');
                texteCalculatrice.setText(accu.getEcran());
            }
        });

        bt5.setOnAction (new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                control.appuyerSurCalculatrice('5');
                texteCalculatrice.setText(accu.getEcran());
            }
        });

        bt6.setOnAction (new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                control.appuyerSurCalculatrice('6');
                texteCalculatrice.setText(accu.getEcran());
            }
        });

        bt7.setOnAction (new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                control.appuyerSurCalculatrice('7');
                texteCalculatrice.setText(accu.getEcran());
            }
        });

        bt8.setOnAction (new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                control.appuyerSurCalculatrice('8');
                texteCalculatrice.setText(accu.getEcran());
            }
        });

        bt9.setOnAction (new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                control.appuyerSurCalculatrice('9');
                texteCalculatrice.setText(accu.getEcran());
            }
        });

        btMult.setOnAction (new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                //On vérifie l'action du bouton au niveau du controleur
                control.appuyerSurCalculatrice('x');
                
                //Pour implanter les données de l'accumulateur dans la zone de texte de l'affichage de la calculatrice 
                texteCalculatrice.setText(accu.resString());
                texteErreur.setText(accu.getErreur());
                
                //On vient incrémenter la mémoire de la calculatrice après avoir appuyé sur le bouton multiplier
                textePile0.setText(accu.getIndexMemoire(3)); 
                textePile1.setText(accu.getIndexMemoire(2));
                textePile2.setText(accu.getIndexMemoire(1));
                textePile3.setText(accu.getIndexMemoire(0));
            }
        });

        btAdd.setOnAction (new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                control.appuyerSurCalculatrice('+');
                texteCalculatrice.setText(accu.resString());
                texteErreur.setText(accu.getErreur());
                textePile0.setText(accu.getIndexMemoire(3));
                textePile1.setText(accu.getIndexMemoire(2));
                textePile2.setText(accu.getIndexMemoire(1));
                textePile3.setText(accu.getIndexMemoire(0));
            }
        });

        btDiv.setOnAction (new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                control.appuyerSurCalculatrice('/');
                texteCalculatrice.setText(accu.resString());
                texteErreur.setText(accu.getErreur());
                textePile0.setText(accu.getIndexMemoire(3));
                textePile1.setText(accu.getIndexMemoire(2));
                textePile2.setText(accu.getIndexMemoire(1));
                textePile3.setText(accu.getIndexMemoire(0));
            }
        });

        btSub.setOnAction (new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                control.appuyerSurCalculatrice('-');
                texteCalculatrice.setText(accu.resString());
                texteErreur.setText(accu.getErreur());
                textePile0.setText(accu.getIndexMemoire(3));
                textePile1.setText(accu.getIndexMemoire(2));
                textePile2.setText(accu.getIndexMemoire(1));
                textePile3.setText(accu.getIndexMemoire(0));
            }
        });
        
        btNeg.setOnAction (new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                control.appuyerSurCalculatrice('N');
                texteCalculatrice.setText(accu.getEcran());
                texteErreur.setText(accu.getErreur());
                textePile0.setText(accu.getIndexMemoire(3));
                textePile1.setText(accu.getIndexMemoire(2));
                textePile2.setText(accu.getIndexMemoire(1));
                textePile3.setText(accu.getIndexMemoire(0));
            }
        });

        btPush.setOnAction (new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                control.appuyerSurCalculatrice('P');
                textePile1.setText(texteCalculatrice.getText());
                texteCalculatrice.setText(accu.getEcran());
                texteErreur.setText(accu.getErreur());
                textePile0.setText(accu.getIndexMemoire(3));
                textePile1.setText(accu.getIndexMemoire(2));
                textePile2.setText(accu.getIndexMemoire(1));
                textePile3.setText(accu.getIndexMemoire(0));
            }
        });

        btReset.setOnAction (new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                control.appuyerSurCalculatrice('R');
                texteCalculatrice.setText(accu.getEcran());
                texteErreur.setText(accu.getErreur());
                textePile0.setText(accu.getIndexMemoire(3));
                textePile1.setText(accu.getIndexMemoire(2));
                textePile2.setText(accu.getIndexMemoire(1));
                textePile3.setText(accu.getIndexMemoire(0));
            }
        });

        btBackspace.setOnAction (new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                control.appuyerSurCalculatrice('B');
                texteCalculatrice.setText(accu.getEcran());
                texteErreur.setText(accu.getErreur());
                textePile0.setText(accu.getIndexMemoire(3));
                textePile1.setText(accu.getIndexMemoire(2));
                textePile2.setText(accu.getIndexMemoire(1));
                textePile3.setText(accu.getIndexMemoire(0));
            }
        });

        btSwap.setOnAction (new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                control.appuyerSurCalculatrice('S');
                texteErreur.setText(accu.getErreur());
                textePile0.setText(accu.getIndexMemoire(3));
                textePile1.setText(accu.getIndexMemoire(2));
                textePile2.setText(accu.getIndexMemoire(1));
                textePile3.setText(accu.getIndexMemoire(0));
            }
        });

        btDrop.setOnAction (new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                control.appuyerSurCalculatrice('D');
                texteErreur.setText(accu.getErreur());
                textePile0.setText(accu.getIndexMemoire(3));
                textePile1.setText(accu.getIndexMemoire(2));
                textePile2.setText(accu.getIndexMemoire(1));
                textePile3.setText(accu.getIndexMemoire(0));
            }
        });

        btPointDecimal.setOnAction (new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                control.appuyerSurCalculatrice('.');
                texteCalculatrice.setText(accu.getEcran());
                textePile0.setText(accu.getIndexMemoire(3));
                textePile1.setText(accu.getIndexMemoire(2));
                textePile2.setText(accu.getIndexMemoire(1));
                textePile3.setText(accu.getIndexMemoire(0));
                
            }
        });


        
        /*
        ICI ON VIENT CREER UN EFFET D OMBRE QUAND ON PASSE SUR L'ENSEMBLE DES BOUTONS
        */
       
        //Création d'un effet d'ombre
        DropShadow ombre = new DropShadow();
        	
        //On met l'effet quand la souris passe sur le bouton
        bt0.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                bt0.setEffect(ombre);
            }
        });
        
        //On enleve l'effet quand la souris quitte le bouton
        bt0.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                bt0.setEffect(null);
            }
        });

        bt1.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                bt1.setEffect(ombre);
            }
        });
        
        bt1.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                bt1.setEffect(null);
            }
        });

        bt2.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                bt2.setEffect(ombre);
            }
        });
        
        bt2.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                bt2.setEffect(null);
            }
        });

        bt3.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                bt3.setEffect(ombre);
            }
        });
        
        bt3.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                bt3.setEffect(null);
            }
        });

        bt4.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                bt4.setEffect(ombre);
            }
        });
        
        bt4.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                bt4.setEffect(null);
            }
        });

        bt5.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                bt5.setEffect(ombre);
            }
        });
        
        bt5.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                bt5.setEffect(null);
            }
        });

        bt6.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                bt6.setEffect(ombre);
            }
        });
        
        bt6.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                bt6.setEffect(null);
            }
        });

        bt7.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                bt7.setEffect(ombre);
            }
        });
        
        bt7.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                bt7.setEffect(null);
            }
        });

        bt8.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                bt8.setEffect(ombre);
            }
        });
        
        bt8.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                bt8.setEffect(null);
            }
        });

        bt9.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                bt9.setEffect(ombre);
            }
        });
        
        bt9.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                bt9.setEffect(null);
            }
        });

        btMult.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                btMult.setEffect(ombre);
            }
        });
        
        btMult.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                btMult.setEffect(null);
            }
        });

        btAdd.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                btAdd.setEffect(ombre);
            }
        });
        
        btAdd.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                btAdd.setEffect(null);
            }
        });

        btDiv.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                btDiv.setEffect(ombre);
            }
        });
        
        btDiv.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                btDiv.setEffect(null);
            }
        });

        btSub.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                btSub.setEffect(ombre);
            }
        });
        
        btSub.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                btSub.setEffect(null);
            }
        });

        btDrop.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                btDrop.setEffect(ombre);
            }
        });
        
        btDrop.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                btDrop.setEffect(null);
            }
        });

        btPointDecimal.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                btPointDecimal.setEffect(ombre);
            }
        });
        
        btPointDecimal.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                btPointDecimal.setEffect(null);
            }
        });

        btPush.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                btPush.setEffect(ombre);
            }
        });
        
        btPush.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                btPush.setEffect(null);
            }
        });

        btReset.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                btReset.setEffect(ombre);
            }
        });
        
        btReset.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                btReset.setEffect(null);
            }
        });

        btBackspace.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                btBackspace.setEffect(ombre);
            }
        });
        
        btBackspace.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                btBackspace.setEffect(null);
            }
        });

        btSwap.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                btSwap.setEffect(ombre);
            }
        });
        
        btSwap.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                btSwap.setEffect(null);
            }
        });
        
        btNeg.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                btNeg.setEffect(ombre);
            }
        });
        
        btNeg.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent e){
                btNeg.setEffect(null);
            }
        });
        
        
        
        /*
        ICI ON VIENT STYLISER L'AFFICHAGE : Bouton, Image, Affichage des calculs et résultat
        */
        
        //On stylise l'affichage qui correspond à la mémoire vive/instantanée (donc pas le stockage de la pile!!)
        Color couleurEcriture = Color.BLUE;
        texteCalculatrice.setTextFill(couleurEcriture);
        texteCalculatrice.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        
        Color couleurEcritureErr = Color.RED;
        texteErreur.setTextFill(couleurEcritureErr);
        texteErreur.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        
        
        //On stylise l'affichage de la grille des boutons et donc des boutons
        grilleBoutons.setHgap(15); // Espace horizontal entre les boutons 
        grilleBoutons.setVgap(15); // Espace vertical entre les boutons
        grilleBoutons.setAlignment(Pos.BOTTOM_CENTER);

        
        //On stylise l'affichage du stockage de la pile
        Color couleurEcriture2 = Color.WHITE;
	textePile0.setTextFill(couleurEcriture2);
        textePile0.setBackground(new Background(new BackgroundFill(Color.DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        textePile1.setTextFill(couleurEcriture2);
        textePile1.setBackground(new Background(new BackgroundFill(Color.DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        textePile2.setTextFill(couleurEcriture2);
        textePile2.setBackground(new Background(new BackgroundFill(Color.DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        textePile3.setTextFill(couleurEcriture2);
        textePile3.setBackground(new Background(new BackgroundFill(Color.DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        
	    
	// On rajoute les éléments textuels pour la mémoire à l'affichage
        ecranCalculatrice.getChildren().add(texteErreur);
        //ecranCalculatrice.setAlignment(Pos.TOP_CENTER);
	ecranCalculatrice.getChildren().add(textePile0);
	ecranCalculatrice.getChildren().add(textePile1);
	ecranCalculatrice.getChildren().add(textePile2);
        ecranCalculatrice.getChildren().add(textePile3);        
        ecranCalculatrice.getChildren().add(texteCalculatrice);
        ecranCalculatrice.setAlignment(Pos.TOP_CENTER);
        
        
        
        calculatrice.setStyle("-fx-font: 40px Dialog"); //On définit la police et la taille des écritures
        // On rajoute l'interface élaborée précédemment à notre calculatrice
        calculatrice.getChildren().add(ecranCalculatrice);
        calculatrice.getChildren().add(grilleBoutons); 

        st.setTitle("Calculatrice 2IA"); //Titre de l'interface/la fenêtre
        st.setScene(scene); //On implémente la scene
        st.show(); //On l'affiche
    }

    @Override
    public void change(ArrayList<String> data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void change(String accu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

