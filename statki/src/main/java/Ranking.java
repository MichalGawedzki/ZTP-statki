import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ranking {
    int highScore;
    int score;
    List<RankPos> highScoreList = new ArrayList<RankPos>();

    Ranking() {

        readFile();
        System.out.println(highScoreList);

    }


    String highScoreToText() {
        String scoreText = "Top 5 highscores: \n";
        int rank = 1;
        for (RankPos pos : highScoreList) {
            scoreText += rank + ". " + pos.name + " : " + pos.score + "\n\n";
            rank++;
        }

        return scoreText;
    }

    void addtoList(RankPos rankPos) {
        String[] splited = rankPos.name.split("\\s+");
        if (splited.length > 1) {
            rankPos.name = "";
            for (int i = 0; i < splited.length; i++)
                rankPos.name += splited[i];
        }
        highScoreList.add(rankPos);
        Collections.sort(highScoreList, Collections.reverseOrder());
        if (highScoreList.size() > 5) highScoreList.remove(5);
    }

    void saveToFile() throws IOException {
        System.out.println(score);
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("src/main/resources/ranking.txt"))) {
            for (RankPos pos : highScoreList) {
                writer.write(pos.name + " " + pos.score + "\n");
            }
        }
    }

    void readFile() {
        List<String> list = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Paths.get("src/main/resources/ranking.txt"))) {
            list = lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String str : list) {
            String[] splited = str.split("\\s+");
            highScoreList.add(new RankPos(Integer.parseInt(splited[1]), splited[0]));
        }
    }
}
