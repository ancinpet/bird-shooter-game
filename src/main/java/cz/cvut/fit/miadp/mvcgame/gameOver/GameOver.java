package cz.cvut.fit.miadp.mvcgame.gameOver;

public class GameOver {
    public String gameOver(int score) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 50; ++i) {
            for (int j = 0; j < 30; ++j) {
                res.append("  Game Over! Score: " + score);
            }
            res.append("\n");
        }

        return res.toString();
    }
}