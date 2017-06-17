package com.bitirme.database;

import com.bitirme.core.Fetcher;
import com.bitirme.model.Context;
import com.bitirme.model.Tweet;
import com.bitirme.model.TwitterUser;
import com.bitirme.model.UserType;
import com.bitirme.util.StringUtils;
import twitter4j.TwitterException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
	private Connection conn = null;

	public DatabaseHelper() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Twitter", "root", "1burhan234");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public int getFeedbackId(String link) throws SQLException,IdNotFoundException{
		PreparedStatement st = conn.prepareStatement("Select id from VotedNames where link = ?");
		st.setString(1,link);
		ResultSet set = st.executeQuery();
		if(set.first()){
			return set.getInt(1);
		}else{
			throw new IdNotFoundException();
		}
	}
	public int getFeedbackValue(int id) throws SQLException,IdNotFoundException{
		PreparedStatement st = conn.prepareStatement("Select count from VotedNames where id = ?");
		st.setInt(1,id);
		ResultSet set = st.executeQuery();
		if(set.first()){
			return set.getInt(1);
		}else{
			throw new IdNotFoundException();
		}
	}
	public  void saveFeedback(String link,String username,int val) throws SQLException{
		try{
			int id = getFeedbackId(link);
			int value = getFeedbackValue(id);
			value += val;
			PreparedStatement st = conn.prepareStatement("update VotedNames set count = ? where id = ?");
			st.setInt(1,value);
			st.setInt(2,id);
			st.executeUpdate();
			//Kullanici veritabaninda var ise update yapicaz. Yoksa insert yapicaz
		}catch (IdNotFoundException ex){
			PreparedStatement st = conn.prepareStatement("insert into VotedNames(name,link,count) values (?,?,?)");
			st.setString(1,username);
			st.setString(2,link);
			st.setInt(3,val);
			st.executeUpdate();

		}

	}
	public boolean save(Tweet tweet) throws SQLException {
		PreparedStatement st = conn.prepareStatement(
				"insert into Tweet(context,linkCount,rtCount,htCount,favCount,date,dateDiff,userId,numberOfCharacters,"
				+ "numberOfWords,numberOfCapitalization,numberOfQuestionMark,numberOfExclamationMark,numberOfDotMark"
				+ ",maximumWordLength,meanWordLength,numberOfWhiteSpace)"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		st.setString(1, tweet.getText());
		st.setInt(2, tweet.getLinkCount());
		st.setInt(3, tweet.getRetweetCount());
		st.setInt(4, tweet.getHashtagCount());
		st.setInt(5, tweet.getFavoriteCount());
		st.setDate(6, new java.sql.Date(tweet.getDate().getTime()));
		st.setLong(7, tweet.getDateDiff());
		st.setInt(8, tweet.getUserId());
		st.setInt(9, tweet.getNumberOfCharacters());
		st.setInt(10, tweet.getNumberOfWords());
		st.setInt(11, tweet.getNumberOfCapitalization());
		st.setInt(12, tweet.getNumberOfQuestionMark());
		st.setInt(13, tweet.getNumberOfExclamationMark());
		st.setInt(14, tweet.getNumberOfDotMark());
		st.setInt(15, tweet.getMaximumWordLength());
		st.setFloat(16, tweet.getMeanWordLength());
		st.setInt(17, tweet.getNumberOfWhiteSpace());
		int affectedRows = st.executeUpdate();
		if (affectedRows == 1)
			return true;
		return false;
	}
	public void saveTweets(Tweet tweet, String userName) throws SQLException {
		PreparedStatement st = conn.prepareStatement(
				"insert into TemporaryTweets(name,linkCount,rtCount,htCount,favCount,dDifference,nofChars,"
						+ "nofWords,nofCapitalization,nofQuestionMark,nofExclamationMark,nofDotMark"
						+ ",maxWordLength,nofWhiteSpace,meanWordLength)"
						+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		st.setString(1, userName);
		st.setFloat(2, tweet.getLinkCount());
		st.setFloat(3, tweet.getRetweetCount());
		st.setFloat(4, tweet.getHashtagCount());
		st.setFloat(5, tweet.getFavoriteCount());
		//st.setDate(6, new java.sql.Date(tweet.getDate().getTime()));
		st.setFloat(6, tweet.getDateDiff());
		//st.setInt(8, tweet.getUserId());
		st.setFloat(7, tweet.getNumberOfCharacters());
		st.setFloat(8, tweet.getNumberOfWords());
		st.setFloat(9, tweet.getNumberOfCapitalization());
		st.setFloat(10, tweet.getNumberOfQuestionMark());
		st.setFloat(11, tweet.getNumberOfExclamationMark());
		st.setFloat(12, tweet.getNumberOfDotMark());
		st.setFloat(13, tweet.getMaximumWordLength());
		st.setFloat(15, tweet.getMeanWordLength());
		st.setFloat(14, tweet.getNumberOfWhiteSpace());
		st.executeUpdate();
	}

	public int save(TwitterUser user) throws SQLException {
		PreparedStatement st = conn.prepareStatement("insert into User(name,type,follower,followed,favCount,"
				+ "location,isDefaultImage,twitterId,lengthUserName,lengthDescriptionName,ratioFollowerFollowed,"
				+ "reputationOfUser,ageOfAccount,followingRate,countOfTweets,numberOfTPerDay,numberOfTPerWeek) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
		st.setString(1, user.getName());
		st.setBoolean(2, user.getType().getBoolean());
		st.setInt(3, user.getFollower());
		st.setInt(4, user.getFollowed());
		st.setInt(5,user.getFavCount());
		st.setString(6, user.getLocation());
		st.setBoolean(7, user.isDefaultImage());
		st.setLong(8, user.getTwitterId());
		st.setInt(9, user.getLengthUserName());
		st.setInt(10, user.getLengthDescriptionName());
		st.setDouble(11, user.getRatioFollowerFollowed());
		st.setDouble(12, user.getReputationOfUser());
		st.setLong(13, user.getAgeOfAccount());
		st.setDouble(14, user.getFollowingRate());
		st.setInt(15, user.getCountOfTweets());
		st.setFloat(16, user.getNumberOfTPerDay());
		st.setFloat(17, user.getNumberOfTPerWeek());
		st.executeUpdate();
		ResultSet rs = st.getGeneratedKeys();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return -1;
	}
	

	public List<TwitterUser> getUsers() throws SQLException{
		Statement st= conn.createStatement();
		List<TwitterUser> list = new ArrayList<>();
		ResultSet set = st.executeQuery("Select * from User");
		while(set.next()){
			//int id = set.getInt(0);
			int follower = set.getInt(4);
			int followed = set.getInt(5);
			int favCount = set.getInt(6);
			boolean isDefaultImage = set.getBoolean(8);
			int lengthOfUserName = set.getInt(10);
			int lengthOfDescriptionName = set.getInt(11);
			double ratioFollowerFollowed = set.getDouble(12);
			float reputationOfUser =set.getFloat(13);
			long ageOfAccount =set.getLong(14);
			double followingRate =set.getDouble(15);
			int countOfTweets =set.getInt(16);
			float numberOfTweetsPerDay = set.getFloat(17);
			boolean type2 = set.getBoolean(3);
			UserType type1 ;
			if(type2 == true){
				 type1 = UserType.Human;
			}else{
				 type1 = UserType.Bot;
			}
			float avgLinkCount = set.getFloat(18);
			float avgRTCount = set.getFloat(19);
			float avgHTCount = set.getFloat(20);
			float avgFavCount = set.getFloat(21);
			float avgDDifference = set.getFloat(22);
			float avgNofChars = set.getFloat(23);
			float avgNofWords = set.getFloat(24);
			float avgNofCapitalization = set.getFloat(25);
			float avgNofQuestionMark = set.getFloat(26);
			float avgNofExclamationMark = set.getFloat(27);
			float avgNofDotMark = set.getFloat(28);
			float avgMaxWordLength = set.getFloat(29);
			float avgNofWhiteSpaces = set.getFloat(30);
			float avgMeanWordLength = set.getFloat(31);
				
			

			TwitterUser usr = new TwitterUser();
			usr.setFollower(follower);
			usr.setFollowed(followed);
			usr.setFavCount(favCount);
			usr.setDefaultImage(isDefaultImage);
			usr.setLengthUserName(lengthOfUserName);
			usr.setLengthDescriptionName(lengthOfDescriptionName);
			usr.setRatioFollowerFollowed(ratioFollowerFollowed);
			usr.setReputationOfUser(reputationOfUser);
			usr.setAgeOfAccount(ageOfAccount);
			usr.setFollowingRate(followingRate);
			usr.setCountOfTweets(countOfTweets);
			usr.setNumberOfTPerDay(numberOfTweetsPerDay);
			usr.setType(type1);
			usr.setAvgLinkCount(avgLinkCount);
			usr.setAvgRTCount(avgRTCount);
			usr.setAvgHTCount(avgHTCount);
			usr.setAvgFavCount(avgFavCount);
			usr.setAvgDDifference(avgDDifference);
			usr.setAvgNofChars(avgNofChars);
			usr.setAvgNofWords(avgNofWords);
			usr.setAvgNofCapitalization(avgNofCapitalization);
			usr.setAvgNofQuestionMark(avgNofQuestionMark);
			usr.setAvgNofExclamationMark(avgNofExclamationMark);
			usr.setAvgNofDotMark(avgNofDotMark);
			usr.setAvgMaxWordLength(avgMaxWordLength);
			usr.setAvgNofWhiteSpaces(avgNofWhiteSpaces);
			usr.setAvgMeanWordLength(avgMeanWordLength);


		
			
			list.add(usr);
		}
		return list;
	}
	public void selectContext() throws SQLException{
		Context text = new Context();
		PreparedStatement st = conn.prepareStatement(("select Tweet.context from Tweet "
				+ "inner join User on User.id = Tweet.userId where User.type = 1"));
		ResultSet rs = st.executeQuery();
		String deneme ;
		int count  = 0;
		 PrintWriter writer;
		try {
			writer = new PrintWriter("type1.txt", "UTF-8");
			while(rs.next()){
				deneme = rs.getString("Tweet.context");			
				deneme = StringUtils.removeUrl(deneme);		
				deneme = StringUtils.removeRT(deneme);
				deneme = StringUtils.removeHashtag(deneme);
				//deneme = StringUtils.removeAllWhiteSpacesExceptOne(deneme);
				deneme = StringUtils.removeAllWhiteSpacesExceptOne(deneme);
				//deneme = StringUtils.lowerCase(deneme);
				   
			   writer.println(deneme);
				    
				//System.out.println(deneme);
				count++;
				
			}
			System.out.println(count);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		
	}
//TODO get averages tweets from temporary table
	public float avgTweets (String value, String userName ) throws SQLException{
		float avgValue = 0;
		PreparedStatement st = conn.prepareStatement("select name,avg("+value+") from TemporaryTweets where name = ?");
		st.setString(1,userName);
		ResultSet rs = st.executeQuery();
		rs.first();
			avgValue = rs.getFloat(2);
			System.out.println(avgValue);

		return avgValue;
	}
	public void updateUsers(int threshold) throws SQLException, TwitterException {
        Fetcher fetcher = new Fetcher();
        DatabaseHelper dbhelper = new DatabaseHelper();
        TwitterUser user = new TwitterUser();
		PreparedStatement st = conn.prepareStatement(("select name,count from VotedNames"));
		ResultSet rs = st.executeQuery();
		while (rs.next()){
            PreparedStatement st1 = conn.prepareStatement(("delete from User  where name = ?"));
            st1.setString(1,rs.getString("name"));
		    if (rs.getInt("count") >= threshold){
		         user = fetcher.getUser(rs.getString("name"));
                if (userExits(user.getName())){
                    st1.executeUpdate();
                    dbhelper.save(user);
                }else{
                    dbhelper.save(user);
                }
            }else if (rs.getInt("count") <= (-threshold)){
                user = fetcher.getUser(rs.getString("name"),UserType.Bot);
                if (userExits(user.getName())){
                    st1.executeUpdate();
                    dbhelper.save(user);
                }else{
                    dbhelper.save(user);
                }
            }
        }
	}

	public boolean userExits(String name) throws SQLException{
	    PreparedStatement st = conn.prepareStatement(("select name from User where name =?"));
	    st.setString(1,name);
	    ResultSet rs = st.executeQuery();
	    if (rs.first())
            return true;
        else
            return false;
    }

	public  int searchUser(String username) throws SQLException {
	    PreparedStatement st = conn.prepareStatement(("select name,type from User "));
	    ResultSet rs = st.executeQuery();
	    while (rs.next()){
	        if (username.equalsIgnoreCase(rs.getString("name"))){
                return rs.getInt("type");
            }
        }
        return -1;
    }

	public void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public class IdNotFoundException extends Exception{

	}
}
