/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import modele.*; //on import tout ce qu'on a codé dans le package model
import vue.*; //on import tout ce qu'on a codé dans le package vue

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class Controleur extends Application implements PropertyChangeListener, EventHandler{
		
                //Définition des attributs
		private InterfaceGraphique fenetre;        
		private Accumulateur model = new Accumulateur();
		
                @Override
		public void start(Stage primaryStage) throws Exception {
                    
                    	model.addPropertyChangeListener(this);

	        	Pile memoire = model.getMemoire();
	        	memoire.addPropertyChangeListener(this);
                        
                        //Pour afficher l'interface graphique
	        	InterfaceGraphique fenetre = new InterfaceGraphique(primaryStage, this);
	        	fenetre.affiche();

	        }		
                
                //Quand on appuie sur un bouton à partir de l'interface on vient ajouter ce chiffre dans notre accumulateur sous forme de caractères
		public void appuyerSurCalculatrice(char chr) {
                    
                    model.accumuler(chr);
                    System.out.println(chr);//Initialement pour tester que la méthode s'exécutait correctement

	        }
                
                //Pour récupérer la valeur stockée dans l'accumulateur pour l'interface graphique
                public Accumulateur getAccu(){
                    return model;
                }
                
                //Methode main pour lancer correctement le programme
		public static void main(String[] args) { 
                    launch(args);

		}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handle(Event event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
