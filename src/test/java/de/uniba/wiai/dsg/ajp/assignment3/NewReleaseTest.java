package de.uniba.wiai.dsg.ajp.assignment3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NewReleaseTest {

    private double currentValue;
    private  Price newReleasePrice;


    @BeforeEach
    public void setUp(){
        currentValue = 0;
        // given
        newReleasePrice= new NewReleasePrice();
    }

    @ParameterizedTest
    @ValueSource(ints ={1,2,3})
    public void getChargeCalledCorrectly(int value) throws RentalException, MovieExption {
        //when
        changeValue(value);
        double charge =  newReleasePrice.getCharge(value);

        //then
        assertEquals(currentValue,charge);

    }

    @ParameterizedTest
    @ValueSource(ints={0,-1,-2})
    public void getChargeNoCalledCorrectly(int value){

        //when and then
        assertThrows(MovieExption.class,()->newReleasePrice.getCharge(value));

    }


    @Test
    public void getPriceCodeReturnCorrectPrice(){

        //when
        int priceCode =  newReleasePrice.getPriceCode();

        //then
        assertEquals(1,priceCode);


    }

    @ParameterizedTest
    @ValueSource(ints ={1,2,3})
    public void getFrenquentPointCalledCorrectly(int value) throws RentalException, MovieExption {

        //when
        changeCurrentValue(value);
        double frequentPoint = newReleasePrice.getFrequentRenterPoints(value);

        // then
        assertEquals(currentValue,frequentPoint);
    }

    @ParameterizedTest
    @ValueSource(ints={0,-1,-2})
    public void getFrequentPointNoCalledCorrectly(int value){

        //when and then
        assertThrows(MovieExption.class,()->newReleasePrice.getFrequentRenterPoints(value));

    }

    public void changeCurrentValue(int value) {
        switch (value) {
            case 1:
                currentValue = 1.0;
                break;
            case 2:
                currentValue = 2.0;
                break;
            case 3:
                currentValue = 2.0;
        }
    }
                public void changeValue(int value){
        switch (value){
            case 1:
                currentValue = 3.0;
                break;
            case 2:
                currentValue = 6.0;
                break;
            case 3:
                currentValue = 9.0;

        }
    }

}
