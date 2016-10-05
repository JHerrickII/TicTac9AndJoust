
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Scoreboard {
	// Yuesen He (yh5mq)

	// Fields
	private int score1;
	private int score2;
	private boolean gameOver;
	private ArrayList<Integer> highScores;
	private int highScoresCapacity;

	// Methods
	public Scoreboard() {
		this.score1 = 0;
		this.score2 = 0;
		this.gameOver = false;
		this.highScores = new ArrayList<Integer>();
		this.highScoresCapacity = 3;
	}
	
	public void score1score(){
		this.score1 ++;
	}
	public void score2score(){
		this.score2 ++;
	}

	public Scoreboard(int highScoresCapacity) {
		this.score1 = 0;
		this.score2 = 0;
		this.gameOver = false;
		this.highScores = new ArrayList<Integer>();
		this.highScoresCapacity = highScoresCapacity;
	}

	public ArrayList<Integer> getHighScores() {
		return highScores;
	}

	public void setHighScores(ArrayList<Integer> highScores) {
		this.highScores = highScores;
	}

	public int getHighScoresCapacity() {
		return highScoresCapacity;
	}

	public void setHighScoresCapacity(int highScoresCapacity) {
		this.highScoresCapacity = highScoresCapacity;
	}

	public void setScore1(int score1) {
		this.score1 = score1;
	}

	public void setScore2(int score2) {
		this.score2 = score2;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public void incrementScore1() {
		this.score1 = this.score1 + 1;
	}

	public void incrementScore2() {
		this.score2 = this.score2 + 1;
	}

	public int getScore1() {
		return this.score1;
	}

	public int getScore2() {
		return this.score2;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void endGame() {
		this.gameOver = true;
	}

	public void addHighScore(int newScore) {
		this.highScores.add(newScore);
		Collections.sort(this.highScores, Collections.reverseOrder());
		while (highScores.size() > this.highScoresCapacity) {
			highScores.remove(this.highScores.size() - 1);
		}
	}

	public void loadHighScores(String filename) throws Exception {
		Scanner csvReader = new Scanner(new File(filename));
		while (csvReader.hasNextLine()) {
			String[] info = csvReader.nextLine().split(",");
			for (int i = 0; i < info.length; i++) {
				highScores.add(Integer.parseInt(info[i]));
			}
		}
		Collections.sort(this.highScores, Collections.reverseOrder());
		while (highScores.size() > this.highScoresCapacity) {
			highScores.remove(this.highScores.size() - 1);

		}
	}

	public void saveHighScores(String filename) throws Exception {
		PrintWriter csvWriter = new PrintWriter(new File(filename));
		String statement = "";
		for (int i = 0; i < highScores.size(); i++) {
			if (i == 0) {
				statement = statement + highScores.get(i);
			} else {
				statement = statement + "," + highScores.get(i);
			}
		}
		csvWriter.print(statement);
		csvWriter.close();

	}

	public String toString() {
		String statement = "";
		for (int i = 0; i < highScores.size(); i++) {
			if (i == 0) {
				statement = statement + highScores.get(i);
			} else {
				statement = statement + "," + highScores.get(i);
			}
		}
		if (gameOver == false) {
			return score1 + " - " + score2
					+ ". Game is not over. High scores: " + statement;
		} else {
			return score1 + " - " + score2 + ". Game is over. High scores: "
					+ statement;
		}
	}
}
