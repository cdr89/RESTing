package it.caldesi.resting.exceptions;

/**
 * @author Domenico Rosario Caldesi, <d.r.caldesi@gmail.com>
 */
public class AssertionFailedException extends Error {

    public AssertionFailedException(String message){
        super(message);
    }

}