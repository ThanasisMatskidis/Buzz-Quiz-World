import java.util.ArrayList;
import java.util.Collections;

/**
 * Η κλάση αναπαριστά μία κατηγορία ερωτήσεων η οποία αποτελείται από το όνομα της κατηγορίας και ένα σύνολο από τις
 * ερωτήσεις που ανήκουν στη συγκεκριμένη κατηγορία.
 * @author Thanasis, Aris
 */
public class Category {

    private String categoryName;
    private ArrayList<Question> questions;
    private int index;

    /**
     *
     * Κατασκευαστής
     *
     * @param categoryName Το όνομα της κατηγορίας.
     */
    public Category(String categoryName){
        this.categoryName = categoryName;
        this.questions = new ArrayList<>();
        this.index = 0;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    /**
     * @return Την πρώτη ερώτηση μετά την τελευταία που εμφανίσθηκε/κλήθηκε να απαντήσει ο παίκτης.
     */
    public Question getQuestion() {
        return questions.get(this.index);
    }

    /**
     * @return Έναν ακέραιο αριθμό που αντιπροσωπεύει σε ποια ερώτηση, από το σετ ερωτήσεων της κατηγορίας, μείναμε την
     *         τελευταία φορά που εμφανίστηκε η συγκεκριμένη κατηγορία στο παιχνίδι.
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Η μέθοδος αυτή ενημερώνει την μεταβλητή index αυξάνοντάς την κατά 1 και σε περίπτωση που ξεπεράσει τον αριθμό των
     * ερωτήσεων της κατηγορίας, δηλαδή έχουν παιχτεί όλες οι ερωτήσεις της κατηγορίας, τότε μηδνίζεται και το σύνολο
     * των ερωτήσεων "ανακατεύεται".
     */
    public void setIndex() {
        this.index += 1;
        if (this.index >= this.questions.size()) {
            this.index = 0;
            Collections.shuffle(questions);
        }
    }

    /**
     * Η μέθοδος αυτή προσθέτει μία ερώτηση στην κατηγορία.
     *
     * @param q Η ερώτηση.
     */
    public void addCategory(Question q){
        questions.add(q);
    }
}
