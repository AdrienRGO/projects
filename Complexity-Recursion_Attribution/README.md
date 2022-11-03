# Complexity and recursion: Attribution

## **1. About this project**

This problem is an **assignment problem**.  
We will therefore use **recursivity** to solve it. Also, the objective is to **optimize the code** by finding the number of exact solutions as fast as possible and thus **reducing the complexity**.

The number of students and subjects **varies according to our data** which are contained in the csv files in the *'data'* folder. 

<div id="csv data" align = "center">
  <img src="https://user-images.githubusercontent.com/105392989/175880351-bf01bc2e-9a0b-4cb1-87c8-d7e43ad5fa2d.png" width="900">
</div>

For each case, each student has the right to **formulate 4 wishes in decreasing order of preference** for the assignment.  
Thus, wish 1 will have a value of *8*, wish 2 a weight of *4*, the 3rd one of *2* and finally the last one will only count for *1*. Several students belonging to the same assignment constitute a **team**.

Please note that there are **2 main constraints**:
  1. A student can only belong to one team
  2. A team is made up of a maximum of 3 students

It is therefore the **sum of the wishes** (and therefore their weight) that must be **optimized**.

The solutions we find are indicated in the file **CR_Attribution**. We find there :
  - The number of students
  - The number of missions
  - The optimized sum
  - The execution time to find the solution

<div id="results" align = "center">
  <img src="https://user-images.githubusercontent.com/105392989/175881003-4ea3c968-2d27-43d9-901b-80fc2af94d88.png" width="900">
</div>

This project was done in a school context with another classmate.
