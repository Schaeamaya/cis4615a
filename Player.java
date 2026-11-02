/* Schae Amaya
World Cup Player Manager
COP3330 Object - Oriented Programming
Player.java
*/

/* Rules implemented : 
    MET00-J - Validate method arguments 
    NUM00-J - Detect or prevent integer overflow 
    OBJ01-J - Limit accessibility of fields 
*/
public class Player
{
    // Attributes
    private String name;
    private String country;
    private String position;
    private int jerseyNumber;
    private int goalsScored;
    private int assists;
    private int age;

    // Sets default values
    public Player()
    {
        // Initialize all instance variables to standard default values
        this.name = "Unknown";
        this.country = "TBD";
        this.position = "Midfielder";
        this.jerseyNumber = 0;
        this.goalsScored = 0;
        this.assists = 0;
        this.age = 18;
    }

    // Sets all variables with given parameters
    public Player(String name, String country, String position, int jerseyNumber, int goalsScored, int assists, int age)
    {
        // SEI CERT RULE MET00-J: Validate method arguments
        if (name == null || country == null || position == null) {
            throw new IllegalArgumentException("String attributes cannot be null.");
        }
        if (jerseyNumber < 0 || goalsScored < 0 || assists < 0 || age < 0) {
            throw new IllegalArgumentException("Numeric attributes cannot be negative.");
        }
        // Assign each parameter to its corresponding class attribute
        this.name = name;
        this.country = country;
        this.position = position;
        this.jerseyNumber = jerseyNumber;
        this.goalsScored = goalsScored;
        this.assists = assists;
        this.age = age;
    }

    // Sets name, country, and jersey number
    public Player(String name, String country, int jerseyNumber)
    {
        // SEI CERT RULE MET00-J: Validate method arguments
        if (name == null || country == null) {
            throw new IllegalArgumentException("Name and country cannot be null.");
        }
        if (jerseyNumber < 0) {
            throw new IllegalArgumentException("Jersey number cannot be negative.");
        }
        // Assign provided values and set defaults for remaining variables
        this.name = name;
        this.country = country;
        this.jerseyNumber = jerseyNumber;
        this.position = "Midfielder";
        this.goalsScored = 0;
        this.assists = 0;
        this.age = 18;
    }

    // Prints basic information
    public void displayInfo()
    {
        System.out.println(this.toString());
    }

    // Prints regular info and detailed stats if true
    public void displayInfo(boolean detailed)
    {
        System.out.println(this.toString());

        if (detailed)
        {
            // Calculates contribution value using goals and assists
            // SEI CERT RULE NUM00-J: Detect or prevent integer overflow
            int contribution = Math.addExact(Math.multiplyExact(this.goalsScored, 2), this.assists);
            // Decides label category based on contribution threshold
            String label = "Developing";
            if (contribution >= 10)
            {
                label = "Star";
            }
            else if (contribution >= 5)
            {
                label = "Solid";
            }

            System.out.println("Contribution: " + contribution);
            System.out.println("Label:        " + label);
            System.out.println("--------------------------------");
        }
    }

    // Updates goals scored
    public void updateStats(int goals)
    {
        if (goals < 0) {
            throw new IllegalArgumentException("Cannot add negative goals.");
        }
        // SEI CERT RULE NUM00-J: Detect or prevent integer overflow
        this.goalsScored = Math.addExact(this.goalsScored, goals);
    }

    public void updateStats(int goals, int assists)
    {
        if (goals < 0 || assists < 0) {
            throw new IllegalArgumentException("Cannot add negative stats.");
        }
        // SEI CERT RULE NUM00-J: Detect or prevent integer overflow
        this.goalsScored = Math.addExact(this.goalsScored, goals);
        this.assists = Math.addExact(this.assists, assists);
    }

    // Converts object data to a formatted string matching the layout guide exactly
    public String toString()
    {
        return String.format("%-10s%s\n", "Name:", this.name) +
               String.format("%-10s%s\n", "Country:", this.country) +
               String.format("%-10s%s\n", "Position:", this.position) +
               String.format("%-10s#%d\n", "Jersey:", this.jerseyNumber) +
               String.format("%-10s%d\n", "Goals:", this.goalsScored) +
               String.format("%-10s%d\n", "Assists:", this.assists) +
               String.format("%-10s%d\n", "Age:", this.age) +
               "--------------------------------";
    }

    // Finds the player with the most goals
    public static Player findTopScorer(Player[] roster)
    {
        // Check if the roster array is null or empty
        if (roster == null || roster.length == 0)
        {
            return null;
        }

        Player topScorer = null;

        // Iterate through the roster array to find the highest goal scorer
        for (int i = 0; i < roster.length; i++)
        {
            if (roster[i] != null)
            {
                if (topScorer == null || roster[i].goalsScored > topScorer.goalsScored)
                {
                    topScorer = roster[i];
                }
            }
        }
        return topScorer;
    }

    // Counts players by a specific position
    public static int countByPosition(Player[] roster, String position)
    {
        if (roster == null || position == null) return 0;
        
        // Iterate through array and count case-insensitive position matches
        int count = 0;
        for (Player p : roster)
        {
            if (p != null && p.position != null && p.position.equalsIgnoreCase(position))
            {
                count++;
            }
        }
        return count;
    }

    // Counts players by a specific position and country
    public static int countByPosition(Player[] roster, String position, String country)
    {
        if (roster == null || position == null || country == null) return 0;

        // Iterate through array and count case-insensitive position and country matches
        int count = 0;
        for (Player p : roster)
        {
            if (p != null && p.position != null && p.country != null &&
                p.position.equalsIgnoreCase(position) && p.country.equalsIgnoreCase(country))
            {
                count++;
            }
        }
        return count;
    }

    // Calculates the average age of all valid players in the roster
    public static double averageAge(Player[] roster)
    {
        if (roster == null || roster.length == 0) return 0.0;

        // Sum the ages of all valid player objects
        // SEI CERT RULE NUM00-J: Use long to prevent integer overflow when summing arrays
        long totalAge = 0;
        int count = 0;
        for (Player p : roster)
        {
            if (p != null)
            {
                totalAge += p.age;
                count++;
            }
        }
        return count == 0 ? 0.0 : (double) totalAge / count;
    }

    // Calculates the average age of players from a specific country
    public static double averageAge(Player[] roster, String country)
    {
        if (roster == null || country == null) return 0.0;

        // Sum the ages of players matching the specified country case-insensitively
        // SEI CERT RULE NUM00-J: Use long to prevent integer overflow when summing arrays
        long totalAge = 0;
        int count = 0;
        for (Player p : roster)
        {
            if (p != null && p.country != null && p.country.equalsIgnoreCase(country))
            {
                totalAge += p.age;
                count++;
            }
        }
        return count == 0 ? 0.0 : (double) totalAge / count;
    }

    // Displays a list of players belonging to a specific country
    public static void filterByCountry(Player[] roster, String country)
    {
        // Check if the provided roster array or country string is null
        if (roster == null || country == null)
        {
            return;
        }

        // Always print the country header and initial divider first
        System.out.println("Players from " + country + ":");
        System.out.println("--------------------------------");

        // First pass: count matching players to determine if any exist
        int count = 0;
        for (Player p : roster)
        {
            if (p != null && p.country != null && p.country.equalsIgnoreCase(country))
            {
                count++;
            }
        }

        // If no players match the country, print the not found message and closing divider
        if (count == 0)
        {
            System.out.println("No players from " + country + " were found.");
            System.out.println("--------------------------------");
        }
        else
        {
            // Display each matching player in array order without sorting
            for (Player p : roster)
            {
                if (p != null && p.country != null && p.country.equalsIgnoreCase(country))
                {
                    p.displayInfo();
                }
            }
        }
    }
}
