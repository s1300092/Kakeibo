package model;

import java.util.List;

import dao.AccountsDAO;

public class RegistrationLogic {
    public boolean execute(Account account, List<String> errorList) {
    	boolean f = true;
    	
		if (account.getUserId().length() > 10 || account.getUserId().length() <= 0){
			errorList.add("ユーザーIDの長さが不正です");
			f = false;
		}
		if (account.getPass().length() > 10 || account.getPass().length() <= 0) {
			errorList.add("パスワードの長さが不正です");
			f = false;
	    } 
		
		if (f) {
	    	AccountsDAO dao = new AccountsDAO();
	    	if (dao.createAccount(account, errorList)) {
	    		return true;
	    	} else {
				if (errorList.size() == 0) {
					errorList.add("登録に失敗しました<br>もう一度やり直してください");
				}
				return false;	
	    	}
		} else {
			return false;
		}	
    	
    }
}
