package com.wingspan.wordsearch.log;

public class LogFactory 
{
	private static volatile ILog _instance = null;
	
	/**
	 * Get the application's current log.  If no log exists, create one.
	 * @return iLog A singleton log object
	 */
	public static ILog getLog()
	{
		if (_instance == null) 
		{
			synchronized (ILog .class)
			{
				if (_instance == null) 
				{
					// TODO: Bootstrap log level from settings
        			_instance = new StandardOutputLog (Log.LOG_LEVEL_DEBUG);
        		}
            }
		}
		return _instance;
	}
}
