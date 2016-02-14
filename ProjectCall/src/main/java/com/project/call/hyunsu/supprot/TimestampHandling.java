package com.project.call.hyunsu.supprot;

import java.sql.Timestamp;

import org.springframework.stereotype.Repository;

@Repository
public class TimestampHandling {

	public boolean isDate(String date, Timestamp timestamp){
		boolean result = true;
		String stamp = String.valueOf(timestamp);
		String YY = date.substring(0,4);
		String MM = date.substring(5,7);
		String DD = date.substring(8,10);
		String yy = stamp.substring(0,4);
		String mm = stamp.substring(5,7);
		String dd = stamp.substring(8,10);
		int fightDate =Integer.parseInt(YY+MM+DD);
		int today = Integer.parseInt(yy+mm+dd);
		if(today > fightDate){
			result = false;
		}
		return result;
	}
	public boolean isDate(Timestamp timestamp1, Timestamp timestamp2){
		boolean result = true;
		String date = String.valueOf(timestamp1);		
		String stamp = String.valueOf(timestamp2);
		System.out.println(date + "\n" + stamp);
		String YY,MM,DD,yy,mm,dd;
		try{
			YY = date.substring(0,4);
			MM = date.substring(5,7);
			DD = date.substring(8,10);
		}catch(StringIndexOutOfBoundsException e){
			return false;
		}
		try{	
			yy = stamp.substring(0,4);
			mm = stamp.substring(5,7);
			dd = stamp.substring(8,10);
		}catch(StringIndexOutOfBoundsException e){
			return true;
		}
			int fightDate =Integer.parseInt(YY+MM+DD);
			int today = Integer.parseInt(yy+mm+dd);
			if(today < fightDate){
				result = false;
			}
			return result;
		
	}
}
