package com.wingspan.wordsearch.grid;

public class GridCoordinate
{
	private int _x;
	private int _y;
	
	public GridCoordinate (int x, int y)
	{
		_x = x;
		_y = y;
	}
	public int getX()
	{
		return _x;
	}
	public int getY()
	{
		return _y;
	}
	public String toString()
	{
		return ("[" + _x + "," + _y + "]");
	}
}
