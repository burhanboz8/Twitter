package com.bitirme.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;import java.util.Map;

public class NGram {
	private HashMap<String, Integer> maps;
	private String outputFileName;
	public String getOutputFileName() {
		return outputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	public NGram(){
		maps = new HashMap<>();
	}
	
	public void setFile(String path){
		
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(path));
			String str = null;
			while((str=reader.readLine())!=null){
			//	calculate(str,2);
				calculate(str,3);
				calculate(str,4);
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}finally{
			if(reader!=null){
				try{
					reader.close();
				}catch(Exception ex){
					
				}
			}
		}
	}
	private void save(int nGram){
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(outputFileName+nGram+".txt"));
			for(Map.Entry<String, Integer> mp : maps.entrySet()){
				String key = mp.getKey();
				int value = mp.getValue();
				writer.write(key+" , "+value);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				if(writer!=null)
					writer.close();
			}catch(Exception ex){
				
			}
		}
		
	}
	public void calculate(String str,int nGram){
		for(int i =0;i<str.length()-nGram+1;i++){
			String gram = "";
			for(int j=i;j<i+nGram;j++){
				gram += str.charAt(j);
			}
			if(maps.containsKey(gram)){
				int value = maps.get(gram);
				maps.put(gram, ++value);
				System.out.println(gram + " - "+value);
			}else{
				maps.put(gram, 1);
				System.out.println(gram + " - "+1);
			}
	
		}
		
		save(nGram);
	}
}
