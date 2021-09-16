import java.util.ArrayList;
import java.util.Collections;

/**
 * Η κλάση αυτή αναπαριστά μία ερώτηση η οποία αποτελείται από τον τίτλο της ερώτησης και ένα σύνολο από τις απαντήσεις της.
 * @author Thanasis, Aris
 */
public class Question {

    private String title;
    private ArrayList<Answer> answers;
    private boolean hasImage;
    private String imageFilename;

    /**
     *
     * Κατασκευαστής
     *
     * @param title Η ερώτηση.
     */
    public Question(String title) {
        this.title = title;
        this.answers = new ArrayList<>();
        this.hasImage = false;
        this.imageFilename = null;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean getHasImage() {
        return this.hasImage;
    }

    public String getImageFilename() {
        return this.imageFilename;
    }

    public void setHasImage(boolean image) {
        this.hasImage = true;
    }

    public void setImageFilename(String filename) {
        this.imageFilename = filename;
    }

    /**
     * @return  Η μέθοδος αυτή επιστρέφει τις ερωτήσεις.
     *
     */
    public ArrayList<Answer> getAnswers() {
        Collections.shuffle(this.answers);
        return this.answers;
    }

    /**
     * Η μέθοδος αυτή προσθέτει μία απάντηση στην ερώτηση.
     *
     * @param answer Η απάντηση.
     */
    public void addAns(Answer answer) {
        if (answers.size() < 4) {
            answers.add(answer);
        }
    }

    /**
     * @return Την σωστή απάντηση στην ερώτηση.
     */
    public String getRightAns() {
        Answer rightAns = answers.get(0);
        for(int i=1; i<answers.size(); i++){
            if(answers.get(i).getCheck()){
                rightAns = answers.get(i);
            }
        }

        return rightAns.getAns();
    }

    /**
     * @param ansNum Η απάντηση του παίκτη.
     * @return Εάν η απάντηση που δόθηκε από τον παίκτη είναι η σωστή ή όχι.
     */
    public boolean checkQuestion(int ansNum) {
        return answers.get(ansNum - 1).getCheck();
    }
}
