package mk.ukim.finki.emit.sports_shop.service.impl;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import mk.ukim.finki.emit.sports_shop.dto.ChargeRequest;
import mk.ukim.finki.emit.sports_shop.models.Transaction;
import mk.ukim.finki.emit.sports_shop.repository.TransactionRepository;
import mk.ukim.finki.emit.sports_shop.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {


    @Value("${STRIPE_SECRET_KEY}")
    private String stripePrivateKey;
    private TransactionRepository repo;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripePrivateKey;
    }

    public PaymentServiceImpl(TransactionRepository repo){
        this.repo = repo;
    }


    @Override
    public Charge charge(ChargeRequest chargeRequest) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, CardException, APIException {

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", chargeRequest.getCurrency());
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", chargeRequest.getStripeToken());
        return Charge.create(chargeParams);

    }

    @Override
    public Transaction addNewTransaction(String id, String status, String balance) {

        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setStatus(status);
        transaction.setBalance(balance);
        repo.save(transaction);

        return transaction;
    }
}
