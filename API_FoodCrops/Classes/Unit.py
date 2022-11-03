# -*- coding: utf-8 -*-
"""
Created on Fri Oct 15 15:19:54 2021

@author: Louis
"""
#Pour créer des classes abstraites
#from abc import ABC, abstractmethod
from Describable import Describable

class Unit(Describable): 
        
    def __init__(self, identite: int, name: str):
             
        self.identite = identite
        self.name = name 
    
    #@abstractmethod
    #def methode_abstraite(self):
    #    pass
      
    def describe(self):
        return str(self.name) #Pour obtenir le nom de la caractéristique étudiée 
                              #comme le volume, le prix, etc...
                         
    def getName(self):
        
        return self.name #On a besoin de ce que l'on compte recherche dans le findMeasurements, donc ici le nom de l'unité comme 'Volume', 'Surface', etc...
 
