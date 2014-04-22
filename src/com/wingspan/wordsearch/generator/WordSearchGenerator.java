package com.wingspan.wordsearch.generator;

import java.util.Iterator;
import java.util.List;

import com.wingspan.wordsearch.config.WordSearchConfig;
import com.wingspan.wordsearch.grid.*;
import com.wingspan.wordsearch.util.RandomNumberGeneratorFactory;

public class WordSearchGenerator
{
	private WordSearchConfig _wordSearchConfig;
	
	public static final int DIR_HORIZONTAL = 0;
	public static final int DIR_VERTICAL = 1;
	public static final int DIR_DIAG_UP = 2;
	public static final int DIR_DIAG_DOWN = 3;
	public static final int DIR_R_HORIZONTAL = 4;
	public static final int DIR_R_VERTICAL = 5;
	public static final int DIR_R_DIAG_UP = 6;
	public static final int DIR_R_DIAG_DOWN = 7;
	
	public static final int[] EASY_DIRECTIONS = {DIR_HORIZONTAL, DIR_VERTICAL};
	public static final int[] NORMAL_DIRECTIONS = {DIR_HORIZONTAL, DIR_VERTICAL, DIR_DIAG_UP, DIR_DIAG_DOWN};
	public static final int[] HARD_DIRECTIONS = {DIR_HORIZONTAL, DIR_VERTICAL, DIR_DIAG_UP, DIR_DIAG_DOWN, DIR_R_HORIZONTAL, DIR_R_VERTICAL, DIR_R_DIAG_UP, DIR_R_DIAG_DOWN};
	
	public static final char[] ALL_CHARS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
		'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	
	
	public WordSearchGenerator(WordSearchConfig wordSearchConfig)
	{
		_wordSearchConfig = wordSearchConfig;
	}
	
	public Grid generate()
	{
		Grid grid = new Grid(_wordSearchConfig.getXDimension(),_wordSearchConfig.getYDimension());
		grid = writeWords(grid);
		//  Write answer key here
		grid = writeRandomChars(grid);
		return grid;
	}
	
	/**
	 * Write the word bank to a blank grid.
	 * @param grid
	 * @return
	 */
	protected Grid writeWords(Grid grid)
	{
		List<String> wordBank = _wordSearchConfig.getWordBank();
		Iterator<String> iter = wordBank.iterator();
		
		while(iter.hasNext())
		{
			String word = iter.next();
			grid = placeWord(word, grid);
		}
		
		return grid;
	}
	
	/**
	 * Write random "noise" characters to any grid coordinates which are blank.
	 * The characters written depend on the difficulty of the puzzle.
	 * @param grid
	 * @return
	 */
	protected Grid writeRandomChars(Grid grid)
	{
		char [] charPool = ALL_CHARS;
		if(_wordSearchConfig.getDifficulty().equals(WordSearchConfig.HARD_DIFFICULTY))
		{
			charPool = generateDifficultCharPool();
		}
		grid = placeCharsFromCharPool(grid, charPool);
		return grid;
	}
	/**
	 * Writes the word at a random location and direction on the grid.
	 */
	protected Grid placeWord(String word, Grid grid)
	{
		boolean isValidPlacement = false;  // Is the word able to be placed at this direction & starting position?
		int direction = 0;
		GridCoordinate startingPosition = new GridCoordinate(0, 0);
		while(!isValidPlacement)
		{
			direction = generateRandomDirection();
			startingPosition = generateRandomCoordinate(grid);
			isValidPlacement = GridUtils.willWordPrint(startingPosition, direction, word, grid);
		}
		Grid result = GridUtils.printWord(startingPosition, direction, word, grid);
		return result;
	}
	
	/**
	 * Generates a direction at random, depending on the difficulty the user has selected.
	 * @return
	 */
	protected int generateRandomDirection()
	{
		String difficulty = _wordSearchConfig.getDifficulty();
		int index;
		if(difficulty.equals(WordSearchConfig.EASY_DIFFICULTY))
		{
			index = RandomNumberGeneratorFactory.getRNG().nextInt(EASY_DIRECTIONS.length - 1);
			return EASY_DIRECTIONS[index];
		}
		else if(difficulty.equals(WordSearchConfig.NORMAL_DIFFICULTY))
		{
			index = RandomNumberGeneratorFactory.getRNG().nextInt(NORMAL_DIRECTIONS.length - 1);
			return NORMAL_DIRECTIONS[index];
		}
		else
		{
			index = RandomNumberGeneratorFactory.getRNG().nextInt(HARD_DIRECTIONS.length - 1);
			return HARD_DIRECTIONS[index];
		}
	}
	
	/**
	 * Generates a GridCoordinate at random, within the grid's constraints.
	 * @return
	 */
	protected GridCoordinate generateRandomCoordinate(Grid grid)
	{
		int x = RandomNumberGeneratorFactory.getRNG().nextInt(grid.getXSize() - 1);
		int y = RandomNumberGeneratorFactory.getRNG().nextInt(grid.getYSize() - 1);
		return new GridCoordinate(x, y);
	}
	
	/**
	 * Generates a character pool based on what characters appear most in the words in the word bank.
	 * @return
	 */
	protected char[] generateDifficultCharPool()
	{
		//  First we need to figure out how large our result array will need to be;
		int totalLength = 0;
		Iterator<String> iter = _wordSearchConfig.getWordBank().iterator();
		while(iter.hasNext())
		{
			totalLength += iter.next().length();
		}
		char[] result = new char[totalLength];
		
		//  Get a new iterator
		iter = _wordSearchConfig.getWordBank().iterator();
		String currentWord = "";
		int j = 0;
		while(iter.hasNext())
		{
			currentWord = iter.next();
			for(int i = 0; i < currentWord.length(); i++)
			{
				result[j] = currentWord.charAt(i);;
				j++;
			}
		}
		return result;
	}
	
	/**
	 * Iterate over the grid, placing chars from the char pool into empty
	 * spaces at random.
	 * @param grid
	 * @param charPool
	 * @return
	 */
	private Grid placeCharsFromCharPool(Grid grid, char[] charPool)
	{
		int char_pool_index = RandomNumberGeneratorFactory.getRNG().nextInt(charPool.length - 1);
		char c = charPool[char_pool_index];
		for(int i = 0; i < grid.getYSize(); i++)
		{
			for(int j = 0; j < grid.getXSize(); j++)
			{
				GridCoordinate gc = new GridCoordinate(j,i);
				if(grid.getCharAt(gc) == Grid.BLANK_CHAR)
				{
					grid.setCharAt(gc, c);
					char_pool_index = RandomNumberGeneratorFactory.getRNG().nextInt(charPool.length - 1);
					c = charPool[char_pool_index];
				}
			}
		}
		return grid;
	}
}
