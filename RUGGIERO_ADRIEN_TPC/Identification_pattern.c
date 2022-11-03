/*
    RUGGIERO Adrien
    VERSION 4 (Finale)
    23/03/2022
*/



#include <time.h>
#include <stdlib.h>
#include <stdio.h>

#define TRUE    1
#define FALSE   0

int ** calendar_to_matrix(int year);

int ident_pattern_mois1[123][13];  //1 : meme pattern; 0 : pas le meme pattern, les mois en colonnes et les annes en ligne de 1900 à 2022
//En ligne : Position0 : Année ; puis Janvier,Février ... Décembre

int matrice[55][7];                 //matrice contenant le calendrier d'une année
int days_in_month[]= {0,31,28,31,30,31,30,31,31,30,31,30,31};


static int month1[42]; //mois provenant du fichier csv
static int month[6][7]; //on le transforme en matrice pour plus de simplicité dans les calculs


/*
    Fonction qui permet de calculer le premier jour de l'année
*/
int determinedaycode(int year)
{
    int daycode;
    int d1, d2, d3;

    d1 = (year - 1.)/ 4.0;
    d2 = (year - 1.)/ 100.;
    d3 = (year - 1.)/ 400.;
    daycode = (year + d1 - d2 + d3) %7;
    return daycode;
}


/*
    Fonction qui retourne si oui ou non une année est bissextile
    Entree : annee
    Sortie : True ou False (1 ou 0)
*/
int bissextile(int year)
{
    if(year% 4 == FALSE && year%100 != FALSE || year%400 == FALSE)
    {
        days_in_month[2] = 29;
        return TRUE;
    }
    else
    {
        days_in_month[2] = 28;
        return FALSE;
    }
}

/*
    Fonction qui lit le fichier csv

    Entree : -nom du fichier avec l'extension
             -separateur csv

*/
void readcsv2(char* fic, char* sep)
{
    FILE* f;
    int col = 0;
    fopen_s(&f,fic, "r");

    if (f == NULL)
    {
        printf("null");
        return;
    }
    char line[200];
    while (fgets(line, sizeof(line),f) != NULL)
    {

        char *token;

        token = strtok(line,sep);
        month1[col]=atoi(token);

        while(token!=NULL)
        {
            //printf("%2s ", token);
            month1[col] = atoi(token);
            col = col +1;
            token = strtok(NULL,sep);
        }
        //printf("\n");
    }
    fclose(f);
}

/*
    Fonction qui lit le fichier csv (variante de la première)

    Entree : -nom du fichier avec l'extension
             -separateur csv

*/
void readcsv6(char* fic, char* sep)
{
    FILE* f;
    int col = 0;
    fopen_s(&f,fic, "r");

    if (f == NULL)
    {
        printf("null");
        return;
    }
    char line[200];
    while (fgets(line, sizeof(line),f) != NULL)
    {

        char *token;

        token = strtok(line,sep);
        month1[col]=atoi(token);

        while(token!=NULL)
        {
            //printf("%2s ", token);
            month1[col] = atoi(token);
            col = col +1;
            token = strtok(NULL,sep);
        }

        if(token==NULL)
        {
            col = col -1;
        }
        //printf("\n");

    }
    fclose(f);
}

/*
    Fonction qui cree le calendrier annuel

    Entree: Annee
    Sortie : Matrice avec le calendrier annuel
*/
int ** calendar_to_matrix(int year)
{
    int daycode;
    int month, day;
    bissextile(year);
    daycode = determinedaycode(year);
    int jour = daycode;

    int j;
    j=0;

    for ( month = 1; month <= 12; month++ )
    {
        for ( day = 1; day <= days_in_month[month]; day++ )
        {
            if(daycode == 0 && j==0)
            {
                matrice[0][6]=1;
                j++;
            }

            if ((day + daycode -1)%7 == 0)
            {
                //printf("%2d  ", (day+daycode -1)%7);
                matrice[j-1][6] = day;
            }
            else
            {
                //printf("%2d  ", (day+daycode -1)%7);
                matrice[j][(day+daycode-1)%7-1] = day;
            }


            //printf("%2d ",day);
            if ( ( day + daycode ) % 7 == 0 )
            {
                j++;
            }

        }
        //On determine le premier jour du mois suivant
        daycode = ( daycode + days_in_month[month] ) % 7;


    }
    return matrice;
}

/*
    Entrée : Année
    Sortie : Affectation dans la matrice finale de la ligne contenant un 1 pour les mois correspondant au pattern, 0 sinon

*/
void identification_pattern_void(int year)
{
    int premier_jour,dernier_jour,indice_dernier_jour,nb_semaine;     //jour du premier et dernier mois de l'année
    int res[12];

    for(int m=0; m<12; m++)
    {
        res[m]=0;
    }
    //IDENTIFICATION DU PREMIER JOUR DU MOIS
    for(int j=0; j<7; j++)
    {
        if(month[0][j]==1)
        {
            premier_jour = j;
            //printf("\n%d\n",premier_jour);
        }
    }
    //IDENTIFICATION DU DERNIER JOUR DU MOIS
    if(month[0][premier_jour + 7*4 + 2]==31)
    {
        dernier_jour =31;
        indice_dernier_jour = premier_jour + 7*4 + 2;

    }
    else if(month[0][premier_jour + 7*4 +1 ]==30)
    {
        dernier_jour =30;
        indice_dernier_jour = premier_jour + 7*4 + 1;
    }
    else if(month[0][premier_jour + 7*4 ]==29)
    {
        dernier_jour =29;
        indice_dernier_jour = premier_jour + 7*4 ;
    }
    else
    {
        dernier_jour = 28;
        indice_dernier_jour = premier_jour + 7*4 -1;
    }
    nb_semaine = indice_dernier_jour / 7;
    indice_dernier_jour = indice_dernier_jour%7;  //on rammene l'indice du dernier jour a un modulo de 7

    /* //Affichage des infos sur le pattern recherché
    printf("%d\n",dernier_jour);
    printf("ld : %d\n",indice_dernier_jour);
    printf("nb_sem : %d\n", nb_semaine);
    */

    int num_mois = 0; //indique le numero du mois

    ident_pattern_mois1[year-1900][0]=year;

    for(int i=0; i<55; i++)
    {
        for(int j=0; j<7; j++)
        {
            if(matrice[i][j]==1)
            {
                if(num_mois <=11) //resout le pb pour l'instant
                {

                    num_mois = num_mois + 1;   //compter le nombre de mois passés
                    //printf("num_mois : %d\n",num_mois);
                    if ( (matrice[i+nb_semaine][indice_dernier_jour]>matrice[i+nb_semaine][indice_dernier_jour+1])
                            && (matrice[i+nb_semaine][indice_dernier_jour]==dernier_jour) )   //on vérifie si le numero du jour d'apres diminue (ie revient à 1 quand on change de mois)
                    {
                        res[num_mois-1] = 1;
                        ident_pattern_mois1[year-1900][num_mois]=1;
                    }
                }
                else
                {
                    return;
                }
            }

        }
    }
    /*
    //TEST AFFICHAGE
    printf("\n\nRES :");
    for(int m=0;m<12;m++)
    {
        printf("%d |",res[m]);
    }
    */
}

//Cette fonction est une dérivée de la précédente, elle n'est utile que pour l'affichage année par année
//Sortie : Vecteur résultat pour une seule année (voir 2ème partie du "main"
int * identification_pattern_tab()
{
    int premier_jour,dernier_jour,indice_dernier_jour,nb_semaine;     //jour du premier et dernier mois de l'année
    static int res[12];

    for(int m=0; m<12; m++)
    {
        res[m]=0;
    }
    //IDENTIFICATION DU PREMIER JOUR DU MOIS
    for(int j=0; j<7; j++)
    {
        if(month[0][j]==1)
        {
            premier_jour = j;
            //printf("\n%d\n",premier_jour);
        }
    }
    //IDENTIFICATION DU DERNIER JOUR DU MOIS
    if(month[0][premier_jour + 7*4 + 2]==31)
    {
        dernier_jour =31;
        indice_dernier_jour = premier_jour + 7*4 + 2;

    }
    else if(month[0][premier_jour + 7*4 +1 ]==30)
    {
        dernier_jour =30;
        indice_dernier_jour = premier_jour + 7*4 + 1;
    }
    else if(month[0][premier_jour + 7*4 ]==29)
    {
        dernier_jour =29;
        indice_dernier_jour = premier_jour + 7*4 ;
    }
    else
    {
        dernier_jour = 28;
        indice_dernier_jour = premier_jour + 7*4 -1;
    }
    nb_semaine = indice_dernier_jour / 7;
    indice_dernier_jour = indice_dernier_jour%7;  //on rammene l'indice du dernier jour a un modulo de 7

    /* ////Affichage des infos sur le pattern recherché
    printf("%d\n",dernier_jour);
    printf("ld : %d\n",indice_dernier_jour);
    printf("nb_sem : %d\n\n", nb_semaine);
    */
    int num_mois = 0; //indique le numero du mois

    for(int i=0; i<55; i++)
    {
        for(int j=0; j<7; j++)
        {
            if(matrice[i][j]==1)
            {
                if(num_mois<=11)
                {
                    num_mois++;   //compter le nombre de mois passé
                    //printf("num_mois : %d\n",num_mois);
                    if (matrice[i+nb_semaine][indice_dernier_jour]>matrice[i+nb_semaine][indice_dernier_jour+1]
                            && matrice[i+nb_semaine][indice_dernier_jour]==dernier_jour)                                //on vérifie si le numero du jour d'apres diminue (ie revient à 1 quand on change de mois)
                    {
                        res[num_mois-1] = 1;
                    }
                }

            }

        }
    }
    return res;
}

/*
    Fonction qui prend en entree le mois parmis les 10 premier csv

*/
void set_pattern(int m)
{
    switch(m)
    {
    case 1:
        printf("ezzz");
        readcsv6("yearmonth_01.csv", " ");      //MOIS 1
        break;
    case 2:
        readcsv2("yearmonth_02.csv",";");       //MOIS 2
        break;
    case 3:
        readcsv2("yearmonth_03.csv",",");       //MOIS 3
        break;
    case 4:
        readcsv2("yearmonth_04.csv",",");       //MOIS 4
        break;
    case 5:
        readcsv2("yearmonth_05.csv"," ");       //MOIS 5
        break;
    case 6:
        readcsv6("yearmonth_06.csv","|");       //MOIS 6
        break;
    case 7:
        readcsv2("yearmonth_07.csv","|");       //MOIS 7
        break;
    case 8:
        readcsv6("yearmonth_08.csv","|");       //MOIS 8
        break;
    case 9:
        readcsv6("yearmonth_09.csv"," ");       //MOIS 9
        break;
    case 10:
        readcsv2("yearmonth_10.csv","	");       //MOIS 10
        break;
    }
}

void main()
{

    float temps;
    clock_t t1, t2;

    t1 = clock();


    //Choix du mois
    int m=11;
    while(m>10 || m<1)    //le mois doit etre compris entre 1 et 10
    {
        printf("Choissisez le pattern du mois a chercher (entre 1 et 10): \n");
        scanf("%d",&m);
    }
    set_pattern(m);

    //test sur le mois 1
    //readcsv6("yearmonth_01.csv", " ");      //MOIS 1

    printf("Le pattern est :\n\n");
    //On affecte le vecteur issu du readcsv en matrice
    int id;
    for(int i=0; i<6; i++)
    {
        for(int j=0; j<7; j++)
        {
            id = i*7+j;
            month[i][j] = month1[id];
            printf("%2d ", month1[id]);
        }
        printf("\n");
    }

    printf("\n");
    printf("-----------------------------------------\n");
    printf("On le retrouve dans :\n\n");

    for(int year=1900; year<2023; year++)
    {
        //RESET CALENDRIER
        for (int i=0; i<55; i++)
        {
            for (int j = 0; j<7; j++)
            {
                matrice[i][j]=0;
            }
        }
        calendar_to_matrix(year);              //On initialise le calendrier pour l'annee correspondante

        for(int i=0; i<12; i++)
        {
            identification_pattern_void(year);   //On construit la matrice résultat année par année
        }
    }

    //Affichage du résultat
    for(int i=0; i<123; i++)
    {
        for(int j=0; j<13; j++)
        {
            printf("%d |",ident_pattern_mois1[i][j]);
        }
        printf("\n");
    }

    t2 = clock();
    temps = (float)(t2-t1)*1000/CLOCKS_PER_SEC;
    //printf("temps = %f  ms\n", temps);
    /*
        /////////////////////////TEST SUR UNE SEUL ANNEE///////////////////////////(HORS SUJET : JUSTE POUR ESSAYER)
        int year;

        year=2001;
        calendar_to_matrix(year);
        readcsv("yearmonth_01.csv");



        for(int i=0;i<6;i++)
        {
           for(int j=0;j<7;j++)
           {
               printf("%2d\t",month[i][j]);
           }
           printf("\n");
        }
        //printf("%d",month[0][5+7*4+2]);   //premier_jour + 7*premier_jour + 2 (pour arriver a 31

         //AFFICHAGE DU CALENDRIER
        for (int i=0;i<55;i++)
        {
            for (int j = 0;j<7;j++)
            {
                printf("%2d ",matrice[i][j]);
            }
            printf("\n");
        }


        //identification_pattern_void();

        calendar_to_matrix(year);
        int *res5 = identification_pattern_tab();
        printf("YEAR : %d  ",year);





        for(int i=0;i<12;i++)
        {
            printf("%d |",*(res5+i));
        }

        ///////////////////////////////////////////FIN DU TEST SUR UNE SEULE ANNEE
    */




}


