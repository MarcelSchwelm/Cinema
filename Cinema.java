package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    private static Scanner scanner;

    public static void main(String[] args) {
        String[][] seatArray = constructCinema();
        menu(seatArray);
    }

    public static void menu(String[][] seatArray) {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int choice= scanner.nextInt();

        switch (choice) {
            case 1:
                printCinema(seatArray);
                break;
            case 2:
                getSeat(seatArray);
                break;
            case 3:
                statistics(seatArray);
                break;
            case 0:
                return;
            default:
                System.out.println("Please choose 1, 2, 3 or 0.");
                menu(seatArray);
                break;
        }
    }

    private static void statistics(String[][] seatArray) {
        int income=0;
        int totalIncome=0;
        int numberPurchasedTickets=0;
        int totalNumberSeats=0;
        double percentage;

        for (int i = 0; i < seatArray.length; i++){
            for (int j = 0; j < seatArray[i].length; j++) {
                if (seatArray[i][j].equals("B")) {
                    numberPurchasedTickets++;
                    totalNumberSeats++;
                    income+=calculatePrice(seatArray, i+1, j);
                }
                else {
                    totalNumberSeats++;
                }
                totalIncome+=calculatePrice(seatArray, i+1, j);
            }
        }

        percentage = ((double) numberPurchasedTickets / totalNumberSeats) * 100;

        System.out.printf("Number of purchased tickets: %d%n", numberPurchasedTickets);
        System.out.printf("Percentage: %.2f%s%n", percentage, "%");
        System.out.printf("Current income: $%d%n", income);
        System.out.printf("Total income: $%d%n", totalIncome);
        menu(seatArray);
    }

    public static String[][] constructCinema() {
        scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = scanner.nextInt();

        String[][] seatArray = new String[rows][seatsPerRow];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(seatArray[i], "S");
        }
        return seatArray;
    }

    public static void getSeat(String[][] seatArray) {
        System.out.println("Enter a row number:");
        int chosenRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int chosenSeat = scanner.nextInt();
        if (chosenRow > seatArray.length || chosenSeat > seatArray[0].length){
            System.out.println("Wrong input!");
            getSeat(seatArray);
        }
        if (seatArray[chosenRow - 1][chosenSeat - 1].equals("B")) {
            System.out.println("That ticket has already been purchased!");
            getSeat(seatArray);
        }
        else {
            seatArray[chosenRow - 1][chosenSeat - 1] = "B";
            System.out.println("Ticket price: $" + calculatePrice(seatArray, chosenRow, chosenSeat));
            menu(seatArray);
        }
    }

    private static int calculatePrice(String[][] seatArray, int chosenRow, int chosenSeat) {
        int price=0;
        int seats = seatArray.length * seatArray[0].length;
        if (seats <= 60) {
            price = 10;
        } else if (chosenRow <= seatArray.length / 2) {
            price = 10;
        } else if (chosenRow > seatArray.length /2) {
            price = 8;
        }
        return price;

    }

    public static void printCinema(String[][] seatArray) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= seatArray[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < seatArray.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seatArray[0].length; j++) {
                System.out.print(seatArray[i][j] + " ");
            }
            System.out.println();
        }
        menu(seatArray);

    }
}