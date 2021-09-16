/**
 * Κλάση Main
 * @author Thanasis, Aris
 */
public class Main {

    /**
     * "Τρέξιμο" του παιχνιδιού
     */
    public static void main(String[] args) {
        DataFile dataFile = new DataFile("data.txt");
        PlayerUI ui = new PlayerUI(dataFile.setData());
        ui.start();
    }
}
