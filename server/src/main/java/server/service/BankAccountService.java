package server.service;

import server.dao.BankAccountDao;
import server.entity.BankAccount;

import javax.ejb.EJB;
import java.util.List;

public class BankAccountService {

    @EJB
    private BankAccountDao bankAccountDao;

    public BankAccount findById(Long id) {
        return bankAccountDao.findById(id);
    }

    public List<BankAccount> findAll() {
        return bankAccountDao.findAll();
    }

    public BankAccount findAccountByAccNumber(String accNumber) {
        try {
            return bankAccountDao.findAccountByAccNumber(accNumber);
        } catch (Exception e) {
            return null;
        }
    }

    public BankAccount create(BankAccount bankAccount) {
        try {
            return bankAccountDao.create(bankAccount);
        } catch (Exception e) {
            return null;
        }
    }

    public BankAccount update(BankAccount bankAccount) {
        try {
            return bankAccountDao.update(bankAccount);
        } catch (Exception e) {
            return null;
        }
    }

}
