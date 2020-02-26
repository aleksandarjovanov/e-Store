package mk.ukim.finki.emit.sports_shop.web.controllers;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import mk.ukim.finki.emit.sports_shop.dto.ChargeRequest;
import mk.ukim.finki.emit.sports_shop.models.Product;
import mk.ukim.finki.emit.sports_shop.service.PaymentService;
import mk.ukim.finki.emit.sports_shop.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaymentController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    private PaymentService paymentService;

    private ProductService productService;

    public PaymentController(PaymentService paymentService, ProductService productService) {
        this.paymentService = paymentService;
        this.productService = productService;
    }


    @PostMapping("/charge")
    public String charge( ChargeRequest chargeRequest, Model model)
            throws StripeException {

        chargeRequest.setDescription("EMT payment");
        chargeRequest.setCurrency(ChargeRequest.Currency.USD);
        Charge charge = paymentService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());


        paymentService.addNewTransaction(charge.getId(), charge.getStatus(), charge.getBalanceTransaction());
        return "result";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }

}
