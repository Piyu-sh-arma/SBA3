package com.FSD.ITS.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidData extends RuntimeException {
    public InvalidData(List<String> msgs) {
     super(msgs.toString());
    }
    public InvalidData(String msg) {
        super(msg);
    }
}
