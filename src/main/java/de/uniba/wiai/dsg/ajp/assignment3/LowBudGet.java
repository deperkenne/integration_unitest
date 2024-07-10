package de.uniba.wiai.dsg.ajp.assignment3;

public class LowBudGet extends Price{
    @Override
    double getCharge(int daysRented)throws MovieExption{
        double result = 0.5; // Coût pour le premier jour
          if(daysRented <= 0){
              throw  new MovieExption("invalid input");
          }
            if (daysRented >= 2) {
                result += 0.5; // Coût pour le deuxième jour
            }
            if (daysRented > 2) {
                result += (daysRented - 2) * 0.5; // Coût pour les jours supplémentaires
            }


        return result;
    }

    @Override
    int getPriceCode() {
        return Movie.LOW_BUDGET;
    }
}
