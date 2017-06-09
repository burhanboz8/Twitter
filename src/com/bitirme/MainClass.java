package com.bitirme;
import static spark.Spark.*;



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
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
	public static void main(String[] args) throws Exception  {
		Fetcher fetcher = new Fetcher();

		port(SERVER_PORT);
		get("/user", (req,rep)->{
			String result;
			int found = 0;
			DatabaseHelper dbhelp = new DatabaseHelper();
			String link = req.queryParams("link");
			if(link==null){
				return "User link can not be null!";
			}
			String userName = link.substring(link.lastIndexOf('/')+1);
			try{
				found = dbhelp.searchUser(userName);
				if (found == 2){
                    TwitterUser user = fetcher.getUser(userName);
                    String arffFilePath = PrepareData.createUserFile(user);
                    Classification cls = new Classification();
                    result = cls.predicate(arffFilePath);
                }else if (found == 1){
				    result = "Human";
                }else
                    result = "Bot";

				
			}catch(TwitterException ex){
				ex.printStackTrace();
				return "Default";
			}
			
			if(result.equalsIgnoreCase("Human") ){
				return "Human";
			}else{
				//System.out.println(result);
				return "Bot";
			}
		});

	    get("/feedback",(req,res) ->{
	        String userName;
            try{
                String link = req.queryParams("link");
                userName = link.substring(link.lastIndexOf('/')+1);
                TwitterUser user = fetcher.getUser(userName);
                if(user.getDisplayName()!=null){
                    userName = user.getDisplayName();
                }
                //TODO veritabanina kayit et
                return responseJson(1,null,null);
            }catch (Exception ex){
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
	
