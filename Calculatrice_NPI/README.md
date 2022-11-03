# Calculatrice NPI

## **1. About this project**

The objective of this project was to realize a calculator respecting the **Reverse Polish Notation**.

It also allows us understand and apply the design of a graphical interface quite simply in order to use the **Model-View-Controller (MVC) pattern** by cutting and linking each of our parts.

This project is divided into 3 packages:
  1. controleur with the class *Controleur*
  2. vue with the classes *IView* and *InterfaceGraphique*.
  3. modele with the classes *Pile*, *Accumulateur* and *IAccumulateur*

The controller side allows to make the **link between the view and the model**.

The visual aspect is established in the 'view package' with the *InterfaceGraphique* class.


