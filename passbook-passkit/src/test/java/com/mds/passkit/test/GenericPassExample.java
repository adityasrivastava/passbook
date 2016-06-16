package com.mds.passkit.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mds.passkit.GeneratePass;
import com.mds.passkit.GolfScore;
import com.mds.passkit.GolfWallet;

public class GenericPassExample {
	
	public static void main(String args[]){
		
		GeneratePass gp = new GeneratePass();
		
		int count = 0;
		
		List<GolfScore> scores = new ArrayList<GolfScore>();
		
		GolfScore golfscore = new GolfScore();
		GolfWallet wallet = new GolfWallet();
		
		wallet.setGolfHoleType("9");
		wallet.setGolfCourseName("Hamoni Golf");
		wallet.setSerialNumber("1");
		wallet.setUserAge(""+1);
		wallet.setUserGender("MALE");
		wallet.setUserName("ABC");
		
		
		while(count<9){
			count++;
			golfscore.setHoleNumber(count);
			golfscore.setPar(count-1);
			golfscore.setScore(count);
			golfscore.setStroke(count);
			golfscore.setYards(count);
			golfscore.setTeeType("RED");
			golfscore.setUser(wallet);
			scores.add(golfscore);
		}
		
		wallet.setGolfScore(scores);
		
		try {
			gp.createGenericPass("/Users/adityasrivastava/Desktop/file.pkpass", scores);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
