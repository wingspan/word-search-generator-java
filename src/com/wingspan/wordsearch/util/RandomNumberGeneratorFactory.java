package com.wingspan.wordsearch.util;

import java.util.Random;

public class RandomNumberGeneratorFactory
{
	private static Random _instance = null;
	
	/**
	 * Get the application's current RNG.  If no RNG exists, create one.
	 * @return Random A singleton RNG object
	 */
	public static Random getRNG()
	{
		if (_instance == null) 
		{
			_instance = new Random();
		}
		return _instance;
	}
}
