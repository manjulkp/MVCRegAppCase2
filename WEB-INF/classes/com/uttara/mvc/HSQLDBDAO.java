package com.uttara.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HSQLDBDAO implements MVCDAO {

	@Override
	public List<RegBean> getRegUsers() {
		
		List<RegBean> users = new ArrayList<RegBean>();
		
		Connection con = null;
		PreparedStatement ps_sel=null;
		ResultSet rs = null;
		try
		{
			con = JDBCHelper.getConnection();
			if(con==null)
				throw new RuntimeException("Connection not established. Contact DBA!");
			else
			{
				ps_sel = con.prepareStatement("select name,email from regusers");
				ps_sel.execute();
				
				rs = ps_sel.getResultSet();
				
				while(rs.next())
				{
					RegBean bean = new RegBean();
					bean.setUname(rs.getString("name"));
					bean.setEmail(rs.getString("email"));
					users.add(bean);
				}
				return users;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally
		{
			JDBCHelper.close(rs);
			JDBCHelper.close(ps_sel);
			JDBCHelper.close(con);
		}
	}
	
	@Override
	public boolean checkUser(LoginBean bean) {
		
		Connection con = null;
		PreparedStatement ps_sel=null;
		ResultSet rs = null;
		try
		{
			con = JDBCHelper.getConnection();
			if(con==null)
				throw new RuntimeException("Connection not established. Contact DBA!");
			else
			{
				ps_sel = con.prepareStatement("select name,email,pass from regusers where email = ? and pass = ?");
				ps_sel.setString(1, bean.getEmail());
				ps_sel.setString(2, bean.getPass());
				ps_sel.execute();
				
				rs = ps_sel.getResultSet();
				
				if(rs.next())
					return true;
				else
					return false;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally
		{
			JDBCHelper.close(rs);
			JDBCHelper.close(ps_sel);
			JDBCHelper.close(con);
		}

	}
	
	public String save(RegBean bean) {
		
		Connection con = null;
		PreparedStatement ps_ins=null;
		ResultSet rs = null;
		try
		{
			con = JDBCHelper.getConnection();
			if(con==null)
				throw new RuntimeException("Connection not established. Contact DBA!");
			else
			{	
				ps_ins = con.prepareStatement("insert into regusers(name,email,pass,cr_dt) values(?,?,?,?)");
				ps_ins.setString(1, bean.getUname());
				ps_ins.setString(2, bean.getEmail());
				ps_ins.setString(3, bean.getPass());
				
				Date dt = new Date();
				ps_ins.setDate(4, new java.sql.Date(dt.getTime()));
				
				ps_ins.execute();
				
				return Constants.SUCCESS;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally
		{
			JDBCHelper.close(rs);
			JDBCHelper.close(ps_ins);
			JDBCHelper.close(con);
		}				
		
	}

	public boolean checkEmailExists(String email) {
	
		Connection con = null;
		PreparedStatement ps_sel=null;
		ResultSet rs = null;
		try
		{
			con = JDBCHelper.getConnection();
			if(con==null)
				throw new RuntimeException("Connection not established. Contact DBA!");
			else
			{
				ps_sel = con.prepareStatement("select email from regusers where email = ?");
				ps_sel.setString(1, email);
				ps_sel.execute();
				
				rs = ps_sel.getResultSet();
				
				if(rs.next())
					return true;
				else
					return false;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally
		{
			JDBCHelper.close(rs);
			JDBCHelper.close(ps_sel);
			JDBCHelper.close(con);
		}

	}

}
