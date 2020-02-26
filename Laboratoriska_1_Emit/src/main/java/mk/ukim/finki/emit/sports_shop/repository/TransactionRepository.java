package mk.ukim.finki.emit.sports_shop.repository;


import mk.ukim.finki.emit.sports_shop.models.Transaction;
import org.springframework.data.repository.Repository;

public interface TransactionRepository extends Repository<Transaction, String> {

    Transaction save(Transaction transaction);
}
