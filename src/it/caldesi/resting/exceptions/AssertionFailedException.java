package it.caldesi.resting.exceptions;

/**
 * @author Domenico Rosario Caldesi, <d.r.caldesi@gmail.com>
 */
public class AssertionFailedException extends Exception {

    public AssertionFailedException(String message){
        super(message);
    }

}