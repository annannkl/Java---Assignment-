import java.util.Collections;
import java.util.List;

public class Quiz {
    private String type; // Type of question (e.g., multiple choice, true/false)
    private String difficulty; // Difficulty level of the question
    private String category; // Optional category of the question (new requirement)
    private String question; // The actual question text
    private String correctAnswer;  // The correct answer to the question
    private List<String> incorrectAnswers; // List of incorrect answer choices

    // Constructor, getters, setters (omitted for brevity)
    /**
     * Creates a new Quiz object with the provided details.
     *
     * @param type        Type of the question
     * @param difficulty  Difficulty level of the question
     * @param category    Optional category of the question (new requirement)
     * @param question    The actual question text
     * @param correctAnswer  The correct answer to the question
     * @param incorrectAnswers  List of incorrect answer choices
     */

    public Quiz(String type, String difficulty, String category, String question, String correctAnswer, List<String> incorrectAnswers) {
        this.type = type;
        this.difficulty = difficulty;
        this.category = category; // Handle new category field
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }
    /**
     * Gets the type of the question (e.g., multiple choice, true/false).
     *
     * @return The type of the question as a string.
     */
    public String getType() {
        return type;
    }
    /**
     * Gets the optional category of the question (new requirement).
     *
     * @return The category of the question as a string, or null if not specified.
     */
    public String getCategory() {
        return category;
    }
    /**
     * Gets the actual question text.
     *
     * @return The question text as a string.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Gets the correct answer to the question.
     *
     * @return The correct answer as a string.
     */

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Creates a combined list of answer choices, including both incorrect answers and the
     * correct answer in a shuffled order.
     *
     * @return A shuffled list of all possible answer choices.
     */

    public List<String> getPossibleAnswers() {
        List<String> possibleAnswers = incorrectAnswers;
        possibleAnswers.add(correctAnswer); // Add correct answer to the list
        Collections.shuffle(possibleAnswers); // Shuffle all answers
        return possibleAnswers;
    }

    /**
     * Checks if the provided user answer matches the correct answer (case-sensitive).
     *
     * @param answer The user's answer string.
     * @return True if the answer is correct, false otherwise.
     */

    public boolean isCorrect(String answer) {
        return this.correctAnswer.equals(answer);
    }
}
