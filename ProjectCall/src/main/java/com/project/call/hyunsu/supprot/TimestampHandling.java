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
	
	
}
