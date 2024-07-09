package de.uniba.wiai.dsg.ajp.assignment3;

import java.util.LinkedList;
import java.util.List;

public class Customer {

	private String name;
	private double currentResult ;
	public  String currentCouponCode;

	private List<Rental> rentals = new LinkedList<Rental>();

	private Gutschein gutschein;

	public Customer(String name,Gutschein gutschein) {
		super();
		this.name = name;
		this.gutschein = gutschein;
	}

	public Gutschein getGutschein() {
		return gutschein;
	}

	public void setGutschein(Gutschein gutschein) {
		this.gutschein = gutschein;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public String statement() throws MovieExption, RentalException {
		String result = "Rental Record for " + getName() + "\n";

		for (Rental each : this.rentals) {
			// show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t"
					+ each.getMovie().getImageQuality() + "\t"+String.valueOf(each.getCharge()) + "\n";
		}

		// add footer lines
		if(cheickGutschein() ) {
			if(gutschein.getGetGutScheinWert() < getTotalCharge()) {
				result +="total amount before Coupon" + "\t" + getResultBeforeCoupon(getTotalCharge() + gutschein.getGetGutScheinWert()) + "\n";
				result += " gutscheinWert und gutscheinCode" + "\t " + gutschein.getGutScheinCOde() + "\t"
						+ "-" + gutschein.getGetGutScheinWert() + "\n";
				result += getResult();
			}else{
				getTotalCharge();
				currentCouponCode = gutschein.getGutScheinCOde();
				result += "total amount before Coupon" + "\t" + currentResult + "\n";
				result += " gutscheinWert und gutscheinCode" + "\t " + currentCouponCode+ "\t"
						+ "-" + gutschein.getGetGutScheinWert() + "\n";
				result += getResult();
			}
		}else {
			result += getResult();
		}
		return result;
	}


	public String htmlStatement() throws MovieExption, RentalException {
		String result = "<h1>Rentals for <em>" + getName() + "</em></h1>\n<p>";

		for (Rental each : this.rentals) {
			// show figures for each rental
			result += each.getMovie().getTitle() + ": "+ each.getMovie().getImageQuality() +": "
					+ String.valueOf(each.getCharge()) + "<br>\n";
		}

		result += "</p>\n";

		// add footer lines
		if(cheickGutschein()) {
			if(gutschein.getGetGutScheinWert() < getTotalCharge()) {
				result += " <p>tolat amount Before Coupon <em>" + getResultBeforeCoupon(getTotalCharge() + gutschein.getGetGutScheinWert()) + "</em>" + "</p>\n";
				result += " <p>guscheinWert <em>" + gutschein.getGutScheinCOde() + "</em>" + "-" + gutschein.getGetGutScheinWert() + "</p>\n";
				result += "<p>You owe <em>" + String.valueOf(getTotalCharge())
						+ "</em></p>\n";
				result += "<p>On this rental you earned <em>"
						+ String.valueOf(getTotalFrequentRenterPoints())
						+ "</em> frequent renter points</p>";
			}else{
				getTotalCharge();
				currentCouponCode = gutschein.getGutScheinCOde();
				result += " <p>total amount Before Coupon <em>" + currentResult+ "</em>" + "</p>\n";
				result += " <p>guscheinWert <em>" + currentCouponCode + "</em>" + "-" + gutschein.getGetGutScheinWert() + "</p>\n";
				result += "<p>You owe <em>" + String.valueOf(getTotalCharge())
						+ "</em></p>\n";
				result += "<p>On this rental you earned <em>"
						+ String.valueOf(getTotalFrequentRenterPoints())
						+ "</em> frequent renter points</p>";

			}
		}
		else{
			result += "<p>You owe <em>" + String.valueOf(getTotalCharge())
					+ "</em></p>\n";
			result += "<p>On this rental you earned <em>"
					+ String.valueOf(getTotalFrequentRenterPoints())
					+ "</em> frequent renter points</p>";
		}
		return result;
	}

	double getTotalCharge() throws MovieExption, RentalException {
		double result = 0;

		for (Rental each : rentals) {
			result += each.getCharge();
		}
		currentResult = result;
		if(cheickGutschein()){
			if (result <= gutschein.getGetGutScheinWert()){
				result = 0;
			}
			else {
				result = result - gutschein.getGetGutScheinWert();
			}
		}
		return result;
	}

	int getTotalFrequentRenterPoints() throws MovieExption, RentalException {
		int result = 0;

		for (Rental each : rentals) {
			result += each.getFrequentRenterPoints();
		}
		if(cheickGutschein()){
		 result = result + 3;
	    }

		return result;
	}

	boolean cheickGutschein (){

		if(gutschein != null){
			return  true;
		}
		return  false;
	}

	double getResultBeforeCoupon(double totalCharge){

		return totalCharge ;
	}

	String getResult() throws RentalException, MovieExption {
		String result = "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
		result += "You earned " + String.valueOf(getTotalFrequentRenterPoints())
				+ " frequent renter points";
		return result;
	}
}
