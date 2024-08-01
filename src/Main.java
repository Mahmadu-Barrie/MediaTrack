import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
 ItemManager itemManager = new ItemManager();
 Scanner scanner = new Scanner(System.in);
 boolean exit = false;
 //itemManager.loadFromFile("itemsSample.txt");

 while(!exit){
     System.out.println("1. Create a new item entry");
     System.out.println("2. Delete an existing item entry");
     System.out.println("3. Upload modifiable attributes of an item");
     System.out.println("4. List items");
     System.out.println("5. Terminate");

     System.out.print("Enter your choice: ");
     int choice = scanner.nextInt();
     scanner.nextLine();

     switch(choice){
         case 1:
                 itemManager.CreateNewItemEntry(scanner);
             break;
         case 2:
               itemManager.deleteExistingItemEntry(scanner);
             break;

         case 3:
               itemManager.updateItemAttributes(scanner);
             break;

         case 4:
              itemManager.listItems();
             break;

         case 5:
             itemManager.saveItemsToFile("items-new.txt"); // items are saved to file before termination
             exit =true;
             break;
         default:
             System.out.println("Invalid entry: Please select a valid choice");
     }

 }
        scanner.close();
 //scanner.close();
        }
    }