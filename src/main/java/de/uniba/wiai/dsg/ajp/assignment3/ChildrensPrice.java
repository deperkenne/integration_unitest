package de.uniba.wiai.dsg.ajp.assignment3;

public class ChildrensPrice extends Price {

	@Override
	double getCharge(int daysRented) throws MovieExption {
		double result = 1.5;

         if(daysRented <=0){
			 throw new MovieExption("daysrented must be greater than 0");
		 }
			if (daysRented > 3) {
				result += (daysRented - 3) * 1.5;
			}

		return result;
	}

	@Override
	int getPriceCode() {
		return Movie.CHILDRENS;
	}

}
