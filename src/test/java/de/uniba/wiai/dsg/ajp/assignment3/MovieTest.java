package de.uniba.wiai.dsg.ajp.assignment3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class MovieTest {


   private Price pricecodeMock;
   private Movie movie;
    @BeforeEach
    public void setUp() throws MovieExption {
      pricecodeMock  = mock(Price.class);
      movie = new Movie("Bal",pricecodeMock,ImageQuality.HD);

    }


    @Test
    public void getCorrectPriceCodeMovie(){

        // configure  Mock for getPriceCode lorsqu'on l'appel il renvoie toujours 1
        given(pricecodeMock.getPriceCode()).willReturn(1);

        // when SUT
        int newpriceCode = movie.getPriceCode(); //

        // then
        assertEquals(1,newpriceCode);

    }



    @ParameterizedTest
    @MethodSource("getChargeList")
    public void getChargeCalledCorrectly(ImageQuality imageQuality,double configValue,int daysRented,double expected) throws MovieExption, RentalException {
        movie = new Movie("Bal",pricecodeMock,imageQuality);
        //configuration du mock pour methode getCharge()
        when(pricecodeMock.getCharge(-1)).thenThrow(new IllegalArgumentException());
        when(pricecodeMock.getCharge(daysRented)).thenReturn(configValue);

        //when SUT
        Executable executable = ()-> movie.getCharge(-1);
        double getCharge = movie.getCharge(daysRented);

        //then
        assertThrows(IllegalArgumentException.class,executable);
        verify(pricecodeMock).getCharge(daysRented);
        assertEquals(expected,getCharge);


    }



    @ParameterizedTest
    @ValueSource(ints={0,-1,-2})

    public void getCorrectFrequentPoint(int value) throws MovieExption, RentalException {
        //configuration du mock
        when(pricecodeMock.getFrequentRenterPoints(value)).thenThrow(new IllegalArgumentException());
       when(pricecodeMock.getFrequentRenterPoints(2)).thenReturn(1);

        //when and SUT
        Executable executable = () -> movie.getFrequentRenterPoints(value);
        int frequentRenterPoints = movie.getFrequentRenterPoints(2);

        //then
        assertEquals(1,frequentRenterPoints);
        assertThrows(IllegalArgumentException.class,executable);

    }


    public static  List<Arguments> movie(){

        return List.of(Arguments.of("Black",1,ImageQuality.HD),
                Arguments.of("Dejavue",2,ImageQuality.SD)
            );

    }

    public static List<Arguments> getChargeList(){
        return List.of(Arguments.of(ImageQuality.SD,2.0,2,2),
                Arguments.of(ImageQuality.HD,3.5,2,5.5),
                Arguments.of(ImageQuality.K4,4.0,2,8.0));
    }


}
