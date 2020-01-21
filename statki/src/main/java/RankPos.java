public class RankPos implements Comparable<RankPos>{

    public RankPos(Integer score, String name) {
        this.score = score;
        this.name = name;
    }

    public Integer score = 0;
    String name = null;

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(RankPos o) {
        return this.score.compareTo(o.score);
    }
}


