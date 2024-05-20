
/**
 * The QuizCategories object is declared by this class,
 * and it gives other classes whatever information they want about.
 * QuizCategories, such as category_id, category_name, and * api_url value.
 */

public class QuizCategories {
    /** The category's id */
    private int category_id;
    /** The name of the category */
    private String category_name;
    /** The value of it's API URL */
    private String api_trivia;

    /**
     * The QuizCategories constructor
     * @param category_id gives the id of the category
     * @param category_name gives the name of the category
     * @param api_trivia gives the value of it's api_url
     */

    public QuizCategories(int category_id, String category_name, String api_trivia) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.api_trivia = api_trivia;
    }

    /**
     * Gives the id of the category
     *
     * @return The id
     */

    public int getCategory_id() {
        return category_id;
    }

    /**
     * Gives the name of the category
     *
     * @return The name
     */

    public String getCategory_name() {
        return category_name;
    }

    /**
     * Gives the value of it's API URL
     *
     * @return The value
     */

    public String getApi_trivia() {
        return api_trivia;
    }
}
