# API_FoodCrops

## **1. About this project**

This project aimed at learning Python and more precisely **object-oriented programming** with the management of classes, attributes, functions...  
It was also a first introduction to dictionaries.
This project was done in a school setting with another classmate.  

The idea behind this project is quite simple. Indeed, from a DB under excel file, we try to **find and match all the elements that meet one or more conditions** to know :
  - **The type of the commodity**
  - **The group to which the commodity in question belongs**
  - **The location of the harvest**
  - **The unit of measurement**

To start the project, we first created the classes and elements associated with the class diagram. Once we had this base, we tried to **understand and relate all these classes**.

## **2. The classes**

Our main class is the _FoodCropDataset_ class which will allow us to :
  - **Load the excel file** we want to browse 
  - **Launch the search for elements** according to the 4 parameters defined previously  

It is through this class that we **instantiate the data** of the Excel file.  
However, in order to be able to instantiate them, we need to **create these elements** and **fill them in a dictionary**, and this is what the _FoodCropFactory_ class is for. We define all the methods to create these elements and dictionaries.  
All other classes simply **characterize a part of each observation** indicated in the excel file.

## **3. Running the API** 

It is in the _Main_ file that we make the changes.  
First of all, to load the data we **indicate the path** where the Excel file is located as follows: 

```python
from FoodCropFactory import FoodCropFactory
from FoodCropDataset import FoodCropDataset

FcF = FoodCropFactory()
FcD = FoodCropDataset()

FcD.load("FeedGrainsTest.csv")
```

Of course, you'll presumably need to change the path I used by doing so :
```python 
FcD.load(r'Path to the Excel\FeedGrainsTest.csv')
```

Finally it is necessary to **fill in the characteristics** that we want to assign to the elements we are looking for. To do this, always in the _Main_ : 
```python 
Recherche = FcD.findMeasurements('Barley',None,None,None) #CommodityType - CommodityGroup - Location - Unit
#Recherche = FcD.findMeasurements(None,None,None,None) 
```
