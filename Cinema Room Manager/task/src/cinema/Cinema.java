package cinema;

import java.util.Scanner;

public class Cinema {
    static int currentIncome;
    static int totalIncome;
    static int counter;
    static int seatsNumeats;
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //input rows and seats
        System.out.println("Enter the number of rows:");
        int rowNum = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsNum = scanner.nextInt();
        seatsNumeats = seatsNum * rowNum;
        String[][] cinema = new String[rowNum][seatsNum];
        for (int row = 0; row < rowNum; row++) {
            for (int col = 0; col < seatsNum; col++) {
                cinema[row][col] = "S";
            }
        }
        System.out.println();
        //menu
        int menu = -1;
        while (menu != 0) {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            menu = scanner.nextInt();
            if (menu > 3) {
                System.out.println("Wrong input!");
            }
            switch (menu) {
                case 1:
                    showTheSits(cinema);
                    break;
                case 2:
                    buyTicket(cinema, rowNum, seatsNum);
                    break;
                case 3:
                    statistics(rowNum, seatsNum);
            }
        }
    }

    private static void showTheSits(String[][] cinema) {
        //PRINT ROOM
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= cinema[0].length; i++) { //до тех пор пока i <=  количества мест в зале( отвечает просто за верхнюю строку)
            System.out.print(" " + i);
        }
        System.out.println();
        for (int k = 0; k < cinema.length; k++) { // до тех пор пока i <= количетву рядов(Отвечает за столбец)
            System.out.print(k + 1);
            for (int j = 0; j < cinema[k].length; j++) {
                System.out.print(" " + cinema[k][j]);
            }
            System.out.println();
        }
    }

    private static void buyTicket(String[][] cinema, int rowNum, int seatsNum) {
        System.out.println("Enter a row number:");
        int rowN = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatN = scanner.nextInt();
        if (rowN < 1 || rowN > rowNum || seatN < 1 || seatN > seatsNum) {
            System.out.println("Wrong input!\n");
            buyTicket(cinema, rowNum, seatsNum);
            return;
        } else if (cinema[rowN - 1][seatN - 1].equals("B")) {
            System.out.println("\nThat ticket has already been purchased!\n");
            buyTicket(cinema, rowNum, seatsNum);
            return;
        } else {
            cinema[rowN - 1][seatN - 1] = "B";
            counter++;
            currentIncome += costTicket(rowNum, rowN);
        }
        System.out.println("Ticket price: $" + costTicket(rowNum, rowN));
    }

    private static void statistics(int rowNum, int seatsNum) {

        PercentIncomeTotal(rowNum, counter, seatsNum);
        System.out.printf("Current income: $%d \n", currentIncome);
        System.out.println("Total income: $" + totalIncome);

    }

    private static void PercentIncomeTotal(int rowNum, int counter, int seatsNum) {
        float perCent;
        int back = rowNum - (rowNum / 2);
        int r2 = rowNum / 2;
        System.out.println("Number of purchased tickets:" + counter);
        perCent = ((float) counter / (float) seatsNumeats) * 100;
        System.out.printf("Percentage: %.2f%%", perCent);
        System.out.println();
        if (seatsNumeats < 60) {
            totalIncome = seatsNumeats * 10;
        }
        if (seatsNumeats > 60) {
            totalIncome = (r2 * seatsNum * 10) + (back * seatsNum * 8);
        }
    }

    private static int costTicket(int rowNum, int rowN) {
        int ticketPrice = 10;
        int backRowsTicketPrice = 8;

        if (seatsNumeats > 60 && rowN > (rowNum / 2)) {
            ticketPrice = backRowsTicketPrice;
        }
        return ticketPrice;
    }
}