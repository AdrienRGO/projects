# -*- coding: utf-8 -*-
"""
Created on Fri Nov  5 16:58:21 2021

@author: Louis
"""

from FoodCropFactory import FoodCropFactory
from FoodCropDataset import FoodCropDataset

FcF = FoodCropFactory()
FcD = FoodCropDataset()

FcD.load("FeedGrainsTest.csv")
#Ou FcD.load("FeedGrains.csv")

#Recherche = FcD.findMeasurements(None,None,None,None) #FONCTIONNE
Recherche = FcD.findMeasurements('Barley',None,None,None) #FONCTIONNE
#Recherche = FcD.findMeasurements('Barley','Prices','United States','Other') #PROBLEME
#Recherche = FcD.findMeasurements('Barley',None,'United States',None) #PROBLEME


print(Recherche)

