package de.uniba.wiai.dsg.ajp.assignment3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class CustomerTest {

    private Customer sut;
    private Gutschein gutschein;
    private Gutschein gutschein1;
    private  Movie movie1;
    private Rental rental1 ;
    private Rental rental2 ;
    private Customer sut1;
    private Customer sut2;
    @BeforeEach
    public void setUp() throws MovieExption, RentalException {

         movie1 = mock(Movie.class);
         rental1 = mock(Rental.class);
         rental2 = mock(Rental.class);
         gutschein = mock(Gutschein.class);
         gutschein1 = mock(Gutschein.class);
         sut = new Customer("kenne",gutschein1);
         sut1 = new Customer("yann",gutschein);
         sut2 = new Customer("herve",null);
        //given
        setUpCustomer();

    }




    @Test
    public void testCalculateFrequentRenterPointsWithoutCoupon() throws MovieExption, RentalException {
        // mes attentes Mock configuration
        when(sut.getRentals().getFirst().getFrequentRenterPoints()).thenReturn(2);
        when(sut.getRentals().get(1).getFrequentRenterPoints()).thenReturn(3);

        // when
        int total = sut.getTotalFrequentRenterPoints();

        // then
        assertEquals(8, total);
    }


    @Test
    public void getFrequentPointCalledCorrectlyWithGuschein() throws MovieExption, RentalException {
        //given
        // mes attentes Mock configuration
        when(sut1.getRentals().getFirst().getFrequentRenterPoints()).thenReturn(2);
        when(sut1.getRentals().get(1).getFrequentRenterPoints()).thenReturn(3);

        // when
        int total = sut1.getTotalFrequentRenterPoints();

        // then

        assertEquals(8,total);



    }
    @Test
    public void testTotalChargeIsZeroWithCouponCoveringTotal() throws MovieExption, RentalException {
        // mes attentes Mock configuration
        when(sut1.getRentals().getFirst().getCharge()).thenReturn(2.0);
        when(sut1.getRentals().get(1).getCharge()).thenReturn(3.0);

        // when
        double total = sut1.getTotalCharge();

        // then
        assertEquals(0.0, total);
        assertEquals("BlackPanthere", sut1.getRentals().getFirst().getMovie().getTitle());
    }


    @Test
    public void testCalculateTotalChargeWithoutCoupon() throws RentalException, MovieExption {
        // mes attentes Mock configuration
        when(sut2.getRentals().getFirst().getCharge()).thenReturn(2.0);
        when(sut2.getRentals().get(1).getCharge()).thenReturn(3.0);

        // when
        double total = sut2.getTotalCharge();

        // then
        assertEquals(5.0, total);
    }


    @Test
    public void testCalculateTotalCharge() throws RentalException, MovieExption {
        // mes attentes Mock configuration
        when(sut.getRentals().getFirst().getCharge()).thenReturn(2.0);
        when(sut.getRentals().get(1).getCharge()).thenReturn(3.0);

        // when
        double total = sut.getTotalCharge();

        // then
        assertEquals(2.0, total);
        assertEquals(movie1, sut.getRentals().getFirst().getMovie());
    }


    public void setUpCustomer() throws MovieExption, RentalException {
        setUpGutschein();

        List <Rental> currentrentalList = rentalList();
        sut.setRentals(currentrentalList);
        sut1.setRentals(currentrentalList);
        sut2.setRentals(currentrentalList);


    }


    public List<Rental> rentalList() throws MovieExption, RentalException {
        List<Rental> currentListRental = new ArrayList<>();
        rental1 = mock(Rental.class);
        rental2 = mock(Rental.class);
        currentListRental.add(rental1);
        currentListRental.add(rental2);

        mockMovie();

        return currentListRental;


    }

    public void mockMovie() throws MovieExption, RentalException {
        // mes attentes notez que setmethode est une fonction non fonctionel sur le Mock car elle ne retourne rien
        // donc lorsqu'une est un mock le setter ne doit pas s'appliquer sur elle
        // le secteur s'apülique sur la classe reelle
       given(rental1.getMovie()).willReturn(movie1);
       given(rental2.getMovie()).willReturn(movie1);

        given(movie1.getTitle()).willReturn("BlackPanthere");
        given(movie1.getImageQuality()).willReturn(ImageQuality.HD);


    }

    public void setUpGutschein(){

        given(gutschein.getGetGutScheinWert()).willReturn(10);
        given(gutschein.getGutScheinCOde()).willReturn("1234555");
        given(gutschein1.getGetGutScheinWert()).willReturn(3);
        given(gutschein1.getGutScheinCOde()).willReturn("1234556");


    }

}
