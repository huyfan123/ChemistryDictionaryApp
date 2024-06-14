package Codes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBoperation 
{
	Statement stm;
	Connection connection;
	private static DBoperation instance;
	
	public void connectDB() 
	{
		try 
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://PTLOI\\SQLSERV:1433;databaseName=ChemistDict;encrypt=true;trustServerCertificate=true";
			String username = "sa";
			String password = "loi92017";
			
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("DONE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public DBoperation() throws SQLException 
	{
        connectDB();
    }
	
	public Connection getConnection() 
	{
        return connection;
    }
	
	public static DBoperation getInstance() throws SQLException 
	{
	       if (instance == null)
	           instance = new DBoperation();
	       else if (instance.getConnection().isClosed())
	           instance = new DBoperation();
	           
	       return instance;
	}
	
	public String checkLogin(String username, String password)
	{
		String rtn = "noaccfound";
		try {
			stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("Select * from accountData");

			String displayName;
			String userCheck;
			String passCheck;
			while (rs.next())
			{
				userCheck = rs.getString(1);
				displayName = rs.getString(2);
				passCheck = rs.getString(3);
				
				if (username.equals(userCheck) )
				{
					if (password.equals(passCheck))
					{
						rtn = displayName;
						break;
					}
					else
					{
						rtn = "wrongpass";
						break;
					}
				}
			}
			rs.close();
			stm.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return(rtn);
	}
	public String checkForgotPass(String displayName, String username, String password, String cfPassword)
	{
		String rtn = "NotFound";
		if (displayName.isBlank() || username.isBlank() || password.isBlank() || cfPassword.isBlank() )
			return("Null");
		if (password.equals(cfPassword) == false)
			return("CfPassWrong");
		try {
			stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("Select * from accountData");

			String nameCheck;
			String userCheck;
			while (rs.next())
			{
				userCheck = rs.getString(1);
				nameCheck = rs.getString(2);
				
				if (username.equals(userCheck) )
				{
					if (displayName.equals(nameCheck))
					{
						rtn = "Accepted";
						break;
					}
					else
					{
						rtn = "NotMatch";
						break;
					}
				}
			}
			rs.close();
			stm.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return(rtn);
	}
	public String checkRegister(String displayName, String username, String password, String cfPassword)
	{
		String rtn = "Accepted";
		if (displayName.isBlank() || username.isBlank() || password.isBlank() || cfPassword.isBlank() )
			return("Null");
		if (password.equals(cfPassword) == false)
			return("CfPassWrong");
		try {
			stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("Select * from accountData");

			String nameCheck;
			String userCheck;
			while (rs.next())
			{
				userCheck = rs.getString(1);
				nameCheck = rs.getString(2);
				
				if (username.equals(userCheck) )
				{
					rtn = "DuplicateUser";
					break;
				}
				else
				{
					if (displayName.equals(nameCheck))
					{
						rtn = "DuplicateName";
						break;
					}
				}
			}
			rs.close();
			stm.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return(rtn);
	}
	public void insertAccount(String displayName, String username, String password)
	{
		try {
			
			PreparedStatement ps = connection.prepareStatement("Insert into accountData (username, displayname, password) values(?,?,?)");

			ps.setString(1,username);
			ps.setString(2,displayName);
			ps.setString(3,password);

			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void changePass(String username, String password)
	{
		try
		{
			PreparedStatement ps = connection.prepareStatement("update accountData set password = ? where username = ?");
			
			ps.setString(1,password);
			ps.setString(2,username);
			
			ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String FindDisplayName(String username)
	{
		String fstm = String.format("Select displayname from accountData where username = '%s'",username);
		String rtn = "";
		try
		{
			stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(fstm);
			if (rs.next())
				rtn = rs.getString("displayname");
			rs.close();
			stm.close();
		}
		catch (SQLException e) {
			e.printStackTrace();	
		}
		return(rtn);
	}
	public ArrayList<String> getHistory(String username)
	{
		ArrayList<String> history = new ArrayList<String>();
		String gh = String.format("Select searched from searchHistory where username = '%s'",username);
		try
		{
			stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(gh);
			
			while (rs.next())
			{
				//System.out.println(rs.getString("searched"));
				history.add(rs.getString("searched"));
			}
			rs.close();
			stm.close();
		}
		catch (SQLException e) {
			e.printStackTrace();	
		}
		return(history);
	}
	public ArrayList<String> getNames(String input, String type)
	{
		input = input + '%';
		ArrayList<String> names = new ArrayList<String>();
		String gn = String.format("Select %s from mainData where %s like '%s'", type,type,input);
		try
		{
			stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(gn);
			
			while (rs.next())
			{
				//System.out.println(rs.getString("searched"));
				names.add(rs.getString(type));
			}
			rs.close();
			stm.close();
		}
		catch (SQLException e) {
			e.printStackTrace();	
		}
		return(names);
	}
	public ArrayList<String> getChemicalData(String type, String chemical)
	{
		ArrayList<String> data = new ArrayList<String>();
		String gcd = String.format("Select * from mainData where %s = '%s'",type,chemical);
		try
		{
			stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(gcd);
			
			while (rs.next())
			{
				for(int i=1; i<=7; i++)
					data.add(rs.getString(i));
			}
			rs.close();
			stm.close();
		}
		catch (SQLException e) {
			e.printStackTrace();	
		}
		return(data);
	}
	public String getType(String username, String searched)
	{
		String type = null;
		String gt = String.format("Select type from searchHistory where username = '%s' and searched = '%s'", username,searched);
		try
		{
			stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(gt);
			while (rs.next())
				type = rs.getString(1);
			
			rs.close();
			stm.close();
		}
		catch (SQLException e) {
			e.printStackTrace();	
		}
		return(type);
	}
	public void addHistory(String username, String searched, String type)
	{
		try {	
		PreparedStatement ps = connection.prepareStatement("Insert into searchHistory (username, searched, type) values(?,?,?)");

		ps.setString(1,username);
		ps.setString(2,searched);
		ps.setString(3,type);

		ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String getEnName(String item, String type)
	{
		String name = null;
		String gen = String.format("Select nameEn from mainData where %s = '%s'", type,item);
		try
		{
			stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(gen);
			while (rs.next())
				name = rs.getString(1);
			
			rs.close();
			stm.close();
		}
		catch (SQLException e) {
			e.printStackTrace();	
		}
		return(name);
	}
}
