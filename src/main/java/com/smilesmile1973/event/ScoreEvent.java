package com.smilesmile1973.event;

public class ScoreEvent {
	private int maximum;

	private int score;

	public ScoreEvent(int score, int maximum) {
		super();
		this.score = score;
		this.maximum = maximum;
	}

	public int getMaximum() {
		return maximum;
	}

	public int getScore() {
		return score;
	}

}
