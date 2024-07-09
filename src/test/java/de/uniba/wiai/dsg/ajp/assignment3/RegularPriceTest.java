package de.uniba.wiai.dsg.ajp.assignment3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RegularPriceTest {
    private double currentValue;
    private  Price regularPrice;


    @BeforeEach
    public void setUp(){
        currentValue = 0;
        // given
        regularPrice = new RegularPrice();
    }

    @ParameterizedTest
    @ValueSource(ints ={1,3})
    public void getChargeCalledCorrectly(int value) throws RentalException, MovieExption {
        //when
        changeValue(value);
        double charge = regularPrice.getCharge(value);

        //then
        assertEquals(currentValue,charge);

    }
    @ParameterizedTest
    @ValueSource(ints={0,-1,-2})
    public void getChargeNoCalledCorrectly(int value){

        //when and then
        assertThrows(MovieExption.class,()->regularPrice.getCharge(value));

    }

    @Test
    public void getPriceCodeReturnCorrectPrice(){

        //when
        int priceCode = regularPrice.getPriceCode();

        //then
        assertEquals(0,priceCode);


    }

    public void changeValue(int value){
        switch (value){
            case 1:
                currentValue = 2.0;
                break;
            case 3:
                currentValue = 3.5;
        }
    }


}
