import java.util.Scanner;

public class KafeHanzel15 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] customerNames = null;
        int[] tableNumbers = null;
        String[][] orderedItems = null;
        int[] totalPrices = null;
        int orderCount = 0;

        while (true) {
            System.out.println("\n===== MENU UTAMA ======");
            System.out.println("1. Tambahkan Pesanan");
            System.out.println("2. Tampilkan Daftar Pesanan");
            System.out.println("3. Keluar");

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
                        System.out.println("Terlalu Banyak Order.");
                    }
                    break;

                case 2:
                    displayOrders(customerNames, tableNumbers, orderedItems, totalPrices, orderCount);
                    break;

                case 3:
                    System.out.println("\nTerima kasih telah berkunjung di KafeHanzel15.");
                    return;

                default:
                    System.out.println("Invalid. Pilih (1-3)");
            }
        }
    }

    public static int getChoice(Scanner sc, int maxChoice) {
        int choice = -1;
        while (true) {
            System.out.print("Pilih menu: ");
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
    
                if (choice >= 1 && choice <= maxChoice) {
                    break;
                } else {
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            } else {
                sc.nextLine();
                System.out.println("Input tidak valid. Silakan coba lagi.");
            }
        }
        return choice;
    }
    

    public static void processOrder(Scanner sc, String[] customerNames, int[] tableNumbers,
            String[][] orderedItems, int[] totalPrices, int orderCount) {
        String[] drinks = { "Kopi Hitam", "Latte", "Teh Tarik", "Mie Goreng" };
        int[] drinkPrices = { 15000, 22000, 12000, 18000 };

        String customerName = "";
        int tableNumber = -1;
        int totalPrice = 0;
        String[] orderItems = new String[10];
        int itemCount = 0;

        while (true) {
            System.out.print("\nMasukkan nama pelanggan: ");
            customerName = sc.nextLine();
            break;
        }

        while (true) {
            System.out.print("Masukkan nomor meja: ");
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
                    System.out.println("Maaf, nomor meja " + tableNumber + " sudah dipesan. Silakan pilih meja lain.");
                } else if (tableNumber > 0) {
                    break;
                } else {
                    System.out.println("Nomor meja harus lebih dari 0. Silakan coba lagi.");
                }
            } else {
                System.out.println("Nomor meja harus berupa angka. Silakan coba lagi.");
                sc.nextLine();
            }
        }

        System.out.println("\n===== MENU KAFE =====");
        displayMenu(drinks, drinkPrices);

        while (true) {
            System.out.print("\nPilih menu (masukkan nomor menu, atau 0 untuk selesai): ");
            int menuChoice = sc.nextInt();
            if (menuChoice == 0) {
                break;
            }

            if (menuChoice >= 1 && menuChoice <= drinks.length) {
                int quantity = 0;
                while (true) {
                    System.out.print("Masukkan jumlah item untuk " + drinks[menuChoice - 1] + ": ");
                    quantity = sc.nextInt();

                    if (quantity > 0) {
                        break;
                    } else {
                        System.out.println("Pesanan tidak boleh 0, coba lagi.");
                    }
                }

                int price = drinkPrices[menuChoice - 1];
                orderItems[itemCount] = drinks[menuChoice - 1] + " x " + quantity + " = Rp " + (price * quantity);
                itemCount++;
                totalPrice += price * quantity;
            } else {
                System.out.println("Menu tidak valid. Silakan coba lagi.");
            }
        }

        customerNames[orderCount] = customerName;
        tableNumbers[orderCount] = tableNumber;
        orderedItems[orderCount] = new String[itemCount];

        for (int i = 0; i < itemCount; i++) {
            orderedItems[orderCount][i] = orderItems[i];
        }

        totalPrices[orderCount] = totalPrice;

        System.out.println("\nPesanan berhasil ditambahkan.");
        System.out.println("Total harga pesanan: Rp " + totalPrice);
    }

    public static void displayMenu(String[] items, int[] prices) {
        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + ". " + items[i] + " - Rp " + prices[i]);
        }
    }

    public static void displayOrders(String[] customerNames, int[] tableNumbers, String[][] orderedItems,
            int[] totalPrices, int orderCount) {
        System.out.println("\n===== DAFTAR PESANAN =====:");

        for (int i = 0; i < orderCount; i++) {
            String customerName = (customerNames[i] != null) ? customerNames[i] : "Tidak ada nama";
            System.out.println("Nama Pelanggan: " + customerName);
            System.out.println("Nomor Meja: " + tableNumbers[i]);
            System.out.println("Detail Pesanan:");

            for (String item : orderedItems[i]) {
                System.out.println("- " + item);
            }

            System.out.println("Total Harga Pesanan : Rp " + totalPrices[i]);
            System.out.println("-------------------------------------");
        }
    }
}
