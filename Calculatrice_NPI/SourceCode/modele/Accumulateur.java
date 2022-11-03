/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class Accumulateur implements IAccumulateur{
	
        private String erreur = "";
	private Pile memoire = new Pile();
	private String ecran = ""; //variable qui decrit ce qui est affiche à l'ecran
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}
	
        @Override
	public void push(){ //Pour stocker un chiffre dans notre pile

			memoire.push(Double.parseDouble(ecran)); // Convertir String to Double
			ecran = "";
                        erreur = "";
        }	
	

        @Override
	public void drop() { //Pour jeter l'ensemble de la pile

		if (memoire.size()<1) {
			System.out.println("Erreur : La pile est deja vide");
			ecran = "";
                        erreur = "Erreur : La pile est deja vide";
		}
		else {
			memoire.drop();
			ecran = "";
                        erreur = "";
		}
	}
	

        @Override
	public void swap() { //Pour échanger les deux derniers éléments de la pile entre eux

		if (memoire.size()<2) {
			System.out.println("Erreur : Pas assez d'elements dans la pile pour échanger");
			ecran = "";
                        erreur = "Erreur : Pas assez d'elements dans la pile pour échanger";
                    }
		else {
			memoire.swap();
			ecran = "";
                        erreur = "";
		}
	}
	

        @Override
	public void add() { //Pour additioner les deux derniers éléments de la pile

		if (memoire.size()<2) {
			System.out.println("Erreur : Pas assez d'elements dans la pile pour additionner ");
			ecran = "";
                        erreur = "Erreur : Pas assez d'elements dans la pile pour additionner ";
			}
		else {
			Double add1 = memoire.pop();
			Double add2 = memoire.pop();  
			memoire.push(add1+add2);
			ecran = "";
                        erreur = "";
			}
		}	
	

        @Override
	public void sub() { //Pour soustraire les deux derniers éléments de la pile

		if (memoire.size()<2) {
			System.out.println("Erreur : Pas assez d'elements dans la pile pour soustraire");
			ecran = "";
                        erreur = "Erreur : Pas assez d'elements dans la pile pour soustraire";
			}
		else {
			Double sub2 = memoire.pop(); //On pop bien sub2 en premier car c'est le dernier element
			Double sub1 = memoire.pop(); 
			memoire.push(sub1-sub2);
			ecran = "";
                        erreur = "";
			}
		}	
	

        @Override
	public void mult() { //Pour multiplier les deux derniers éléments de la pile

		if (memoire.size()<2) {
			System.out.println("Erreur : Pas assez d'elements dans la pile pour multiplier");
			ecran = "";
                        erreur = "Erreur : Pas assez d'elements dans la pile pour multiplier";
			} 
		else {
			Double mult1 = memoire.pop();
			Double mult2 = memoire.pop();  
			memoire.push(mult1*mult2);
			ecran = "";
                        erreur = "";
		}
	}
	
        @Override
	public void div() { //Pour faire le rapport entre les deux derniers éléments de la pile

		if (memoire.size()<2) {
			System.out.println("Erreur : Pas assez d'elements dans la pile pour diviser");
			ecran = "";
                        erreur = "Erreur : Pas assez d'elements dans la pile pour diviser";
			}
		else {
			Double den = memoire.pop(); //Même réflexion que pour la soustraction
			Double num = memoire.pop();  
			if (den != 0) {	
				memoire.push(num/den);
				ecran = "";
                                erreur = "";
			}
			else {
				System.out.println("Erreur : le denominateur est nul donc division impossible");   //on affiche une erreur si le denominateur est nul
				ecran = "";
                                erreur = "Erreur : le denominateur est nul donc division impossible";
			}
		}
	}

	
        @Override
	public void neg(){ //Pour prendre l'opposé du dernier chiffre de la pile

		if (memoire.size()<1) {
			System.out.println("Erreur : aucune valeur dans la pile, donc on ne peut rendre négatif");
			ecran = "";
                        erreur = "Erreur : aucune valeur dans la pile, donc on ne peut rendre négatif";
			} 
		else {
			double neg = memoire.pop(); //Initialisation de la variable
			ecran = "";
                        erreur = "";
			if (neg == 0){
				memoire.push(neg); //On retourne 0 dans dans le cas où on a un 0
				ecran = "";
                                erreur = "";
				} 
			else {
				memoire.push(-neg); //On retourne l'opposé sinon
				ecran = "";
                                erreur = "";
			}
		}
	}	
	
	
        @Override
	public void backspace() { //Pour effacer le dernier caractère rentré à l'écran (donc nombre non push)

		ecran = ecran.substring(0,ecran.length()-1); //On supprime le dernier élément affiché à l'écran
                
	}
        
        @Override
	public void reset() { //Pour effacer toutes les données stockées dans la pile, on remet tout à "0" = à rien
		memoire.clear();
		ecran = "";
                erreur = "";
	}
		
        @Override
    public void accumuler(char character){ //Permet de mettre à jour l'acccumulateur selon la méthode appelée définie précédemment
		 if (character == 'N') {      
                this.neg();
            } 
            else if (character == '+') {
                this.add();
                System.out.println(res());
            } 
            else if (character == '-') {
                this.sub();
            } 
            else if (character == 'x') {
                this.mult();
            } 
            else if (character == '/') {
                this.div();
            } 
            else if (character == 'B') { //B pour Backspace
                this.backspace();
            } 
            else if (character == 'P') { //P pour Push
                this.push();
            }  
            else if (character == 'S') {
                this.swap();
            } 
            else if (character == 'D') {
                this.drop();
            } 
            else if (character == 'R') {
                this.reset();
            } 
            else {
            	ecran += character; //Cas où notre caractère correspond à un chiffre
            }
    }
	
	public double res() { //Pour récupérer le résultat des opérations
		return memoire.get(memoire.size()-1); //On récupère le dernier élément 
	}
           
	
	public Pile getMemoire() { //Pour récupérer la mémoire au niveau du controleur et faire le lien avec la partie modèle
		return this.memoire;
	}
        

	public String getIndexMemoire(int n) { //Nous permet de récuperer les valeurs à stocker et afficher dans la pile 
		
		ArrayList<String> liste = new ArrayList();
		for(int i=0;i<n+1;i++) {
			if (memoire.size()>i) {
				liste.add(memoire.get(memoire.size()-1-i).toString());
			}
			else {
				liste.add("");
			}
		}
		return liste.get(n);
	}
        

        
        public String getEcran(){ 
        //Permet de faire le lien entre la partie modèle et la partie vue en récupérant
        //ce qui est stocké dans l'attribut écran
            return this.ecran; 
        }
        
        public String getErreur(){ 
        //Permet d'indiquer à l'utilisateur une erreur dans la partie interface
            return this.erreur;
        }

	 public String resString() { 
        //Pour récupérer le résultat sous forme de String pour l'afficher dans le label
		return memoire.get(memoire.size()-1).toString(); 
                //On récupère le dernier élément 
	}
}
