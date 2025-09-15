package javalike;

public class Player {

	private Game game;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	private int score;

	public Player(Game game) {

		this.game = game;
	}

	public void getEgg() {

		if (game.basketObject.collision()) {
			game.eggObject.setY(0);
			game.eggObject.Xrandom();
			score += 10;
			System.out.println(score);

		}

	}
}
