package com.bitirme.core;

import com.bitirme.database.DatabaseHelper;
import com.bitirme.model.Tweet;
import com.bitirme.model.TwitterUser;
import com.bitirme.model.UserType;
import twitter4j.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class Fetcher {
	private String userName;
	private UserType type;
	private DatabaseHelper helper;
	public String s;

	public Fetcher() {
		this.helper = new DatabaseHelper();
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public void fetch(String userName, UserType type) throws TwitterException {
		this.userName = userName;
		this.type = type;
		fetch();
	}

	public void fetch() throws TwitterException {
		int userId = fetchUser();
		if (userId == -1) {
			System.out.println("User Id -1 ERROR!");
			return;
		}
		try {
			fetchTweets(userId,userName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int countWords(String s) {

		int wordCount = 0;

		boolean word = false;
		int endOfLine = s.length() - 1;

		for (int i = 0; i < s.length(); i++) {
			// if the char is a letter, word = true.
			if (Character.isLetter(s.charAt(i)) && i != endOfLine) {
				word = true;
				// if char isn't a letter and there have been letters before,
				// counter goes up.
			} else if (!Character.isLetter(s.charAt(i)) && word) {
				wordCount++;
				word = false;
				// last word of String; if it doesn't end with a non letter, it
				// wouldn't count without this.
			} else if (Character.isLetter(s.charAt(i)) && i == endOfLine) {
				wordCount++;
			}
		}
		return wordCount;
	}
	public TwitterUser getUser(String userName,UserType type) throws TwitterException{
		Twitter twitter = TwitterHelper.getTwitter();
		User user = twitter.showUser(userName);
		TwitterUser us = new TwitterUser();
		Fetcher fetch = new Fetcher();
		DatabaseHelper dbhelp = new DatabaseHelper();

		us.setDisplayName(user.getScreenName());
		Calendar cal = Calendar.getInstance();

		us.setFollower(user.getFollowersCount());
		us.setFollowed(user.getFriendsCount());
		us.setFavCount(user.getFavouritesCount());
		us.setLocation(user.getLocation());
		us.setTwitterId(user.getId());
		us.setDefaultImage(user.isDefaultProfileImage());
		us.setLengthUserName(user.getName().length());
		us.setLengthDescriptionName(user.getDescription().length());
		us.setType(type);
		us.setReputationOfUser((float) us.getFollower() / (float) (us.getFollower() + (float) us.getFollowed()));
		if(us.getFollowed() != 0){
			us.setRatioFollowerFollowed((us.getFollower() / us.getFollowed()));
		}
		else{
			us.setRatioFollowerFollowed(us.getFollower());
		}
		us.setAgeOfAccount((cal.getTimeInMillis() - user.getCreatedAt().getTime()) / 3600000); // type of hours
		us.setFollowingRate((double)us.getFollower() / (double)us.getAgeOfAccount());
		us.setCountOfTweets(user.getStatusesCount());
		us.setNumberOfTPerDay((float)us.getCountOfTweets() / (float)((cal.getTimeInMillis() - user.getCreatedAt().getTime()) / 86400000));
		us.setNumberOfTPerWeek(((float)us.getCountOfTweets() / (float)((cal.getTimeInMillis() - user.getCreatedAt().getTime()) / 86400000)) * 7);
		us.setName(userName);
		//TODO fetch user tweets
		try {
			fetchTweets(1,userName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			us.setAvgFavCount(dbhelp.avgTweets("favCount",userName));
			us.setAvgMeanWordLength(dbhelp.avgTweets("meanWordLength",userName));
			us.setAvgNofWhiteSpaces(dbhelp.avgTweets("nofWhiteSpace",userName));
			us.setAvgMaxWordLength(dbhelp.avgTweets("maxWordLength",userName));
			us.setAvgNofDotMark(dbhelp.avgTweets("nofDotMark",userName));
			us.setAvgNofExclamationMark(dbhelp.avgTweets("nofExclamationMark",userName));
			us.setAvgNofQuestionMark(dbhelp.avgTweets("nofQuestionMark",userName));
			us.setAvgNofCapitalization(dbhelp.avgTweets("nofCapitalization",userName));
			us.setAvgNofWords(dbhelp.avgTweets("nofWords",userName));
			us.setAvgNofChars(dbhelp.avgTweets("nofChars",userName));
			us.setAvgDDifference(dbhelp.avgTweets("dDifference",userName));
			us.setAvgHTCount(dbhelp.avgTweets("htCount",userName));
			us.setAvgRTCount(dbhelp.avgTweets("rtCount",userName));
			us.setAvgLinkCount(dbhelp.avgTweets("linkCount",userName));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return us;
	}
	public TwitterUser getUser(String userName) throws TwitterException{
		return getUser(userName,UserType.Human);
	}


	private int fetchUser() throws TwitterException {
		Twitter twitter = TwitterHelper.getTwitter();
		User user = twitter.showUser(userName);
		TwitterUser us = new TwitterUser();
		
		Calendar cal = Calendar.getInstance();
		
		us.setFollower(user.getFollowersCount());
		us.setFollowed(user.getFriendsCount());
		us.setFavCount(user.getFavouritesCount());
		us.setLocation(user.getLocation());
		us.setTwitterId(user.getId());
		us.setDefaultImage(user.isDefaultProfileImage());
		us.setLengthUserName(user.getName().length());
		us.setLengthDescriptionName(user.getDescription().length());
		// us.setAgeOfAcount(ageOfAcount);
		// us.setCountOfTweets(countOfTweets);
		// us.setFollowingRate(followingRate);
		// us.setNumberOfTPerDay(numberOfTPerDay);
		// us.setNumberOfTPerWeek(numberOfTPerWeek);
		us.setReputationOfUser((float) us.getFollower() / (float) (us.getFollower() + (float) us.getFollowed()));
		if(us.getFollowed() != 0){
			us.setRatioFollowerFollowed((us.getFollower() / us.getFollowed()));
		}		
		else{
			us.setRatioFollowerFollowed(us.getFollower());
		}
		us.setAgeOfAccount((cal.getTimeInMillis() - user.getCreatedAt().getTime()) / 3600000); // type of hours
		//System.out.println((cal.getTimeInMillis() - user.getCreatedAt().getTime()) / 86400000);  // type of day
		us.setFollowingRate((double)us.getFollower() / (double)us.getAgeOfAccount());
		us.setCountOfTweets(user.getStatusesCount());
		us.setNumberOfTPerDay((float)us.getCountOfTweets() / (float)((cal.getTimeInMillis() - user.getCreatedAt().getTime()) / 86400000));
		us.setNumberOfTPerWeek(((float)us.getCountOfTweets() / (float)((cal.getTimeInMillis() - user.getCreatedAt().getTime()) / 86400000)) * 7);
		us.setName(userName);
		us.setType(this.type);

		return 0;
	}
	
	
	

	private void fetchTweets(int userId,String userName) throws TwitterException, IOException {
		Twitter twitter = TwitterHelper.getTwitter();
		int twitterPage = 0;
		String name = null;
		List<Status> status = null;
		int tweetCounter = 0;
		int tweetPageCount = 100 / 100;
		int total = 0;
		int i = 0;
		while ((status == null || status.size() != 0) && tweetCounter < tweetPageCount) {
			Paging pagin = new Paging(++twitterPage, 100); // bir seferinde 100
			Tweet tweet = new Tweet(); // tweet aliyo 
			
			//System.out.println(userName);
			status = twitter.getUserTimeline(userName, pagin);

			for (i = 0; i < status.size(); i++) {
				total++;
				int upperCase = 0;
				for (int k = 0; k < status.get(i).getText().length(); k++) {
					if (Character.isUpperCase(status.get(i).getText().charAt(k)))
						upperCase++;
				}
				
				int questionCount = 0;
				int exclamationCount = 0;
				int dotCount = 0;
				int whiteSpaceCount = 0;
				for (int l = 0; l < status.get(i).getText().length(); l++) {
					if (status.get(i).getText().charAt(l) == '?')
						questionCount++;
					else if (status.get(i).getText().charAt(l) == '!')
						exclamationCount++;
					else if (status.get(i).getText().charAt(l) == '.')
						dotCount++;
					else if(status.get(i).getText().charAt(l) == ' ')
						whiteSpaceCount++;

				}
				
				String[] arr = status.get(i).getText().split(" ");
				int meanWordLength = 0;
				int maxWordLength = 0;
				int jCount = 0;
				int zCount = 0;
				for (int j = 0 ; j < arr.length ; j++)
				{
					
					for(int z = 0 ; z < arr[j].length() ; z ++)
					{
						zCount++;
					}
					if(maxWordLength < zCount)
					{
						maxWordLength = zCount ;
					}
					meanWordLength += zCount;
					jCount++;
					zCount = 0;
				}
				
				tweet.setNumberOfWhiteSpace(whiteSpaceCount);
				tweet.setMaximumWordLength(maxWordLength);
 				tweet.setMeanWordLength((float)meanWordLength / (float)jCount); 
				tweet.setNumberOfQuestionMark(questionCount);
				tweet.setNumberOfExclamationMark(exclamationCount);
				tweet.setNumberOfDotMark(dotCount);
				tweet.setUserId(userId);
				tweet.setFavoriteCount(status.get(i).getFavoriteCount());
				tweet.setRetweetCount(status.get(i).getRetweetCount());
				//tweet.setText(StringUtils.replaceTurkishCharacters(status.get(i).getText()));
				tweet.setNumberOfWords(countWords(status.get(i).getText()));
				tweet.setNumberOfCharacters(status.get(i).getText().length());
				//tweet.setDate(status.get(i).getCreatedAt());
				tweet.setDateDiff(status.get(i).getCreatedAt().getTime() / 1000);
				tweet.setNumberOfCapitalization(upperCase);
				//System.out.println(status.get(i).getText());
				if (status.get(i).getURLEntities() != null) {
					tweet.setLinkCount(status.get(i).getURLEntities().length);
				}
				if (status.get(i).getHashtagEntities() != null) {
					tweet.setHashtagCount(status.get(i).getHashtagEntities().length);
				}
				
				//StringUtils.replaceTurkishCharacters(status.get(i).getText()); Türkçe karakterleri ingilizceye çevirme işlemi
				// System.out.println(status.get(i).getCreatedAt());
				// System.out.println(status.get(i).getText());
				// System.out.println(tweet.getFavoriteCount());
				// System.out.println(tweet.getRetweetCount());
				// System.out.println(status.get(i).getMediaEntities());
				// System.out.println(status.get(i).getHashtagEntities());
				// System.out.println();
				
			/*	try {
					helper.save(tweet);
				} catch (SQLException e) {
					e.printStackTrace();
				}*/
				try{
					helper.saveTweets(tweet,userName);
				} catch (SQLException ex){
					ex.printStackTrace();
				}
			//TODO save tweets temporary db calculate avg from that table
			}
			
			
			tweetCounter++;
		}
		// bu kodu eklediginde bu kod seri olarak bir kullanicinin tweetlerini
		// cekiyo.

		//System.out.println(total);
		

	}
}
