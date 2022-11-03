# Complexity and recursion: Attribution

## **1. About this project**

This problem tries to solve the **assignment problem**.  
The goal is to use the principle of **recursivity** to solve it. Also, the objective is to **optimize the code** by finding the number of exact solutions as fast as possible and thus **reducing the complexity**.

The number of students and subjects **varies according to our data** which are contained in the csv files in the *'data'* folder. 

<div id="csv data" align = "center">
  <img src="https://user-images.githubusercontent.com/105392989/175880351-bf01bc2e-9a0b-4cb1-87c8-d7e43ad5fa2d.png" width="900">
</div>

For each case, each student can formulate **4 wishes** from the most wanted to the less wanted.  
To solve the problem, we map each wish to a precise integer value. Indeed, the most wanted wish is assigned by a waight of *8*, wish 2 a weight of *4*, the 3rd one of *2* and finally the last one will only count for *1*. Several students belonging to the same assignment constitute a **team**.

Please note that there are **2 main constraints**:
  1. A student can only belong to one team
  2. A team is made up of a maximum of 3 students

