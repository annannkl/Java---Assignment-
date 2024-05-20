import java.util.List;

public class Game {
    private ColoredUI coloredUI;
    // A list to store the quizzes for the current game session
    private List<Quiz> quizList;
    // A reference to the Player object associated with the current game session
    private Player player;
    // The current score accumulated by the player during the game session
    private int gameScore;
    // An index variable used to track the current position within the quiz list
    private int current;

    public void setPlayer(Player player) {
        // Assign the provided Player object to the game's player variable
        this.player = player;
    }

    /**
     * Starts the game by fetching questions from the API (using API_Trivia).
     * Initializes game score and current question index.
     */

    public void startGame() {
        // Create an API_Trivia object to interact with the trivia API
        API_Trivia api = new API_Trivia();
        // Retrieve a list of quizzes from the trivia API
        quizList = api.apiReader();
        // Reset the game score to zero for the new game session
        gameScore = 0;
        // Set the current question index to zero
        current = 0;
        coloredUI = new ColoredUI();
    }

    /**
     * Checks if the current question is a multiple-choice type.
     *
     * @return True if the question is multiple choice, false otherwise.
     */

    public boolean checkType() {
        return "multiple".equals(quizList.get(current).getType());
    }

    /**
     * Retrieves a list of answer choices for the current question (applicable to multiple choice questions).
     *
     * @return A list of strings representing the answer choices.
     */

    public List<String> getChoices() {
        List<String> choices = quizList.get(current).getPossibleAnswers();
        return choices;
    }

    /**
     * Gets the current question text.
     *
     * @return A formatted string representing the current question (e.g., "Q1: What is the capital of France?").
     */

    public String getCurrentQuestion() {
        return "Q" + (current+1) + ": " + quizList.get(current).getQuestion();
    }

    /**
     * Checks the user's answer for the current question.
     *
     * @param answer The user's answer string.
     */

    public void checkCurrentAnswer(String answer) {
        // Check if the user's answer is correct for the current question
        if(quizList.get(current).isCorrect(answer)) {
            System.out.println(coloredUI.getGreen() + "\nCorrect!" + coloredUI.getRESET());
            increaseScore();
        } else {
            System.out.println(coloredUI.getRed() +"\nIncorrect!" + coloredUI.getRESET());
        }
        current++;
    }

    /**
     * Increases the game score by 1.
     */

    public void increaseScore() {
        // Increment the game score by 1 point
        gameScore++;
    }

    /**
     * Gets the current game score.
     *
     * @return The integer value of the game score.
     */

    public int getScore() {
        // Return the current value of the game score
        return gameScore;
    }

    /**
     * Resets the game score to 0.
     */

    public void resetScore() {
        // Set the game score back to zero
        gameScore = 0;
    }

    /**
     * Updates the player's overall score by adding the current game score.
     *
     * @return The updated player score.
     */

    public int updateScore() {
        // Calculate the new score by adding the game score to the player's current score

        // Update the player's score with the new value (assuming the Player class has a method for this)
        // player.setScore(newScore);  // Uncomment if the Player class has a setScore method

        // Return the new score
        return player.getScore() + gameScore;
    }
}
