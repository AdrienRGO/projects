/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.util.ArrayList;

public interface IView{

    public void affiche();
    public void change(ArrayList<String> data);
    public void change(String accu);

}
