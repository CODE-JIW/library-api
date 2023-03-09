package com.example.library.api.exceptiohandle;

import com.example.library.domian.expection.BusinessRuleException;
import com.example.library.domian.expection.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExpectionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){

        List<Field> fieldList = new ArrayList<Field>();

        for(ObjectError e: ex.getBindingResult().getAllErrors()){
            String name = ((FieldError)e).getField();
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());

            fieldList.add( new Field(name, message));
        }
        Error error = new Error();
        error.setStatus(status.value());
        error.setDateTime(OffsetDateTime.now());
        error.setTitle("Erro ao válidar os campos, por favor preencha os campos correntamente!!!");
        error.setFields(fieldList);

        return  handleExceptionInternal(ex, error, headers, status, request);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundExpection(BusinessRuleException ex, WebRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;

        Error error = new Error();
        error.setStatus(status.value());
        error.setDateTime(OffsetDateTime.now());
        error.setTitle(ex.getMessage());
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<Object> handleBusinessRoleExpection(BusinessRuleException ex, WebRequest request){

        HttpStatus status = HttpStatus.BAD_REQUEST;

        Error error = new Error();
        error.setStatus(status.value());
        error.setDateTime(OffsetDateTime.now());
        error.setTitle(ex.getMessage());
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }
}
