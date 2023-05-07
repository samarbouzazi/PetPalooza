package petpalooza.Services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;

public class PaiementJobService {
    private String apiKey;
    private static String secretKey = "sk_test_51N1zyPIuuxyX48MtMvkO8E9EW1LsKmPYUgftWXgB3ctgep7LUxDGtBs8l3Pnsmhb1BcKKogvrBvJ97itqwa2I59F00xZpblwx1"; // key

    public static Charge chargeCreditCard(String token, int amount) throws StripeException {
        Stripe.apiKey = secretKey;
        ChargeCreateParams chargeParams = new ChargeCreateParams.Builder()
                .setAmount((long) amount)
                .setCurrency("usd")
                .setDescription("Delivery Fees")
                .setSource(token)
                .build();
        Charge charge = Charge.create(chargeParams);
        return charge;
    }
}
