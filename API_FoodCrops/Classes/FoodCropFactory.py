# -*- coding: utf-8 -*-
"""
Created on Wed Oct 20 16:21:42 2021

@author: Adrien
"""


from Commodity import CommodityGroup, Commodity
from Unit import Unit
from Volume import Volume
from Price import Price
from Weight import Weight
from Surface import Surface
from Count import Count
from Other import Other
from Indicator import IndicatorGroup, Indicator
from Measurement import Measurement




class FoodCropFactory :
    
    def __init__(self):
        
        #On initialise les dictionnaires
        self.__unitsRegistry = dict()
        self.__IndicatorsRegistry = dict()
        self.__commodityRegistry = dict()
        
    #Fonction pour créer un volume dans les dictionnaires. La structure est identique pour toutes les fonctions qui la suivent     
    def createVolume(self, identite : int, multiplier : float):
        if identite not in self.__unitsRegistry: #On vérifie si l'élément est déjà dans le dictionnaire
            self.__unitsRegistry[identite] = Volume(identite, multiplier) #On crée et on ajoute le volume dans le dictionnaire
        return self.__unitsRegistry[identite]    #on renvoie l'élément dont la clé est "identite"
    
    #Globalement, on regarde si l'élément appartient au dictionnaire et si ce n'est pas le cas, alors on l'ajoute 

    def createPrice(self, identite : int):
        if identite not in self.__unitsRegistry:                                   
            self.__unitsRegistry[identite] = Price(identite)    
        return self.__unitsRegistry[identite] 
    
    def createWeight(self, identite : int, multiplier : float):
        if identite not in self.__unitsRegistry:                                   
            self.__unitsRegistry[identite] = Weight(identite, multiplier)    
        return self.__unitsRegistry[identite] 
    
    def createSurface(self, identite : int, multiplier : float):
        if identite not in self.__unitsRegistry:                                   
            self.__unitsRegistry[identite] = Surface(identite, multiplier)    
        return self.__unitsRegistry[identite] 
    
    def createCount(self, identite : int, what : str):
        if identite not in self.__unitsRegistry:                                   
            self.__unitsRegistry[identite] = Count(identite, what)    
        return self.__unitsRegistry[identite] 
    
    def createOther(self, identite : int, what : str):
        if identite not in self.__unitsRegistry:                                   
            self.__unitsRegistry[identite] = Other(identite, what)    
        return self.__unitsRegistry[identite] 
        
    def createCommodity(self, group : CommodityGroup, identite : int, name : str):
        if identite not in self.__commodityRegistry:
            self.__commodityRegistry[identite] = Commodity(group, identite, name)
        return self.__commodityRegistry[identite]
    
    def createIndicator(self, identite : int, frequency : int, freqDesc : str, geogLocation : str, indicatorGroup : IndicatorGroup, unit : Unit):
        if identite not in self.__IndicatorsRegistry:
            self.__IndicatorsRegistry[identite] = Indicator(identite, frequency, freqDesc, geogLocation, indicatorGroup, unit)
        return self.__IndicatorsRegistry[identite]

    def createMeasurement(self, identite : int, year : int, value: float, timeperiodId : int, timeperiodDesc : str, commodity : Commodity, indicator : Indicator):    
        return Measurement(identite, year, value, timeperiodId, timeperiodDesc, commodity, indicator)          #les mesures ne sont pas uniques comme les unités 
