package server.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import server.entity.BankAccount;
import server.exception.SaveRecordException;

import java.util.List;

@ApplicationScoped
public class BankAccountDao {

    @PersistenceContext(name = "my")
    private EntityManager em;


    public BankAccount findById(Long id) {
        return em.find(BankAccount.class, id);
    }

    public List<BankAccount> findAll() {
        Query query = em.createNamedQuery("findAll");
        return (List<BankAccount>) query.getResultList();
    }

    public BankAccount findAccountByAccNumber(String accNumber) {
        Query query = em.createNamedQuery("findAccountByAccNumber");
        query.setParameter("accNumber", accNumber);

        return (BankAccount) query.getSingleResult();
    }

    public BankAccount create(BankAccount bankAccount) throws SaveRecordException {
        em.persist(bankAccount);
        return bankAccount;
    }

    public void update(BankAccount bankAccount) {
        em.merge(bankAccount);
    }

    public void delete(BankAccount bankAccount) {
        bankAccount = em.merge(bankAccount);
        em.remove(bankAccount);
    }

    public long countByOwnerAndAccNumber(BankAccount bankAccount) {
        try {
            Query query = em.createNamedQuery("countByOwnerAndAccNumber");
            query.setParameter("owner", bankAccount.getOwner());
            query.setParameter("accNumber", bankAccount.getAccNumber());
            return (long) query.getSingleResult();
        } catch (Exception e) {
            return 1L;
        }
    }
}
