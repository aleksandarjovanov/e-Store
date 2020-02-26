package mk.ukim.finki.emit.sports_shop.service;

import com.stripe.exception.*;
import com.stripe.model.Charge;
import mk.ukim.finki.emit.sports_shop.dto.ChargeRequest;
import mk.ukim.finki.emit.sports_shop.models.Transaction;

public interface PaymentService {

    Charge charge(ChargeRequest chargeRequest) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, CardException, APIException;

    Transaction addNewTransaction(String id, String status, String balance);
}
