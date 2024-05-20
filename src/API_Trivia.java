import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class API_Trivia {
    private ColoredUI coloredUI;
    private ErrorLogger_Quiz errorLogger;

    public API_Trivia() {
        coloredUI = new ColoredUI();
        errorLogger = new ErrorLogger_Quiz();
    }
    /**
     * Fetches a list of quiz questions from the Open Trivia API, optionally filtering by category.
     * @return A list of Quiz objects containing question details.
     */

    public List<Quiz> apiReader() {
        List<Quiz> quizList = new ArrayList<>(); // Initialize an empty list to store quizzes
        try {
            // Construct the URL to fetch 10 trivia questions ( by modifying "amount" for a different number)
            URL url = new URL("https://opentdb.com/api.php?amount=10");

            // Opening a stream to read data from the URL
            InputStreamReader isr = new InputStreamReader(url.openStream());
            JSONParser JP = new JSONParser();
            JSONObject JO = (JSONObject) JP.parse(isr);

            // Extract the "results" array containing individual quiz objects
            JSONArray quizzes = (JSONArray) JO.get("results");

            // Loop through each quiz object (JSONObject) in the "results" array
            for(Object item : quizzes ) {
                // Process the current quiz object and get a list of quiz objects
                quizList.addAll(getQuizzes((JSONObject) item));
            }
        } catch (MalformedURLException e) {
            errorLogger.error(new Date() + "," + getClass().getSimpleName() + ", " + e.getMessage());
            System.out.println(coloredUI.getRed() + "\nCurrently the URL link is not found.\n" + coloredUI.getRESET());
        } catch (IOException e) {
            errorLogger.error(new Date() + "," + getClass().getSimpleName() + ", " + e.getMessage());
            System.out.println(coloredUI.getRed() + "\nCurrently the URL link can not be read.\n" + coloredUI.getRESET());
        } catch (ParseException e) {
            errorLogger.error(new Date() + "," + getClass().getSimpleName() + ", " + e.getMessage());
            System.out.println(coloredUI.getRed() + "\nCurrently the URL link is facing a parse failure.\n" + coloredUI.getRESET());
        }
        return quizList;
    }

    private static List<Quiz> getQuizzes(JSONObject item) {
        String type = (String) item.get("type");
        String difficulty = (String) item.get("difficulty");
        String category = (String) item.get("category");
        String question = (String) item.get("question");
        String correctAnswer = (String) item.get("correct_answer");
        JSONArray incorrectAnswersArray = (JSONArray) item.get("incorrect_answers");

        List<String> incorrectAnswers = new ArrayList<>();

        // Loop through incorrect answer objects in the array and adding them to a separate list
        for (Object answer : incorrectAnswersArray) {
            incorrectAnswers.add((String) answer);
        }

        List<Quiz> quizList = new ArrayList<>();
        Quiz aQuiz = new Quiz(type, difficulty, category, question, correctAnswer, incorrectAnswers);

        quizList.add(aQuiz);

        return quizList;
    }
}
