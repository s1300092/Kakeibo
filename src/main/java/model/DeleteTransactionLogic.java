package model;

import dao.TransactionDAO;

public class DeleteTransactionLogic {
    public boolean execute(int id) {
    	TransactionDAO dao = new TransactionDAO();
    	return dao.deleteById(id);
    }
}
