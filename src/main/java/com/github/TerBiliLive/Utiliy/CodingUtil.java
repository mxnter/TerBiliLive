package com.github.TerBiliLive.Utiliy;

public class CodingUtil {




	public static String ascii2native ( String asciicode )
	{
		String[] asciis = asciicode.split ("\\\\u");
		String nativeValue = asciis[0];
		try
		{
			for ( int i = 1; i < asciis.length; i++ )
			{
				String code = asciis[i];
				nativeValue += (char) Integer.parseInt (code.substring (0, 4), 16);
				if (code.length () > 4)
				{
					nativeValue += code.substring (4, code.length ());
				}
			}
		}
		catch (NumberFormatException e)
		{
			return asciicode;
		}
		return nativeValue;
	}


}