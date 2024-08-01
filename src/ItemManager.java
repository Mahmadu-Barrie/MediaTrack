
import java.util.*;
import java.io.*;
import java.util.Comparator;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
public class ItemManager {
    private LinkedList<Entertainment> itemList;

    public ItemManager() {
        itemList = new LinkedList<>();

    }

    /**
     * Items defined in given file are loaded
     * and kept in an itemList variable of type LinkedList<Entertainment>
     * @param fileName
     */
    public void loadFromFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            String line;
            while (scanner.hasNext()) {
                line = scanner.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }
                String[] attribute = line.split("\\s+,2");

                String attributeName = attribute[0].trim();
                String attributeValue = attribute[0].trim();

                switch (attributeName) {
                    case "type":
                        String type = attributeValue;
                        String uniqueId = scanner.nextLine().split("\\s+")[0];
                        String title = scanner.nextLine().split("\\s+")[0];
                        String person = scanner.nextLine().split("\\s+")[0];
                        double cost = Double.parseDouble(scanner.nextLine().substring("cost".length() + 1));
                        String haveIt = scanner.nextLine().split("\\s+")[0];
                        String comment = scanner.nextLine().split("\\s+")[0];
                        String date = "";
                        int numberOfPlayers = 0;


                        ;
                        if (type.equals("movie") || type.equals("game")) {
                            date = scanner.nextLine().split("\\s+")[0].trim();
                        }
                        if (type.equals("game")) {
                            numberOfPlayers = Integer.parseInt(scanner.nextLine().split("\\s+")[0].trim());
                        }

                        if (type.equals("album")) {
                            itemList.add(new Album(type, uniqueId, title, person, cost, haveIt, comment));
                        } else if (type.equals("movie")) {
                            itemList.add(new Movie(type, uniqueId, title, person, cost, haveIt, comment, date));
                        } else if (type.equals("game")) {
                            itemList.add(new Game(type, uniqueId, title, person, cost, haveIt, comment, date, numberOfPlayers));
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    public void saveItemsToFile(String fileName) {
        try {
            File file = new File(fileName);
            PrintWriter writer = new PrintWriter(file);
            for (Entertainment item : itemList) {
                writer.println("type " + item.getAttributeType());
                writer.println("uniqueID " + item.getUniqueId());
                writer.println("title " + item.getTitle());
                writer.println("person " + item.getPerson());
                writer.println("cost " + item.getCost());
                writer.println("haveIt " + item.gethaveIt());
                writer.println("comment " + item.getComment());

                if (item.getAttributeType().equals("Game")) {
                    // item is of type "game"
                    writer.println("date " + ((Game) item).getDate());
                    writer.println("numberOfPlayers " + ((Game) item).getNumberOfPlayers());

                } else if (item.getAttributeType().equals("movie")) {
                    // item is of type "movie"
                    writer.println("date " + ((Movie) item).getDate());
                } else {
                    throw new IllegalArgumentException("Unrecognized attributeType: " + item.getAttributeType());
                }

            }

         writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * generates a random string of 10 alphanumeric characters
     * @return returns said generated string
     */

    private String generateUniqueID() {
        String ALPHANumericChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int stringLength = 10;
        StringBuilder randomString = new StringBuilder(stringLength);
        Random random = new Random();

        for (int i = 0; i < stringLength; i++) {
            int index = random.nextInt(ALPHANumericChars.length());
            char randChar = ALPHANumericChars.charAt(index);
            randomString.append(randChar);
        }
        return randomString.toString();

    }

    /**
     * upon user entry a new Item of an entered type is created
     * with its specific attributes also requested and added to the creation of the new Item
     * @param scanner
     */

    public void CreateNewItemEntry(Scanner scanner) {
        System.out.println("Enter the type of the item(album,movie, or Game): ");
        String type = scanner.nextLine();
        String uniqueID = generateUniqueID();
        System.out.println("Enter the name of this " + type);
        String title = scanner.nextLine();
        System.out.println("Enter the individual responsible for this " + type + " aka (director, artist, developer");
        String person = scanner.nextLine();
        System.out.println("Enter the cost of this " + type);
        double cost = Double.parseDouble(scanner.nextLine());
        System.out.println("Do you have this " + type);
        String haveIt = scanner.nextLine();
        System.out.println("Enter a one line comment regarding this " + type);
        String comment = scanner.nextLine();
        String movieDate = null;
        String gameDate = null;
        int numPlayers = 0;


        if (type.equalsIgnoreCase("movie")) {
            System.out.println("Enter the date this movie went on sale");
            movieDate = scanner.nextLine();
        }

        if (type.equalsIgnoreCase("game")) {
            System.out.println("Enter the date this game went on sale");
            gameDate = scanner.nextLine();

            System.out.println("Enter the number of players for this game");
            numPlayers = scanner.nextInt();
        }


        Entertainment newItem = null;

        if (type.equalsIgnoreCase("album")) {
            newItem = new Album(type.toLowerCase(), uniqueID, title, person, cost, haveIt, comment);
        } else if (type.equalsIgnoreCase("movie")) {
            newItem = new Movie(type.toLowerCase(), uniqueID, title, person, cost, haveIt, comment, movieDate);
        } else if (type.equalsIgnoreCase("game")) {
            newItem = new Game(type.toLowerCase(), uniqueID, title, person, cost, haveIt, comment, gameDate, numPlayers);
        } else {
            System.out.println("Invalid item entry, Please enter a valid type (game,movie,album)");
            return;
        }
        itemList.add(newItem);

        System.out.println("Successful new item entry");
    }

    /**
     * Item is identified by its uniqueId and removed from the itemList
     * if remove is unsuccessful user is informed item was not in list
     * @param scanner
     */

    public void deleteExistingItemEntry(Scanner scanner) {
        System.out.println("Enter the uniqueId of the item to be deleted: ");
        String uniqueId = scanner.nextLine();

        boolean removed = false;
        Iterator<Entertainment> iterator = itemList.iterator();

        while (iterator.hasNext()) {
            Entertainment item = iterator.next();
            if (item.getUniqueId().equals(uniqueId)) {
                iterator.remove();
                removed = true;
                break;
            }
        }
        if (removed) {
            System.out.println("Item with uniqueID: " + uniqueId + " has been successfully been removed");

        } else {
            System.out.println("Item with uniqueID: " + uniqueId + " has not been found in itemList");
        }

    }

    /**
     * Items are identified by their uniqueId and attributes by name
     * upon user entry one is allowed to modify specific attributes within a said itemEntry
     * @param scanner
     */

    public void updateItemAttributes(Scanner scanner) {
        System.out.println("Enter the uniqueId of the item to be modified: ");
        String uniqueId = scanner.nextLine();

        Entertainment itemToUpdate = null;

        for (Entertainment item : itemList) {
            if (item.getUniqueId().equals(uniqueId)) {
                itemToUpdate = item;
                break;
            }
        }
        if (itemToUpdate == null) {
            System.out.println("Item with uniqueId: " + uniqueId + " not found in itemList");
            return;
        }
        System.out.println("Enter the name of the attribute you would like to update(title,person,cost,haveIt,comment,date,numberOfPlayers): ");
        String attributeName = scanner.nextLine();

        switch (attributeName.toLowerCase()) {
            case "title":
                System.out.println("Enter the new title:");
                itemToUpdate.setTitle(scanner.nextLine());
                break;
            case "person":
                System.out.println("Enter the new (artist/developer/director):");
                itemToUpdate.setPerson(scanner.nextLine());
                break;
            case "cost":
                System.out.println("Enter the new cost:");
                itemToUpdate.setCost(Double.parseDouble(scanner.nextLine()));
                break;
            case "haveIt":
                System.out.println("Enter the new status of whether you posses this item or not");
                itemToUpdate.setHaveIt(scanner.nextLine());
                break;
            case "comment":
                System.out.println("Enter the new comment regarding this item");
                itemToUpdate.setComment(scanner.nextLine());
                break;
            case "date":
                if (itemToUpdate instanceof Movie || itemToUpdate instanceof Game) {
                    System.out.println("Enter the new date: ");
                    String newDate = scanner.nextLine();
                    if (itemToUpdate instanceof Movie) {
                        ((Movie) itemToUpdate).setDate(newDate);
                    } else {
                        ((Game) itemToUpdate).setDate(newDate);
                    }
                } else {
                    System.out.println("Invalid attribute for this type of item");
                }
                break;

            case "numberofplayers":
                if (itemToUpdate instanceof Game) {
                    System.out.println("Enter the new number of players for this game ");
                    int newNumPlayers = Integer.parseInt(scanner.nextLine());
                    ((Game) itemToUpdate).setnumberOfPlayers(newNumPlayers);
                } else {
                    System.out.println("Invalid attribute for this type of item");
                }
            default:
                System.out.println("Invalid attribute name. Please enter a valid entry(title,person,cost,haveIt,comment,date,numberOfPlayers): ");

        }
        System.out.println("Item attributes have been updated successfully");

    }

    /**
     * items are listed in sorted order, albums, then movies, then Games
     * within a kind of item items are listed alphabetically according to their title
     */

    public void listItems() {

        // defining a custom comparator for sorting the items
        Comparator<Entertainment> itemComparator = new Comparator<Entertainment>() {
            @Override
            public int compare(Entertainment o1, Entertainment o2) {
                int typeComparison = o1.getAttributeType().compareTo(o2.getAttributeType());
                if (typeComparison == 0) {
                    return o1.getTitle().compareTo(o2.getTitle());
                }
                return typeComparison;
            }
        };
         // utilizing custom comparator
        Collections.sort(itemList, itemComparator);

        // printing the sorted itemList
        System.out.println("List of items:");
        for (Entertainment item : itemList) {
            System.out.println(item);

        }
    }

}