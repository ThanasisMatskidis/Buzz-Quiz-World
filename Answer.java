/**
 * Η κλάση αναπαριστά μία απάντηση η οποία αποτελείται από το κείμενο της απάντησης και μία λογική (boolean) που
 * δηλώνει εάν η συγκεκριμένη είναι η απάντηση στην ερώτηση.
 * @author Thanasis, Aris
 */
public class Answer {

    private String ans;
    private boolean check;

    /**
     *
     * Κατασκευαστής
     *
     * @param ans Το κείμενο της απάντησης.
     * @param check Η λογική (boolean) τιμή που δηλώνει εάν είναι η σωστή απάντηση.
     */
    public Answer(String ans,boolean check){
        this.ans = ans;
        this.check = check;
    }

    public String getAns(){
        return this.ans;
    }

    public boolean getCheck(){
        return check;
    }
}
