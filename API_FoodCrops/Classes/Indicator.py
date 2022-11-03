# -*- coding: utf-8 -*-
"""
Created on Wed Oct 20 16:42:13 2021

@author: Louis
"""
from enum import Enum #package python déjà implémenté
from Describable import Describable #from le fichier import la classe
from Unit import Unit

# On met cette classe en 1er car la classe Indicator fait appel à elle
class IndicatorGroup(Enum): 
    
    PRICES = 1 #D'après le fichier texte de l'énoncé 
    SUPPLY_AND_USE = 2
    EXPORTS_AND_IMPORTS = 3
    TRANSPORTATION = 4
    ANIMAL_UNIT_INDEXES = 5
    QUANTITIES_FED = 6
    FEED_PRICE_RATIOS = 7 

class Indicator(Describable):
    
    def __init__(self, identite : int, frequency : int, freqDesc : str, 
            geogLocation : str, indicatorGroup : IndicatorGroup, unit : Unit):
        
        self.identite = identite #identité numérique de l'indicateur
        self.__frequency = frequency #fréquence numérique de l'indicateur
        self.__freqDesc = freqDesc #la fréquence de récolte
        self.__geogLocation = geogLocation #origine de l'indicateur
        self.indicatorGroup = indicatorGroup #groupe d'appartenance de l'indicateur
        self.unit = unit     #unité de mesure de l'indicateur  
        
        
    def describe(self):
        
        return "L'indicateur a pour identité numérique "+str(self.identite) +". Sa fréquence numérique correspond au numéro "+str(self.__frequency)+". La fréquence de récolte est "+str(self.__freqDesc)+". Cet indicateur est orignaire de "+str(self.__geogLocation)+". Egalement, il appartient au groupe des "+str(self.indicatorGroup.getName())+" et son unité est un(e) "+str(self.unit.getName())     
        

    def getName(self):
        
       return str(self.indicatorGroup) #On a besoin de ce que l'on compte recherche dans le findMeasurements, donc ici le groupe de l'indicateur

        
