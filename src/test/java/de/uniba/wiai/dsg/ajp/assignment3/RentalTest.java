package de.uniba.wiai.dsg.ajp.assignment3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class RentalTest {
    private Movie mockMovie;
    private Rental sut;
    private CustomerException.ValidationInput validationInputMock;

    @BeforeEach
    public void setUp(){
        mockMovie = mock(Movie.class);
        validationInputMock = mock(CustomerException.ValidationInput.class);
        //  given
        sut = new Rental();
    }

    @Test
    public void getMovieCallCorrectly() throws MovieExption, RentalException {
        //  given
        sut = new Rental();
        movieSetUp();
        sut.setMovie(mockMovie);

        // when SUT and then
        assertEquals(mockMovie,sut.getMovie());

    }



    @Test
    public void getMovieNotCallCorrectly() throws MovieExption {
        mockMovie = null;
        // when SUT and then
        assertThrows(MovieExption.class , ()-> sut.setMovie(mockMovie));

    }

    @Test
    public void getDaysRentedCalledCorrectly() throws MovieExption {

        //when SUt
        sut.setDaysRented(1);
        int days = sut.getDaysRented();

        //then

        assertEquals(1 ,days);
    }


    @Test
    public void getChargeCalledCorrectly() throws RentalException, MovieExption {


        sut.setDaysRented(1);
        movieSetUp();
        sut.setMovie(mockMovie);

        //when SUt

        double charge = sut.getCharge();

        // then
        verify(mockMovie).getCharge(1);
        assertEquals(3,charge);

    }

    @Test
    public void getFrequentPointRentedCalledCorrectly() throws RentalException, MovieExption {

        sut.setDaysRented(1);
        movieSetUp();
        sut.setMovie(mockMovie);

        //when SUT

        double charge = sut.getFrequentRenterPoints();

        // then
        verify(mockMovie).getFrequentRenterPoints(1);
        assertEquals(2,charge);
    }




    public void movieSetUp() throws RentalException, MovieExption {
        when(mockMovie.getTitle()).thenReturn("BlackPanthere");
        when(mockMovie.getImageQuality()).thenReturn(ImageQuality.HD);
        when(mockMovie.getPriceCode()).thenReturn(1);
        when(mockMovie.getCharge(sut.getDaysRented())).thenReturn(3.0);
        when(mockMovie.getFrequentRenterPoints(sut.getDaysRented())).thenReturn(2);

    }
/*

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) throws MovieExption {
        try {
            new ValidatorMovie(movie).validate();
            this.movie = movie;
        }catch (MovieExption e){
            throw new MovieExption("invalid input");
        }
    }

    public int getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }

    public double getCharge() throws RentalException, MovieExption {
        return movie.getCharge(daysRented);
    }

    public int getFrequentRenterPoints() throws RentalException {
        return movie.getFrequentRenterPoints(daysRented);

    }

    */


}
