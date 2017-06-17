package com.bitirme.classification;

import com.bitirme.model.TwitterUser;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class PrepareData {
	public static String createUserCSVFile(List<TwitterUser> lst){
		String fileName = "tmp/"+new Random().nextInt(1000)+100+".csv";
		File file = new File(fileName);
		if(file.exists())
			file.delete();
		BufferedWriter writer = null;
		try {
			 writer = new BufferedWriter(new FileWriter(file));
			 writer.write("follower,followed,favcount,defaultimage," +
					 "lengthUserName,lengthDescName,ratioFollowerFollowed," +
					 "getReputation,AgeOfAccount,FollowingRate,CountOfTweets," +
                     "NumberOfTPerDay,AvgLinkCount,AvgRTCount,AvgHTCount,AvgFavCount" +
                     ",AvgDDifference,AvgNOfChars,AvgNOfWords,AvgNOfCapitalization" +
                     ",AvgNOfQuestionMark,AvgNOfExclamationMark,AvgNOfDotMark,AvgMaxWordLength," +
                     "AvgNOfWhiteSpaces,AvgMeanWordLength,Type\n");
			 
			for(int i =0;i<lst.size();i++){
				TwitterUser tu = lst.get(i);
				//TODO yap 
				        writer.write(tu.getFollower()+","+tu.getFollowed()+","
				        +tu.getFavCount()+","+tu.isDefaultImage()+","+tu.getLengthUserName()+","
                        +tu.getLengthDescriptionName()+","+tu.getRatioFollowerFollowed()+","
                        +tu.getReputationOfUser()+","+tu.getAgeOfAccount()+","+tu.getFollowingRate()+","
			        	+tu.getCountOfTweets()+","+tu.getNumberOfTPerDay()+","+tu.getAvgLinkCount()+","+tu.getAvgRTCount()+","
						+tu.getAvgHTCount()+","+tu.getAvgFavCount()+","
						+tu.getAvgDDifference()+","+tu.getAvgNofChars()+","+tu.getAvgNofWords()+","
						+tu.getAvgNofCapitalization()+","+tu.getAvgNofQuestionMark()+","+tu.getAvgNofExclamationMark()+","
						+tu.getAvgNofDotMark()+","+tu.getAvgMaxWordLength()+","+tu.getAvgNofWhiteSpaces()+","
						+tu.getAvgMeanWordLength()+","+tu.getType()+"\n");
			}
			writer.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				if(writer!=null)
					writer.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
		return fileName;
		
	}
	public static String createUserFile(TwitterUser tu){
		String str = "@relation 406100\n\n@attribute follower numeric\n@attribute followed numeric\n" +
				"@attribute favcount numeric\n@attribute defaultimage {false,true}\n" +
				"@attribute lengthUserName numeric\n@attribute lengthDescName numeric\n" +
				"@attribute ratioFollowerFollowed numeric\n@attribute getReputation numeric\n" +
				"@attribute AgeOfAccount numeric\n@attribute FollowingRate numeric\n" +
				"@attribute CountOfTweets numeric\n@attribute NumberOfTPerDay numeric\n" +
				"@attribute AvgLinkCount numeric\n@attribute AvgRTCount numeric\n" +
				"@attribute AvgHTCount numeric\n@attribute AvgFavCount numeric\n" +
				"@attribute AvgDDifference numeric\n@attribute AvgNOfChars numeric\n" +
				"@attribute AvgNOfWords numeric\n@attribute AvgNOfCapitalization numeric\n" +
				"@attribute AvgNOfQuestionMark numeric\n@attribute AvgNOfExclamationMark numeric\n" +
				"@attribute AvgNOfDotMark numeric\n@attribute AvgMaxWordLength numeric\n" +
				"@attribute AvgNOfWhiteSpaces numeric\n@attribute AvgMeanWordLength numeric\n" +
				"@attribute Type {Human,Bot}\n\n@data\n";
		str += tu.getFollower()+","+tu.getFollowed()+","
				+tu.getFavCount()+","+tu.isDefaultImage()+","+tu.getLengthUserName()+","+tu.getLengthDescriptionName()+","+tu.getRatioFollowerFollowed()+","
				+tu.getReputationOfUser()+","+tu.getAgeOfAccount()+","+tu.getFollowingRate()+","
				+tu.getCountOfTweets()+","+tu.getNumberOfTPerDay()+","
				+tu.getAvgLinkCount()+","+tu.getAvgRTCount()+","
				+tu.getAvgHTCount()+","+tu.getAvgFavCount()+","
				+tu.getAvgDDifference()+","+tu.getAvgNofChars()+","+tu.getAvgNofWords()+","
				+tu.getAvgNofCapitalization()+","+tu.getAvgNofQuestionMark()+","+tu.getAvgNofExclamationMark()+","
				+tu.getAvgNofDotMark()+","+tu.getAvgMaxWordLength()+","+tu.getAvgNofWhiteSpaces()+","
				+tu.getAvgMeanWordLength()+",?\n";
		String fileName = "tmp/user.arff";
		//System.out.println(fileName);
		File file = new File(fileName);
		if(file.exists())
			file.delete();
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				if(writer!=null)
					writer.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return fileName;
		
	}
	public static String createUserARFFFile(String csvFilePath){
		//CSV yi ARFF ye cevir
		String fileName = "tmp/"+new Random().nextInt(1000)+100+".arff";
		File file = new File(fileName);
		if(file.exists())
			file.delete();
		BufferedWriter writer = null;
		try {
			 writer = new BufferedWriter(new FileWriter(file));
			// load CSV
			 CSVLoader loader = new CSVLoader();
			 loader.setSource(new File(csvFilePath));
			 Instances data = loader.getDataSet();
			 // save ARFF
			 ArffSaver saver = new ArffSaver();
			 saver.setInstances(data);
			 saver.setFile(file);
			 saver.setDestination(file);
			 saver.writeBatch();
			writer.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				if(writer!=null)
					writer.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
		return fileName;
		

	}
}
