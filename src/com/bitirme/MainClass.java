package com.bitirme;
import static spark.Spark.*;



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.*;

import com.bitirme.classification.Classification;
import com.bitirme.classification.PrepareData;
import com.bitirme.core.Fetcher;
import com.bitirme.core.NGram;
import com.bitirme.database.DatabaseHelper;
import com.bitirme.model.TwitterUser;

import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.TwitterException;
import weka.classifiers.Evaluation;

public class MainClass {
	private static final int SERVER_PORT = 3547;
    private static  ModelUpdater updater = new ModelUpdater();
	public static void main(String[] args) {
		Fetcher fetcher = new Fetcher();
        DatabaseHelper helper = new DatabaseHelper();
        updater.start();
		port(SERVER_PORT);
		get("/hello",(req,rep) ->{
		   return "Hello friend!";
        });
		get("/status",(req,rep) ->{
		    if(updater.isLock()){
		        return "System is currently updating...";
            }else{
		        return "System is not locked!";
            }
        });
		get("/user", (req,rep)->{
		    if(updater.isLock()){
		        return responseJson(-1,"Server currently updating..",null);
            }
			String result;
			int found = 0;
			DatabaseHelper dbhelp = new DatabaseHelper();
			String link = req.queryParams("link");
			if(link==null){
			    return responseJson(0,"Twitter user url required! such as https://twitter.com/realdonaldtrump",null);
			}
			String userName = link.substring(link.lastIndexOf('/')+1);
			String displayName = null;
			try{
				found = dbhelp.searchUser(userName);
				if (found == 2){
                    TwitterUser user = fetcher.getUser(userName);
                    displayName = user.getDisplayName();
                    String arffFilePath = PrepareData.createUserFile(user);
                    Classification cls = new Classification();
                    result = cls.predicate(arffFilePath);
                }else if (found == 1){
				    result = "Human";
                }else
                    result = "Bot";

			}catch(TwitterException ex){
				ex.printStackTrace();
				return responseJson(0,"Url is not recognized!",null);
			}catch (SQLException ex){
			    ex.printStackTrace();
			    return responseJson(0,"Could not complete the request",null);
            }catch (NullPointerException ex){
			    ex.printStackTrace();
                return responseJson(0,"Could not complete the request",null);
            }
			
			if(result.equalsIgnoreCase("Human") ){
			    Map<String,String> mp = new HashMap<>();
			    mp.put("type","Human");
			    if(displayName!=null){
                    mp.put("name",displayName);
                }else{
			        mp.put("name",userName);
                }
				return responseJson(1,null,mp);
			}else{
                Map<String,String> mp = new HashMap<>();
                mp.put("type","Bot");
                if(displayName!=null){
                    mp.put("name",displayName);
                }else{
                    mp.put("name",userName);
                }
                return responseJson(1,null,mp);
			}
		});

	    get("/feedback",(req,res) ->{
            if(updater.isLock()){
                return responseJson(-1,"Server currently updating..",null);
            }
	        String userName;
            try{
                String link = req.queryParams("link");
                if(link==null){
                    return responseJson(0,"Twitter user url required! such as https://twitter.com/realdonaldtrump",null);
                }
                String typeStr = req.queryParams("type");
                if(typeStr == null ){
                    return responseJson(0,"Required Type(Human for 1, Bot for 0)",null);
                }
                userName = link.substring(link.lastIndexOf('/')+1);
                TwitterUser user = fetcher.getUser(userName);
                if(user.getDisplayName()!=null){
                    userName = user.getDisplayName();
                }
                helper.saveFeedback(link,userName,Integer.parseInt(typeStr));
                System.out.println("Request geldiii"+link);
                return responseJson(1,null,null);
            }catch (Exception ex){
                ex.printStackTrace();
	           return responseJson(0,"Wrong link!",null);
           }
        });
	}

    public static String responseJson(int success,String error, Map<String,String> keys){
        JSONObject object = new JSONObject();
        try {
            object.put("success",success);
            if(error!=null){
                object.put("error",error);
            }
            if(keys!=null){
                for(Map.Entry<String,String> kk : keys.entrySet()){
                    object.put(kk.getKey(),kk.getValue());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }
}
	
