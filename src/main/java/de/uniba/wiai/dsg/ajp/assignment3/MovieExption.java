package de.uniba.wiai.dsg.ajp.assignment3;

public class MovieExption extends  Exception{
    String message;
    Throwable throwable;
    Boolean test = false;
    boolean test1 = false;
    public MovieExption(String message){
        super(message);
        this.message = message;
    }

    public MovieExption(String message,Throwable throwable,boolean test,boolean test1){
        super(message,throwable,test,test1);
        this.message = message;
        this.test = test;
        this.throwable = throwable;
        this.test1 = test1;
    }
}
