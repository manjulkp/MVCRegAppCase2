package com.uttara.mvc;

import java.util.List;

public class Model {

	public String register(RegBean bean)
	{
		try
		{
			System.out.println("in Model->register() bean = "+bean);
			
			/*
			 * 1) perform input validations
			 * 2) perform business validations
			 * 3) apply business logic
			 * 4) return success/failure msg
			 * 
			 */
			System.out.println("in Model->going to invoke bean.validate()");
			String result = bean.validate();
			System.out.println("in Model->after bean.validate() result = "+result);
			if(result.equals(Constants.SUCCESS))
			{
				
				MVCDAO dao = DAOFactory.getDAO(Constants.DAO);
				
				if(dao.checkEmailExists(bean.getEmail()))
					return "Email is a duplicate. Enter a unique email id!";
				else
				{
					//business validation has succeeded!
					//now store data
					
					String msg = dao.save(bean);
					
					return msg;
				}
				
			}
			else
				return result;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			return "Oops an exception occurred msg = "+e.getMessage();
		}
	}

	public String authenticate(LoginBean bean) {
		
		String result = bean.validate();
		if(result.equals(Constants.SUCCESS))
		{
			MVCDAO dao = DAOFactory.getDAO(Constants.DAO);
			boolean res = dao.checkUser(bean);
			if(res)
				return Constants.SUCCESS;
			else
				return "Email/Pass combination is invalid!Try again!!";
			
		}
		else
			return result;
	}

	public List<RegBean> getUsersInfo() {
		
		MVCDAO dao = DAOFactory.getDAO(Constants.DAO);
		
		return dao.getRegUsers();
	}
	
}





