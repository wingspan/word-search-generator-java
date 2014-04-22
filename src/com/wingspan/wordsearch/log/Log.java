package com.wingspan.wordsearch.log;

public abstract class Log implements ILog
{
	/*
	 * Log levels:
	 * 0 : Error
	 * 1 : Warn
	 * 2 : Info
	 * 3 : Debug
	 */
	private int _level;
	
	public static int LOG_LEVEL_ERROR = 0; 
	public static int LOG_LEVEL_WARN = 1; 
	public static int LOG_LEVEL_INFO = 2; 
	public static int LOG_LEVEL_DEBUG = 3; 
		
	public int getLevel()
	{
		return _level;
	}

	public void setLevel(int newLevel)
	{
		_level = newLevel;
	}
}
