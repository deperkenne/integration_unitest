package de.uniba.wiai.dsg.ajp.assignment3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CouponTest {
    private Gutschein gutschein;
    @BeforeEach
    public void setUp(){

        gutschein = new Gutschein();

    }

    @Test
    public void getGuscheinCodeCalledCorrectly(){
        //when
        String code = gutschein.getGutScheinCOde();

        //then
        assertTrue(code.length() < 11);


    }

}
