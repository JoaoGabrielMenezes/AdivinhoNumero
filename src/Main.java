import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Joao Menezes
 */
public class Main {
    private static int choice;
    private static int attempts;
    private static int delay = 40;
    private static Scanner sc;
    // Cores
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public static void main(String[] args) throws Exception {
        clear();
        Thread.sleep(500);
        _init_();
    }
    private static void clear() throws InterruptedException, IOException {
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
//            Runtime.getRuntime().exec("clear");
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    private static void _init_() throws InterruptedException, IOException {
        attempts = 0;
        System.out.println("+-----------------Well-Come-----------------+\n");
        System.out.print("\tPress ENTER to start\n");
        sc = new Scanner(System.in);
        String start = sc.nextLine();
        if (start.equals("")) Load();
    }

    private static void PlayAgain() throws InterruptedException, IOException {
        Thread.sleep(1000);
        clear();
        delay = 0;
        _init_();
    }

    private static void EndGame() throws InterruptedException, IOException {
        System.out.println("Thanks for playing!");
        Thread.sleep(1000);
        clear();
        System.exit(0);
    }

    private static void Ask() throws InterruptedException, IOException {
        System.out.print("Would like to play again? (y/n) ");
        String playAgain = sc.next().toLowerCase();
        switch (playAgain) {
            case "y" -> PlayAgain();
            case "n" -> EndGame();
            default -> {
                System.out.println("Invalid options only [y/n]");
                Ask();
            }
        }
        while (!playAgain.equals("s") && !playAgain.equals("n"))
            Ask();

    }

    private static void Load() throws InterruptedException, IOException {
        String anim = "|/-\\";
        for (int i = 0; i < 101; i++) {
            String data = "\r" + anim.charAt(i % anim.length()) + " " + i;
            System.out.write(data.getBytes());
            Thread.sleep(delay);
        }
        clear();
        Game();
    }

    private static void Game() throws InterruptedException, IOException {
        try {
            System.out.println(ANSI_PURPLE + "\n\nTry think what number I'm thinking? (0 - 100)\n" + ANSI_RESET);
            int random = new Random().nextInt(101);
            // System.out.println(random);
            while (choice != random) {

                attempts++;
                System.out.print(">_ ");
                choice = sc.nextInt();
                if (choice < random) {
                    System.out.println("Bigger than -> " + ANSI_YELLOW + choice + ANSI_RESET);
                } else if (choice > random) {
                    System.out.println("Lower than -> " + ANSI_YELLOW + choice + ANSI_RESET);
                } else {
                    System.out.println(ANSI_GREEN + "\n\tGREAT" + ANSI_RESET + "\nYou attempts: " + ANSI_GREEN
                            + attempts + ANSI_RESET);
                    Ask();
                }
            }
        } catch (Exception e) {
            System.err.println(ANSI_RED + "\n\nType only numbers" + ANSI_RESET);
            Thread.sleep(1000);
            clear();
            delay = 0;
            _init_();
        }
    }
}
