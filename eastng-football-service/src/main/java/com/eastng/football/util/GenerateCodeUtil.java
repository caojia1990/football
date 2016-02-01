package com.eastng.football.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerateCodeUtil {
	
	/** 最大随机数范围　 */
    private final static int MAX_RANDOM_RANGE = 1000000000;
    
    /** 最大随机位数 */
    private final static int MAX_INTEGER_DIGITS_NINE = 9;

    public static String generateTeamNo(){
		StringBuilder sb = new StringBuilder();

        /* 拼装日期 */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = sdf.format(new Date());
		
        /* 生成9位随机数 */
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setGroupingUsed(false);
        Random random = new Random();
        int randomInt = random.nextInt(MAX_RANDOM_RANGE); // 小于1000000000的随机整数
        formatter.setMinimumIntegerDigits(MAX_INTEGER_DIGITS_NINE);  //最多九位
        String randomIntStr = formatter.format(randomInt);
        
        sb.append(dateStr);
        sb.append(randomIntStr);
		return sb.toString();
		
	}
    
    public static String generateMatchNo(String prefix){
		StringBuilder sb = new StringBuilder();

        /* 拼装日期 */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = sdf.format(new Date());
		
        /* 生成9位随机数 */
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setGroupingUsed(false);
        Random random = new Random();
        int randomInt = random.nextInt(MAX_RANDOM_RANGE); // 小于1000000000的随机整数
        formatter.setMinimumIntegerDigits(MAX_INTEGER_DIGITS_NINE);  //最多九位
        String randomIntStr = formatter.format(randomInt);
        
        sb.append(prefix);
        sb.append(dateStr);
        sb.append(randomIntStr);
		return sb.toString();
	}

}
