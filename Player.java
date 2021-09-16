/**
 * Αυτή η κλάση αναπαριστά έναν παίκτη.
 * @author Thanasis, Aris
 */
public class Player {

    private String name;
    private float score;
    private int bet;
    private float highScore;
    private int wins;

    /**
     *
     * Κατασκευαστής
     *
     * @param name Το όνομα που έχει δώσει ο παίκτης.
     * @param score Το σκορ του παίκτη για το παιχνίδι "Σωστή απάντηση"
     *                           (στη συγκεκριμένη υλοποίηση ισούται με μηδέν μιας και δεν κρατάμε αρχείο νικών).
     //* @param betPoints Το σκορ του παίκτη για το παιχνίδι "Ποντάρισμα"
     *                  (στη συγκεκριμένη υλοποίηση ισούται με μηδέν μιας και δεν κρατάμε αρχείο νικών).
     */
    public Player(String name, float score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return this.name;
    }

    public float getScore() {
        return this.score;
    }

    public float getHighScore() {
        return this.highScore;
    }

    public int getWins() {
        return this.wins;
    }

    public void setHighScore(float highScore) {
        this.highScore = highScore;
    }

    public void setWins(int wins) {
        this.wins = wins + 1;
    }

    /**
     * Η μέθοδος αυτή αντιπροσωπεύει το παιχνίδι "Σωστή απάντηση".
     *
     * @param ans Η απάντηση που έδωσε ο παίκτης.
     * @param rightAns Η σωστή απάντηση της ερώτησης.
     */
    public String correctAnswer(boolean ans, String rightAns) {
        if (ans) {
            this.score += 1000;
            return ("Correct! 1000 points are yours!\nYour score so far is: " + this.score);
        }
        else {
            return ("Wrong! Right answer is: " + rightAns + "\nYour score so far is: " + this.score);
        }
    }

    /**
     * @return Μία τιμή εκ των 250, 500, 750 ή 1000 που αντιπροσωπεύει το ποσό που θέλει να ποντάρει ο παίκτης
     *         πραγματοποιώντας έλεγχο εγκυρότητας εισόδου.
     */
    public void placeBet(int bet) {
        this.bet = bet;
    }

    /**
     * Η μέθοδος αυτή αντιπροσωπεύει το παιχνίδι "Ποντάρισμα".
     *
     * @param ans Η απάντηση που έδωσε ο παίκτης.
     //* @param bet Το ποσό που στοιχημάτισε ο παίκτης.
     * @param rightAns Η σωστή απάντηση της ερώτησης.
     */
    public String Bet(boolean ans, String rightAns) {
        if (ans) {
            this.score += this.bet;
            return ("Correct!\n" + this.bet + " points are yours!\nYour score so far is: " + this.score);
        }
        else {
            this.score -= this.bet;
            return ("Wrong! Right answer is:" + rightAns + "\nYou lost " + this.bet + " points!\nYour score so far is: " + this.score);
        }
    }

    public String stopTheClock(boolean ans, float time, String rightAns) {
        if (ans) {
            this.score += (time * 0.2);
            return ("Correct!\n" + (Math.round((time * 0.2) * 10) / 10.0) + " points are yours!\nYour score so far is: " + this.score);
        }
        else {
            this.score += 0;
            return ("Wrong! Right answer is:" + rightAns + "\nYour score so far is: " + this.score);
        }
    }

    public String quickAnswer(boolean ans, float score, String rightAns) {
        this.score += score;
        if (ans) {
            if (score == 1000) {
                return ("Correct and fast! " + score + " points are yours!\nYour score so far is: " + this.score);
            }
            else {
                return ("Correct but not too fast! " + score + " points are yours!\nYour score so far is: " + this.score);
            }
        }
        else {
            if (score == 500) {
                return ("Wrong! Right answer is:" + rightAns + "\nYour score so far is: " + this.score);
            }
            else {
                this.score += 500;
                return ("Slow bat still 500 points are yours!\nYour score so far is: " + this.score);
            }
        }
    }

    public String thermometer(boolean ans, int correctAnswers, String rightAns) {
        if (correctAnswers % 5 == 0) {
            this.score += 5000;
            return ("You reached 5 correct answers! 5000 points are yours!\nYour score so far is: " + this.score);
        }
        else {
            if (ans) {
                return ("Correct! You need " + (5 - (correctAnswers % 5)) + " more correct answers to collect 5000 points!\nYour score so far is: " + this.score);
            }
            else {
                return ("Wrong! Right answer is:" + rightAns + " You need " + (5 - (correctAnswers % 5)) + " more correct answers to collect 5000 points!\nYour score so far is: " + this.score);
            }
        }
    }
}
