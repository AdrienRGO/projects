# -*- coding: utf-8 -*-
"""
Created on Fri Oct 15 15:12:42 2021

@author: Louis
"""

from Unit import Unit  #Nom du fichier puis classe dans le fichier

class Count(Unit):  
    
    def __init__(self, identite: int, what: str):
        super().__init__(identite, name = "Count") 
        
        self.__what = what

    def describe(self):
        return str(self.name) #Pour obtenir le nom de la caractéristique étudiée 
                              #comme le volume, le prix, etc...
