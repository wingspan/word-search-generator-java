package com.wingspan.wordsearch.grid;

import com.wingspan.wordsearch.generator.WordSearchGenerator;

public class GridUtils
{
	/**
	 * Determines whether a word will validly print for a given 
	 * set of starting coords and direction
	 * @return
	 */
	public static boolean willWordPrint(GridCoordinate start, int direction, String word, Grid grid)
	{
		if(!checkLength(start, direction, word.length(), grid))
		{
			return false;
		}
		else if(!checkCollisions(start, direction, word, grid))
		{
			return false;
		}
		return true;
	}
	/**
	 * Increment/decrement coordinates based upon direction
	 */
	private static GridCoordinate move(GridCoordinate gc, int direction)
	{
		if(direction == WordSearchGenerator.DIR_HORIZONTAL)
		{
			return new GridCoordinate(gc.getX() + 1, gc.getY());
		}
		else if(direction == WordSearchGenerator.DIR_VERTICAL)
		{
			return new GridCoordinate(gc.getX(), gc.getY() + 1);
		}
		else if(direction == WordSearchGenerator.DIR_DIAG_UP)
		{
			return new GridCoordinate(gc.getX() + 1, gc.getY() - 1);
		}
		else if(direction == WordSearchGenerator.DIR_DIAG_DOWN)
		{
			return new GridCoordinate(gc.getX() + 1, gc.getY() + 1);
		}
		else if(direction == WordSearchGenerator.DIR_R_HORIZONTAL)
		{
			return new GridCoordinate(gc.getX() - 1, gc.getY());
		}
		else if(direction == WordSearchGenerator.DIR_R_VERTICAL)
		{
			return new GridCoordinate(gc.getX(), gc.getY() - 1);
		}
		else if(direction == WordSearchGenerator.DIR_R_DIAG_UP)
		{
			return new GridCoordinate(gc.getX() - 1, gc.getY() - 1);
		}
		else  //  Reverse Diagonal Down
		{
			return new GridCoordinate(gc.getX() - 1, gc.getY() + 1);
		}
	}
	
	/**
	 * Check to ensure that the word will fit on the grid given the direction
	 * and starting point.
	 * @return True if word fits, false otherwise
	 */
	private static boolean checkLength(GridCoordinate currentPos, int direction, int length, Grid grid)
	{
		for(int i = 0; i < length; i++)
		{
			int x = currentPos.getX();
			int y = currentPos.getY();
			if(x >= grid.getXSize() || y >= grid.getYSize() || x < 0 || y < 0)
			{
				return false;
			}
			currentPos = move(currentPos, direction);
		}
		return true;
	}
	/**
	 * Check to ensure that the the word won't overwrite other words already on the grid given the
	 * diraction and starting point.
	 * and starting point.
	 * @return True if word fits, false otherwise
	 */
	private static boolean checkCollisions(GridCoordinate currentPos, int direction, String word, Grid grid)
	{
		for(int i = 0; i < word.length(); i++)
		{
			char word_char = word.charAt(i);
			char grid_char = grid.getCharAt(currentPos);
			//  The spot we are printing should either be blank or have the same character (overlapping words)
			if (grid_char == Grid.BLANK_CHAR || grid_char == word_char)
			{
				currentPos = move(currentPos, direction);
			}
			else
			{
				return false;
			}
			
		}
		return true;
	}
	
	/**
	 * Prints a word to the grid, starting at the GridCoordinate specified and 
	 * traversing in the direction specified.
	 * @param currentPos
	 * @param direction
	 * @param word
	 * @param grid
	 * @return
	 */
	public static Grid printWord(GridCoordinate currentPos, int direction, String word, Grid grid)
	{
		for(int i = 0; i < word.length(); i++)
		{
			char c = word.charAt(i);
			grid.setCharAt(currentPos, c);
			currentPos = move(currentPos, direction);
		}
		return grid;
	}
}
