package de.uniba.wiai.dsg.ajp.assignment3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LowBudGetTest {

    private double currentValue;
    private  Price lowBudgetPrice;


    @BeforeEach
    public void setUp(){
        currentValue = 0;
        // given
        lowBudgetPrice = new LowBudGet();
    }

    @ParameterizedTest
    @ValueSource(ints ={1,2,3})
    public void getChargeCalledCorrectly(int value) throws RentalException, MovieExption {
        //when
        changeValue(value);
        double charge = lowBudgetPrice.getCharge(value);

        //then
        assertEquals(currentValue,charge);

    }

    @Test
    public void getPriceCodeReturnCorrectPrice(){

        //when
        int priceCode = lowBudgetPrice.getPriceCode();

        //then
        assertEquals(3,priceCode);


    }

    public void changeValue(int value){
        switch (value){
            case 1:
                currentValue = 0.5;
                break;
            case 2:
                currentValue = 1.0;
                break;
            case 3:
                currentValue = 1.5;
        }
    }



}
