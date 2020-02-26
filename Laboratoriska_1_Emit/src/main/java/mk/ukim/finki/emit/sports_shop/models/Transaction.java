package mk.ukim.finki.emit.sports_shop.models;

import javax.persistence.   *;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    private String id;
    private String status;
    private String balance;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
