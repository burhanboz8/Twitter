package com.bitirme.model;

public class Tweet {
	private String text;
	private int	   retweetCount;
	private int    favoriteCount;
	private int    linkCount;
	private int    hashtagCount;
	private int    userId;
	private long   dateDiff;
	private double latitude;
	private double longitude;
	private int    numberOfCharacters;
	private int	   numberOfWords;
	private int    numberOfCapitalization;
	private int    numberOfWhiteSpace;
	private int    numberOfQuestionMark;
	private int    numberOfExclamationMark;
	private int    numberOfDotMark;
	private int    maximumWordLength;
	private float  meanWordLength;
	private java.util.Date   date;
	public int getMaximumWordLength() {
		return maximumWordLength;
	}
	public void setMaximumWordLength(int maximumWordLength) {
		this.maximumWordLength = maximumWordLength;
	}
	public float getMeanWordLength() {
		return meanWordLength;
	}
	public void setMeanWordLength(float meanWordLength) {
		this.meanWordLength = meanWordLength;
	}
	public int getNumberOfExclamationMark() {
		return numberOfExclamationMark;
	}
	public void setNumberOfExclamationMark(int numberOfExclamationMark) {
		this.numberOfExclamationMark = numberOfExclamationMark;
	}
	public int getNumberOfDotMark() {
		return numberOfDotMark;
	}
	public void setNumberOfDotMark(int numberOfDotMark) {
		this.numberOfDotMark = numberOfDotMark;
	}

	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getRetweetCount() {
		return retweetCount;
	}
	public void setRetweetCount(int retweetCount) {
		this.retweetCount = retweetCount;
	}
	public int getFavoriteCount() {
		return favoriteCount;
	}
	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}
	public int getLinkCount() {
		return linkCount;
	}
	public void setLinkCount(int linkCount) {
		this.linkCount = linkCount;
	}
	public int getHashtagCount() {
		return hashtagCount;
	}
	public void setHashtagCount(int hashtagCount) {
		this.hashtagCount = hashtagCount;
	}
	public long getDateDiff() {
		return dateDiff;
	}
	public void setDateDiff(long dateDiff) {
		this.dateDiff = dateDiff;
	}
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	public int getNumberOfCharacters() {
		return numberOfCharacters;
	}
	public void setNumberOfCharacters(int numberOfCharacters) {
		this.numberOfCharacters = numberOfCharacters;
	}
	public int getNumberOfWords() {
		return numberOfWords;
	}
	public void setNumberOfWords(int numberOfWords) {
		this.numberOfWords = numberOfWords;
	}
	public int getNumberOfCapitalization() {
		return numberOfCapitalization;
	}
	public void setNumberOfCapitalization(int numberOfCapitalization) {
		this.numberOfCapitalization = numberOfCapitalization;
	}
	public int getNumberOfQuestionMark() {
		return numberOfQuestionMark;
	}
	public void setNumberOfQuestionMark(int numberOfQuestionMark) {
		this.numberOfQuestionMark = numberOfQuestionMark;
	}
	public int getNumberOfWhiteSpace() {
		return numberOfWhiteSpace;
	}
	public void setNumberOfWhiteSpace(int numberOfWhiteSpace) {
		this.numberOfWhiteSpace = numberOfWhiteSpace;
	}
	

	
}
