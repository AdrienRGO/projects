/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class Pile extends java.util.Stack<Double>{
	
	private ArrayList<Double> valeurs = new ArrayList<Double>();
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	public void avertir(){ //Pour prévenir l'utilisateur des changements de valeurs suite à une opération
		pcs.firePropertyChange("Modification de: ", valeurs, valeurs);
	}
        
        
	public void addPropertyChangeListener(PropertyChangeListener listener){
		this.pcs.addPropertyChangeListener(listener);
	}	
	
        //On redéfinit la méthode push d'une pile : en plus de push, on avertit l'utilisateur du changement fait suite à la méthode (avertir())
	public void push(double valeur) {

		valeurs.add(valeur);
		avertir();
		super.push(valeur);

	}
        
        /*
        Pour toutes les méthodes, même principe et même raisonnement que pour la méthode push
        */
        
        @Override
	public Double pop(){

		valeurs.remove(valeurs.size()-1);
		avertir();
		return super.pop();

	}

	public void drop() {
		avertir();
		pop();

	}
	
	public void reset() {

		valeurs.clear();
		avertir();
		super.clear();
	}
	
	
	public void swap() {
		double dernier = this.pop(); //On retire le dernier element en premier
		double av_dernier = this.pop();
		avertir();
		this.push(dernier);
		this.push(av_dernier);

	}
		
	
}
