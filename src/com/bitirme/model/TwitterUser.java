package com.bitirme.model;

public class TwitterUser {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String name;
	private String displayName;
	private UserType type;
	private long twitterId;
	private int follower;
	private int followed;
	private int favCount;
	private String location;
	private float avgLinkCount;
    private float avgRTCount;
    private float avgHTCount;
    private float avgFavCount;
    private float avgDDifference;
    private float avgNofChars;
    private float avgNofWords;
    private float avgNofCapitalization;
    private float avgNofQuestionMark;
    private float avgNofExclamationMark;
    private float avgNofDotMark;
    private float avgNofWhiteSpaces;
    private float avgMaxWordLength;
    private float avgMeanWordLength;
    private boolean defaultImage;
    private int lengthUserName;
    private int lengthDescriptionName;
    private int countOfTweets;
    private double ratioFollowerFollowed;
    private float reputationOfUser;
    private double followingRate;
    private float numberOfTPerDay;
    private float numberOfTPerWeek;
    private long  ageOfAccount;

	public float getAvgLinkCount() {
		return avgLinkCount;
	}

	public void setAvgLinkCount(float avgLinkCount) {
		this.avgLinkCount = avgLinkCount;
	}

	public float getAvgRTCount() {
		return avgRTCount;
	}

	public void setAvgRTCount(float avgRTCount) {
		this.avgRTCount = avgRTCount;
	}

	public float getAvgHTCount() {
		return avgHTCount;
	}

	public void setAvgHTCount(float avgHTCount) {
		this.avgHTCount = avgHTCount;
	}

	public float getAvgFavCount() {
		return avgFavCount;
	}

	public void setAvgFavCount(float avgFavCount) {
		this.avgFavCount = avgFavCount;
	}

	public float getAvgDDifference() {
		return avgDDifference;
	}

	public void setAvgDDifference(float avgDDifference) {
		this.avgDDifference = avgDDifference;
	}

	public float getAvgNofChars() {
		return avgNofChars;
	}

	public void setAvgNofChars(float avgNofChars) {
		this.avgNofChars = avgNofChars;
	}

	public float getAvgNofWords() {
		return avgNofWords;
	}

	public void setAvgNofWords(float avgNofWords) {
		this.avgNofWords = avgNofWords;
	}

	public float getAvgNofCapitalization() {
		return avgNofCapitalization;
	}

	public void setAvgNofCapitalization(float avgNofCapitalization) {
		this.avgNofCapitalization = avgNofCapitalization;
	}

	public float getAvgNofQuestionMark() {
		return avgNofQuestionMark;
	}

	public void setAvgNofQuestionMark(float avgNofQuestionMark) {
		this.avgNofQuestionMark = avgNofQuestionMark;
	}

	public float getAvgNofExclamationMark() {
		return avgNofExclamationMark;
	}

	public void setAvgNofExclamationMark(float avgNofExclamationMark) {
		this.avgNofExclamationMark = avgNofExclamationMark;
	}

	public float getAvgNofDotMark() {
		return avgNofDotMark;
	}

	public void setAvgNofDotMark(float avgNofDotMark) {
		this.avgNofDotMark = avgNofDotMark;
	}

	public float getAvgNofWhiteSpaces() {
		return avgNofWhiteSpaces;
	}

	public void setAvgNofWhiteSpaces(float avgNofWhiteSpaces) {
		this.avgNofWhiteSpaces = avgNofWhiteSpaces;
	}

	public float getAvgMaxWordLength() {
		return avgMaxWordLength;
	}

	public void setAvgMaxWordLength(float avgMaxWordLength) {
		this.avgMaxWordLength = avgMaxWordLength;
	}

	public float getAvgMeanWordLength() {
		return avgMeanWordLength;
	}

	public void setAvgMeanWordLength(float avgMeanWordLength) {
		this.avgMeanWordLength = avgMeanWordLength;
	}



	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}


	public int getLengthUserName() {
		return lengthUserName;
	}
	public void setLengthUserName(int lengthUserName) {
		this.lengthUserName = lengthUserName;
	}
	public int getLengthDescriptionName() {
		return lengthDescriptionName;
	}
	public void setLengthDescriptionName(int lengthDescriptionName) {
		this.lengthDescriptionName = lengthDescriptionName;
	}
	public int getCountOfTweets() {
		return countOfTweets;
	}
	public void setCountOfTweets(int countOfTweets) {
		this.countOfTweets = countOfTweets;
	}
	public double getRatioFollowerFollowed() {
		return ratioFollowerFollowed;
	}
	public void setRatioFollowerFollowed(double ratioFollowerFollowed) {
		this.ratioFollowerFollowed = ratioFollowerFollowed;
	}
	public float getReputationOfUser() {
		return reputationOfUser;
	}
	public void setReputationOfUser(float reputationOfUser) {
		this.reputationOfUser = reputationOfUser;
	}
	public double getFollowingRate() {
		return followingRate;
	}
	public void setFollowingRate(double followingRate) {
		this.followingRate = followingRate;
	}
	public float getNumberOfTPerDay() {
		return numberOfTPerDay;
	}
	public void setNumberOfTPerDay(float numberOfTPerDay) {
		this.numberOfTPerDay = numberOfTPerDay;
	}
	public float getNumberOfTPerWeek() {
		return numberOfTPerWeek;
	}
	public void setNumberOfTPerWeek(float numberOfTPerWeek) {
		this.numberOfTPerWeek = numberOfTPerWeek;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
	public long getTwitterId() {
		return twitterId;
	}
	public void setTwitterId(long twitterId) {
		this.twitterId = twitterId;
	}
	public int getFollower() {
		return follower;
	}
	public void setFollower(int follower) {
		this.follower = follower;
	}
	public int getFollowed() {
		return followed;
	}
	public void setFollowed(int followed) {
		this.followed = followed;
	}
	public int getFavCount() {
		return favCount;
	}
	public void setFavCount(int favCount) {
		this.favCount = favCount;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public boolean isDefaultImage() {
		return defaultImage;
	}
	public void setDefaultImage(boolean defaultImage) {
		this.defaultImage = defaultImage;
	}
	public long getAgeOfAccount() {
		return ageOfAccount;
	}
	public void setAgeOfAccount(long ageOfAccount) {
		this.ageOfAccount = ageOfAccount;
	}
	
}
