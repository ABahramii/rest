package server.dao;

import server.entity.BankAccount;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
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

    public BankAccount create(BankAccount bankAccount) {
        em.persist(bankAccount);
        return bankAccount;
    }

    public BankAccount update(BankAccount bankAccount) {
        em.merge(bankAccount);
        return bankAccount;
    }
}
