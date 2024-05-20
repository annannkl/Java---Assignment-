import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Player {
    private ColoredUI coloredUI;
    private ErrorLogger_Quiz errorLogger = new ErrorLogger_Quiz();
    private Game game; // Reference to the associated Game object
    private int player_id;  // Hashed personal id for security
    private String username; // Hashed unique username for security
    private String password; // Hashed password for security
    private int score;  //Current player score

    // Constructor, getters, setters (omitted for brevity)

    /**
     * Registers a new player in the database. Hashes the user's password for security.
     *
     * @throws SQLException If there's an issue connecting to the database or executing the query.
     */

    public Player(String username, String password) {
        coloredUI = new ColoredUI();
        this.username = username;
        this.password = password;
    }

    /**
     * This constructor creates a Player object from the database, including player ID,
     * username,password(hashed), and score
     * @param player_id The unique identifier for the player in the database (hashed).
     * @param username The username of the player.
     * @param password The hashed password of the player.
     * @param score The player's current score.
     */

    public Player(int player_id, String username, String password, int score) {
        coloredUI = new ColoredUI();
        this.player_id = player_id;
        this.username = username;
        this.password = password;
        this.score = score;
    }

    /**
     * Registers a new player in the database.
     * Hashes the user's password for security before storing it.
     *
     * @throws SQLException If there is an issue connecting to the database
     * or executing the query.
     */

    public void saveNewPlayer() {
        Connection c = DB_Connection.getConnection();
        String query = "INSERT INTO players (username, password, score) VALUES (?, ?, 0)";
        try {
            assert c != null;
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, username);
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12)); // Hash the password
            // before saving.
            ps.setString(2, hashedPassword);
            ps.execute();
        } catch (SQLException e) {
            errorLogger.error(new Date() + "," + getClass().getSimpleName() + ", " + e.getMessage());
            System.out.println(coloredUI.getRed() + "\nUsername is taken, please try again.\n" + coloredUI.getRESET());
        }
    }

    /**
     * Attempts to load player data from the database based on username and password.
     * Verifies password using BCrypt.
     *
     * @return True if the player is successfully loaded, false otherwise.
     * @throws SQLException If there's an issue connecting to the database or executing the query.
     */

    public boolean loadPlayer() {
        Connection c = DB_Connection.getConnection();
        String query = "SELECT * FROM players WHERE username=?";
        try {
            assert c != null;
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                boolean match = BCrypt.checkpw(password, rs.getString("password")); // Check hashed password.
                if(match) {
                    player_id = rs.getInt("player_id");
                    score = rs.getInt("score");
                    return true;
                }
            }
        } catch (SQLException e) {
            errorLogger.error(new Date() + "," + getClass().getSimpleName() + ", " + e.getMessage());
            System.out.println("\nError: loading player");
        }
        return false;
    }

    /**
     * Saves the updated player score to the database.
     *
     * @throws SQLException If there's an issue connecting to the database or executing the query.
     */

    public void saveScore() {
        int newScore = game.updateScore(); // Get the updated score from the Game object
        Connection c = DB_Connection.getConnection();
        String query = "UPDATE players SET score=? WHERE player_id=?";
        try {
            assert c != null;
            PreparedStatement st = c.prepareStatement(query);
            st.setInt(1, newScore);
            st.setInt(2, player_id);
            st.execute();
        } catch (SQLException e) {
            errorLogger.error(new Date() + "," + getClass().getSimpleName() + ", " + e.getMessage());
            System.out.println("\nError: saving score");
        }
    }

    /**
     * Saves the game history to the database, including username, date, and score.
     *
     * @throws SQLException If there's an issue connecting to the database or executing the query.
     */

    public void saveGame() {
        Connection c = DB_Connection.getConnection(); // Get a connection to the database
        String query = "INSERT INTO history (username, date, score, player_id) VALUES (?, NOW(), ?, ?)";
        try {
            assert c != null;
            PreparedStatement st = c.prepareStatement(query); // Prepare the SQL statement
            st.setString(1, username); // Set username parameter
            st.setInt(2, game.getScore()); // Get the score from the associated Game object
            // and set the parameter.

            st.setInt(3, player_id); // Set player ID parameter
            st.execute(); // Execute the prepared statement to save the game history
        } catch (SQLException e) {
            errorLogger.error(new Date() + "," + getClass().getSimpleName() + ", " + e.getMessage());
            System.out.println("\nError: saving game"); // Printing an error message if there seems to be an issue.
        }
    }

    /**
     * Retrieves the player's game history from the database and returns it as
     * a list of formatted strings.
     *
     * @return A list of strings containing the player's username,date and score for each game played.
     */

    public List<String> getPlayerHistory() {
        List<String> history = new ArrayList<>(); // Create an empty list to store the history
        Connection c = DB_Connection.getConnection(); // Get a connection to the database
        String query = "SELECT * FROM history WHERE player_id=?"; // Select all history entries for this player
        try {
            assert c != null;
            PreparedStatement st = c.prepareStatement(query); // Prepare the SQL statement
            st.setInt(1, player_id); // Set the player ID parameter
            ResultSet rs = st.executeQuery(); // Execute the prepared statement and get the results
            while(rs.next()) { // Loop through each row in the result set
                history.add(rs.getString("username") + "   " + rs.getString("date")
                        + "       " + rs.getString("score"));  // Add a formatted string to the history list
            }
        } catch (SQLException e) {
            errorLogger.error(new Date() + "," + getClass().getSimpleName() + ", " + e.getMessage());
            System.out.println("\nError: getting player's history"); // Print an error message if there is a problem
        }
        return history;
    }

    /**
     * Retrieves the top scores from the database and returns them as a list of formatted strings.
     *
     * @return A list of strings containing the username and score of each player
     * ordered by highest score first.
     */

    public List<String> getScoreboard() {
        List<String> topScores = new ArrayList<>(); // Create an empty list to store the top scores
        Connection c = DB_Connection.getConnection(); // Get a connection to the database
        String query = "SELECT username, score FROM players ORDER BY score DESC"; // Select username
        // and score from players, ordered by score descending
        try {
            assert c != null;
            PreparedStatement st = c.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                topScores.add(rs.getString("username")
                        + "         " + rs.getString("score"));
            }
        } catch (SQLException e) {
            errorLogger.error(new Date() + "," + getClass().getSimpleName() + ", " + e.getMessage());
            System.out.println("\nError: loading scoreboard");
        }
        return topScores;
    }

    /**
     * Retrieves the average scores from the database, grouped by player,
     * and returns them as a list of formatted strings.
     *
     * @return A list of string containing the username and average score of each player.
     */

    public List<String> getAverageScores() {
        List<String> averageScores = new ArrayList<>(); // Create an empty list to store the average scores
        Connection c = DB_Connection.getConnection(); // Get a connection to the database
        String query = "SELECT username, AVG(score) AS Average FROM history " + " " // Select username and average score
                // from history.
                + "GROUP BY player_id ORDER BY Average DESC"; // Group by player ID and order by average score descending
        try {
            assert c != null;
            PreparedStatement st = c.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                averageScores.add(rs.getString("username")
                        + "        " + rs.getString("Average"));
            }
        } catch (SQLException e) {
            errorLogger.error(new Date() + "," + getClass().getSimpleName() + ", " + e.getMessage());
            System.out.println("\nError: loading average scores");
        }
        return averageScores;
    }

    /**
     * Associates the player object with a Game object. In result, it will allow the player to access game data
     * (the score).
     * while also update the player's score within the Game object.
     * @param game The Game object to associate with this player.
     */

    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Getter method to return the player's current score.
     *
     * @return the player's current score.
     */

    public int getScore() {
        return score;
    }
}