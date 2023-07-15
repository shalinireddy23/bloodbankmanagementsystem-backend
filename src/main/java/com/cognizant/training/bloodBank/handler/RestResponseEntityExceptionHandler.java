package com.cognizant.training.bloodBank.handler;


import com.cognizant.training.bloodBank.exception.*;
import com.cognizant.training.bloodBank.handlerEntity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BloodBankException.class)
    public ResponseEntity<ErrorMessage> userNotCreatedException(BloodBankException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessage);

    }

    @ExceptionHandler(BloodBankNotFoundException.class)
    public ResponseEntity<ErrorMessage> userNotFoundException(BloodBankNotFoundException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorMessage);

    }

    @ExceptionHandler(BloodDetailsException.class)
    public ResponseEntity<ErrorMessage> orderNotProcessedException(BloodDetailsException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessage);

    }

    @ExceptionHandler(BloodDetailsNotFoundException.class)
    public ResponseEntity<ErrorMessage> cartNotUpdatedException(BloodDetailsNotFoundException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessage);

    }

    @ExceptionHandler(DonorException.class)
    public ResponseEntity<ErrorMessage> cartNotFoundException(DonorException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorMessage);

    }

    @ExceptionHandler(DonorNotFoundException.class)
    public ResponseEntity<ErrorMessage> cartNotFoundException(DonorNotFoundException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorMessage);

    }
    
    @ExceptionHandler(InvalidBloodBankException.class)
    public ResponseEntity<ErrorMessage> cartNotAddedException(InvalidBloodBankException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessage);

    }

    @ExceptionHandler(InvalidBloodDetailsException.class)
    public ResponseEntity<ErrorMessage> medicineNotCreatedException(InvalidBloodDetailsException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessage);

    }

    @ExceptionHandler(InvalidDonorException.class)
    public ResponseEntity<ErrorMessage> medicineNotFoundException(InvalidDonorException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorMessage);

    }

    @ExceptionHandler(InvalidSeekerException.class)
    public ResponseEntity<ErrorMessage> orderNotFoundException(InvalidSeekerException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorMessage);

    }
    
    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<ErrorMessage> orderNotFoundException(LocationNotFoundException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorMessage);

    }
    
    @ExceptionHandler(SeekerException.class)
    public ResponseEntity<ErrorMessage> orderNotFoundException(SeekerException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorMessage);

    }
    
    @ExceptionHandler(SeekerNotFoundException.class)
    public ResponseEntity<ErrorMessage> orderNotFoundException(SeekerNotFoundException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorMessage);

    }

}