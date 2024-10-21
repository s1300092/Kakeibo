package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Account;
import model.Login;

public class AccountsDAO {
    private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/myKakeibo";
    private final String DB_USER = "sa";
    private final String DB_PASS = "";
    
    public Account findByLogin(Login login) {
    	Account account = null;
    	
    	try {
    		Class.forName("org.h2.Driver");
    	} catch (ClassNotFoundException e) {
    		throw new IllegalStateException("JDBCドライバを読み込めませんでした");
    	}
    	
    	try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
    		String sql = "SELECT USER_ID, PASS FROM ACCOUNTS WHERE USER_ID = ? AND PASS = ?";
    		PreparedStatement pStmt = conn.prepareStatement(sql);
    		pStmt.setString(1, login.getUserId());
    		pStmt.setString(2, login.getPass());
    		
    		ResultSet rs = pStmt.executeQuery();
    		
    		if (rs.next()) {
    			String userId = rs.getString("USER_ID");
    			String pass = rs.getString("PASS");
    			account = new Account(userId, pass);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return null;
    	}
    	
    	return account;
    }
    
    public boolean createAccount(Account account, List<String> errorList) {
    	try {
    		Class.forName("org.h2.Driver");
    	} catch (ClassNotFoundException e) {
    		throw new IllegalStateException("JDBCドライバを読み込めませんでした");
    	}
    	
    	try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
    		
    		String checkSql = "SELECT COUNT(*) FROM ACCOUNTS WHERE USER_ID = ?";
   	        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
   	        checkStmt.setString(1, account.getUserId());
   	        ResultSet rs = checkStmt.executeQuery();
   	            
   	        if (rs.next() && rs.getInt(1) != 0) {
   	            errorList.add("そのIDは使用されています");
   	        	return false;
   	        }
    		
    		String sql = "INSERT INTO ACCOUNTS(USER_ID, PASS) VALUES(?, ?)";
    		PreparedStatement pStmt = conn.prepareStatement(sql);
    		pStmt.setString(1, account.getUserId());
    		pStmt.setString(2, account.getPass());
    		
    		int result = pStmt.executeUpdate();
    		if (result != 1) {
    			return false;
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    	return true;
    }
}
