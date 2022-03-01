package server.service;

import server.dao.BankAccountDao;
import server.entity.BankAccount;
import server.exception.DuplicateRecordException;
import server.exception.SaveRecordException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
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

    private boolean canSave(BankAccount bankAccount) throws DuplicateRecordException {
        if (bankAccountDao.countByOwnerAndAccNumber(bankAccount) == 0L) {
            return true;
        }
        throw new DuplicateRecordException();
    }

    public BankAccount create(BankAccount bankAccount) throws SaveRecordException, DuplicateRecordException {
        if (canSave(bankAccount)) {
            try {
                return bankAccountDao.create(bankAccount);
            } catch (Exception e) {
                throw new SaveRecordException();
            }
        }
        return null;
    }

    public void update(BankAccount bankAccount) throws DuplicateRecordException {
        if (canSave(bankAccount)) {
            bankAccountDao.update(bankAccount);
        }
    }

    public void delete(BankAccount bankAccount) {
        bankAccountDao.delete(bankAccount);
    }

}
