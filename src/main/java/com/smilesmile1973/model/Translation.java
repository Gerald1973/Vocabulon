package com.smilesmile1973.model;

/**
 * Cette classe contient le mot à traduire. La source contient le language
 * source et le target la réponse exacte.
 * 
 * @author Papa
 */
public class Translation {
	private String source;
	private String target;
	private String answer;
	private String sourceLanguge;
	private String targetLanguage;
	
	public String getSourceLanguge() {
		return sourceLanguge;
	}

	public void setSourceLanguge(String sourceLanguge) {
		this.sourceLanguge = sourceLanguge;
	}

	public String getTargetLanguage() {
		return targetLanguage;
	}

	public void setTargetLanguage(String targetLanguage) {
		this.targetLanguage = targetLanguage;
	}

	private boolean answered = false;

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public void swap() {
		String tmp = getSource();
		setSource(getTarget());
		setTarget(tmp);
		tmp = getSourceLanguge();
		setSourceLanguge(getTargetLanguage());
		setTargetLanguage(tmp);
	}

	public boolean isGoodAnswer() {
		boolean result = false;
		if (getAnswer() != null && !getAnswer().isEmpty()) {
			if (getTarget().trim().equals(getAnswer().trim())) {
				result = true;
			}
		}
		return result;
	}

	public boolean isAnswered() {
		return answered;
	}

	public void setAnswered(boolean answered) {
		this.answered = answered;
	}
}
