package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

import org.springframework.util.StringUtils;

public class fileread {
	public static void main(String[] args) {
		FileReader fReader = null;
		FileWriter fWriter = null;
		try{
			fReader = new FileReader("E:\\ddd.csv");
			fWriter = new FileWriter("E:\\V7__import-iniial-data4.sql");
			BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\mmm.csv"),"MS949"));
			///BufferedReader bReader = new BufferedReader(fReader, 1024);
			BufferedWriter bWriter = new BufferedWriter(fWriter, 1024);
			String line = "";
			
			while((line = bReader.readLine()) != null){
				String word[] = StringUtils.split(line, ",");
				String line2 = "insert into vocabulary (word_type, word_en, word_kr) values ('M', '" + word[0] + "', '" + word[1] + "');";
				System.out.println(line2);
				bWriter.write(line2);
				bWriter.newLine();
			}
			bReader.close();
			bWriter.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
