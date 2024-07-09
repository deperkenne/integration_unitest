package de.uniba.wiai.dsg.ajp.assignment3;

public class Rental {

	private int daysRented;
	private Movie movie;
	public  CustomerException.ValidationInput validationInput ;

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) throws MovieExption {

            if(movie == null){
				throw new MovieExption("error inpur");

			}
		    validationInput = new CustomerException.ValidationInput(movie) ;
			this.movie = movie;




	}

	public void initialiseValidationInput(Movie movie){
		validationInput = new CustomerException.ValidationInput(movie) ;
	}

	public int getDaysRented() throws MovieExption {
		validationInput.validate();
		return daysRented;
	}

	public void setDaysRented(int daysRented) throws MovieExption {

		this.daysRented = daysRented;
	}

	public double getCharge() throws RentalException, MovieExption {
		validationInput.validate();
		return movie.getCharge(daysRented);
	}

	public int getFrequentRenterPoints() throws RentalException, MovieExption {
		    validationInput.validate();
			return movie.getFrequentRenterPoints(daysRented);

	}



}
