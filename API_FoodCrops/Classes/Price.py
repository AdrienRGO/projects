# -*- coding: utf-8 -*-
"""
Created on Fri Oct 15 15:11:43 2021

@author: Louis
"""

from Unit import Unit  #Nom du fichier puis classe dans le fichier

class Price(Unit):  
    
    def __init__(self, identite: int):
        super().__init__(identite, name = "Price") 
        
    def describe(self):
        return str(self.name) #Pour obtenir le nom de la caractéristique étudiée 
                              #comme le volume, le prix, etc...
                            
