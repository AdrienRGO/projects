/*
BERTHIER Louis
RUGGIERO Adrien
WITKOWICZ Nathan
*/

//Processeur : Intel(R) Core(TM) i5-8265U CPU @ 1.60GHz wuth Turbo Boost up to 3.9GHz

#include<stdlib.h>
#include<stdbool.h>
#include<stdio.h>
#include<string.h>
#include <time.h>

#define NB_ELEVES_MAX 100 //On definit arbitrairement le nombre maximal d'eleves contenus dans le csv
#define NB_SUJETS_MAX 50 //On definit arbitrairement le nombre maximal de sujets contenus dans le csv

int choix1[NB_ELEVES_MAX]; //Correspond à la liste des premiers sujets de chaque eleve
int choix2[NB_ELEVES_MAX]; //Correspond à la liste des deuxiemes sujets de chaque eleve
int choix3[NB_ELEVES_MAX]; //Correspond à la liste des troisiemes sujets de chaque eleve
int choix4[NB_ELEVES_MAX]; //Correspond à la liste des quatriemes sujets de chaque eleve
int somme_dynamique[NB_ELEVES_MAX];
int eleve_par_sujet[NB_SUJETS_MAX];

int val1 = 8; //Si le sujet correspond au premier choix, il a une valeur de 8 (points)
int val2 = 4; //Si le sujet correspond au deuxième choix, il a une valeur de 4 (points)
int val3 = 2; //Si le sujet correspond au troisième choix, il a une valeur de 2 (points)
int val4 = 1; //Si le sujet correspond au qauatrième (dernier) choix, il a une valeur de 1  (points)

int nbeleves; //Nombre d'eleves contenus dans le csv
int nbsujets; //Nombre de sujets contenus dans le csv
int somme_finale; //C'est l'optimum, le resultat apres optimisation de la somme

//Permet de lire les données dans le fichier csv contenant les élèves et leurs choix
void readcsv(char* fic)
{
    FILE* f;
    f=fopen(fic, "r");
    nbeleves = 0;
    while (fscanf(f, "%d;%d;%d;%d", choix1 + nbeleves, choix2 + nbeleves, choix3 + nbeleves, choix4 + nbeleves) == 4)
        nbeleves++;
    fclose(f);
}

//Permet de compter les sujets
void comptesujets(void)
{
    static char yest[NB_SUJETS_MAX];
    int i, k;

    for (i = 0; i < NB_SUJETS_MAX; i++)
        yest[i] = 0;

    nbsujets = 0;
    for (i = 0; i < nbeleves; i++)
    {
        k = choix1[i];
        if (yest[k] == 0) {
            nbsujets++; yest[k] = 1;
        }
        k = choix2[i];
        if (yest[k] == 0) {
            nbsujets++; yest[k] = 1;
        }
        k = choix3[i];
        if (yest[k] == 0) {
            nbsujets++; yest[k] = 1;
        }
        k = choix4[i];
        if (yest[k] == 0) {
            nbsujets++; yest[k] = 1;
        }
    }
}

//Permet d'afficher les choix de chaque élève (pas indispensable mais nous donne une idée !)
int afficher_csv(char* fic)
{
    FILE* f;
    char ligne[NB_ELEVES_MAX]; //On définit arbitrairement le nombre de ligne pour notre fichier csv que l'on va lire (au maximum)
    char *ptr_chaine; //Pointeur pour balayer les sous-chaines obtenues
    int num_ligne=1;
    int data_entier1;
    int matrice[NB_ELEVES_MAX][4];

    f = fopen(fic, "r");
    if (f == NULL)
        return 0; //Si le fichier n'existe pas ou n'a pas le bon nom

    while( fgets(ligne,NB_ELEVES_MAX,f) != NULL) //On parcourt l'ensemble des lignes
    {
        printf("\n Eleve %2hd:", num_ligne);
        num_ligne++ ;
        ptr_chaine = strtok (ligne, ";"); // Appel d'initialisation de strtok. Ici le séparateur est ';'

        // On lit l'ensemble des lignes colonne par colonne
        // On vient lire la 1ère colonne (donc le sujet 1)
        if (sscanf(ptr_chaine,"%hd", &data_entier1) != 1) //On gère l'erreur s'il y a un problème de lecture
        {
            puts("\nPb de lecture choix 1 !");
            data_entier1=-11111;
        }
        matrice[num_ligne][0] = data_entier1;
        printf("\n\t choix 1 : %hd ", data_entier1);
        ptr_chaine = strtok (NULL, ";"); // Remplace le séparateur suivant par 0. On saute la colonne 1

        // On vient lire la 2ème colonne (donc le sujet 2)
        if (sscanf(ptr_chaine,"%hd", &data_entier1) != 1) //On gère l'erreur s'il y a un problème de lecture
        {
            puts("\nPb de lecture choix 2 !");
            data_entier1=-11111;
        }
        matrice[num_ligne][1] = data_entier1;
        printf("\n\t choix 2 : %hd ", data_entier1);
        ptr_chaine = strtok (NULL, ";"); // Remplace le séparateur suivant par 0. On saute la colonne 2

        // On vient lire la 3ème colonne (donc le sujet 3)
        if (sscanf(ptr_chaine,"%hd", &data_entier1) != 1) //On gère l'erreur s'il y a un problème de lecture
        {
            puts("\nPb de lecture choix 3 !");
            data_entier1=-11111;
        }
        matrice[num_ligne][2] = data_entier1;
        printf("\n\t choix 3 : %hd ", data_entier1);
        ptr_chaine = strtok (NULL, ";"); // Remplace le séparateur suivant par 0. On saute la colonne 3

        // On vient lire la 4ème colonne (donc le sujet 4)
         if (sscanf(ptr_chaine,"%hd", &data_entier1) != 1) //On gère l'erreur s'il y a un problème de lecture
        {
            puts("\nPb de lecture choix 4 !");
            data_entier1=-11111;
        }
        matrice[num_ligne][3] = data_entier1;
        printf("\n\t choix 4 : %hd ", data_entier1); //on affiche le sujet numéro 4 de l'élève concerné
        ptr_chaine = strtok (NULL, ";"); // Pas nécessaire ici comme on a au maximum 4 sujets
    }
    fclose(f);
    return matrice;
}

void affiliation_sujet(int numero_eleve, int somme_actuelle)
{
    int numero_sujet;
	if (numero_eleve < nbeleves){

        //Ce premier agencement dans le code ralentit le temps d'execution du processeur

        /*
        numero_sujet = choix4[numero_eleve]; //On regarde le dernier choix de l'eleve
		if(eleve_par_sujet[numero_sujet] < 3){
			eleve_par_sujet[numero_sujet]++;
            if( (val4 + somme_actuelle + somme_dynamique[numero_eleve+1]) > somme_finale )
                affiliation_sujet(numero_eleve+1, somme_actuelle + val4);
                eleve_par_sujet[numero_sujet]--;
		}
        numero_sujet = choix3[numero_eleve]; //On regarde le 3eme choix de l'eleve
		if(eleve_par_sujet[numero_sujet] < 3){
			eleve_par_sujet[numero_sujet]++;
            if( (val3 + somme_actuelle + somme_dynamique[numero_eleve+1]) > somme_finale )
                affiliation_sujet(numero_eleve+1, somme_actuelle + val3);
                eleve_par_sujet[numero_sujet]--;
		}
		numero_sujet = choix2[numero_eleve]; //On regarde le 2eme choix de l'eleve
		if(eleve_par_sujet[numero_sujet] < 3){
			eleve_par_sujet[numero_sujet]++;
            if( (val2 + somme_actuelle + somme_dynamique[numero_eleve+1]) > somme_finale )
                affiliation_sujet(numero_eleve+1, somme_actuelle + val2);
                eleve_par_sujet[numero_sujet]--;
		}
		*/

		//Ce deuxieme agencement accelere grandement les performances de notre CPU

		numero_sujet = choix1[numero_eleve]; //On regarde le 1er choix de l'eleve
		if(eleve_par_sujet[numero_sujet] < 3){ //On verifie si on peut encore attribuer un eleve au sujet (max 3 par sujet)
			eleve_par_sujet[numero_sujet]++; //On ajoute un eleve sur le sujet
			if( (val1 + somme_actuelle + somme_dynamique[numero_eleve+1]) > somme_finale )
                affiliation_sujet(numero_eleve+1, somme_actuelle + val1); //On appelle recursivement
                eleve_par_sujet[numero_sujet]--;
		}
        numero_sujet = choix2[numero_eleve]; //On regarde le 2eme choix de l'eleve
		if(eleve_par_sujet[numero_sujet] < 3){
			eleve_par_sujet[numero_sujet]++;
            if( (val2 + somme_actuelle + somme_dynamique[numero_eleve+1]) > somme_finale )
                affiliation_sujet(numero_eleve+1, somme_actuelle + val2);
                eleve_par_sujet[numero_sujet]--;
		}
		numero_sujet = choix3[numero_eleve]; //On regarde le 3eme choix de l'eleve
		if(eleve_par_sujet[numero_sujet] < 3){
			eleve_par_sujet[numero_sujet]++;
            if( (val3 + somme_actuelle + somme_dynamique[numero_eleve+1]) > somme_finale )
                affiliation_sujet(numero_eleve+1, somme_actuelle + val3);
                eleve_par_sujet[numero_sujet]--;
		}
		numero_sujet = choix4[numero_eleve]; //On regarde le dernier choix de l'eleve
		if(eleve_par_sujet[numero_sujet] < 3){
			eleve_par_sujet[numero_sujet]++;
            if( (val4 + somme_actuelle + somme_dynamique[numero_eleve+1]) > somme_finale )
                affiliation_sujet(numero_eleve+1, somme_actuelle + val4);
                eleve_par_sujet[numero_sujet]--;
		}
		return ;
    }
    if(somme_actuelle > somme_finale){ //On compare la nouvelle somme avec l'ancienne somme
        somme_finale = somme_actuelle; //On conserve la somme la plus elevee
    }
    return ;
}

//Permet d'executer l'ensemble de notre code
int main(int nombre_argument, char* texte_console[])
{
    if (nombre_argument < 2){
        return 0; //Si on ne donne pas 2 parametres (nom du fichier en C et nom du fichier csv à analyser)
    }

    readcsv(texte_console[1]); //Permet de lire le fichier csv indique à la console
    comptesujets();
    afficher_csv(texte_console[1]); //Permet d'afficher les choix de chaque eleve

    printf("\n On a %d eleves", nbeleves); //Indique le nombre d'eleves dans notre base data choisie
    printf("\n On a %d sujets", nbsujets); //Indique le nombre de sujets dans notre base data choisie

    for (int eleve = nbeleves - 1; eleve >=0 ; eleve--){
        affiliation_sujet(eleve,0);
        somme_dynamique[eleve]=somme_finale;
    }
    printf("\n La somme maximale vaut %d",somme_finale);

    float time_ms = 0; //Initialisation du temps (ms)
    float time_s = 0; //Initialisation du temps (s)
    time_ms = clock(); //On recupere le nombre de "tick"
    time_s = time_ms / CLOCKS_PER_SEC; //On convertit les "tick" en temps "reel" en secondes
    printf("\n Le CPU requiert %f secondes pour l'execution", time_s );

    return 0;
}
