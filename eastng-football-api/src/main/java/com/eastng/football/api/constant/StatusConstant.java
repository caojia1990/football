package com.eastng.football.api.constant;

/**
 * 
 * @author huang.xiaolong
 * @date 2016-07-26 09:12:14
 * 
 */
public class StatusConstant {
	public static final class Code {
		/**
		 * @Fields ok : 成功
		 */
		public static final int success = 1;
		/**
		 * @Fields error : 失败
		 */
		public static final int error = -9999;

		/**
		 * @Fields miss param
		 */
		public static final int missParam = -1001;
		
		/**
		 * 
		 */
		public static final int missSeason = -2001;
		
		public static final int missTeamSeasonScore = -2002;
		public static final int missLeagues = -2003;

	}

	public static final class Message {
		/**
		 * @Fields ok : 成功
		 */
		public static final String success = "success";
		/**
		 * @Fields error : 失败
		 */
		public static final String error = "system error";
		
		/**
		 * @Fields missParam : 缺少参数
		 */
		public static final String missParam = "miss param : ";
		
		public static final String missSeason = "miss season : can't find season information";
		
		/**
		 * 
		 */
		public static final String missTeamSeasonScore = "missTeamSeasonScore : can't find  team season score";
		public static final String missLeagues = "miss leagues : can't find leagues";
	}

}
