package model;

import java.util.List;

import dao.TransactionDAO;

public class GetTransactionListLogic {
    public List<Transaction> execute(String userId) {
        TransactionDAO dao = new TransactionDAO();
        return dao.findByUser(userId);
    }
}
