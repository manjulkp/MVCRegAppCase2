package com.uttara.mvc;

public class DAOFactory {

	public static MVCDAO getDAO(String dao)
	{
		switch(dao)
		{
			case "hsqldb":
					return new HSQLDBDAO();
					
			default:
					return null;
		}	
	}
}
