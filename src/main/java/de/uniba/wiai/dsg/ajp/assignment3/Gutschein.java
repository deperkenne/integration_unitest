package de.uniba.wiai.dsg.ajp.assignment3;

import java.security.SecureRandom;
import java.util.Random;

public class Gutschein {
   private String gutScheinCOde ;
   private int getGutScheinWert;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int COUPON_LENGTH = 10; // longueur du code de coupon
    private static final Random RANDOM = new SecureRandom();

    public int getGetGutScheinWert() {
        return getGutScheinWert;
    }

    public void setGetGutScheinWert(int getGutScheinWert) {
        this.getGutScheinWert = getGutScheinWert;
    }

    public String getGutScheinCOde() {
        gutScheinCOde = generateCouponCode();
        return this.gutScheinCOde;
    }


    public static String generateCouponCode() {
        StringBuilder couponCode = new StringBuilder(COUPON_LENGTH);
        for (int i = 0; i < COUPON_LENGTH; i++) {
            couponCode.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return couponCode.toString();
    }
}
