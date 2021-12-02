
package com.innowave.mahaulb.portal.utils;

/*
Generated Jan 02 2016
Ashok Parmar 
*/


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

 
public class DateTimeZoneHelper {
	
	private static final String DDMMYYWITHSLASH="dd/MM/yyyy";
	private static final String DDMMYYHHMMWITHSLASH="dd/MM/yyyy HH:mm";
	private static final String DDMMYYHHMMWITHSLASHDASH="dd-MM-yyyy HH:mm";
	private static final String YYYYMMDDWITHDOT="yyyy.MM.dd";
	private static final String MMDDYYYYWITHDOT="MM.dd.yyyy";
	private static final String HHmmss = "HH:mm:ss";
	
	public static String getStrSysDate(String strDateFormat)
	{
		
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}
	public static Date getSysDate()
	{
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}
	public static String getStrSysTime(String strTimeFormat)
	{
	   return getStrSysDate(strTimeFormat);
	}
	

	//converts date from default format to format required by DB
	public static String changeDefaultToDBDateFormat(String strDate) 
	{
		// if date is not in default db format, convert it to format required by DB
		String strDefaultDateFormat = CommonUtils.DATE_FORMAT;
		String strDefaultDbDateFormat = CommonUtils.JDBC_DATE_FORMAT;
				
		if (strDate.equals("")){
			return strDate;
		} else if (!strDefaultDateFormat.equals(strDefaultDbDateFormat)) {
			return changeDefaultToDB2DateFormat(strDate,strDefaultDateFormat);
		} else {
			return strDate;
		}
	}

	//this method contains DB2 specific implementation
	private static String changeDefaultToDB2DateFormat(String strDate, String strDefaultDateFormat) 
		 {
		
		if (strDefaultDateFormat.equals(DDMMYYWITHSLASH)){
			return strDate.replace('/','.');
		} else if (strDefaultDateFormat.equals(YYYYMMDDWITHDOT)){
		    String strDd = strDate.substring(8,10);
		    String strMm = strDate.substring(5,7);
		    String strYyyy = strDate.substring(0,4);
		    
		    return strDd + "." + strMm + "." + strYyyy;
		} else if (strDefaultDateFormat.equals(MMDDYYYYWITHDOT)){
		    String strDd = strDate.substring(3,5);
		    String strMm = strDate.substring(0,2);
		    String strYyyy = strDate.substring(6,10);
		    
		    return strDd + "." + strMm + "." + strYyyy;
		} else {
			throw new IllegalArgumentException("Invalid date format '"+strDefaultDateFormat+"'.");
		}
	}

	//this method accepts date, month and year and returns date in default format set int the app
	public static String getDateDefaultFormat(String strDate,String strMonth,String strYear) 
	 {
		String strDefaultDateFormat = CommonUtils.DATE_FORMAT;
		if (strDefaultDateFormat.equals("dd.MM.yyyy")) {
			return strDate+"."+strMonth+"."+strYear;	
		} else if (strDefaultDateFormat.equals(DDMMYYWITHSLASH)) {
			return strDate+"/"+strMonth+"/"+strYear;
		} else if (strDefaultDateFormat.equals(YYYYMMDDWITHDOT)) {
			return strYear+"."+strMonth+"."+strDate;
		} else if (strDefaultDateFormat.equals(MMDDYYYYWITHDOT)) {
			return strMonth+"."+strDate+"."+strYear;
		} else {
			throw new IllegalArgumentException("Invalid date format '"+strDefaultDateFormat+"'.");
		}
	}

	//	this method accepts date string in default format and returns sql date in DB format
	public static java.sql.Date getSQLDate(String pStrDate)  //pending - 2 b removed
	throws ParseException {
		
		String strDefaultDateFormat = CommonUtils.DATE_FORMAT;
		SimpleDateFormat formatter = new SimpleDateFormat(strDefaultDateFormat);
		Date utildate = formatter.parse(pStrDate);
		return new java.sql.Date(utildate.getTime());
	}
//	this method accepts date string in default format and returns sql date in DB format
	public static java.sql.Date getJDBCSQLDate(String pStrDate)  //pending - 2 b removed
	throws ParseException {
		
		String strDefaultDateFormat = CommonUtils.JDBC_DATE_FORMAT;
		SimpleDateFormat formatter = new SimpleDateFormat(strDefaultDateFormat);
		Date utildate = formatter.parse(pStrDate);
		return new java.sql.Date(utildate.getTime());
	}
	//  this method returns the system current sql date in DB format
	public static java.sql.Date getSysDateDbFormat() throws ParseException{
	
		String strDateFormat = CommonUtils.DATE_FORMAT;
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		Calendar cal = Calendar.getInstance();
		String strDate = dateFormat.format(cal.getTime());
		return getSQLDate(strDate);
	}

//	this method accepts date string in specified format and returns  sql date object
	public static java.sql.Date getSQLDateObj(String pStrDate,String pStrDate_format) 
	throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(pStrDate_format);
		Date utildate = formatter.parse(pStrDate);
		return new java.sql.Date(utildate.getTime());
	}
	
	public static String getDateStr(Date p_obj_date,String pStrDate_format) {
		SimpleDateFormat formatter = new SimpleDateFormat(pStrDate_format);
		return formatter.format(p_obj_date);
	}
	
	public static Timestamp getCurrentSysTimestamp(){
		java.util.Date date= new java.util.Date();
		return new Timestamp(date.getTime());
	}
	
	public static java.sql.Date getUtilDateToSQLdate(Date objUtilDate){
		Calendar cal = Calendar.getInstance();
		cal.setTime( objUtilDate );
		return new java.sql.Date(cal.getTime().getTime());
	}
	
	public static boolean isTimeAfter(int hrs){
		Calendar cal = Calendar.getInstance();
		 
		// Set time of calendar to 21:00
		cal.set(Calendar.HOUR_OF_DAY, hrs);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return Calendar.getInstance().after(cal);
		 
	}
	
	public static Date getUtilDate(String strDate) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(CommonUtils.SQL_DATE_FORMAT);
		return sdf.parse(strDate);
	}
	
	public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
	
	public static String getDateIntoString(Date date,String format)throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		 return sdf.format(date);
	}
	
	public static  Date changeUserInputDateToDBDateFormat(String strDate)throws ParseException{
		SimpleDateFormat dbDateFormat = new SimpleDateFormat(CommonUtils.SQL_DATE_FORMAT1);
		SimpleDateFormat fromUser = new SimpleDateFormat(CommonUtils.DATE_FORMAT);
		
		Date convertedCurrentDate=null;
		if(strDate != null){
			String reformattedStr = dbDateFormat.format(fromUser.parse(strDate));
			convertedCurrentDate = dbDateFormat.parse(reformattedStr);
		}
		return convertedCurrentDate;
	}
	
	public static  String changeStrDateFormat(String strDate,String inputDateFormat,String outputDateFormat)throws ParseException{
		SimpleDateFormat inputDateFormatO = new SimpleDateFormat(inputDateFormat);
		SimpleDateFormat outputDateFormatO = new SimpleDateFormat(outputDateFormat);
		String reformattedStr = ""; 
		if(strDate != null){
			reformattedStr = outputDateFormatO.format(inputDateFormatO.parse(strDate));
		}
		return reformattedStr;
	}
	
	public static Date changeDateFormate (Date date,String strDateFormate ) throws ParseException {
		SimpleDateFormat ft = new SimpleDateFormat (strDateFormate);
		String strDate = ft.format(date);
		return ft.parse(strDate);
	}
	
	public static String getCurrentYear() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String yearInString = String.valueOf(year);
		return yearInString;
	}
	public static String getCurrentMonth() {
		Calendar now = Calendar.getInstance();
		String month = now.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH ).toLowerCase();
		return month;
	}
	
	
      public static String getFormattedDateForDb(Date date) {
    	 
          SimpleDateFormat sqlDt = new SimpleDateFormat("yyyy-MM-dd");                      
          String btime = sqlDt.format(date);
          return btime;
		}
      public static String getFormattedDateForView(Date date) {
    	 
    	  SimpleDateFormat sqlDt = new SimpleDateFormat("dd-MM-yyyy");                      
    	  return sqlDt.format(date);
      }
      
      public static String getFormattedDateForDatetime(Date date) {
     	 
    	  SimpleDateFormat sqlDt = new SimpleDateFormat(DDMMYYHHMMWITHSLASH);                      
    	  return sqlDt.format(date);
      }
      
      public static String formattedDateForDatetime(Date date) {
      	 
    	  SimpleDateFormat sqlDt = new SimpleDateFormat(DDMMYYHHMMWITHSLASHDASH);                      
    	  return sqlDt.format(date);
      }
      
      
      //public static 
      
 public static Timestamp getTimestampFromStrDate(String date,String dtFormat,String newFormat) throws ParseException {
    	  
    	  DateFormat formatter = new SimpleDateFormat(dtFormat);
    	  Date d = formatter.parse(date);
    	  ((SimpleDateFormat) formatter).applyPattern(newFormat);
    	  String newDateString = formatter.format(d);
    	  

    	  return Timestamp.valueOf(newDateString);
      }
 
 	public static Date calenderstringtodate(String date) throws ParseException {
		SimpleDateFormat dates = new SimpleDateFormat(DDMMYYWITHSLASH);
		return dates.parse(date);
	}
 	
 	public static Timestamp dtTime(Date dt) throws ParseException {
		
		Calendar now = Calendar.getInstance();
		now.setTime(dt);
	    now.add(Calendar.MINUTE, 30);
	    Date teenMinutesFromNow = now.getTime();
	    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String st = ft.format(teenMinutesFromNow);
		return DateTimeZoneHelper.getTimestampFromStrDate(st, "yyyy-MM-dd HH:mm:ss.SSS", "yyyy-MM-dd HH:mm:ss.SSS");
	    
		}
 
 public static String dateTime(Date date) {

		Calendar now = Calendar.getInstance();
		now.setTime(date);
	    Date teenMinutesFromNow = now.getTime();
	    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	    return ft.format(teenMinutesFromNow);
	   
		}
 
 public static char checkOtpDateTime(String newDate,String oldDate) throws ParseException {
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
      Date nDate = sdf.parse(newDate);
      Date oDate = sdf.parse(oldDate);

      String newDate1 = sdf.format(nDate);
      String oldDate1 = sdf.format(oDate);
     

      if (nDate.after(oDate)) {
    	  return 'a';
      }else if (nDate.before(oDate)) {
    	  return 'b';
      }else {
    	  return 'n';
      }
	 
 }
 
 public static String dateTime1(String date) throws ParseException {
	 SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	 	Date date1 =	ft.parse(date);
		Calendar now = Calendar.getInstance();
		now.setTime(date1);
	    Date teenMinutesFromNow = now.getTime();
	    
	    return ft.format(teenMinutesFromNow);
	   
		}
 
 	public static int getDifferenceInDays(Date start, Date end)
	{
		Calendar c1=Calendar.getInstance();
     c1.setTime(start);
     Calendar c2=Calendar.getInstance();
     c2.setTime(end);

     Date d1=c1.getTime();
     Date d2=c2.getTime();

     long diff=d2.getTime()-d1.getTime();
     return (int)(diff/(1000*24*60*60));
     
     
	}
 	
 	
 	// adding method to convert dbDate of yyyy-MM-dd into dd/MM/yyyy in String date
 	public static String getConvertedStringDt(String dbDt) {
 		SimpleDateFormat dbFmt = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat fmt = new SimpleDateFormat(DDMMYYWITHSLASH);
		Date date = new Date();
		String strDate;
			try {
				date = dbFmt.parse(dbDt);
				strDate = fmt.format(date);
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			strDate = fmt.format(date);
 		return strDate;
 	}
 	
 	
 	
 	//method to get formatted String date in dd/MM/yyyy format from db Date.
 	public static String getFormattedStringDt(Date dbDt) {
 	SimpleDateFormat fmt = new SimpleDateFormat(DDMMYYWITHSLASH);
 	String strDate;
 	strDate = fmt.format(dbDt);
 	return strDate;
 	}
 	
		public static Date getPreviousDaysDate(int days)
	{
 		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -days);
		return cal.getTime();
	}
	
	// method to get Date from String date 
  	public static Date getDateFromStringDt(String formDt) {
  		Date date = new Date();
		try {
			date = new SimpleDateFormat(DDMMYYWITHSLASH).parse(formDt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
  		
  		return date;
  	}
  	
	public static Date getDateFromStringDtWithSlash(String formDt) {
  		Date date = new Date();
		try {
			date = new SimpleDateFormat(DDMMYYHHMMWITHSLASH).parse(formDt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
  		
  		return date;
  	}
 	
  	public static Date getNextYear() throws ParseException
	{		
		int year = Calendar.getInstance().get(Calendar.YEAR);		
		int monthNumber = Calendar.getInstance().get(Calendar.MONTH);
	    int firstDate = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	    int year2 = year + 1;
		String year3 = String.valueOf(year2);
		String dtFormat = firstDate+"/"+monthNumber+"/"+year3;
		SimpleDateFormat dt = new SimpleDateFormat(DDMMYYWITHSLASH);
		return  dt.parse(dtFormat);
		
	}
  	
  	public static Date getStartDateMonth() throws ParseException
	{	
  		 Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);		
		int monthNumber = calendar.get(Calendar.MONTH);
	    int firstDate = 1;
	    calendar.set(year, monthNumber, firstDate);
	    
	    return calendar.getTime();
		
	}
  	public static Date getEndDateMonth() 
	{		
	    Calendar calendar = Calendar.getInstance();
	    int numOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	    calendar.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth-1);
	    
	    return calendar.getTime();
	  
	}
  	
	public static Date calenderstringtodatewithoutslash(String date) throws ParseException {
		SimpleDateFormat dates = new SimpleDateFormat("dd-MM-yyyy");
		return dates.parse(date);
	}

	public static String getTimeToString(Date valTime)throws Exception {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(valTime);
		SimpleDateFormat fmt = new SimpleDateFormat(HHmmss);
		String strDate;
		strDate = fmt.format(calendar.getTime());

		return strDate;

	}
}

