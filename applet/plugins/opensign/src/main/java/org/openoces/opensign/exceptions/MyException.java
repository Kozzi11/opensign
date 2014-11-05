package org.openoces.opensign.exceptions;

/**
 * Created by kozzi on 5.4.14.
 */
public class MyException extends Exception {
    public String getCode() {
        return errCode;
    }

    String errCode = null;

    public MyException(String msg, String errCode) {
        super(msg);
        this.errCode = errCode;
    }


}
