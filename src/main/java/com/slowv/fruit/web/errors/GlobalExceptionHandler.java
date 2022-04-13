package com.slowv.fruit.web.errors;

import com.slowv.fruit.component.MessageTranslator;
import com.slowv.fruit.domain.rest.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.MethodNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Slf4j
@AllArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String VALIDATION_ERROR_KEY = "error.validation";

    private final MessageTranslator messageTranslator;

    @Override
    @NonNull
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          @NonNull HttpHeaders headers,
                                                                          @NonNull HttpStatus status,
                                                                          @NonNull WebRequest request) {
        String message = ex.getParameterName() + messageTranslator.getMessage("error.param.missing");
        return buildResponseEntity(BAD_REQUEST, new ApiError(message, BAD_REQUEST.value()));
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     @NonNull HttpHeaders headers,
                                                                     @NonNull HttpStatus status,
                                                                     @NonNull WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(messageTranslator.getMessage("error.media.type.not.support"));
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        return buildResponseEntity(UNSUPPORTED_MEDIA_TYPE,
                new ApiError(builder.substring(0, builder.length() - 2), UNSUPPORTED_MEDIA_TYPE.value()));
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatus status,
                                                                  @NonNull WebRequest request) {
        ApiError apiError = new ApiError(messageTranslator.getMessage(VALIDATION_ERROR_KEY), BAD_REQUEST.value());
        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        apiError.addValidationError(ex.getBindingResult().getGlobalErrors());

        return buildResponseEntity(BAD_REQUEST, apiError);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleTypeMismatch(@NonNull TypeMismatchException ex,
                                                        @NonNull HttpHeaders headers,
                                                        @NonNull HttpStatus status,
                                                        @NonNull WebRequest request) {
        ApiError error = new ApiError(ex.getMessage(), BAD_REQUEST.value());
        return buildResponseEntity(BAD_REQUEST, error);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleBindException(@NonNull BindException ex,
                                                         @NonNull HttpHeaders headers,
                                                         @NonNull HttpStatus status,
                                                         @NonNull WebRequest request) {
        ApiError apiError = new ApiError(messageTranslator.getMessage(VALIDATION_ERROR_KEY), BAD_REQUEST.value());
        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        apiError.addValidationError(ex.getBindingResult().getGlobalErrors());

        return buildResponseEntity(BAD_REQUEST, apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        ApiError apiError = new ApiError(messageTranslator.getMessage(VALIDATION_ERROR_KEY), BAD_REQUEST.value());
        apiError.addValidationErrors(ex.getConstraintViolations());
        return buildResponseEntity(BAD_REQUEST, apiError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        String messageKey = ObjectUtils.isEmpty(ex.getMessage()) ? "error.data.not.found" : ex.getMessage();
        ApiError error = new ApiError(messageTranslator.getMessage(messageKey), NOT_FOUND.value());
        return buildResponseEntity(NOT_FOUND, error);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleHttpMessageNotReadable(@NonNull HttpMessageNotReadableException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatus status,
                                                                  @NonNull WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        log.info("{} to {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());
        String message = messageTranslator.getMessage("error.malformed.json");
        return buildResponseEntity(BAD_REQUEST, new ApiError(message, BAD_REQUEST.value()));
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleHttpMessageNotWritable(@NonNull HttpMessageNotWritableException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatus status,
                                                                  @NonNull WebRequest request) {
        String message = messageTranslator.getMessage("error.writing.json");
        return buildResponseEntity(INTERNAL_SERVER_ERROR, new ApiError(message, INTERNAL_SERVER_ERROR.value()));
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleNoHandlerFoundException(@NonNull NoHandlerFoundException ex,
                                                                   @NonNull HttpHeaders headers,
                                                                   @NonNull HttpStatus status,
                                                                   @NonNull WebRequest request) {
        String message = String.format(messageTranslator.getMessage("error.no.handler.method"), ex.getHttpMethod(), ex.getRequestURL());
        ApiError error = new ApiError(message, BAD_REQUEST.value());
        return buildResponseEntity(BAD_REQUEST, error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            return buildResponseEntity(CONFLICT, new ApiError(messageTranslator.getMessage("error.database"), CONFLICT.value()));
        }
        return buildResponseEntity(INTERNAL_SERVER_ERROR, new ApiError(messageTranslator.getMessage("error.internal.server"), INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = String.format(messageTranslator.getMessage("error.method.argument.type.mismatch"),
                ex.getName(), ex.getValue(), Objects.requireNonNull(ex.getRequiredType()).getSimpleName());
        ApiError error = new ApiError(message, BAD_REQUEST.value());
        return buildResponseEntity(BAD_REQUEST, error);
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleBusinessService(BusinessException ex) {
        log.error("Business Exception: ", ex);
        String message = messageTranslator.getMessage(ex.getMessageKey());
        ApiError error = new ApiError(message, ex.getErrorCode());
        return buildResponseEntity(CONFLICT, error);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        ApiError error = new ApiError(ex.getMessage(), FORBIDDEN.value());
        return buildResponseEntity(FORBIDDEN, error);
    }

    @ResponseStatus(METHOD_NOT_ALLOWED)
    @ExceptionHandler(MethodNotSupportedException.class)
    protected ResponseEntity<Object> handleMethodNotSupportedException(MethodNotSupportedException ex) {
        ApiError error = new ApiError(ex.getMessage(), METHOD_NOT_ALLOWED.value());
        return buildResponseEntity(METHOD_NOT_ALLOWED, error);
    }

    @ExceptionHandler
    protected ResponseEntity<Object> handleInternalException(Exception ex) {
        log.error("Internal Exception: ", ex);
        ApiError error = new ApiError(ex.getMessage(), INTERNAL_SERVER_ERROR.value());
        return buildResponseEntity(INTERNAL_SERVER_ERROR, error);
    }

    private ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, ApiError error) {
        return new ResponseEntity<>(Response.fail(error), httpStatus);
    }
}
