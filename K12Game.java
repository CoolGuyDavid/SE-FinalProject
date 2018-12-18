/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David and Blain
 */
import java.util.*;
public class K12Game {
    static Scanner scan = new Scanner(System.in);
    static Random rand = new Random();
    static dice die = new dice();
    public static String playerName;
    public static int playerhp;
    public static int maxhp;
    public static int maxmana;
    public static int mana;
    public static int playermeleedmg;
    public static int xp;
    public static int enemyhp;
    public static int enemymeleedmg;
    public static int Level;
    public static String charclass;
    public static boolean fighting = false; //globals for player stats & enemy stats
    private static void printStats() {
        if(charclass.equals("mage")){
            System.out.println("\n\n\n" + playerName + "\n\033[32;1;2mHP: " + playerhp + "\033[0m"+ "\n\033[34mMANA: " + mana + "\033[0m\n\033[31mDAMAGE: " + playermeleedmg + "\033[0m\nxp: " + xp + "\n");
        }else{
            System.out.println("\n\n\n" + playerName + "\n\033[32mHP: " + playerhp + "\033[0m\n\033[31mdamage: " + playermeleedmg + "\033[0m\nxp: " + xp + "\n");
        }
        }
    private static void printEnemyStats() {
        System.out.println("Enemy"+"\n\033[32mHP: " + enemyhp + "\033[0m\n\033[31mdmg: " + enemymeleedmg + "\033[0m\n");
    }
    private static void buildWarrior() {
        charclass = "warrior";
        maxhp = 20;
        playerhp = 20;
        playermeleedmg = 4;
        xp = 0;
        Level = 1; 
    }
    private static void buildArcher() {
        charclass = "archer";
        maxhp = 14;
        playerhp = 14;
        playermeleedmg = 6;
        xp = 0;
        Level = 1;
    }
    private static void buildMage() {
        charclass = "mage";
        maxhp = 10;
        playerhp = 10;
        mana = 20;
        maxmana = 20;
        playermeleedmg = 2;
        xp = 0;
        Level = 1; // initializes globals according to class
    }
    private static void buildEnemy() {
        switch(Level){
        case 1:
            enemyhp = 9;
            enemymeleedmg = 1;
            System.out.println("Its a Goblin. \033[32;1;2m\nHP: " + enemyhp +"\033[0m"+ "\n\033[31;1mDamage: " + enemymeleedmg + "\033[30;2m");
            break;
        case 2:
            enemyhp = 19;
            enemymeleedmg = 4;
            System.out.println("Its a Orc. \033[32;1;2m\nHP: " + enemyhp +"\033[0m"+ "\nDamage: " + enemymeleedmg);
            break;
        case 3:
            enemyhp = 24;
            enemymeleedmg = 6;
            System.out.println("Its a Captain. \033[32;1;2m\nHP: " + enemyhp +"\033[0m"+ "\nDamage: " + enemymeleedmg);
            break;
        case 4:
            enemyhp = 32;
            enemymeleedmg = 7;
            System.out.println("Its a Troll. \033[32;1;2m\nHP: " + enemyhp +"\033[0m"+ "\n\033[31;1m Damage: \033[30;2m" + enemymeleedmg);
            break;
        case 5:
            enemyhp = 40;
            enemymeleedmg = 9;
            System.out.println("Its a Hydra. \033[32;1;2m\nHP: " + enemyhp +"\033[0m"+ "\nDamage: " + enemymeleedmg);
            break; //initializes enemy stats based on player level
        }
    }
    private static void fight() {
        String action;
        String spellAction = null;
        System.out.println("An enemy approaches");
        buildEnemy();
        Scanner temp = new Scanner(System.in);
        Scanner temp1 = new Scanner(System.in);
        Scanner temp2 = new Scanner(System.in);
        fighting = true;
        while(fighting = true){
            System.out.println("\nPress 'a' to attack\nPress 'i' for info");
            if(charclass.equals("mage")){
                System.out.print("Press 's' for spells\n");
            }
            action = temp.nextLine();
            if(action.equalsIgnoreCase("a") ){
                System.out.println("\nSuccess");
                fighting = attack();
                if(fighting == false){
                    switch(Level){
                    case 1: 
                        xp = xp + 4;
                        break;
                    case 2:
                        xp = xp + 6;
                        break;
                    case 3:
                        xp = xp + 9;
                        break;
                    case 4:
                        xp = xp + 12;
                        break;
                    }
                    System.out.println("You earned :" + xp + " xp");
                    checkLevelUp();
                    return;
                }
                enemyattack();  
            }

            if(action.charAt(0) == 'i'){
                System.out.println("_____________________________");
                printStats();
                printEnemyStats();

            }
            if(action.charAt(0) == 's'){
            System.out.println("Press 'f' for fireball\nPress 'h' to heal\n");
            spellAction = temp1.nextLine();
            if(spellAction.charAt(0) == 'f'){
            if(die.roll10() > 2){
                mana = mana - 10;
                if(mana <0){
                    System.out.println("You don't have enough mana...");
                    mana = mana + 10;
                }else{
                int k = die.roll10(); //randomly hurts 1-10
                System.out.println("You hit for " + k + " damage!");
                enemyhp = enemyhp - k;
                if(enemyhp <= 0){
                    System.out.println("\033[31;1m You Won!\033[30;2m"); 
                    switch(Level){
                    case 1: 
                        xp = xp + 4;
                        break;
                    case 2:
                        xp = xp + 6;
                        break;
                    case 3:
                        xp = xp + 9;
                        break;
                    case 4:
                        xp = xp + 12;
                        break;
                    }
                    System.out.println("You earned :" + xp + " xp");
                    checkLevelUp();
                    return;
                }
                enemyattack();  
                }
            }
            else{
                System.out.println("\033[31mYou miss!\033[0m");
                enemyattack();
            }
            }else
            if(spellAction.charAt(0) == 'h'){
                mana = mana - 8;
                if(mana <0){
                    System.out.println("\nYou don't have enough mana...");
                    mana = mana + 8;
                }else{
                    int x = die.roll10(); //randomly heals 1-8
                System.out.println("\nYou heal your wounds...");
                System.out.println("+ " + x + " HP");
                playerhp = playerhp + x;
                if(playerhp>maxhp){
                    playerhp = maxhp;
                }
                enemyattack();  
            }
            }

    }
    }
    }

    private static void checkLevelUp() {
        if(xp >= 100 && Level == 4){
            System.out.println("Level 5!");
            Level = Level + 1;
            maxhp = maxhp + 25;
            playerhp = maxhp;
            if(charclass.equals("mage")){
                maxmana = maxmana + 7;
                mana = maxmana;
            }
            playermeleedmg = playermeleedmg + 3;
            printStats();
        }else
        if(xp >= 50 && Level == 3){
            System.out.println("Level 4!");
            Level = Level + 1;
            maxhp = maxhp + 20;
            playerhp = maxhp;
            if(charclass.equals("mage")){
                maxmana = maxmana + 7;
                mana = maxmana;
            }
            playermeleedmg = playermeleedmg + 2;
            printStats();
        }else
        if(xp >= 25 && Level == 2){
            System.out.println("Level 3!");
            Level = Level + 1;
            maxhp = maxhp + 10;
            playerhp = maxhp;
            if(charclass.equals("mage")){
                maxmana = maxmana + 7;
                mana = maxmana;
            }
            playermeleedmg = playermeleedmg + 2;
            printStats();
        }else
        if(xp >= 10 && Level == 1){
            System.out.println("Level 2!");
            Level = Level + 1;
            maxhp = maxhp + 5;
            playerhp = maxhp;
            if(charclass.equals("mage")){
                maxmana = maxmana + 7;
                mana = maxmana;
            }
            playermeleedmg = playermeleedmg + 1;
            printStats();
        }//increments player level and adds to stats with xp

    }
    private static void enemyattack() {
        if(die.roll6() > 2){
            System.out.println("\033[31mEnemy hits!\033[0m");
            playerhp = playerhp - enemymeleedmg;
            if(playerhp <= 0){
                gameover();
                System.exit(0);//game over if player health < 0
            }
        }else{
            System.out.println("\033[32mEnemy Misses!\033[0m");
        }       
    }
    private static boolean attack() {
        if(die.roll6() > 2){
            System.out.println("\033[32mYou hit!\033[0m");
            enemyhp = enemyhp - playermeleedmg;
            if(enemyhp <= 0){
                System.out.println("\033[31;1m You Won!\033[30;2m"); //prints if enemy health < 0
                return false; 
            }
        }else{
            System.out.println("\033[31mYou miss!\033[0m");
        }
        return true;
    }

    private static void gameover() {
        System.out.println(playerName + " Died!") ;
        System.out.println("GAME OVER!");
        System.exit(0); //terminates if lost
        return;
    }
    public static void main(String[] args) {
        String charclass;
        int num = 2;
        while(num > 1){
        System.out.println("Enter your Name: ");
        playerName = scan.nextLine();
        System.out.println("Choose your class: ");
        System.out.println("'w' for warrior");
        System.out.println("'a' for archer");
        System.out.println("'m' for mage");
        charclass = scan.nextLine();
        while(charclass.charAt(0) != 'w' && charclass.charAt(0) != 'a' && charclass.charAt(0) != 'm'){
            System.out.println("'w' for warrior");
            System.out.println("'a' for archer");
            System.out.println("'m' for mage");
            charclass = scan.nextLine();
        }
        if(charclass.charAt(0) == 'w'){
            buildWarrior();
        }
        if(charclass.charAt(0) == 'a'){
            buildArcher();
        }
        if(charclass.charAt(0) == 'm'){
            buildMage();
        }
        printStats();
        while(Level == 1){
        System.out.println("\n\n\nWould you like to 1: explore, 2: rest, or 3: eat?:\n");
        int choice = 0;
        while(choice != 1 && choice != 2 && choice != 3){
           choice = scan.nextInt();
        while(choice != 1 && choice != 2 && choice != 3){
            System.out.println("\n\n\nWould you like to 1: explore, 2: rest, or 3: eat?:\n");
            choice = scan.nextInt();
        }
            System.out.println("________________________________________");
        }
        fight();
        }
        System.out.println("This area is clear... time to move on\n");
        while(Level == 2){
            System.out.println("Would you like to 1: explore, 2: rest, or 3: eat?:\n");
        int choice = 0;
        while(choice != 1 && choice != 2 && choice != 3){
           choice = scan.nextInt();
        while(choice != 1 && choice != 2 && choice != 3){
            System.out.println("Would you like to 1: explore, 2: rest, or 3: eat?:\n");
            choice = scan.nextInt();
        }
            System.out.println("________________________________________");
        }
        fight();
        }
        System.out.println("This area is clear... time to move on\n");
        while(Level == 3){
            fight();
        }
        System.out.println("This area is clear... time to move on\n");
        while(Level == 4){
            fight();
        }
        System.out.println("This area is clear... time to move on\n");
        while(Level == 5){
            fight();
        }//keeps in area until levelUp
    }

    }
}
