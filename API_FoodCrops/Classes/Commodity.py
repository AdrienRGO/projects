# -*- coding: utf-8 -*-
"""
Created on Wed Oct 20 16:39:02 2021

@author: Louis
"""

from enum import Enum
from Describable import Describable

# On met cette classe en 1er car Commodity fait appel à elle
class CommodityGroup(Enum):
    
    ANIMAL_PROTEIN_FEEDS = 8
    BARLEY = 9
    BYPRODUCT_FEEDS = 10
    COARSE_GRAINS = 11
    CORN = 12
    ENERGY_FEEDS = 13
    FEED_GRAINS = 14
    GRAIN_PROTEIN_FEEDS = 15
    HAY = 16
    OATS = 17
    OILSEED_MEAL_FEEDS = 18 
    PROCESSED_FEEDS = 19
    SORGHUM = 20

class Commodity(Describable):
    
    def __init__(self, group : CommodityGroup, identite : int, name : str):
        
        self.identite = identite #identité numérique de la commodité
        self.__name = name #nom de la commodité
        self.group = group #groupe d'appartenance de la commodité
    
    def describe(self):
        
        return "La commodité a pour identité numérique "+str(self.identite)+". En réalité, elle se nomme "+str(self.__name)+" et fait partie du groupe des "+str(self.group.getName())
    
    def getName(self):
       return self.group #On a besoin de ce que l'on compte recherche dans le findMeasurements, donc ici le groupe de la commodité





    



