package com.wingspan.wordsearch.config;

import java.io.*;
import java.util.List;

import com.wingspan.wordsearch.log.LogFactory;
import com.wingspan.wordsearch.wordbank.reader.CSVWordBankReader;
import com.wingspan.wordsearch.wordbank.reader.IWordBankReader;

public class WordSearchConfig
{
	public static final String EASY_DIFFICULTY = "EASY";
	public static final String NORMAL_DIFFICULTY = "NORMAL";
	public static final String HARD_DIFFICULTY = "HARD";
	
	public static final String[] AVAILABLE_DIFFICULTIES = {EASY_DIFFICULTY, NORMAL_DIFFICULTY, HARD_DIFFICULTY};
	
	private final static String HELP_MSG = "  Proper arguments are: -difficulty -xsize -ysize -wordbankpath";
	
	private String _difficulty;
	
	private int _x_dim;
	private int _y_dim;
	
	private List<String> _wordBank;
	
	public WordSearchConfig(String difficulty, int x, int y, List<String> wordBank)
	{
		_difficulty = difficulty;
		_x_dim = x;
		_y_dim = y;
		_wordBank = wordBank;
	}
	
	public static String interpretDifficulty(String input)
	{
		String result = NORMAL_DIFFICULTY;
		for(int i = 0; i <= AVAILABLE_DIFFICULTIES.length; i++)
		{
			if(input.equalsIgnoreCase(AVAILABLE_DIFFICULTIES[i]))
			{
				result = AVAILABLE_DIFFICULTIES[i];
				break;
			}
		}
		return result;
	}
	
	/**
	 * Validate the command-line input for the application.
	 * Returns true if validation was successful.
	 * @param args
	 */
	private static boolean validateArgs(String[] args)
	{
		if(args.length < 4)
		{
			LogFactory.getLog().error("Not enough arguments."  + HELP_MSG);
		}
		else if
		(args.length > 4)
		{
			LogFactory.getLog().error("Too many arguments."  + HELP_MSG);
		}
		else if(!(args[0].equalsIgnoreCase(EASY_DIFFICULTY) || args[0].equalsIgnoreCase(NORMAL_DIFFICULTY) ||
				args[0].equalsIgnoreCase(HARD_DIFFICULTY)))
		{
			StringBuffer errorMsg = new StringBuffer("Invalid difficulty: acceptable values are: ");
			for(int i = 0; i < AVAILABLE_DIFFICULTIES.length; i++)
			{
				if(i != 0)
				{
					errorMsg.append(", ");
				}
				errorMsg.append(AVAILABLE_DIFFICULTIES[i]);
			}
			errorMsg.append("." + HELP_MSG);
			LogFactory.getLog().error(errorMsg.toString());
		}
		else if(Integer.parseInt(args[1]) < 1)
		{
			LogFactory.getLog().error("xsize must be greater than one."  + HELP_MSG);
		}
		else if(Integer.parseInt(args[2]) < 1)
		{
			LogFactory.getLog().error("ysize must be greater than one."  + HELP_MSG);
		}
		else
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Returns a WordSearchConfig object from command-line arguments.
	 * @param args
	 * @return
	 */
	public static WordSearchConfig buildWordSearchConfigFromArgs(String[] args)
	{
		if(!validateArgs(args))
		{
			throw new RuntimeException("Invalid arguments!  See log for details.");
		}
		String difficulty = WordSearchConfig.interpretDifficulty(args[0]);
		int x = Integer.parseInt(args[1]);
		int y = Integer.parseInt(args[2]);
		List<String> wordBank;
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(args[3]));
			//  TODO: Add support for other types of wordbank readers (XML, SQL, etc)
			IWordBankReader wbr = new CSVWordBankReader(br);
			wordBank = wbr.read();
		}
		catch (FileNotFoundException e)
		{
			LogFactory.getLog().error("Could not find file: " + args[3]);
			throw new RuntimeException("Invalid arguments!  See log for details.");
		}
		
		return new WordSearchConfig(difficulty, x, y, wordBank);
	}
	
	public void setDifficulty(String difficulty)
	{
		_difficulty = difficulty;
	}
	
	public String getDifficulty()
	{
		return _difficulty;
	}
	
	public void setXDimension(int x)
	{
		_x_dim = x;
	}
	
	public int getXDimension()
	{
		return _x_dim;
	}
	
	public void setYDimension(int y)
	{
		_y_dim = y;
	}
	
	public int getYDimension()
	{
		return _y_dim;
	}
	
	public void setWordBank(List<String> wordBank)
	{
		_wordBank = wordBank;
	}
	
	public List<String> getWordBank()
	{
		return _wordBank;
	}
}
