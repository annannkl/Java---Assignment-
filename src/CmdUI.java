import java.util.List;
import java.util.Scanner;

public class CmdUI {

    // Reference to a ColoredUI class for color codes
    private ColoredUI coloredUI;

    // Scanner object for user input
    private Scanner scanner;

    // Game object to handle game logic, questions, scoring, etc.
    private Game game;

    // Player object to manage player data, login, registration, history, etc.
    private Player player;

    public CmdUI() {
        // Create a new Scanner object to read user input
        game = new Game();
        // Create a new Game object to manage the game logic
        scanner = new Scanner(System.in);
        coloredUI = new ColoredUI();
    }

    /**
     * Presents the login menu to the user and handles their choice.
     *
     * @return The chosen menu option (1 for login, 2 for register, 0 to exit).
     */

    public int WelcomeMenu() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("1. Sign in to the System");
        System.out.println("2. Sign up to the System");
        System.out.println("0. EXIT");
        System.out.println("-------------------------------------------------------------");
        System.out.print("Select one of the following options:  ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character after integer input
        return choice;
    }

    /**
     * Presents the player menu to the user and handles their choice.
     *
     * @return The chosen menu option (1 to play, 2 to view history, 3 for scoreboards, 0 to logout).
     */

    public int playerMenu() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("1. Play:");
        System.out.println("2. History of Games:");
        System.out.println("3. Statistics of Players:");
        System.out.println("0. LOG OUT:");
        System.out.println("-------------------------------------------------------------");
        System.out.print("Select one of the following options: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("-------------------------------------------------------------");
        return choice;
    }

    /**
     * Presents the statistics menu to the user and handles their choice.
     *
     * @return The chosen menu option (1 for best scores, 2 for most consistent players, 0 to go back).
     */

    public int statisticsMenu() {
        System.out.println("1. Sort by Best Score:");
        System.out.println("2. Sort by Average Score:");
        System.out.println("0. GO BACK:");
        System.out.println("-------------------------------------------------------------");
        System.out.print("Select one of the following options: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    /**
     * Displays the available categories and asks the player
     * to choose, it then calls the selectCategory method from
     * player class providing with the list of categories and
     * the player's choice
     */

    public void DemonstrateCategory() {
        System.out.println(coloredUI.getMagenta() + "-------------------------------------------------------------");
        List<QuizCategories> categories = player.getCategories();
        for (QuizCategories aCategory : categories) {
            System.out.println(aCategory.getCategory_id() + ") " + aCategory.getCategory_name());
        }
        System.out.print(coloredUI.getRESET());
        System.out.println(coloredUI.getCyan() + "-------------------------------------------------------------");
        System.out.print("Select: " + coloredUI.getRESET());
        int choice = scanner.nextInt();
        scanner.nextLine();
        game.selectCategory(categories, choice);
    }

    /**
     * Displays the player's game history in a formatted way.
     */

    public void showPlayerHistory() {
        System.out.println(coloredUI.getBlue() + "#  Player /  Date   /  Time  /  Score" + coloredUI.getRESET());
        int index = 1;
        if (player.getPlayerHistory().isEmpty())
            System.out.println(coloredUI.getRed() + "There is no records of games!" + coloredUI.getRESET());
        for (String item : player.getPlayerHistory()) {
            System.out.println(index + ". " + item);
            index++;
        }
    }

    /**
     * Presents the current question to the user along with answer choices (if multiple choice) or prompts for true/false input.
     * Also displays the current score.
     */

    public void nextQuestion() {
        System.out.println(coloredUI.getBlue() + "\n" + game.getCurrentQuestion());
        if (game.checkType()) {  // Check if it's a multiple choice question
            System.out.print("\n");
            int index = 1;
            for (String item : game.getChoices()) {
                System.out.println(index + ") " + item);
                index++;
            }
            System.out.print(coloredUI.getRESET());
        } else {
            System.out.println(coloredUI.getYellow() + "\nTrue or False ?" + coloredUI.getRESET());
        }
        System.out.println(coloredUI.getCyan() + "\nThe Current score: " + game.getScore() + coloredUI.getRESET());
        System.out.print(coloredUI.getYellow() + "\nThe Answer: " + coloredUI.getRESET() + coloredUI.getRESET());
        String answer = scanner.nextLine();
        game.checkCurrentAnswer(answer);
    }

    /**
     * Asks the user if they want to play again and returns their answer (yes or no).
     *
     * @return "y" if the user wants to play again, "n" otherwise.
     */

    public String playAgain() {
        System.out.print(coloredUI.getMagenta() + "\nPlay Again? (Yes/No): " + coloredUI.getRESET());
        String response = scanner.nextLine();
        return response;
    }

    public void run() {
        // Initialize a variable to store user menu choices
        int choice = 0;
        // Print a welcome message and introduction to the application
        System.out.println(coloredUI.getMagenta()+ "-------------------------------------------------------------");
        System.out.println("Hello There! :D");
        System.out.println("-------------------------------------------------------------" + coloredUI.getRESET());
        System.out.println(coloredUI.getBlue() + "-------------------------------------------------------------");
        System.out.println("Welcome to our Exclusive Trivia Page Quiz Game!");
        System.out.println("Feel free to use, our user-contributed trivia question database!");
        System.out.println("Are you smart enough to solve these challenging quizzes?");
        System.out.println("Find out yourself!");
        System.out.println("-------------------------------------------------------------" + coloredUI.getRESET());

        // Enter the main loop of the application
        do {
            // Present the main menu and get user choice
            System.out.print(coloredUI.getCyan());
            choice = WelcomeMenu();
            System.out.print(coloredUI.getRESET());
            // Handle user choice based on the returned value
            switch (choice) {
                case 1:
                    if (login()) {
                        int option1;
                        do {
                            System.out.print(coloredUI.getCyan());
                            option1 = playerMenu();
                            System.out.print(coloredUI.getRESET());
                            switch (option1) {
                                case 1:
                                    DemonstrateCategory();
                                    String response;
                                    do {
                                        game.startGame();
                                        for (int i = 0; i < 10; i++) {
                                            nextQuestion();
                                        }
                                        System.out.println(coloredUI.getCyan() + "\nThe Final score: " + game.getScore() + coloredUI.getRESET());
                                        player.saveScore();
                                        player.saveGame();
                                        player.loadPlayer();
                                        game.resetScore();
                                        response = playAgain();
                                    } while (response.equals("Yes"));
                                    break;
                                case 2:
                                    showPlayerHistory();
                                    break;
                                case 3:
                                    int option2;
                                    do {
                                        System.out.print(coloredUI.getCyan());
                                        option2 = statisticsMenu();
                                        System.out.print(coloredUI.getRESET());
                                        switch (option2) {
                                            case 1:
                                                System.out.println(coloredUI.getMagenta() + "-------------------------------------------------------------");
                                                System.out.println("   SCOREBOARD" + coloredUI.getRESET());
                                                System.out.println(coloredUI.getBlue() + "\n#  Player / Score" + coloredUI.getRESET());
                                                System.out.println("-------------------------------------------------------------");
                                                int position1 = 1;
                                                for (String item : player.getScoreboard()) {
                                                    System.out.println(position1 + ". " + item);
                                                    position1++;
                                                }
                                                System.out.println("-------------------------------------------------------------");
                                                break;
                                            case 2:
                                                System.out.println(coloredUI.getMagenta() + "-------------------------------------------------------------");
                                                System.out.println("   MOST CONSISTENT" + coloredUI.getRESET());
                                                System.out.println(coloredUI.getBlue() + "\n#  Player / Average Score" + coloredUI.getRESET());
                                                System.out.println("-------------------------------------------------------------");
                                                int position2 = 1;
                                                for (String item : player.getAverageScores()) {
                                                    System.out.println(position2 + ". " + item);
                                                    position2++;
                                                }
                                                System.out.println("-------------------------------------------------------------");
                                                break;
                                        }
                                    } while (option2 != 0);
                            }
                        } while (option1 != 0);
                    } else {
                        System.out.println(coloredUI.getRed() + "\nAccording to the system, the credentials you have given are invalid!\n" + coloredUI.getRESET());
                        break;
                    }
                    break;
                case 2:
                    register();
                    break;
            }
        } while (choice != 0);

        // Application termination message
        System.out.println(coloredUI.getMagenta() + "-------------------------------------------------------------");
        System.out.println("That's all for now! :D ");
        System.out.println("-------------------------------------------------------------" + coloredUI.getRESET());
        // Print colored text to the console
        System.out.println(coloredUI.getBlue() + "-------------------------------------------------------------");
        System.out.println("Thank you for visiting our website.");
        System.out.println("We hope you enjoyed the game!");
        System.out.println("Until next time. Goodbye!");
        System.out.println("-------------------------------------------------------------" + coloredUI.getRESET());

    }

    public void register() {
        // Prompts the user for username and password
        System.out.println(coloredUI.getPurple() + "-------------------------------------------------------------");
        System.out.print("Give a username ->: ");
        String username = scanner.nextLine();
        System.out.print("Give password ->: ");
        String password = scanner.next();
        System.out.println("-------------------------------------------------------------" + coloredUI.getRESET());
        // Creates a new Player object with the provided credentials
        player = new Player(username, password);
        // Calls the Player object's method to save the new player data
        player.saveNewPlayer();
    }

    public boolean login() {
        System.out.println(coloredUI.getPurple() + "-------------------------------------------------------------");
        // Prompts the user for username and password
        System.out.print("Please give a username ->: ");
        String username = scanner.nextLine();
        System.out.print("Please give password ->: " + coloredUI.getRESET());
        String password = scanner.nextLine();
        // Creates a new Player object with the provided credentials (for login attempt)
        player = new Player(username, password);
        // Calls the Player object's method to attempt loading player data based on credentials
        // If login is successful, associate player with game and vice versa
        boolean ok = player.loadPlayer();
        game.setPlayer(player);
        player.setGame(game);
        // Returns true if login is successful, false otherwise
        return ok;
    }
}









