package de.uniba.wiai.dsg.ajp.assignment3;


public class NewReleasePrice extends Price{

	@Override
	double getCharge(int daysRented) throws MovieExption{
		if(daysRented<=0){
			throw new MovieExption("invalid input");
		}
		return daysRented * 3;
	}

	@Override
	int getFrequentRenterPoints(int daysRented) throws MovieExption {
		    if(daysRented<=0){
				throw new MovieExption("invalid input");
			}
			if (daysRented > 1) {
				return 2;
			} else {
				return 1;
			}

	}
	@Override
	int getPriceCode() {
		return Movie.NEW_RELEASE;
	}
	
}
