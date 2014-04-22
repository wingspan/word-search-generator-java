package com.wingspan.wordsearch.wordbank.reader;

import java.io.*;
import java.util.*;

import com.wingspan.wordsearch.log.LogFactory;

public class CSVWordBankReader implements IWordBankReader
{
	private static final String CSV_DELIMITER = ",";
	private BufferedReader _br;
	public CSVWordBankReader(BufferedReader br)
	{
		_br = br;
	}
	
	public List<String> read() 
	{
		List<String> rawWords = new ArrayList<String>();
		
		String line;
		try 
		{
			while ((line = _br.readLine()) != null) 
			{
				rawWords.addAll(Arrays.asList(line.split(CSV_DELIMITER)));
			}
	 
		} catch (FileNotFoundException e) 
		{
			LogFactory.getLog().error("CSVWordBankReader could not find file", e);
		} 
		catch (IOException e) 
		{
			LogFactory.getLog().error("CSVWordBankReader encountered an IOException", e);
		} 
		finally 
		{
			if (_br != null) 
			{
				try 
				{
					_br.close();
				} 
				catch (IOException e) 
				{
					LogFactory.getLog().error("CSVWordBankReader encountered an IOException while trying to close BufferedReader", e);
				}
			}
		}
		//  Convert all of the words to upper case
		List<String> result = new ArrayList<String>(rawWords.size());
		Iterator<String> iter = rawWords.iterator();
		while(iter.hasNext())
		{
			result.add(iter.next().toUpperCase());
		}
		 
		LogFactory.getLog().info("CSVWordBankReader read " + result.size() + " words.");
		
		return result;
	}
}
