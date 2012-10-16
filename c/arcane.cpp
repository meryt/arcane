// arcane.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <stdlib.h>
#include <time.h>

int periodic_check();
int cave_menu();
int pool_menu();
int lake_menu();
short RollDice(short numsides);
void exit_menu();
void MakeDoor();
void MakePassage();
void MakeSpecialPassage();
void MakeChamber();
void CheckForExits();
void MakeUnusualShape();
void MakeStairs();
void MakeMap();
float RandNum(float flMax);

int main(int argc, char* argv[])
{
	int result, choice;
	
    srand(unsigned(time(NULL)));

	printf("Welcome to the random dungeon generator.\n");
	printf("Please create a starting point for your dungeon.\n");
MAINMENU:	printf("\n");
	printf("MAIN MENU\n");
	printf("[1] Periodic check\n");
	printf("[2] Cave menu\n");
	printf("[3] Pool menu\n");
	printf("[4] Lake menu\n");
	printf("[5] Stop\n");
	do {result = scanf("%d",&choice);} while (choice > 5);
	switch(choice)
	{
	case 1:
//		periodic_check();
		MakeMap();
        goto MAINMENU;
	case 2:
		cave_menu();
		goto MAINMENU;
	case 3:
		pool_menu();
		goto MAINMENU;
	case 4:
		lake_menu();
		goto MAINMENU;
	case 5:
		break;
	default:
		break;
	}

	return 0;
}

int periodic_check()
{
    short result;
    result = RollDice(20);
    switch (result)
    {
    case 1: case 2:
        printf("The passage continues straight for 60 feet.\n");
        break;
    case 3: case 4: case 5:
        printf("A Door is found.\n");
        MakeDoor();
        break;
    case 6: case 7: case 8: case 9: case 10:
        printf("There is a side passage.\n");
        break;
    case 11: case 12: case 13:
        printf("Passage turns.\n");
        // TODO: passage menu
        break;
    case 14: case 15: case 16:
        printf("A chamber is found.\n");
        MakeChamber();
        break;
    case 17:
        printf("There are some stairs.\n");
        MakeStairs();
        break;
    case 18:
        printf("There is a dead end.\n");
        break;
    case 19:
        printf("A trap is located.\n");
        break;
    case 20:
        printf("A wandering monster is located. Check again so its direction of approach\ncan be determined.\n");
        break;
    default:
        printf("Dice roll error.\n");
        break;
    }
	return 0;
}

int cave_menu()
{
	printf("You chose cave menu\n");
	return 0;
}

int pool_menu()
{
	printf("You chose pool menu\n");
	return 0;
}

int lake_menu()
{
	printf("You chose lake menu");
	return 0;
}

void exit_menu() {
    
    int choice, result;
EXITMENU:
    printf("EXIT SUBMENU\n");
    printf("[1] Another exit\n");
    printf("[2] Return to main menu\n");
	do {result = scanf("%d",&choice);} while (choice > 2);
    if (choice == 2) {return;}
    switch (RollDice(20)) {
    case 1: case 2: case 3: case 4: case 5: case 6: case 7:
        printf("The exit is on the opposite wall.\n");
        break;
    case 8: case 9: case 10: case 11: case 12:
        printf("The exit is on the left wall.\n");
        break;
    case 13: case 14: case 15: case 16: case 17:
        printf("The exit is on the right wall.\n");
        break;
    case 18: case 19: case 20:
        printf("The exit is on the same wall.\n");
        break;
    default:
        printf("Error in exit menu.\n");
        break;
    }
    switch (RollDice(20)) {
    case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8:
        printf("The exit is a passage.\n");
        // TODO: GOTO 450
        break;
    case 9: case 10: case 11: case 12: case 13: case 14: case 15:
        printf("The exit is a door.\n");
        // TODO: GOTO 30
        break;
    case 16: case 17: case 18: case 19:
        printf("The exit is a secret door.\n");
        break;
        // TODO: GOTO 30
    case 20:
        printf("The exit is a trick door.\n");
        break;
    default:
        printf("Error locating door.\n");
        break;
    }
    goto EXITMENU;
}

short RollDice(short numsides)
{
    float randnum = (float)rand();
    short result1;
    result1 = (short)((randnum / RAND_MAX) * numsides) + 1;
    return result1;
}

void MakeDoor()
{
    short result;
    short result2;
    // Find door location
    result = RollDice(20);
    switch (result)
    {   
    case 1: case 2: case 3: case 4: case 5: case 6:
        printf("It is on the left wall.\n");
        break;
    case 7: case 8: case 9: case 10: case 11: case 12:
        printf("It is on the right wall.\n");
        break;
    case 13: case 14: case 15: case 16: case 17: case 18: case 19: case 20:
        printf("It is straight ahead.\n");
        break;
    default:
        printf("Dice roll error while locating door.\n");
        break;
    }
    // Check for locks
    result = RollDice(20);
    switch (result)
    {
    case 1: case 2: case 3: case 4: case 5: case 6: case 7:
    case 8: case 9: case 10: case 11: case 12: case 13: case 14: case 15:
        printf("It has no lock.\n");
        break;
    case 16: case 17: case 18:
        printf("It has a normal lock.\n");
        break;
    case 19:
        printf("It has a trapped lock.\n");
        break;
    case 20:
        printf("It is wizard-locked.\n");
        break;
    default:
        printf("Dice roll error while checking for locks.\n");
        break;
    }
    // Check to see what is beyond door
    result2 = RollDice(20);
    switch (result2)
    {
    case 1: case 2: case 3: case 4:
        if (result > 12 && result < 21) {
            printf("Beyond the door is a 10' x 10' room.\n");
            break;
        }
        else {
        	printf("Beyond the door is a parallel passage running 30' in either direction.\n");
	        break;
        }
    case 5: case 6: case 7: case 8: case 9: case 10:
        printf("Beyond the door is a passage running 30' straight ahead.\n");
        break;
    case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18:
        printf("Beyond the door is a room.\n");
        break;
    case 19: case 20:
        printf("Beyond the door is a chamber.\n");
        MakeChamber();
        break;
    default:
        printf("Dice roll error while checking beyond door.\n");
        break;
    }

}

void MakePassage()
{
    short result;
    result = RollDice(20);
    switch (result) {
    case 1: case 2: case 3: case 4: case 5: case 6:
    case 7: case 8: case 9: case 10: case 11: case 12:
        printf("The passage is 10' wide.\n");
        break;
    case 13: case 14: case 15: case 16:
        printf("The passage is 20' wide.\n");
        break;
    case 17:
        printf("The passage is 30' wide.\n");
        break;
    case 18:
        printf("The passage is 5' wide.\n");
        break;
    case 19: case 20:
        MakeSpecialPassage();
        break;
    default:
        printf("Error in dice roll while making passage.\n");
        break;
    }
}

void MakeSpecialPassage()
{
    short result;
    short result2;
    result = RollDice(20);
    switch (result)
    {
    case 1: case 2: case 3: case 4:
        printf("The passage is 40' wide with columns down the center.\n");
        break;
    case 5: case 6: case 7:
        printf("The passage is 40' wide with a double row of columns.\n");
        break;
    case 8: case 9: case 10:
        printf("The passage is 50' wide with a double row of columns.\n");
        break;
    case 11: case 12:
        printf("The passage is 50' wide with columns 10' right and left supporting\n10' wide upper galleries 20' above.\n");
        result2 = RollDice(20);
        if (result2 > 15) {printf("The stairs up to the gallery are at the beginning of the passage.\n");}
        else {printf("The stairs up to the gallery are at the end of the passage.\nIf there are stairs located at the end of the passage, they are separate\nfrom the stairs leading up to the gallery 50% of the time.\n");}
        break;
    case 13: case 14: case 15:
        printf("The 10' wide passage is bisected by a stream.\n");
        result2 = RollDice(20);
        if (result2 < 16) {printf("There is a bridge over the stream.\n");}
        break;
    case 16: case 17: case 18: case 19:
        if (result == 16 || result ==17) {
        	printf("The 20' wide passage is bisected by a river.\n");
        }
        if (result == 18) {
	        printf("The 40' wide passage is bisected by a river.\n");
        }
        if  (result ==19) {
	        printf("The 60' wide passage is bisected by a river.\n");
        }
        result2 = RollDice(20);
        switch (result2) {
        case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:
        case 10:
            printf("There is a bridge over the river.\n");
            break;
        case 11: case 12: case 13: case 14: case 15:
            printf("There is a boat on the bank. (50% chance either bank)\n");
            break;
        case 16: case 17: case 18: case 19: case 20:
            printf("The river is an obstacle.\n");
            break;
        default:
            printf("There was an error checking for bridges.\n");
            break;
        }
    case 20:
		printf("The 20' wide passage is bisected by a chasm (150'-200' deep).\n");
        result2 = RollDice(20);
        switch(result2) {
        case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:
        case 10:
            printf("There is a bridge over the chasm.\n");
            break;
        case 11: case 12: case 13: case 14: case 15:
            printf("There is a jumping place 10'-15' wide.\n");
            break;
        case 16: case 17: case 18: case 19: case 20:
            printf("The chasm is an obstacle.\n");
            break;
        default:
            printf("There was an error checking for bridges.\n");
            break;
        }
        break;
    default:
        printf("Error in making special passage.\n");
    }
}

void MakeChamber() {
    short result;
    result = RollDice(20);
    switch (result) {
    case 1: case 2: case 3: case 4:
        printf("The chamber is square, 20' x 20'.\n");
        CheckForExits();
        break;
    case 5: case 6:
        printf("The chamber is square, 30' x 30'.\n");
        CheckForExits();
        break;
    case 7: case 8:
        printf("The chamber is square, 40' x 40'.\n");
        CheckForExits();
        break;
    case 9: case 10: case 11: case 12: case 13:
        printf("The chamber is rectangular, 20' x 30'.\n");
        CheckForExits();
        break;
    case 14: case 15:
        printf("The chamber is rectangular, 30' x 50'.\n");
        CheckForExits();
        break;
    case 16: case 17:
        printf("The chamber is rectangular, 40' x 60'.\n");
        CheckForExits();
        break;
    case 18: case 19: case 20: // Unusual size and shapes
        MakeUnusualShape();
    }
}

void CheckForExits() {
    switch (RollDice(20)) {
    case 1: case 2: case 3:
        printf("There is one exit if the room is < 600 sq.ft., 2 if > 600 sq.ft.\n");
        break;
    case 4: case 5: case 6:
        printf("There are two exits if the room is < 600 sq.ft., three for > 600 sq.ft.\n");
        break;
    case 7: case 8: case 9:
        printf("There are three exits if the room is < 600 sq.ft., four for < 600 sq.ft.\n");
        break;
    case 10: case 11: case 12: case 13: case 14: case 15:
        printf("There is one exit.\n");
        break;
    case 16: case 17: case 18:
        printf("There is %d exit(s).\n", RollDice(4));
        break;
    case 19: case 20:
        printf("There is one exit--a door in a chamber, a passage in a room.\n");
        break;
    default:
        printf("Error checking for exits.\n");
        break;
    }
    exit_menu();
}

void MakeUnusualShape() {
    switch (RollDice(20)) {
    case 1: case 2: case 3: case 4: case 5:
        printf("The room is circular.\n");
        switch (RollDice(20)) {
        case 1: case 2: case 3: case 4: case 5:
            printf("There is a pool in the room.\n");
            break;
        case 6: case 7:
            printf("There is a well in the room.\n");
            break;
        case 8: case 9: case 10:
            printf("There is a shaft in the room.\n");
            break;
        }
        break;
    case 6: case 7: case 8:
        printf("The room is triangular.\n");
        break;
    case 9: case 10: case 11:
        printf("The room is trapezoidal.\n");
        break;
    case 12: case 13:
        printf("The room is odd-shaped: draw whatever shape you desire or will fit the map.\n");
        break;
    case 14: case 15:
        printf("The room is oval.\n");
        break;
    case 16: case 17:
        printf("The room is hexagonal.\n");
        break;
    case 18: case 19:
        printf("The room is octagonal.\n");
        break;
    case 20:
        printf("The room is a cave (check to see if pool therein).\n");
        break;
    default:
        printf("Error checking unusual shape and size.\n");
        break;
    }
STARTSIZE:    
    switch (RollDice(20)) {
    case 1: case 2: case 3:
        printf("It is about 500 sq. feet.\n");
        break;
    case 4: case 5: case 6:
        printf("It is about 900 sq. feet.\n");
        break;
    case 7: case 8:
        printf("It is about 1,300 sq. feet.\n");
        break;
    case 9: case 10:
        printf("It is about 2,000 sq. feet.\n");
        break;
    case 11: case 12:
        printf("It is about 2,700 sq. feet.\n");
        break;
    case 13: case 14:
        printf("It is about 3,400 sq. feet.\n");
        break;
    case 15: case 16: case 17: case 18: case 19: case 20:
        printf("Add 2,000 sq. feet to the next result...\n");
        goto STARTSIZE;
        break;
    default:
        printf("Error determining size of room.\n");
        break;
    }
}

void MakeStairs() {
    short result;
    switch (RollDice(20)) {
    case 1: case 2: case 3: case 4: case 5:
        printf("There are stairs down one level.\n");
        if (RollDice(20) == 7) {printf("There is a door which closes egress for the day.\n");}
        break;
    case 6:
        printf("There are stairs down two levels.\n");
        result = RollDice(20);
        if (result == 1 || result ==2) {printf("There is a door which closes egress for the day.\n");}
        break;
    case 7:
        printf("There are stairs down three levels.\n");
        result = RollDice(20);
        if (result > 0 && result < 4) {printf("There is a door which closes egress for the day.\n");}
        break;
    case 8:
        printf("There are stairs up one level.\n");
        break;
    case 9:
        printf("There are stairs up to a dead end.\n");
        if (RollDice(6) == 2) {printf("At the top there is a trap that chutes down two levels.\n");}
        break;
    case 10:
        printf("There are stairs down to a dead end.\n");
        if (RollDice(6) == 2) {printf("At the bottom there is a trap that chutes down one level.\n");}
        break;
    case 11:
        printf("There is a chimney up one level; passage continues 30'.\n");
        break;
    case 12:
        printf("There is a chimney up two levels; passage continues 30'.\n");
        break;
    case 13:
        printf("There is a chimney down two levels; passage continues 30'.\n");
        break;
    case 14: case 15: case 16:
        printf("There is a trap door down one level; passage continues 30'.\n");
        break;
    case 17:
		printf("There is a trap door down two levels; passage continues 30'.\n");
        break;
    case 18: case 19: case 20:
        printf("There are stairs up one level, down two levels, chamber at end.\n");
        break;
    default:
        printf("Error making stairs.\n");
        break;
    }
}

float RandNum(float flMax) {
    float randnum = (float)rand();
    float result;
	result = (randnum/RAND_MAX) * flMax;
    return result;
}

void MakeMap() {
	const int NumCols = 32;
	const int NumRows = 28;

	int MapArray[NumCols][NumRows];
	int i,j,k,l;
	int m;
	// Initialize each element of array to 0
	for (i = 0; i < NumCols; i++) {
		for (j = 0; j < NumRows; j++) {
			MapArray[i][j] = 0;
		}
	}
	// Now set each element's value
	for (i = 0; i < NumCols; i++) {
		for (j = 0; j < NumRows; j++) {
			// Determine m (weighting factor)
            m = 0;

			for (k = i-1;k < i+2;k++) {
				for (l = j-1; l < j+2; l++) {
					if (k >= 0 && k <= NumCols && l >= 0 && l <= NumRows) {
						m = m + MapArray[k][l];
					}
				}
			}
			// Now calculate the cell's new value
			//if (RandNum(1) >= 0.05 * m + 0.5) {MapArray[i][j] = 1;}
            if (RandNum(1) >= ((0.5 * m) + 0.5)) {MapArray[i][j] = 1;}
			//if (m > 0) {MapArray[i][j] = 1;}

			else {MapArray[i][j] = 2;}
//            printf("%f",m);
            printf("%i",MapArray[i][j]);
		}
	}
    return;
}