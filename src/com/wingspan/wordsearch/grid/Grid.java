package com.wingspan.wordsearch.grid;

/**
 * Represents the grid of characters that makes up the word search puzzle
 *  Grid
 *
 */
public class Grid
{
	private char[][] _char_matrix;
	private int _x_size;
	private int _y_size;
	
	public static final char BLANK_CHAR = '0'; 
	
	public Grid(int x, int y)
	{
		_x_size = x;
		_y_size = y;
		generateBlankGrid();
	}
	
	/**
	 * Generates our starting grid, filling it with a pre-defined "blank" character.
	 * @param x_dim
	 * @param y_dim
	 * @return
	 */
	protected void generateBlankGrid()
	{
		_char_matrix = new char[_x_size][_y_size];
		for(int i = 0; i < _y_size; i++)
		{
			for(int j = 0; j < _x_size; j++)
			{
				_char_matrix[i][j] = BLANK_CHAR;
			}
		}
	}
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < _y_size; i++)
		{
			for(int j = 0; j < _x_size; j++)
			{
				if(j != 0)
				{
					sb.append(' ');
				}
				sb.append(_char_matrix[j][i]);
			}
			sb.append('\n');
		}
		return sb.toString();
	}
	
	public void setCharAt(GridCoordinate gc, char input)
	{
		_char_matrix[gc.getX()][gc.getY()] = input;
	}
	
	public char getCharAt(GridCoordinate gc)
	{
		return _char_matrix[gc.getX()][gc.getY()];
	}
	
	public char getBlankChar()
	{
		return BLANK_CHAR;
	}
	
	public int getXSize()
	{
		return _x_size;
	}
	
	public int getYSize()
	{
		return _y_size;
	}
}
