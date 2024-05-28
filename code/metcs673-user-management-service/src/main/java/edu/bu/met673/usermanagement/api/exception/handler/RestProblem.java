package edu.bu.met673.usermanagement.api.exception.handler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RestProblem {

    private HttpStatus httpStatus;
    private List<RestError> errors = new ArrayList<>();

    public RestProblem(){
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public RestProblem(HttpStatus httpStatus){
        this.httpStatus = httpStatus;
    }
}
