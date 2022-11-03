# -*- coding: utf-8 -*-
"""
Created on Wed Oct 20 17:00:34 2021

@author: Adrien
"""

from FoodCropFactory import FoodCropFactory
import pandas as pd
from Unit import Unit
from Indicator import IndicatorGroup
from Commodity import CommodityGroup


class FoodCropDataset:
   
    def __init__(self):
        
        #On créé les dictionnaires des quatre critères
        self.__unitMeasurementIndex = dict()
        self.__commodityGroupMeasurementIndex = dict()
        self.__indicatorGroupMeasurementIndex = dict()
        self.__locationMeasurementIndex = dict()
        self.factory = FoodCropFactory()
    
    #Fonction pour charger la base de données
    def load(self, datasetPath : str):   
        
        dataframe = pd.read_csv(datasetPath, error_bad_lines=False, sep = ';', low_memory=False) #On charge la BDD que l'on vient lire
        
        nbrMeasurement = 0  #Compteur qui indique à quelle ligne on est dans la boucle
        
        #print(dataframe.columns) #Permettait d'afficher les colonnes et de debugger en cas d'erreur d'adressage
        
        for index, row in dataframe.iterrows():
            
            SC_Group_Desc = row['SC_Group_Desc'] #Nous permet d'éviter les erreurs d'adressage contrairement à un row[0]         
            SC_GroupCommod_Desc = row['SC_GroupCommod_Desc']
            SC_GeographyIndented_Desc = row['SC_GeographyIndented_Desc'].lstrip() #On ne prend pas compte les espaces en trop avec .lstrip()
            SC_Commodity_ID = row['SC_Commodity_ID']
            SC_Commodity_Desc = row['SC_Commodity_Desc']
            SC_Attribute_ID = row['SC_Attribute_ID']
            SC_Unit_ID = row['SC_Unit_ID']
            SC_Unit_Desc = row['SC_Unit_Desc']
            Year_ID = row['Year_ID']
            SC_Frequency_ID = row['SC_Frequency_ID']
            SC_Frequency_Desc = row['SC_Frequency_Desc']
            Timeperiod_ID = row['Timeperiod_ID']
            Timeperiod_Desc = row['Timeperiod_Desc']
            Amount = row['Amount']

            #On vient instancier les unités associées à l'item en allant chercher une correspondance dans la colonne 12 : SC_UNIT_DESC
            #Ici on s'intéresse à toutes les unités possibles que l'on vient instancier selon le nom de l'unité et de ce qu'elle représente : prix, volume, surface, autre, etc...
            if "bushels" in SC_Unit_Desc.lower():
                if "million"in SC_Unit_Desc.lower():
                    instanciationUnit = self.factory.createVolume(SC_Unit_ID,1e6) #Bushel représente un volume donc on crée un volume. On a 1e6 qui représente le million
                else :
                    instanciationUnit = self.factory.createVolume(SC_Unit_ID,1)


            elif "acres" in SC_Unit_Desc.lower():    #Pas besoin de traiter le cas du type "per acre" car acre est écrit au singulier et pas au pluriel
                if "million" in SC_Unit_Desc.lower():
                    instanciationUnit = self.factory.createSurface(SC_Unit_ID,1e6) 
                else:
                    instanciationUnit = self.factory.createSurface(SC_Unit_ID,1000)
           

            elif "ton" in SC_Unit_Desc.lower():
                if "1,000 metric" in SC_Unit_Desc.lower():            
                    instanciationUnit = self.factory.createWeight(SC_Unit_ID,1000)
                elif "1,000 tons" in SC_Unit_Desc.lower():            
                    instanciationUnit = self.factory.createWeight(SC_Unit_ID,1000)
                
                elif "million metric" in SC_Unit_Desc.lower():            
                    instanciationUnit = self.factory.createWeight(SC_Unit_ID,1e6)
                elif "Ton" in SC_Unit_Desc:
                    instanciationUnit = self.factory.createWeight(SC_Unit_ID,1)
            

            elif "liters" in SC_Unit_Desc.lower():
                if "1,000" in SC_Unit_Desc.lower():
                   instanciationUnit = self.factory.createVolume(SC_Unit_ID,1000)


            elif  "Gallons" in SC_Unit_Desc.lower():
                instanciationUnit = self.factory.createVolume(SC_Unit_ID,1)


            elif "hectare" in SC_Unit_Desc.lower():
                instanciationUnit = self.factory.createSurface(SC_Unit_ID,1000)
            

            elif "Carloads originated" in SC_Unit_Desc.lower():
                instanciationUnit = self.factory.createCount(SC_Unit_ID,"Carloads originated")      
            
            
            elif "Million animal units" in SC_Unit_Desc.lower():
                instanciationUnit = self.factory.createCount(SC_Unit_ID,"Million animal units") 
           
            else :
                instanciationUnit = self.factory.createOther(SC_Unit_ID,SC_Unit_Desc) 



            #On instancie le reste du modèle pour la ligne de la mesure associée
            instanciationCommodity = self.factory.createCommodity(SC_GroupCommod_Desc, SC_Commodity_ID, SC_Commodity_Desc)
            instanciationIndicator = self.factory.createIndicator(SC_Attribute_ID, SC_Frequency_ID, SC_Frequency_Desc, SC_GeographyIndented_Desc, SC_Group_Desc, instanciationUnit) 
            instanciationMeasurement = self.factory.createMeasurement(nbrMeasurement,Year_ID, Amount, Timeperiod_ID,Timeperiod_Desc, instanciationCommodity, instanciationIndicator) #Comme il y autant de lignes que de mesures, on affecte le numéro de ligne comme Id pour la mesure
            
            
            #On vérifie si les instanciations font parties des dictionnaires. Si ce n'est pas le cas alors on ajoute sa mesure
            if str(instanciationUnit.getName()) not in self.__unitMeasurementIndex: #On vérifie si l'instanciation n'appartient pas au dictionnaire
                self.__unitMeasurementIndex[str(instanciationUnit.getName())] = []  #Si c'est le cas, on crée une liste vide associée à la clé "instanciationUnit" qui correspond à l'unité de la ligne correspondante
            self.__unitMeasurementIndex[str(instanciationUnit.getName())].append(instanciationMeasurement.describe()) #On ajoute la mesure associée à l'unité "instanciationUnit"


            if str(instanciationCommodity.getName()) not in self.__commodityGroupMeasurementIndex:
                self.__commodityGroupMeasurementIndex[str(instanciationCommodity.getName())] = []
                #print(instanciationCommodity.getName()) #Permettait d'afficher l'ensemble des clés que l'on a dans le dictionnaire = l'ensemble des éléments que l'on peut chercher
            self.__commodityGroupMeasurementIndex[str(instanciationCommodity.getName())].append(instanciationMeasurement.describe())


            if str(instanciationIndicator.getName()) not in self.__indicatorGroupMeasurementIndex:
                self.__indicatorGroupMeasurementIndex[str(instanciationIndicator.getName())] = []
            self.__indicatorGroupMeasurementIndex[str(instanciationIndicator.getName())].append(instanciationMeasurement.describe())


            if SC_GeographyIndented_Desc not in self.__locationMeasurementIndex:
                self.__locationMeasurementIndex[SC_GeographyIndented_Desc] = []
            self.__locationMeasurementIndex[SC_GeographyIndented_Desc].append(instanciationMeasurement.describe())

            #print(self.__unitMeasurementIndex.keys()) #Permettait d'afficher les clés recensés dans notre dictionnaire après avoir été créé

            nbrMeasurement = nbrMeasurement + 1 #Une fois les instanciations réalisées, on passe à la ligne suivante  
    
        #print(sum(map(len, self.__commodityGroupMeasurementIndex.values()))) #Permettait de vérifier si l'ensemble des lignes étaient parcourues à la fin du for                                 

  
    def findMeasurements(self, commodityType : CommodityGroup = None, indicatorGroup : IndicatorGroup = None, geographicalLocation : str = None, unit : Unit = None):

            resultatIntermediaire = []
            
            nb_arg = 0 #On compte le nombre d'arguments en entrée
            
            if commodityType is not None: 
                #print(commodityType)
                #print(self.__commodityGroupMeasurementIndex[commodityType])
                #resultatIntermediaire = resultatIntermediaire + self.__commodityGroupMeasurementIndex[commodityType] 
                #print(resultatIntermediaire) #Permettait de debugger en affichant l'état précédent pour vérifier si l'action de la ligne suivante retournait le resultat attendu                                    
                resultatIntermediaire.append(self.__commodityGroupMeasurementIndex[commodityType]) #On vient ajouter à notre liste de resultat la commodité et ses renseignements annexes (cf. classe Commodity)               
                nb_arg +=1
                #print(len(self.__commodityGroupMeasurementIndex[commodityType])) #Permettait de compter le nombre d'éléments recensés et de le comparer à notre fichier excel
                #print(resultatIntermediaire) #Permettait de debugger en affichant l'état suivant pour vérifier si l'action de la ligne précédente retournait le resultat attendu  

            if indicatorGroup is not None:
                resultatIntermediaire.append(self.__indicatorGroupMeasurementIndex[indicatorGroup])
                nb_arg +=1

            if geographicalLocation is not None:
                resultatIntermediaire.append(self.__locationMeasurementIndex[geographicalLocation])
                nb_arg +=1

            if unit is not None:
                resultatIntermediaire.append(self.__unitMeasurementIndex[unit])
                nb_arg +=1

           
            #Cas où il n'y a pas d'argument renseigné
            if nb_arg == 0:  
                return [self.__commodityGroupMeasurementIndex, self.__indicatorGroupMeasurementIndex, self.__locationMeasurementIndex, self.__unitMeasurementIndex]
                
            #print(resultatIntermediaire) #Pour verifier pourquoi notre fonction findMeasurement ne fonctionne pas avec plus d'un seul paramètre
            #Cas où il y a au moins un argument renseigné
            ligneDeCommande ="[value for value in resultatIntermediaire" #On créer une chaîne de caractère pour vérifier l'appartenance d'une mesure dans tous les critères spécifiés en argument (CommodityGroup, IndicatorGroup, Location et Unit)
            for i in range (nb_arg):
                if i==(nb_arg-1):
                    ligneDeCommande += "["+str(i)+"]]"
           
                else:
                    ligneDeCommande += "["+str(i)+"] if value in resultatIntermediaire"
                    
            
            return eval(ligneDeCommande) #eval() permet d'executer une commande stockée sous forme d'un String

            #return toutes les mesures qui matchent avec tous les critères renseignés







