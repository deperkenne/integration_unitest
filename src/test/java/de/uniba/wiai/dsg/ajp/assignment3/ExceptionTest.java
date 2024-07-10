package de.uniba.wiai.dsg.ajp.assignment3;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExceptionTest {

    private Movie movieMock;
    private CustomerException.ValidationInput sut;
    private Rental rentalMock;
    private Customer customerMock;

    @BeforeEach
    public void setUp(){
        rentalMock = mock(Rental.class);
        movieMock = mock(Movie.class);
        customerMock = mock(Customer.class);

    }


    @ParameterizedTest
    @MethodSource("argumentsList")
    public void testValidateThrowsExceptionForInvalidMovies(String title, ImageQuality imageQuality) throws MovieExption {
        movieSetUp(title, imageQuality);
        // SUT when
        assertThrows(MovieExption.class, () -> sut.validate());
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 0, -1})
    public void testValidateRentalThrowsExceptionForNullMovie(int param) throws MovieExption {
        String test = "black";
        ImageQuality imageQuality = ImageQuality.K4;
        setMovieNull(param, test, imageQuality);

        // when SUT and Then
        assertThrows(MovieExption.class, () -> sut.validateRental());
    }

    @Test
    public void testValidateCustomerInputThrowsExceptionForInvalidInput() {
        // given
        setUpCustomer();

        // when and then
        assertThrows(MovieExption.class, () -> sut.validateCustomerInput());
    }



    public void setUpCustomer(){
        // configure Mock
        when(customerMock.getName()).thenReturn("");

        // inject dependency
        sut = new CustomerException.ValidationInput(customerMock);

    }
    public void movieSetUp(String title,ImageQuality imageQuality) throws MovieExption {
        //configuration Mock
        when(movieMock.getTitle()).thenReturn(title);
        when(movieMock.getImageQuality()).thenReturn(imageQuality);
        when(rentalMock.getMovie()).thenReturn(movieMock);
        sut = new CustomerException.ValidationInput(movieMock);

    }

    public void setMovieNull(int param,String test,ImageQuality imageQuality) throws MovieExption {

        if(param > 0){
            movieMock = null;
        }
        else{
            movieSetUp(test,imageQuality);
        }
        when(rentalMock.getMovie()).thenReturn(movieMock);
        when(rentalMock.getDaysRented()).thenReturn(param);
        sut = new CustomerException.ValidationInput(rentalMock);


    }


    public static  List<Arguments> argumentsList(){

        return List.of(Arguments.of("",ImageQuality.HD)
                ,Arguments.of("black",null)
        );
    }



}
