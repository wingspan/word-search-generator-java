package com.wingspan.wordsearch;

import com.wingspan.wordsearch.config.WordSearchConfig;
import com.wingspan.wordsearch.generator.WordSearchGenerator;
import com.wingspan.wordsearch.grid.Grid;
import com.wingspan.wordsearch.writer.IWordSearchWriter;
import com.wingspan.wordsearch.writer.StandardOutputWordSearchWriter;

public class WordSearch
{
	public static void main(String[] args)
	{
		WordSearchConfig wordSearchConfig = WordSearchConfig.buildWordSearchConfigFromArgs(args);
		WordSearchGenerator wordSearchGenerator = new WordSearchGenerator(wordSearchConfig);
		Grid grid = wordSearchGenerator.generate();
		IWordSearchWriter writer = new StandardOutputWordSearchWriter(grid, wordSearchConfig);
		writer.write();
	}
}
