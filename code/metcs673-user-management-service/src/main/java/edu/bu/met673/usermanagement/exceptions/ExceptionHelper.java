package edu.bu.met673.usermanagement.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public final class ExceptionHelper {
	
	private ExceptionHelper() {}

    public static String getStackTrace(Throwable throwable){
        if(throwable !=null){
            Writer result = new StringWriter();
            PrintWriter printWriter = new PrintWriter(result);
            throwable.printStackTrace(printWriter);
            return  result.toString();
        }
        return "";
    }

    public static String getException(Throwable throwable){
        if(throwable !=null){
            return throwable.getClass().getName();
        }
        return "";
    }
}
