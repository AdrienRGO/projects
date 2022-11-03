# -*- coding: utf-8 -*-
"""
Created on Fri Oct 15 15:09:44 2021

@author: Louis
"""

from Unit import Unit  #Nom du fichier puis classe dans le fichier

class Volume(Unit):  
    
    def __init__(self, identite: int, multiplier: float):
        super().__init__(identite, name = "Volume")     
        
        self.__multiplier = multiplier
        
    def describe(self):
        return str(self.name) #Pour obtenir le nom de la caractéristique étudiée 
                              #comme le volume, le prix, etc...
