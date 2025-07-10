/*You're building a Movie Review Analyzer for a streaming platform. 
Your application will:
 - Read a list of N movies and their viewer ratings and reviews.
 - Compute a popularity score based on ratings and review lengths.
 - Classify movies as Blockbuster, Hit, or Flop.
 - Output a summary report for each movie.

Popularity Score Formula
------------------------
    - ratingScore = rating / 5.0
    - reviewScore = min(1.0, reviewLength / 200.0)
    - Final Score = (0.6 * ratingScore + 0.4 * reviewScore) * 100

Movie Classification
--------------------

    | Score-Range| Classification|
    ------------------------------
    |  ≥ 80	     | Blockbuster   |
    |  50–79	 | Hit           |
    |  < 50	     | Flop          | 


Tasks to Implement:
-------------------
- Create a POJO class Movie
    - Fields: title, rating, review
    - Constructor, getters, setters

- Create a POJO class MovieReport
    - Fields: Movie, score, category
    - toString() to return formatted output

- Define an interface MovieAnalyzer
    - Method: MovieReport analyze(Movie movie);

- Implement the interface in MovieAnalyzerImpl
    - Compute the score
    - Classify into category
    - Return a MovieReport

- Update Main class to:
    - Read N movies and their data from standard input
    - Print formatted analysis for each

Sample Input:
-------------
3
Inception
4.8
Mind-bending thriller with amazing visuals and story.
The Room
1.5
Terrible acting and editing. Poor direction.
Interstellar
4.5
Science fiction masterpiece. Great concept and emotional depth.

Sample Output
-------------
Movie: Inception, Score: 68.2, Category: Hit
Movie: The Room, Score: 26.8, Category: Flop
Movie: Interstellar, Score: 66.6, Category: Hit


Main.java
---------*/
import java.util.*;

public class Day76P1{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String title = sc.nextLine();
            double rating = Double.parseDouble(sc.nextLine());
            String review = sc.nextLine();
            movies.add(new Movie(title, rating, review));
        }

        MovieAnalyzer analyzer = new MovieAnalyzerImpl();

        for (Movie m : movies) {
            MovieReport report = analyzer.analyze(m);
            System.out.println(report);
        }
    }
}

// POJO class for Movie
class Movie {
    private String title;
    private double rating;
    private String review;

    // Constructor (uses setters)
    public Movie(String title, double rating, String review) {
        setTitle(title);
        setRating(rating);
        setReview(review);
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setReview(String review) {
        this.review = review;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }
}

// POJO class for MovieReport
class MovieReport {
    private Movie movie;
    private double score;
    private String category;

    // Constructor
    public MovieReport(Movie movie, double score, String category) {
        this.movie = movie;
        this.score = score;
        this.category = category;
    }

    // toString method for formatted output
    @Override
    public String toString() {
        return String.format("Movie: %s, Score: %.1f, Category: %s",
                movie.getTitle(), score, category);
    }
}

// Interface for MovieAnalyzer
interface MovieAnalyzer {
    MovieReport analyze(Movie movie);
}

// Implementation of MovieAnalyzer
class MovieAnalyzerImpl implements MovieAnalyzer {
    @Override
    public MovieReport analyze(Movie movie) {
        double ratingScore = movie.getRating() / 5.0;
        int reviewLength = movie.getReview().length();
        double reviewScore = Math.min(1.0, reviewLength / 200.0);

        double finalScore = (0.6 * ratingScore + 0.4 * reviewScore) * 100;

        String category;
        if (finalScore >= 80.0) {
            category = "Blockbuster";
        } else if (finalScore >= 50.0) {
            category = "Hit";
        } else {
            category = "Flop";
        }

        return new MovieReport(movie, finalScore, category);
    }
}
