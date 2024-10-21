package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Serializable{
	private int id;
    private String purpose;
    private Integer money;
    private LocalDate date;
    private String username;
    
    public Transaction(String purpose, Integer money, LocalDate date, String username) {
    	this.id = -1;
    	this.purpose = purpose;
    	this.money = money;
    	this.date = date;
    	this.username = username;
    }
    
    public Transaction(int id, String purpose, Integer money, LocalDate date, String username) {
    	this.id = id;
    	this.purpose = purpose;
    	this.money = money;
    	this.date = date;
    	this.username = username;
    }
    
    public int getId() { return id; }
    public String getPurpose() { return purpose; }
    public Integer getMoney() { return money; }
    public LocalDate getDate() { return date; }
    public String getUsername() { return username; }
}
