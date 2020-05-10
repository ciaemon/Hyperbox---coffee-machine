package machine;
import java.util.Scanner;

public class CoffeeMachine {
    enum State {
        COMMAND_WAIT,
        COFFEE_CHOOSE,
        WATER_ADD,
        MILK_ADD,
        BEANS_ADD,
        CUPS_ADD
    }
    static State state = State.COMMAND_WAIT;
    static int water = 400;
    static int milk = 540;
    static int beans = 120;
    static int cups = 9;
    static int money = 550;

    final static Scanner scanner = new Scanner(System.in);

    static boolean haveEnough(int waterNeed, int milkNeed, int beansNeed) {
        if (waterNeed > water) {
            System.out.println("Sorry, not enough water!");
            return false;
        }
        if (milkNeed > milk) {
            System.out.println("Sorry, not enough milk!");
            return false;
        }
        if (beansNeed > beans) {
            System.out.println("Sorry, not enough coffee beans!");
            return false;
        }
        if (cups == 0) {
            System.out.println("Sorry, not enough disposable cups!");
            return false;
        }
        water -= waterNeed;
        milk -= milkNeed;
        beans -= beansNeed;
        cups--;
        return true;
    }
    static void list() {
      System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println(money + " of money");
    }
    void buy(String choice) {
        switch (choice) {
            case "1":
                if (haveEnough(250, 0, 16)) {
                    money += 4;
                    System.out.println("I have enough resources, making you a coffee!");
                }
                break;
            case "2":
                if (haveEnough(350, 75, 20)) {
                    money += 7;
                    System.out.println("I have enough resources, making you a coffee!");
                }
                break;
            case "3":
                if (haveEnough(200, 100, 12)) {
                   money += 6;
                    System.out.println("I have enough resources, making you a coffee!");
                }
                break;
                }
    }
    static void process(String input) {
         switch (state) {

             case COMMAND_WAIT:
                 switch (input) {
                     case "buy":
                         System.out.println("What do you want to buy& 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
                         state = State.COFFEE_CHOOSE;
                         break;
                     case "fill":
                         System.out.print("Write how many ml of water do you want to add: ");
                         state = State.WATER_ADD;
                         /*
                         water += scanner.nextInt();
                         System.out.print("Write how many ml of milk do you want to add: ");
                         milk += scanner.nextInt();
                         System.out.print("Write how many grams of coffee beans do you want to add: ");
                         beans += scanner.nextInt();
                         System.out.print("Write how many disposable cups of coffee do you want to add: ");
                         cups += scanner.nextInt(); */
                         break;
                     case "take":
                         System.out.println("I gave you $" + money);
                         money = 0;
                         System.out.println("Write action (buy, fill, take, remaining, exit): ");
                         state = State.COMMAND_WAIT;
                         break;
                     case "remaining":
                         list();
                         System.out.println("Write action (buy, fill, take, remaining, exit): ");
                         state = State.COMMAND_WAIT;
                         break;
                 }
                 break;
             case COFFEE_CHOOSE:
                 switch (input) {
                     case "1":
                         if (haveEnough(250, 0, 16)) {
                             money += 4;
                             System.out.println("I have enough resources, making you a coffee!");
                         }
                         break;
                     case "2":
                         if (haveEnough(350, 75, 20)) {
                             money += 7;
                             System.out.println("I have enough resources, making you a coffee!");
                         }
                         break;
                     case "3":
                         if (haveEnough(200, 100, 12)) {
                             money += 6;
                             System.out.println("I have enough resources, making you a coffee!");
                         }
                         break;
                 }
                 System.out.println("Write action (buy, fill, take, remaining, exit): ");
                 state = State.COMMAND_WAIT;
                 break;
             case WATER_ADD:
                 water += Integer.parseInt(input.trim());
                 System.out.print("Write how many ml of milk do you want to add: ");
                 state = State.MILK_ADD;
                 break;
             case MILK_ADD:
                 milk += Integer.parseInt(input.trim());
                 System.out.print("Write how many grams of coffee beans do you want to add: ");
                 state = State.BEANS_ADD;
                 break;
             case BEANS_ADD:
                 beans += Integer.parseInt(input.trim());
                 System.out.print("Write how many disposable cups of coffee do you want to add: ");
                 state = State.CUPS_ADD;
                 break;
             case CUPS_ADD:
                 cups += Integer.parseInt(input.trim());
                 System.out.println("Write action (buy, fill, take, remaining, exit): ");
                 state =State.COMMAND_WAIT;
                 break;
             default:
                 throw new IllegalStateException("Unexpected value: " + state);
         }

}



    static void main(String[] args) {



        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String command = scanner.next();

        while (!command.equals("exit")) {
            process(command);
            command = scanner.next();
        }

    }
}
