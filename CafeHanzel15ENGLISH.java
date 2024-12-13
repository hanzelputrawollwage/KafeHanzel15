import java.util.Scanner;

public class CafeHanzel15ENGLISH {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] customerNames = null;
        int[] tableNumbers = null;
        String[][] orderedItems = null;
        int[] totalPrices = null;
        int orderCount = 0;

        while (true) {
            System.out.println("\n===== MAIN MENU ======");
            System.out.println("1. Place an Order");
            System.out.println("2. Display Order");
            System.out.println("3. Exit");

            int choice = getChoice(sc, 3);

            switch (choice) {
                case 1:
                    if (orderCount == 0) {
                        customerNames = new String[100];
                        tableNumbers = new int[100];
                        orderedItems = new String[100][];
                        totalPrices = new int[100];
                    }

                    if (orderCount < customerNames.length) {
                        processOrder(sc, customerNames, tableNumbers, orderedItems, totalPrices, orderCount);
                        orderCount++;
                    } else {
                        System.out.println("Too Many Order.");
                    }
                    break;

                case 2:
                    displayOrders(customerNames, tableNumbers, orderedItems, totalPrices, orderCount);
                    break;

                case 3:
                    System.out.println("\nThankyou for visiting CafeHanzel15.");
                    return;

                default:
                    System.out.println("Invalid. Choose (1-3)");
            }
        }
    }

    public static int getChoice(Scanner sc, int maxChoice) {
        int choice = -1;
        while (true) {
            System.out.print("Select Menu: ");
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
    
                if (choice >= 1 && choice <= maxChoice) {
                    break;
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            } else {
                sc.nextLine();
                System.out.println("Invalid choice. Try again.");
            }
        }
        return choice;
    }
    

    public static void processOrder(Scanner sc, String[] customerNames, int[] tableNumbers,
            String[][] orderedItems, int[] totalPrices, int orderCount) {
        String[] drinks = { "Black Coffee", "Latte", "Teh Tarik", "Mie Goreng" };
        int[] drinkPrices = { 15000, 22000, 12000, 18000 };

        String customerName = "";
        int tableNumber = -1;
        int totalPrice = 0;
        String[] orderItems = new String[10];
        int itemCount = 0;

        while (true) {
            System.out.print("\nEnter customer's name: ");
            customerName = sc.nextLine();
            break;
        }

        while (true) {
            System.out.print("Enter table number: ");
            if (sc.hasNextInt()) {
                tableNumber = sc.nextInt();
                sc.nextLine();

                boolean tableOccupied = false;
                for (int i = 0; i < orderCount; i++) {
                    if (tableNumbers[i] == tableNumber) {
                        tableOccupied = true;
                        break;
                    }
                }

                if (tableOccupied) {
                    System.out.println("Sorry, table " + tableNumber + " already booked, please choose another table.");
                } else if (tableNumber > 0) {
                    break;
                } else {
                    System.out.println("Table number should more than 0.");
                }
            } else {
                System.out.println("Table number should be a number.");
                sc.nextLine();
            }
        }

        System.out.println("\n===== CAFE MENU =====");
        displayMenu(drinks, drinkPrices);

        while (true) {
            System.out.print("\nChoose Menu (enter menu's number, 0 to complete order): ");
            int menuChoice = sc.nextInt();
            if (menuChoice == 0) {
                break;
            }

            if (menuChoice >= 1 && menuChoice <= drinks.length) {
                int quantity = 0;
                while (true) {
                    System.out.print("Enter quantity for  " + drinks[menuChoice - 1] + ": ");
                    quantity = sc.nextInt();

                    if (quantity > 0) {
                        break;
                    } else {
                        System.out.println("The order can't be 0.");
                    }
                }

                int price = drinkPrices[menuChoice - 1];
                orderItems[itemCount] = drinks[menuChoice - 1] + " x " + quantity + " = Rp " + (price * quantity);
                itemCount++;
                totalPrice += price * quantity;
            } else {
                System.out.println("Invalid menu. Try again.");
            }
        }

        customerNames[orderCount] = customerName;
        tableNumbers[orderCount] = tableNumber;
        orderedItems[orderCount] = new String[itemCount];

        for (int i = 0; i < itemCount; i++) {
            orderedItems[orderCount][i] = orderItems[i];
        }

        totalPrices[orderCount] = totalPrice;

        System.out.println("\nOrder Successfull.");
        System.out.println("Total Order Prices: Rp " + totalPrice);
    }

    public static void displayMenu(String[] items, int[] prices) {
        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + ". " + items[i] + " - Rp " + prices[i]);
        }
    }

    public static void displayOrders(String[] customerNames, int[] tableNumbers, String[][] orderedItems,
            int[] totalPrices, int orderCount) {
        System.out.println("\n===== ORDER LIST =====:");

        for (int i = 0; i < orderCount; i++) {
            String customerName = (customerNames[i] != null) ? customerNames[i] : "No name";
            System.out.println("Customer's name: " + customerName);
            System.out.println("Table number: " + tableNumbers[i]);
            System.out.println("Detail Order:");

            for (String item : orderedItems[i]) {
                System.out.println("- " + item);
            }

            System.out.println("Total Order Prices : Rp " + totalPrices[i]);
            System.out.println("-------------------------------------");
        }
    }
}
