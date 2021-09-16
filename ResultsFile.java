import java.io.*;

/**
 * @author Thanasis, Aris
 */
public class ResultsFile {

    private String filename;

    public ResultsFile(String filename) {
        this.filename = filename;
    }

    public void setPlayerResults(Player player, int numberOfPlayers) {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filename))) {
            String line;
            File temp = new File("temp.txt");
            FileWriter tempWriter = new FileWriter("temp.txt");
            boolean entered = false;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                entered = true;
                tempWriter.write(line);
                tempWriter.write("\n");
                if (player.getName().equals(line)) {
                    found = true;
                    line = reader.readLine();
                    float currentHighScore = Float.parseFloat(line);
                    if (player.getScore() > currentHighScore) {
                        player.setHighScore(player.getScore());
                    }
                    tempWriter.write(Float.toString(player.getHighScore()));
                    tempWriter.write("\n");
                    if (numberOfPlayers == 2) {
                        line = reader.readLine();
                        int wins = Integer.parseInt(line);
                        player.setWins(wins);
                    } else {
                        line = reader.readLine();
                    }
                    tempWriter.write(Integer.toString(player.getWins()));
                    tempWriter.write("\n");
                } else {
                    line = reader.readLine();
                    tempWriter.write(line);
                    tempWriter.write("\n");
                    line = reader.readLine();
                    tempWriter.write(line);
                    tempWriter.write("\n");
                }
            }
            tempWriter.close();
            FileWriter resultsWriter = new FileWriter(this.filename);
            resultsWriter.flush();
            if (entered) {
                try (BufferedReader tempReader = new BufferedReader(new FileReader("temp.txt"))) {
                    String tempLine;
                    while ((tempLine = tempReader.readLine()) != null) {
                        resultsWriter.write(tempLine);
                        resultsWriter.write("\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!found) {
                    resultsWriter.write(player.getName());
                    resultsWriter.write("\n");
                    player.setHighScore(player.getScore());
                    resultsWriter.write(Float.toString(player.getHighScore()));
                    resultsWriter.write("\n");
                    resultsWriter.write(Integer.toString(player.getWins()));
                    resultsWriter.write("\n");
                }
            } else {
                resultsWriter.write(player.getName());
                resultsWriter.write("\n");
                player.setHighScore(player.getScore());
                resultsWriter.write(Float.toString(player.getHighScore()));
                resultsWriter.write("\n");
                resultsWriter.write(Integer.toString(player.getWins()));
                resultsWriter.write("\n");
            }
            resultsWriter.close();
            temp.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
