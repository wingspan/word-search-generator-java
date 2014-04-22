package com.wingspan.wordsearch.writer;

import java.util.Iterator;
import java.util.List;

import com.wingspan.wordsearch.config.WordSearchConfig;
import com.wingspan.wordsearch.grid.Grid;

public class StandardOutputWordSearchWriter implements IWordSearchWriter
{
	private Grid _grid;
	private WordSearchConfig _config;
	
	public StandardOutputWordSearchWriter(Grid grid, WordSearchConfig config)
	{
		_grid = grid;
		_config = config;
	}
	
	public Grid getGrid()
	{
		return _grid;
	}
	public void setGrid(Grid grid)
	{
		_grid = grid;
	}
	public WordSearchConfig getConfig()
	{
		return _config;
	}
	public void setConfig(WordSearchConfig config)
	{
		_config = config;
	}
	
	public void write()
	{
		System.out.println(_grid.toString());
		System.out.println();
		System.out.println("====  WORD BANK  ====");
		List<String> wordBank = _config.getWordBank();
		Iterator<String> iter = wordBank.iterator();
		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}
	}
}
