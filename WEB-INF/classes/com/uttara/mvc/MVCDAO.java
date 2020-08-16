package com.uttara.mvc;

import java.util.List;

public interface MVCDAO {

	public String save(RegBean bean);
	public boolean checkEmailExists(String email); 
	public boolean checkUser(LoginBean bean);
	public List<RegBean> getRegUsers();
}
