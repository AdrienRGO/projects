/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

public interface IAccumulateur {
	
    //Pour comprendre le fonctionnement de toutes ces méthodes, on peut regarder le détail dans la classe Accumulateur
	public void push();
	public void drop();
	public void swap();
	public void add();
	public void sub();
	public void mult();
	public void div();	
	public void neg();	
	public void backspace();	
	public void accumuler(char character);	
	public void reset();	
	
}
