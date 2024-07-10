package de.uniba.wiai.dsg.ajp.assignment3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {
    private Customer customerWithGutSchein;
    private Customer customerWithoutGuschein ;
    private Gutschein gutschein;
    private Rental rentalWithdaysEqual1;
    private  Rental rentalWitdaysEqual3;
    private List<Customer>customerList = new ArrayList<>();
    private static String  result ;

    @BeforeEach
    void setUp() throws MovieExption {
        gutschein = new Gutschein();

        gutschein.setGetGutScheinWert(10);
        customerWithGutSchein = createCustomer("kenne",gutschein);
        customerWithoutGuschein = createCustomer("yann",null);
        Movie movieWithImageK4 = new Movie("BlackPathere",3,ImageQuality.K4);
        Movie movieWithImageHD = new Movie ("Dejavue",2,ImageQuality.HD);
        rentalWitdaysEqual3 = new Rental();
        rentalWithdaysEqual1 = new Rental();
        rentalWitdaysEqual3.setDaysRented(3);
        rentalWitdaysEqual3.setMovie(movieWithImageK4);
        rentalWithdaysEqual1.setDaysRented(1);
        rentalWithdaysEqual1.setMovie(movieWithImageHD);

    }


    @AfterEach
    void tearDown() {
      customerWithGutSchein.getRentals().clear();
    }

    @Test
    public void testStatementGeneratesCorrectOutput() throws MovieExption, RentalException {
        customerWithGutSchein.getRentals().add(rentalWitdaysEqual3);
        customerWithGutSchein.getRentals().add(rentalWithdaysEqual1);

        // when
        result = customerWithGutSchein.statement();
        String expectedOutput = expectedOutputText();

        // given
        assertEquals(expectedOutput, result);
    }
    @Test
    public void testModifyGutscheinWertUpdatesStatementCorrectly() throws MovieExption, RentalException {
        customerWithGutSchein.getRentals().add(rentalWitdaysEqual3);
        customerWithGutSchein.getRentals().add(rentalWithdaysEqual1);
        gutschein.setGetGutScheinWert(5);

        // when
        String result = customerWithGutSchein.statement();

        // then
        String currentExpectedOutput = expectedOutputTextWithModifyCouponValue();
        assertEquals(currentExpectedOutput, result);
    }

    @Test
    public void testHtmlStatementGeneratesCorrectOutput() throws RentalException, MovieExption {
        // given
        customerWithGutSchein.getRentals().add(rentalWitdaysEqual3);
        customerWithGutSchein.getRentals().add(rentalWithdaysEqual1);

        // when
        String actual = customerWithGutSchein.htmlStatement();

        // then
        String expectedOutputHtml = expectedOutputHtml(customerWithGutSchein.getName());
        assertEquals(expectedOutputHtml, actual);
    }



    public Customer createCustomer(String name, Gutschein gutschein){
        return new Customer(name,gutschein);
    }

    public String expectedOutputText(){
        return  "Rental Record for kenne\n" +
                "\tBlackPathere\tK4\t5.5\n" +
                "\tDejavue\tHD\t3.5\n" +
                "total amount before Coupon\t9.0\n"+
                " gutscheinWert und gutscheinCode" + "\t " + customerWithGutSchein.currentCouponCode+ "\t" + "-" + "10\n" +
                "Amount owed is 0.0\n" +
                "You earned 5 frequent renter points";


    }

    public String expectedOutputTextWithModifyCouponValue(){

        return  "Rental Record for kenne\n" +
                "\tBlackPathere\tK4\t5.5\n" +
                "\tDejavue\tHD\t3.5\n" +
                "total amount before Coupon\t9.0\n"+
                " gutscheinWert und gutscheinCode" + "\t " + customerWithGutSchein.currentCouponCode+ "\t" + "-" + "5\n" +
                "Amount owed is 4.0\n" +
                "You earned 5 frequent renter points";

    }

    public String expectedOutputHtml(String customerName){
        // expected
        String expected = "<h1>Rentals for <em>" + customerName + "</em></h1>\n";
        expected += "<p>BlackPathere: K4: 5.5<br>\n";
        expected += "Dejavue: HD: 3.5<br>\n";
        expected += "</p>\n";
        expected += " <p>total amount Before Coupon <em>9.0</em></p>\n";
        expected += " <p>guscheinWert <em>" + customerWithGutSchein.currentCouponCode + "</em>" + "-" + "10</p>\n";
        expected += "<p>You owe <em>0.0</em></p>\n";
        expected += "<p>On this rental you earned <em>" + 5 + "</em> frequent renter points</p>";

        return expected;


    }


}
