import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Thanasis, Aris
 */
public class DataFile {

    private String filename;

    public DataFile(String filename) {
        this.filename = filename;
    }

    public ArrayList<Category> setData() {
        ArrayList<Category> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(this.filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                boolean found = false;
                int indexFound = data.size();
                for (int i = 0; i < data.size(); i++) {
                    if (line.equals(data.get(i).getCategoryName())) {
                        found = true;
                        indexFound = i;
                    }
                }
                if (!found) {
                    data.add(new Category(line));
                }

                line = reader.readLine();
                Question q = new Question(line);

                line = reader.readLine();
                if (line.equals("Yes")) {
                    q.setHasImage(true);
                    line = reader.readLine();
                    q.setImageFilename(line);
                }
                line = reader.readLine();
                int numberCorrectAnswer = Integer.parseInt(line) - 1;
                for (int i = 0; i < 4; i++) {
                    line = reader.readLine();
                    Answer a;
                    if (i != numberCorrectAnswer) {
                        a = new Answer(line, false);
                    } else {
                        a = new Answer(line, true);
                    }
                    q.addAns(a);
                }
                data.get(indexFound).addCategory(q);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
