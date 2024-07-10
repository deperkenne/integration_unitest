package de.uniba.wiai.dsg.ajp.assignment3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ChildrenTest {
    private Price childrenPrice;
  @BeforeEach
  public void setUp(){
      childrenPrice = new ChildrensPrice();


  }

    @ParameterizedTest
    @MethodSource("validRentalDaysAndExpectedCharges")
    public void testChildrenPriceChargeWithValidDays(int rentalDays, double expectedCharge) throws RentalException, MovieExption {
        // when
        double charge = childrenPrice.getCharge(rentalDays);

        // then
        assertEquals(expectedCharge, charge);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -2})
    public void testChildrenPriceChargeWithInvalidDays(int rentalDays) {
        // when and then
        assertThrows(MovieExption.class, () -> childrenPrice.getCharge(rentalDays));
    }


    @Test
    public void testGetChildrenPriceCode() {
        // when
        int priceCode = childrenPrice.getPriceCode();

        // then
        assertEquals(2, priceCode);
    }

    @Test
    public void testGetChildrenPriceCodeMismatch() {
        int unexpectedPriceCode = 3;
        // when
        int priceCode = childrenPrice.getPriceCode();

        // then
        assertTrue(unexpectedPriceCode != priceCode);
    }


    public static List<Arguments> argumentsList(){
        return List.of(Arguments.of(2,1.5),Arguments.of(5,4.5));

    }

}
