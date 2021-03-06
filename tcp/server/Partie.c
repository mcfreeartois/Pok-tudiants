#include "Partie.h"
#include "server.h"

llist ajouterEnTete(llist liste, char*  name, Client player)
{
    /* On crée un nouvel élément */
    game* new_game = malloc(sizeof(game));
 
    /* On assigne la valeur au nouvel élément */
    new_game->name=(char*) malloc(sizeof(char*));
    strcpy(new_game->name, name);
    new_game->nmbr_player+=1;
    new_game->tabplayer[(new_game->nmbr_player-1)]=player;
    
 
    /* On assigne l'adresse de l'élément suivant au nouvel élément */
    new_game->nxt = liste;
 
    /* On retourne la nouvelle liste, i.e. le pointeur sur le premier élément */
    return new_game;
}


void afficherListe(llist liste)
{
    game *tmp = liste;
    /* Tant que l'on n'est pas au bout de la liste */
    while(tmp != NULL)
    {
        /* On affiche */
        printf("%s ", tmp->name);
	printf("%d \n", tmp->nmbr_player);
        /* On avance d'une case */
        tmp = tmp->nxt;
    }
}
int number_game(llist liste)
{
	int i=0;
    game *tmp = liste;
    /* Tant que l'on n'est pas au bout de la liste */
    while(tmp != NULL)
    {i++;
        /* On avance d'une case */
        tmp = tmp->nxt;

    }
return i;
}

llist find_game(llist liste, char* name_game)
{
    game *tmp=liste;
    while(tmp != NULL)
    {
        if(strcmp(tmp->name,name_game)==0)
        {
            return tmp;
        }
        tmp = tmp->nxt;
    }
    return NULL;
}


llist join_game(llist liste,char* name_game, Client player)
{

game* gamejoin=find_game(liste,name_game);
if((gamejoin->nmbr_player)<=5)
{
    gamejoin->nmbr_player+=1;
    gamejoin->tabplayer[(gamejoin->nmbr_player-1)]=   player;
    return gamejoin;
}

return NULL;
    
}



int my_rand(int a, int b){
	srand (time (NULL));
    return rand()%(b-a) +a;
}




