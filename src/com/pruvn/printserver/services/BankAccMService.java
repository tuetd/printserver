package com.pruvn.printserver.services;

import com.pruvn.printserver.entity.BankAccM;




public interface BankAccMService  {

	BankAccM findByBankCode(String bank_CODE);

}
