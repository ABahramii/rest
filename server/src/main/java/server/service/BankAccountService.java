package server.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import server.dao.BankAccountDao;
import server.entity.BankAccount;
import server.exception.DuplicateRecordException;
import server.exception.SaveRecordException;

import java.util.List;

@ApplicationScoped
public class BankAccountService {

    @Inject
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
