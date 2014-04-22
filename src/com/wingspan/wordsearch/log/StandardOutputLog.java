package com.wingspan.wordsearch.log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StandardOutputLog extends Log 
{
	protected StandardOutputLog(int level)
	{
		setLevel(level);
	}
	
	public void debug(String msg) 
	{
		if(getLevel() >= LOG_LEVEL_DEBUG)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss.SSS");
			Date currentDate = new Date(System.currentTimeMillis());
			System.out.println(sdf.format(currentDate) + " - DEBUG: " + msg);
		}
	}

	public void info(String msg) 
	{
		if(getLevel() >= LOG_LEVEL_INFO)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss.SSS");
			Date currentDate = new Date(System.currentTimeMillis());
			System.out.println(sdf.format(currentDate) + " - INFO: " + msg);
		}
	}

	public void warn(String msg) 
	{
		if(getLevel() >= LOG_LEVEL_WARN)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss.SSS");
			Date currentDate = new Date(System.currentTimeMillis());
			System.out.println(sdf.format(currentDate) + " - WARN: " + msg);
		}

	}

	public void error(String msg) 
	{
		if(getLevel() >= LOG_LEVEL_ERROR)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss.SSS");
			Date currentDate = new Date(System.currentTimeMillis());
			System.out.println(sdf.format(currentDate) + " - ERROR: " + msg);
		}

	}

	public void error(String msg, Exception e) 
	{
		if(getLevel() >= LOG_LEVEL_ERROR)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss.SSS");
			Date currentDate = new Date(System.currentTimeMillis());
			System.out.println(sdf.format(currentDate) + " - ERROR: " + msg);
			System.out.println("CAUSED BY: ");
			System.out.println(e.getMessage());
			e.printStackTrace(System.out);
		}
	}
	
	public void error(Exception e) 
	{
		if(getLevel() >= LOG_LEVEL_ERROR)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss.SSS");
			Date currentDate = new Date(System.currentTimeMillis());
			System.out.println(sdf.format(currentDate) + " - ERROR: " + e.getMessage());
			e.printStackTrace(System.out);
		}
	}
}
