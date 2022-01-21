package server.entity;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "findAll",
                query = "select bankAccount from BankAccount bankAccount"
        ),
        @NamedQuery(
                name = "findAccountByAccNumber",
                query = "select bankAccount from BankAccount bankAccount where bankAccount.accNumber=:accNumber"
        )
})

@Entity
@Table(name = "BANK_ACCOUNT")
public class BankAccount {

    private Long id;
    private String owner;
    private Long balance;
    private String accNumber;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "OWNER")
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Column(name = "BALANCE")
    public Long getBalance() {
        return balance;
    }
    public void setBalance(Long balance) {
        this.balance = balance;
    }

    @Column(name = "ACC_NUMBER")
    public String getAccNumber() {
        return accNumber;
    }
    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

}
