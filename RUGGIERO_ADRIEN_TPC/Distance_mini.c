/*
    RUGGIERO Adrien
    VERSION 5 (Finale)
    23/03/2022
*/



#include<stdio.h>
#include <stdlib.h>
#include<math.h>

#define TRUE    1
#define FALSE   0

#define INFINITY 9999

float calcul_distance(int num_jour[2]);

int days_in_month[]= {0,31,28,31,30,31,30,31,31,30,31,30,31};
char *months[]=


{
    " ",
    "\n\n\nJanvier",
    "\n\n\nFevrier",
    "\n\n\nMars",
    "\n\n\nAvril",
    "\n\n\nMai",
    "\n\n\nJuin",
    "\n\n\nJuillet",
    "\n\n\nAout",
    "\n\n\nSeptembre",
    "\n\n\nOctobre",
    "\n\n\nNovembre",
    "\n\n\nDecembre"
};

static int matrice[55][7];

struct date
{
    int x;
    int y;
};
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


void calendar_to_txt(int year)
{

    FILE *fichier = fopen("calendrier.txt","w");
    int daycode;
    int month, day;
    bissextile(year);
    daycode = determinedaycode(year);

    int jour = daycode;

    int j;
    j=0;
    printf("\n");
    fprintf(fichier,"----------Calendrier %d----------",year);
    for ( month = 1; month <= 12; month++ )
    {

        fprintf(fichier,"%s", months[month]);
        fprintf(fichier,"\n\nDim Lun  Mar  Mer  Jeu  Ven  Sam\n" );

        //On place la premiere date correctement
        for ( day = 1; day <= 1 + daycode * 5; day++ )
        {
            fprintf(fichier," ");
        }
        //printf("%2d : ",month);
        //Dates pour chaque mois
        for ( day = 1; day <= days_in_month[month]; day++ )
        {

            fprintf(fichier,"%2d", day );

            //Si fin de semaine:
            if ( ( day + daycode ) % 7 > 0 )
                fprintf(fichier,"   " );

            else
            {
                j++;
                fprintf(fichier,"\n " );
            }
        }

        //On calcule la position du premier jour pour le mois suivant
        daycode = ( daycode + days_in_month[month] ) % 7;


    }
}

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
        daycode = ( daycode + days_in_month[month] ) % 7;


    }
    return matrice;
}

void afficher_matrice(int mat[55][7])
{
    int i,j;
    for(i=0; i<55; i++)
    {
        for(j=0; j<7; j++)
        {
            printf("%2d |", *(*(mat+i)+j));
        }
        printf("\n");
    }
}

/*
    Fonction qui calcule la distance GEOMETRIQUE (avec Pythagore) entre deux date
    Entree : deux dates
    Sortie : distance :float
*/
float distance(struct date a, struct date b)
{
    float resultat;
    int x1 = a.x;
    int y1 = a.y;

    int x2 = b.x;
    int y2 = b.y;

    resultat = sqrt((y2-y1)*(y2-y1)+(x2-x1)*(x2-x1));

    if(resultat != resultat)
    {
        resultat = resultat;
    }

    //printf("distance : %f\n",resultat);
    return resultat;
}

/*
    Trouve les numero de jours en fonction des dates rentrées par l'utilisateur
    Comme dit dans l'énoncé, on rentre les coordonées dans la matrice pour un jour et non le numero de jour

    Sortie : vecteur de taille 2 avec le numero de jour des deux dates
*/
int * prog_Q1()
{
    int jour1,jour2,semaine1,semaine2;
    jour1=-1;
    while(jour1<0 || jour1>7)
    {
        printf("Choisissez le numero de jour de la premiere date:");
        scanf("%d",&jour1);
        if (jour1<0 || jour1>7)
        {
            printf("veuillez rentrer une valeur valide\n");
        }
    }
    semaine1=-1;
    while(semaine1<0 || semaine1>4)
    {
        printf("Choisissez le numero de semaine de la premiere date :");
        scanf("%d",&semaine1);
        if (semaine1<0 || semaine1>4)
        {
            printf("veuillez rentrer une valeur valide\n");
        }
    }
    jour2=-1;
    while(jour2<0 || jour2>7 || jour1==jour2)
    {
        printf("Choisissez le numero de jour de la deuxieme date:");
        scanf("%d",&jour2);
        if (jour2<0 || jour2>7)
        {
            printf("veuillez rentrer une valeur valide\n");
        }
        else if(jour1==jour2)
        {
            printf("Les deux dates ne peuvent pas avoir le meme jour\n");
        }
    }
    semaine2=-1;
    while(semaine2<0 || semaine2>4)
    {
        printf("Choisissez le numero de semaine de la deuxieme date :");
        scanf("%d",&semaine2);
        if (semaine2<0 || semaine2>4)
        {
            printf("veuillez rentrer une valeur valide\n");
        }

    }

    static int res[2];

    if(jour1 > 27 || jour2 >27)
    {
        printf("Date inf à 27");
        return;
    }
    else if(matrice[semaine1][jour1]*matrice[semaine2][jour2]==0)  //on regarde si l'un des des est nul
    {
        printf("Probleme de date");
    }
    else
    {

        res[0] = matrice[semaine1][jour1];
        res[1] = matrice[semaine2][jour2];
        return res;
    }


}

/*
    Fonction qui calcul la distance totale sur l'annee entiere
    Entree: deux numero de jours
    Sortie : Distance totale : float
*/
float calcul_distance(int num_jour[2])
{
    int jours1[12];       //indices des jours pour la premiere date du mois
    int semaines1[12];

    int jours2[12];
    int semaines2[12];
    //printf("numm jour : %d,%d\n",num_jour[0],num_jour[1]);
    int k1,k2;
    k1=0;  //Les deux compteurs pour le remplissage des jours1/semaines1 et jours2/semaines2
    k2=0;
    while(k1<12 && k2<12)
    {


        for(int i=0; i<55; i++ )
        {
            for(int j=0; j<7; j++)
            {
                if (matrice[i][j]==num_jour[0])
                {
                    jours1[k1] = j;
                    semaines1[k1]= i;
                    k1++;

                    //printf("indices 1 %d: %d,%d\n",k,i,j);
                }
                else if(matrice[i][j]==num_jour[1])
                {
                    jours2[k2] = j;
                    semaines2[k2]= i;
                    k2++;
                    //printf("indices 2 %d: %d,%d\n",k,i,j);
                }

            }
        }
    }

    float distance_totale;
    distance_totale =0.;

    for(int n=0; n<12; n++)
    {
        struct date date1;
        struct date date2;
        struct date date_next;

        date1.x = jours1[n];
        date1.y = semaines1[n];

        date2.x = jours2[n];
        date2.y = semaines2[n];


        distance_totale = distance_totale + distance(date1,date2);

        if(n<=10)
        {
            // /!!\ on ajoute la distance quand on change de mois (ex: du 12 janvier au 1er fevrier) /!!\

            date1 = date2;

            date_next.x=jours1[n+1];
            date_next.y=semaines1[n+1];
            distance_totale = distance_totale + distance(date1,date_next);
        }
    }

    //printf("Distance entre les deux dates : %f\n",distance_totale);

    return distance_totale;
}



void main(void)
{
    //QUESTION 1 : EXPORTATION DU CALENDRIER EN FICHIER TXT
    //calendar_to_txt(2022);


    /*
    //on initialise la matrice
    for (int k=0; k<55; k++)
    {
        for(int l=0; l<7; l++)
        {
            matrice[l][k] = 0;
        }
    }


    int year;
    year = 2021;
    printf("Annee : %d\n",year);

    //AFFICHAGE DE LA MATRICE
    afficher_matrice(calendar_to_matrix(year));
    printf("\n");


    */

    /////////QUESTION2--CALCUL DE LA DISTANCE EN FONCTION DE DEUX DATES DONNEES PAR L'UTILISATEUR

    //calcul_distance(prog_Q1());  //QUESTION 2: calcul une distance pour un annee

    //DERNIERE QUESTION - CALCUL DE LA DISTANCE MINIMALE POUR TOUTE LES DATES DE 1900 A 2022

    float dist_min,dist;
    dist_min = INFINITY;
    int jours[2],annee,date1,date2;             //on peut amerliorer avec deux int dans l'appel de la fonction calcul distance

    //recherche de la distance mini
    for(int year = 1900; year<2023; year++)
    {
        calendar_to_matrix(year);               //creation de la matrice
        for(int i=1; i<28; i++)
        {
            for(int j=i+1; j<29; j++)
            {
                if((j-i)%7!=0)                  //enumeration de tous les jours possibles pour le calcul des distances
                {
                    jours[0] = i;
                    jours[1] =j;

                    dist = calcul_distance(jours);
                    if(dist<=dist_min)
                    {
                        //printf("%f",dist_min);
                        dist_min = dist;

                        annee = year;
                        date1 = i;
                        date2 = j;

                    }
                }
            }
        }
    }

    printf("DISTANCE_MINIMALE : %f\n\n",dist_min);

    //ENUMERATION DE TOUTES LES DATES AYANT LA DISTANCE MINIMALE

    int test_date1[200];
    int test_date2[200];
    int test_annee[200];

    int compt_test=0;  //compteur qui va permettre d'afficher toutes les dates ayant une distance minmale


    for(int year = 1900; year<2023; year++)
    {
        calendar_to_matrix(year);               //creation de la matrice
        for(int i=1; i<28; i++)
        {
            for(int j=i+1; j<29; j++)           //enumeration de tous les jours possibles pour le calcul des distances
            {
                if((j-i)%7!=0)              //on verifie que les jours ne sont pas les memes modulo 7 (ie sur la meme colonne)
                {
                    jours[0] = i;
                    jours[1] =j;

                    dist = calcul_distance(jours);
                    if(dist == dist_min)
                    {
                        test_annee[compt_test] = year;
                        test_date1[compt_test] = i;
                        test_date2[compt_test] = j;

                        compt_test++;
                    }
                }

            }
        }

    }

    printf("%d dates ont la distance minimale :\n\n",compt_test);
    for(int k=0; k<compt_test; k++)
    {
        printf("Distance min %d pour la date :\n\n",k);

        printf("Annee  : %d\n",test_annee[k]);
        printf("Date 1 : %d\n",test_date1[k]);
        printf("Date 2 : %d\n\n",test_date2[k]);
    }
}
