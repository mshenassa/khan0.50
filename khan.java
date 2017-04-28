/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khan;
import java.util.Scanner;



/**
 *
 * @author mitch
 */
public class khan {
static int numToRoll = 6;
static int[] roundRoll = new int [numToRoll];
static String[] rollResult = new String [numToRoll];
static int tempRoll[][] = new int[2][numToRoll];  //create an array 
static String rolltext;

//initialize turn points

public static Player[] playerArray;
public static int turnNumber;
public static int numPlayers;
public static int winner = 0;
public static int control = 0;
public static int grow = 0;
public static int trade = 0;
public static int attack = 0;
public static int trust = 0;
public static int plunder = 0;


//initialize the map
static int[][] map = {
    {0,0}, // two slots in the northern plains aka 0
    {0,0}, // two slots in the western plains aka 1
    {0,0}, // two slots in the southern plains aka 2
    {0,0}, // two slots in the eastern plains aka 3
    {0,0,0,0}, // four slots in central plains  aka 4
   
};


//initialize players
public static class Player {
    int number;
    String name ="";
    int population = 900;
    int controlScore = 0;
    int wealth = 0;
    int locationA = 0;
    int locationB = 0;
    
    public Player() { //constructor
    }
    
    public void setPop(int pop){
        this.population = pop;
    }
    public int getPop(){
        return population;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    public void setControl(int cont){
        this.controlScore = cont;
    }
    public int getControl(){
        return controlScore;
    }
    public void setWealth(int inwealth){
        this.wealth = inwealth;
    }
    public int getWealth(){
        return wealth;
    }
    public void setLocationA(int newA){
        this.locationA = newA;
    }
    public int getLocationA(){
        return locationA;
    }
    public void setLocationB(int newB){
        this.locationB = newB;
    }
    public int getLocationB(){
        return locationB;
        
    }
    
    
    
    public void attack(){
    //attack
    }
    public void grow(){
    //grow
    }   
    public void control(){
    }
    public void trust(){
    }
    public void plunder(){
    }
    public void trade(){
    }
}
    
    
    public static void main(String[] args) {
        // SET UP GAME

        players();
        setMap();
        //ROLL DICE
        while (winner == 0) 
            turn();
        
        // RESOLVE DICE
    
        // MOVE
        // POWER UPS
        // END TURN
    }
    //SET UP GAME METHOD
    
    
     public static void players(){
        Scanner input = new Scanner(System.in);
        numPlayers = 0; //initialize a variable for number of clans 
        
        while (numPlayers < 1 || numPlayers > 4){  //make sure the number of clans is 1-3
        System.out.print("How many clans challenge us? (1-3)? ");
        numPlayers = input.nextInt()+1;
        }
              
        playerArray = new Player[numPlayers]; //create an array for players, add a spot for player 0
            for (int j = 1; j < playerArray.length; j++){
            playerArray[j] = new Player();
            System.out.print ("\nWhat shall we call enemy clan " + (j) + ": ");
            String enemyName = input.next();
            System.out.print ("You wish us to call them " + enemyName + ", my lord? (Y/N) ");
            char command; 
            command = input.next().charAt(0);
            if (command == 'Y'){
                    playerArray[j].setName(enemyName);
            } else
            while (command != 'Y'){
                    System.out.print ("My apologies, my lord.  What should we call them? ");
                    enemyName = input.next();
                    System.out.print ("You wish us to call them " + enemyName + ", my lord? (Y/N) ");
                    command = input.next().charAt(0);
                    if (command == 'Y')
                        playerArray[j].setName(enemyName);
            }
        }
        playerArray[0] = new Player();
        System.out.print("\n What do you call our clan, mighty commander? ");
        String playerName = input.next();
        System.out.print("You call us " + playerName +", noble commander? (Y/N) ");
        char command = input.next().charAt(0);
        if (command == 'Y'){
        playerArray[0].setName(playerName);
            } else
            while (command != 'Y'){
                    System.out.print ("I am a fool, commander!  What shall we be called? I will not forget this time, I swear it! ");
                    playerName = input.next();
                    System.out.print ("We are the " + playerName + "! Correct, my lord? (Y/N) ");
                    command = input.next().charAt(0);
                    if (command == 'Y')
                        playerArray[0].setName(playerName);
                }
        
       System.out.print("\nWe mighty " + playerArray[0].getName() + " will slaughter the");
        for (int i = 1; i < playerArray.length; i ++){
            if (i > 1 && i == playerArray.length-1)
                System.out.print(" and");
            System.out.print(" " + playerArray[i].getName());
        }
        System.out.print("!!!");

        CLS(1);
                
     }

public static void setMap(){  
    Scanner input = new Scanner(System.in);
  
    int command;
   System.out.print("\nWhere shall we establish the main " + playerArray[0].getName() + " camp commander?"+
            "\n(1) Northern Plains\n(2) Western Plains\n(3) Southern Plains\n(4) Eastern Plains" +
            "\n Select 1, 2, 3, or 4: ");
    command = input.nextInt();
    playerArray[0].setLocationA(command-1);
    playerArray[0].setLocationB(0);
    moveTo(0, playerArray[0].getLocationA(), playerArray[0].getLocationB());
    System.out.println("\nWe will encamp at " + getLocationName(playerArray[0].getLocationA()));
    for (int i = 1; i<numPlayers; i++){
        moveTo(i, (command-1+i)%numPlayers, 0);
        System.out.println("\nClan " + playerArray[i].getName() + " encamps at " + getLocationName(playerArray[i].getLocationA()));
    }
    }
    
    


    
        //Shuffle tiles

        //Randomly assign tiles

public static void prompt(int playerNumber){
    System.out.printf("\n\nTurn %d | The %s Clan | %s | Pop: %d| Wealth: %d | Control: %d\n  ", turnNumber+1, playerArray[playerNumber].getName(),getLocationName(playerArray[playerNumber].getLocationA()), playerArray[playerNumber].population, playerArray[playerNumber].wealth, playerArray[playerNumber].controlScore * 5);
}  //display turn, clan name, and location}
// BEGIN TURN METHOD
public static void turn(){
    int playerNumber = ((turnNumber%(numPlayers)));  // assign the turn to the correct player
    prompt(playerNumber);
    
    if (playerArray[playerNumber].population > 0) {
        rollDice();
        action(playerNumber);
        getMoves(playerNumber);
    
    if (playerArray[playerNumber].controlScore > 19)
        winner = playerNumber;
    } else
        System.out.println("The " + playerArray[playerNumber].getName() + " are extinct and forgotten...");
    turnNumber++;
}
        //If LocatonBonuses, ApplyThem
        //If Special Effects, Apply Them}
    
    // A Method For The Dice Rolling Sequence
    
    public static void rollDice(){
    
        Scanner input = new Scanner(System.in);
        int die; // Create an integer for the die
        //set the reroll flag on all the dice to 1/keep
        tempRoll[1][0] = 1;
        tempRoll[1][1] = 1;
        tempRoll[1][2] = 1;
        tempRoll[1][3] = 1;
        tempRoll[1][4] = 1;
        tempRoll[1][5] = 1;
        
        for (int i = 0; i < 6; i++){
        // Generate a random int 0-5
            die = (int) (Math.random()*6)+1;
            //Store the roll result on line 1 of the tempRoll array
            tempRoll[0][i] = die;
           }

        //allow up to 3 rolls
        mainloop:
        for (int numroll = 0; numroll < 3; numroll++){
            rollTable(3-numroll); //show the current roll
            System.out.print("\nWhich opinions do you want to influence?\n(Enter number(s) of their seats separated by spaces, or 0 if you agree): ");
        
        String select = input.nextLine();
            // Go through the input string character by character looking for integers
            for (int i=0; i < select.length(); i++){
                int a = Character.getNumericValue(select.charAt(i));
                if ((int) a >0 && (int) a <= 9){
                    tempRoll[1][a-1] = 0; //if you find an integer, set the keep re-roll flag on the die for that integer to 0
                }   
                if ((int) a == 0){ 
                    System.out.println("\nYour grand influence has prevailed.");
                    break mainloop; // if you find a 0, quit searching
                }    
            
            
            for (int t=0; t < 6; t++ ) {  //go through all of the rolls and look for keep roll flags that are 0 
            if (tempRoll[1][t] == 0){ //if you find one, reroll it
                 die = (int) (Math.random()*5)+1; //reroll the die
                 tempRoll[0][t] = die; //set the array to the roll
                 tempRoll[1][t] = 1; // reset the keep flag to 1
            }
            
            }
        
   
            }
             
    if (numroll==2){
            rollTable(3-numroll);
            System.out.println("\nYou have wasted enough time with these fools...");
    }
    //return tempRoll[][];
    }
    }
public static void rollTable(int n){
    
    //show a table of dice results
        System.out.println("\nThe tribal council is debating the priorities for the season...");
        //System.out.printf("%15s%20s%20s%20s%20s%20s\n", "Die 1", "Die 2", "Die 3", "Die 4", "Die 5", "Die 6");
        System.out.printf("First Elder: %10s\nSecond Elder: %10s\nThird Elder: %10s\nFourth Elder: %10s\nFifth Elder: %10s\nSixth Elder: %10s\n", diceTrans(0), diceTrans(1), diceTrans(2), diceTrans(3), diceTrans(4), diceTrans(5));
        System.out.println("\nYour status as the commander will give you " + n +" more chances to change their opinions.\n");
        
        
}

public static String getLocationName(int locCode){
        String location ="";
        switch (locCode){  
            case 0: location = "The Northern Plains";
                break;
            case 1: location = "The Western Plains";
                break;
            case 2: location = "The Southern Plains";
                break;
            case 3: location = "The Eastern Plains";
                break;
            case 4: location = "The Central Plains";
                break;
            case 5: location = "The Central Plains";
                break;
        }
        return location;
    }
    

public static String diceTrans(int number){
String text = "";

    switch (tempRoll[0][number]){
                case 1: text = "Grow the Population"; 
                                        break;// +1 Population
                case 2: text = "Attack the Enemy"; 
                                        break;// +1 Might
                case 3: text = "Expand Control"; 
                                        break;// +1 Control (Win @ 20)
                case 4: text = "Develop Trust"; 
                                        break;// +1 Vulnerability
                case 5: text = "Plunder the Countryside"; 
                                        break;// +1 Plunder
                case 6: text = "Engage in Trade"; 
                                        break;// +1 Prosperity
                
            }
    

return text;    
}


  //A method to clear the screen

    public static void CLS(int breaks){
        for (int i = 0; i < breaks; i++){
        System.out.print("\n\n\n\n\n\n");  
        
        }
   }  


  //RESOLVE DICE METHOD
        //Present Options + Number of each
public static void action(int playerNumber){
    Scanner input = new Scanner(System.in);
    CLS(1);
    

    for (int i = 0; i<6; i++){
        switch (tempRoll[0][i]){
            case 1: grow = grow + 1;
                break;
            case 2: attack = attack + 1;
                break;
            case 3: control = control + 1;
                break;
            case 4: trust = trust + 1;
                break;
            case 5: plunder = plunder + 1;
                break;
            case 6: trade = trade + 1;
                break;
        }
    }
    while (grow + attack + control + trust + plunder + trade > 0) {
        prompt(playerNumber);
        System.out.println("What next, Commander?: \n");
    
        if (grow > 0)
                System.out.println("(A)llow the men spend time with their wives\n");
        if (attack > 0)
                System.out.println("(L)ead the warriors to battle\n");
        if (control > 0)
            System.out.println("(T)end to administrative matters in your territory\n");
        if (trust > 0)
            System.out.println("(D)evelop good will with neighboring tribes\n");
        if (plunder > 0)
            System.out.println("(S)end the raiders to plunder the countryside\n");
        if (trade > 0)
            System.out.println("(O)pen the markets\n");
        System.out.print("We await your command: ");
        String command = input.nextLine();
         // Go through the input string character by character looking for integers
            for (int i=0; i < command.length(); i++){
                char a = command.toUpperCase().charAt(i);
                switch (a){
                    case 'A': grow(playerNumber);
                    break;
                    case 'T': control(playerNumber);
                    break;
                    case 'O': trade(playerNumber);
                    break;
                    case 'D': trust = 0;
                    break;
                    case 'S': plunder = 0;
                    break;
                    case 'L': attack(playerNumber);
                    break;
                }
    }            }   
    }



        //Attack
        public static void attack(int playerNumber){
        CLS(1);
        prompt(playerNumber);
            if (playerArray[playerNumber].getLocationA() < 4 && !isOpen(4)){ //If you're in zones 0-3 and anyone's in 4
            playerArray[whosIn(playerNumber, 4)].population = playerArray[whosIn(playerNumber, 4)].population - attack*100; //Resolve number of Attack Points to Player in 4
            System.out.println("\nYou send " + attack*25 + " ferocious " +
                    "horsemen to raid on the " + playerArray[whosIn(playerNumber, 4)].getName() + 
                    ".  Your men slaughter " + attack * 100 + " of the enemy" + 
                    ", leaving only " + playerArray[whosIn(playerNumber, 4)].population + " living.");
            } else
            if (playerArray[playerNumber].getLocationA() == 4) 
                if( !isOpen(0) || !isOpen(1) || !isOpen(2) || !isOpen(3)){ //If you're in zone 4, and anyones in zones 1-3
                
                for (int i =0; i < 4; i ++){
                    while (whosIn(playerNumber, i) != playerNumber){
                        playerArray[whosIn(playerNumber, i)].population = 
                                playerArray[whosIn(playerNumber, i)].population 
                                - attack*100;
                        System.out.println("\nYou send " + attack*25 + 
                                " ferocious " + "horsemen to raid on the " + 
                                playerArray[whosIn(playerNumber, i)].getName() + 
                    ".  Your men slaughter " + attack * 100 + " of the enemy" + 
                    ", leaving only " + 
                                playerArray[whosIn(playerNumber, i)].population 
                                + " living.");
                    }
                } 
            } else
                System.out.println("\nThere is no one for us to attack at the moment.");
        attack = 0;
        CLS(1);
        }
             
                    
                
                    //Resolve number of Attack points to all Players in 1-4
                //Else print there's nobody to attack!
        //Grow
        public static void grow(int playerNumber){
            CLS(1);
            prompt(playerNumber);
            if (playerArray[playerNumber].population < 1000){
            System.out.printf("%-3d pregnancies have been announced in the village!\n", grow*100);
            playerArray[playerNumber].population = playerArray[playerNumber].population + (grow * 100);
            if (playerArray[playerNumber].population > 1000) {
                System.out.println("\nWe don't have enough food to feed all of these mouths!\nThe sound of hungry crying haunts the village.\n\n" + (playerArray[playerNumber].population - 1000) + " children die of malnutrition.\n");
                playerArray[playerNumber].population = 1000;
            }
            grow = 0;
            System.out.println("The tribe is now " + playerArray[playerNumber].population + " strong!");
            }
            CLS(1);
        }


        //Plunder
          //While Destroy points>0
            //Show user buildings + units + costs
              //Prompt user for target
              //TargetLife = TargetLife - Destroy Ponts
              //Stat = Stat + TargetValue
        
        //Control
        public static void control(int playerNumber){
            CLS(1);
            prompt(playerNumber);
            System.out.println("You administer to your territory, solidifying your control of the steppe.\n");
            playerArray[playerNumber].controlScore = playerArray[playerNumber].controlScore + control;
            System.out.printf("You now control %2d%% of the steppe, just %2d%% eludes your grasp!", (playerArray[playerNumber].controlScore * 5),(20 - playerArray[playerNumber].controlScore) * 5);
            control = 0;
            CLS(1);
        }

        
        //Wealth
        public static void trade(int playerNumber){
            CLS(1);
            prompt(playerNumber);
            System.out.println("Your territory's vast trade routes feed your markets and the merchants are very successful.\n");
            playerArray[playerNumber].wealth = playerArray[playerNumber].wealth + trade;
            System.out.printf("The merchants made %d ounces of gold, and the treasury adds %d ounces of gold to the treasury\n", trade * 50, trade * 10);
            System.out.printf("We now have %d ounces of gold at our disposal", playerArray[playerNumber].wealth * 10);
            trade = 0;
            CLS(1);
        }

            
        //Trust
            //If Damage points = 1
                //attackPower = int Number of Units in Zone
                //Player's HP = HP - attackPower
                //attackPower = 0
            //If Damage points = 2
                //attackPower = int Number of Units in Zone
                //All Players in Zone Hp = HP - attackPower
                //AttackPower=0
            //If Damage points = 3
                //attackPower = int Number of Units in Zone
                //Player's HP = HP - attackPower
                //Attack Power = 0
                //next Player

    //MOVE METHOD
    
        public static boolean isOpen(int where){
            boolean answer = true;
            for (int i = 0; i < numPlayers; i++){
                if (playerArray[i].getLocationA() == where)
                    answer = false;
                
            }
            return answer;
        }
        public static void moveTo(int who, int A, int B) {
            int playerNumber = who;  // assign the turn to the correct player
            map[playerArray[who].getLocationA()][playerArray[playerNumber].getLocationB()] = 0; // set the old spot on the map to empty
            playerArray[who].setLocationA(A); //set the new location A coordinates
            playerArray[who].setLocationB(B); //set the new location B coordinates
            map[playerArray[who].getLocationA()][playerArray[playerNumber].getLocationB()] = playerNumber;
        }
    // a method for determining available movement
        public static void getMoves(int playerNumber){
        Scanner input = new Scanner(System.in);
            
        if (playerArray[playerNumber].getLocationA() < 4) {
         if (isOpen(4)){
            System.out.println("\nNow is our chance to capture the central plains!");
            System.out.println("We will break camp immediately to capture the vital crossroads!");
            moveTo(playerNumber, 4, 0);
         }
         else {
            for (int m = 0; m < 4; m++){
                if (isOpen(m)){
                    showMoves(m);
                }
            }    
            System.out.print("\nWhere should we go, commander? (Enter number): ");
            int goTo = input.nextInt();
            if (isOpen(goTo))
            moveTo(playerNumber, goTo, 0);
         }
           
        }
        else
            if (playerArray[playerNumber].getLocationA() == 4){
                if (playerArray[playerNumber].getLocationB() == 3)
                    System.out.println("We have the vital points of the realm in our grasp!");
                else
                    moveTo(playerNumber, 4, playerArray[playerNumber].getLocationB()+1);
                if (playerArray[playerNumber].population <= 500)
                    System.out.println("Our Central position has left the " +
                            "clanspeople vulnerable and our population has " +
                            " dwindled to a mere " + 
                            playerArray[playerNumber].population + " souls! " +
                            "Should we\n(0: Retreat?");
        
            }
        
        }
        
        public static void showMoves(int avail){
        System.out.println(avail + " " + getLocationName(avail));
        }
        
        public static int whosIn(int playerNumber, int location){
            int who=playerNumber;
            for (int i = 0; i<numPlayers; i ++){
                if (playerArray[i].getLocationA() == location)
                    who = i;
                    
            }
            return who;
            
        }  	
}        