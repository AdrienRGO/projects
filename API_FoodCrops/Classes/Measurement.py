# -*- coding: utf-8 -*-
"""
Created on Fri Oct 15 14:40:49 2021

@author: Louis
"""

from Commodity import Commodity 
from Indicator import Indicator
from Describable import Describable

class Measurement(Describable): #Ici on crée la classe Measurement
    
    #Constructeur de la classe A, on peut donner des indices au
    #sujet du type des paramètres avec param: type et fixer des
    #valeurs par défaut avec param: type = valeur
    
    def __init__(self, identite: int, year: int, value: float, timeperiodId: int,
        timeperiodDesc: str, commodity: Commodity, indicator: Indicator):        
        
        #Attributs privées (__) en préfixe
        self.__year = year #année où la valeur a été prise
        self.__value = value #valeur de la mesure
        self.__timeperiodId = timeperiodId #identité numérique de la période de récolte
        self.__timeperiodDesc = timeperiodDesc #période de récolte
        self.commodity = commodity #La commodité mesurée
        self.indicator = indicator #L'indicateur utilisé pour la mesure
    
    def describe(self):
        
        return "La mesure a été prise en "+str(self.__year)+". Elle a pour valeur "+str(self.__value)+". Son identité numérique et sa période de récolte correspondent respectivement à "+str(self.__timeperiodId)+" et "+str(self.__timeperiodDesc)+". La commodité mesurée est "+str(self.commodity.getName())+" avec l'aide de l'indicateur suivant "+str(self.indicator.getName())
        
