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
	private UserType type;
	private long twitterId;
	private int follower;
	private int followed;
	private int favCount;
	private String location;
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
