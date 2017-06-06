package com.bitirme.core;




import java.util.ArrayList;
import java.util.Date;

public class TwitterApplication {
	private int id;
	private boolean available = true;
	public boolean isAvailable(){
		return available;
	}
	private Date lastUsedTime;
	public Date getLastUsedTime(){
		return lastUsedTime;
	}
	public void setLastUsedTime(Date lastUsedTime){
		this.lastUsedTime = lastUsedTime;
	}
	public int getId() {
		return id;
	}
	public String getConsumerKey() {
		return ConsumerKey;
	}
	public String getConsumerSecret() {
		return ConsumerSecret;
	}
	public String getAccessToken() {
		return AccessToken;
	}
	public String getAccessTokenSecret() {
		return AccessTokenSecret;
	}
	
	private String ConsumerKey,ConsumerSecret,AccessToken,AccessTokenSecret;
	public TwitterApplication(int id, String consumerKey,
			String consumerSecret, String accessToken, String accessTokenSecret) {
		super();
		this.id = id;
		ConsumerKey = consumerKey;
		ConsumerSecret = consumerSecret;
		AccessToken = accessToken;
		AccessTokenSecret = accessTokenSecret;
	}
	
	public static ArrayList<TwitterApplication> getTwitterApplications(){
		ArrayList<TwitterApplication> list = new ArrayList<TwitterApplication>();
		TwitterApplication app1 = new TwitterApplication(1, "c7bM71G6UZjAamZYpriXdWmHi", "937ArspoqM5VcKrbEgkt2tI9oMSn1rgNTWyTjViTWQ5LeqE605", "2438828191-NGabSHeEk7k5SPTAg164xBEihFAO3rGz8vVyc9m", "PW1JNF8WHjwKWpDGoR6pzam76ua39fRS2paQ3EKoLR1uj");
		list.add(app1);
		return list;
		
	}
	public void setAvailable(boolean b) {
		this.available = b;
		
	}
}