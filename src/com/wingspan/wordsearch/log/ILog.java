package com.wingspan.wordsearch.log;

public interface ILog 
{
	public int getLevel();
	public void setLevel(int newLevel);
	
	public void debug(String msg);
	public void info(String msg);
	public void warn(String msg);
	public void error(String msg);
	public void error(String msg, Exception e);
	public void error(Exception e);
}
