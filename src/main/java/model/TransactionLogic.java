package model;

import java.util.List;

import dao.TransactionDAO;

public class TransactionLogic {
    public boolean execute(Transaction transaction, List<String> errorList) {
    	
    	
		if (transaction.getPurpose().length() > 10){
			errorList.add("用途が長すぎます");
			return false;
		} else {
	    	TransactionDAO dao = new TransactionDAO();
	    	if (dao.entry(transaction, errorList)) {
	    		return true;
	    	} else {
				errorList.add("登録に失敗しました<br>もう一度やり直してください");
				return false;	
	    	}
		}

    }
}
