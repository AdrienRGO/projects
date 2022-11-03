# -*- coding: utf-8 -*-
"""
Created on Thu Oct 28 09:33:42 2021

@author: Louis
"""

#Cette classe sert à ranger tous ce qui n'est pas une des autres classes héritants de Unit

from Unit import Unit  #Nom du fichier puis classe dans le fichier
#Pas besoin d'import Describable car Other hérite de Unit

class Other(Unit):  
    
    def __init__(self, identite: int, what: str):
        super().__init__(identite, name = "Other") 
        
    def describe(self):
        return str(self.name) #Pour obtenir le nom de la caractéristique étudiée 
                              #comme le volume, le prix, etc...

