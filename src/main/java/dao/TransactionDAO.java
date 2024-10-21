package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Transaction;

public class TransactionDAO {
    private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/myKakeibo";
    private final String DB_USER = "sa";
    private final String DB_PASS = "";
    
    public boolean deleteById(int id) {
    	
    	try {
    		Class.forName("org.h2.Driver");
    	} catch (ClassNotFoundException e) {
    		throw new IllegalStateException("JDBCドライバを読み込めませんでした");
    	}
    	
    	try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
    		String sql = "DELETE FROM KAKEIBO WHERE ID = '" + id + "'";
    		PreparedStatement pStmt = conn.prepareStatement(sql);
    		
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
    
    public List<Transaction> findByUser(String userId) {
    	List<Transaction> transactionList = new ArrayList<>();
    	
    	try {
    		Class.forName("org.h2.Driver");
    	} catch (ClassNotFoundException e) {
    		throw new IllegalStateException("JDBCドライバを読み込めませんでした");
    	}
    	
    	try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
    		String sql = "SELECT ID,PURPOSE,MONEY,DATE FROM KAKEIBO WHERE USERNAME = '" + userId + "' ORDER BY DATE DESC";
    		PreparedStatement pStmt = conn.prepareStatement(sql);
    		
    		ResultSet rs = pStmt.executeQuery();
    		
    		while (rs.next()) {
    			int id = rs.getInt("ID");
    			String purpose = rs.getString("PURPOSE");
    			int money = rs.getInt("MONEY");
    			LocalDate date = rs.getDate("DATE").toLocalDate();
    			Transaction t = new Transaction(id, purpose, money, date, userId);
    			transactionList.add(t);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return null;
    	}
    	return transactionList;
    }
    
    public boolean entry(Transaction transaction, List<String> errorList) {
    	try {
    		Class.forName("org.h2.Driver");
    	} catch (ClassNotFoundException e) {
    		throw new IllegalStateException("JDBCドライバを読み込めませんでした");
    	}
    	
    	try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
    		
   	        String sql = "INSERT INTO KAKEIBO(PURPOSE, MONEY, DATE, USERNAME) VALUES(?, ?, ?, ?)";
    		PreparedStatement pStmt = conn.prepareStatement(sql);
    		pStmt.setString(1, transaction.getPurpose());
    		pStmt.setInt(2, transaction.getMoney());
    		pStmt.setDate(3, java.sql.Date.valueOf(transaction.getDate()));
    		pStmt.setString(4, transaction.getUsername());
    		
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
