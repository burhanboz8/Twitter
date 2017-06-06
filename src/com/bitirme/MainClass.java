package com.bitirme;
import static spark.Spark.*;



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.bitirme.classification.Classification;
import com.bitirme.classification.PrepareData;
import com.bitirme.core.Fetcher;
import com.bitirme.core.NGram;
import com.bitirme.database.DatabaseHelper;
import com.bitirme.model.TwitterUser;

import twitter4j.TwitterException;
import weka.classifiers.Evaluation;

public class MainClass {
	private static final int SERVER_PORT = 3547;
	public static void main(String[] args) throws Exception  {
		/*
		DatabaseHelper dbhelp = new DatabaseHelper();
		List<TwitterUser> userList = dbhelp.getUsers();
		String csvFilePath = PrepareData.createUserCSVFile(userList);
		String arffFilePath = PrepareData.createUserARFFFile(csvFilePath);
		System.out.println(arffFilePath);
		
		Classification cls = new Classification();
		cls.training(arffFilePath);
		String clientSentence;*/
		Fetcher fetcher = new Fetcher();
		
		
		port(SERVER_PORT);
		get("/user",(request, response) -> {
            String type = request.queryParams("type");
            System.out.println(type);

            return "Human";

        });
		post("/user", (req,rep)->{
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
                  //  System.out.println("Dbye girnedi geldi");

                }else if (found == 1){
				    result = "Human";
				  //  System.out.println("Dbden geldi");
                }else
                    result = "Bot";

				
			}catch(TwitterException ex){
				//twitter.com/settings gibi bir adrese girildiginde hata vericek.
				//serverin patlamamasi icin bu try catch zorunlu.
				// ve kullaniciya default degeri donduruluyo ki iconu normal icon yapsin...
				ex.printStackTrace();
				return "Default";
			}
			
			if(result.equalsIgnoreCase("Human") ){
				//System.out.println(result);
				return "Human";
			}else{
				//System.out.println(result);
				return "Bot";
			}
		});
		
		
		}
		/*
		 * [ {method = NaiveBayes, accuracy= 80} { 
		 */
		//fetcher.setUserName("tarihtekibugun");
		//fetcher.setUserType(UserType.Bot);
		//fetcher.fetch();
	/*	try {				//Dosyadan isimleri okup dbye kaydetme
			File file = new File("asd.txt");
			
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				//System.out.println(line);
				fetcher.fetch(line,UserType.Human);
				
			}
			fileReader.close();
			//System.out.println(stringBuffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} */
		
	/*	try {       //icerikleri cekip duzenliyor...
				dbhelp.selectContext();
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			*/
		//fetcher.fetch("rihanna",UserType.Human);      Bir kişinin tweetlerini çekiyo
	/*	NGram gram = new NGram();
		gram.setOutputFileName("0type");
		gram.setFile("type0.txt");
		NGram gram2 = new NGram();
		gram2.setOutputFileName("1type");
		gram2.setFile("type1.txt");
		
		*/
		
		
}
	
