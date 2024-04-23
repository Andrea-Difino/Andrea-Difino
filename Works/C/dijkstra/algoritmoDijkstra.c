#include <stdio.h>
#include <limits.h>
#include <stdbool.h>

#define NODES 8

int weights[NODES] = {INT_MAX,0,INT_MAX,INT_MAX,INT_MAX,INT_MAX,INT_MAX,INT_MAX};
int visited[NODES] = {0,1,0,0,0,0,0,0};
int newMinValue = 0; //peso minimo delle distanze col tempo

// PRE : *V è l'array dei nodi visitati con dim uguale a NODES;
// POST: ritorna 0 se c'è almeno un nodo non visitato altrimenti 1
int checkVisited(int *V){
    for(int i = 0; i < NODES; i++){
        if(V[i] == 0){
            return 0;
        }
    }
    return 1;
}


void calcMinWeight(int *A, int pos){

    int d = weights[pos];

    for(int j = 0; j < NODES; j++){
        if(*A != 0){
            int dAdiacente;
            if(weights[j] != INT_MAX){    //uso l'if per evitare che con la somma ad INT_MAX ritorni numeri strani
                dAdiacente = weights[j] + *A;  //minima distanza con vertice adiacente
            }else{
                dAdiacente = INT_MAX;
            }
            
            if(dAdiacente <= d){
                d = dAdiacente;    // sovrascrivo d per la minima distanza del vertice preso in considerazione
            }
        }
        A++;
    }

    weights[pos] = d;  //aggiorno l'array dei pesi
}



int main(){

    char nodes[NODES] = {'A','B','C','D','E','F','G','H'};

    int nodesAd[][NODES] = {   //matrice di adiacenza dei nodi
    //   A  B  C D E F G H
        {0 ,6,10,0,0,3,6,0}, //A
        {6 ,0,0 ,0,0,2,0,0}, //B
        {10,0,0 ,7,0,0,1,0}, //C
        {0 ,0,7 ,0,3,0,5,4}, //D
        {0 ,0,0 ,3,0,0,0,4}, //E
        {3 ,2,0 ,0,0,0,1,0}, //F
        {6 ,0,1 ,5,0,1,0,9}, //G
        {0 ,0,0 ,4,4,0,9,0}  //H
    };

    printf("Distanze Iniziali: ");

    for(int k = 0; k < NODES; k++){
        (weights[k] == INT_MAX)?printf("inf "):printf("%d ", weights[k]);
    }

    printf("\n\n");

    
    while (true)
    {
        for(int i = 0; i < NODES; i++){
            if(visited[i] == 0){
                calcMinWeight(nodesAd[i], i);
            }
        }

        int minDistance = INT_MAX; //distanza minima
        int posMin = 0; //posizione nodo con distanza minima
        
        //cerco nell'array dei pesi quale sia il nodo con il peso minimo, escludendo i nodi già visitati
        for(int j = 0; j < NODES; j++){    
            if(weights[j] > newMinValue){
                if(weights[j] < minDistance){
                    minDistance = weights[j];
                    posMin = j;
                }
            }
        }
        
        newMinValue = minDistance;
        visited[posMin] = 1;  //aggiorno l'array dei nodi visitati con il nodo con la distanza di peso minimo
        
        printf("Pesi: ");
        
        for(int k = 0; k < NODES; k++){
            printf("%d ", weights[k]);
        }
        
        printf("    Vert - %c", nodes[posMin]);
        printf("\n");

        if(checkVisited(visited) == 1){
            break;
        }
    }
    
    
    

    


}