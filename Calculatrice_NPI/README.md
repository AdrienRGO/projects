# Calculatrice NPI

## **1. About this project**

The objective of this project was to realize a calculator respecting the **Reverse Polish Notation**.

This is a different calculation method than the conventional one. Indeed, it will allow us to have a real **management of the storage** notably through the concept of *stack* and *accumulator* notably for the order of the operations.

This project also allows us to design a graphical interface quite simply in order to use the **Model-View-Controller (MVC) pattern** by cutting and linking each of our parts.

So we have 3 packages:
  1. controleur with the class *Controleur*
  2. vue with the classes *IView* and *InterfaceGraphique*.
  3. modele with the classes *Pile*, *Accumulateur* and *IAccumulateur*

The controller side allows to make the **link between the view and the model**.

More precisely, the mathematical aspect is defined in the model part, in particular with the *Accumulateur* class and the various arithmetic operations.

Finally, the visual aspect is defined in the view package with the *InterfaceGraphique* class in order to display to the user all the numerical processing within the *Pile* and *Accumulateur* classes.

Here is a simple and effective example of the principle of inverse Polish notation :

<div id="inverse Polish notation" align = "center">
  <img src="https://user-images.githubusercontent.com/105392989/173848897-18db84e6-c6b0-447e-941a-f8f800e56bbb.png" width="600">
</div>

## **2. Use and basic principles** 

We distinguish two use cases:

- Case 1 called _flexible_ : The value displayed in the dynamic cell of the calculator *(direct memory)* can be used without having been pushed beforehand
- Case 2 called _strict_ : Only the values of the stack can be used as operands *(storage memory)*

So I focused on the **use case n°2** where we can only perform operations with values directly stored in the stack and not waiting to be pushed.

## **3. About our calculator**

The calculator is able to perform the classical arithmetic operations:
  - The **division** (with the management of the division by 0)
  - The **multiplication**
  - The **addition**
  - The **subtraction**
  - The **opposite** (with the transformation in negative/positive)

I also propose some additional functionalities:
  - The **swap** which allows to exchange the last two values stored in the stack (and which are thus at the top of the stack)
  - **Deletion** to correct the dynamic element being written (before being sent to the stack)
  - **Drop** to delete the last value stored in the stack
  - The **reset** which simply reinitializes our entire calculator, in particular with the total erasure of the stack
  - The **decimal point** to operate with decimals and not only with reals

For the memory management, I allow a storage of '**n**' elements so in theory an infinity (modulo the capacities and physical/software resources).

However, I only display the **last 5 values stored** in the stack! These values are displayed **from the most recent to the oldest from bottom to top**.

Here is our interface after execution : 

<div id="interface" align = "center">
  <img src="https://user-images.githubusercontent.com/105392989/174567763-825b2509-2f0d-4be3-84ff-a93ce4f3af01.png" width="850">
</div>

## **4. Some ideas to improve and develop this project**

- The addition of extra buttons to get closer to a scientific calculator (cos, sin, tan, sqrt, power, imaginary, etc...)

- The addition of a command to define functions or equations

- The addition of a graphical editor to display a function

- The use of the calculator directly via the mouse with a keyboard at the same time

- The use of JComponents for the development of the graphical interface (according to the WEB documentation, JComponents are the most used elements to build an interface)

- Instant error display: when an error occurs (e.g. division by 0), you have to press any button on the calculator to get the error. Also, after an error it is recommended to reset the calculator with the AC button to make sure the calculator is working properly

- Improve the graphical interface a bit more, especially the display of the storage memory and the result. Better distinguish/separate the calculator control part (buttons) and the calculator display part (result and memory)
