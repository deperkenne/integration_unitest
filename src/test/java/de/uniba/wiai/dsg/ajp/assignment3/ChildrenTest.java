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
    @MethodSource("argumentsList")
    public void childrenTestCalledCorrectly(int value,double expected) throws RentalException, MovieExption {
    //when
    double charge = childrenPrice.getCharge(value);

    // then
    assertEquals(expected,charge);

    }

    @ParameterizedTest
    @ValueSource(ints={0,-1,-2})
    public void getChargeNoCalledCorrectly(int value){

      //when and then
        assertThrows(MovieExption.class,()->childrenPrice.getCharge(value));

    }



    @Test
    public void getPriceCalledCorrectly(){
      // when
        int value = childrenPrice.getPriceCode();

        // then
        assertEquals(2,value);

    }

    @Test
    public void getPriceCodeNotCalledCorrectly(){

      int expected = 3;
        // when
        int value = childrenPrice.getPriceCode();

        // then
        assertTrue(expected != value);


    }

    public static List<Arguments> argumentsList(){
        return List.of(Arguments.of(2,1.5),Arguments.of(5,4.5));

    }

}
