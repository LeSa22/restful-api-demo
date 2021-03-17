package com.spring.error;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.zalando.problem.Status;
import org.zalando.problem.StatusType;
import org.zalando.problem.ThrowableProblem;

public class ValidationErrorException extends ThrowableProblem {

    /**
     * Constant serialVersionUID.
     */
    private static final long serialVersionUID = -5386968192167592489L;

    /**
     * type.
     */
    private final URI type;

    /**
     * entity name.
     */
    private final String entityName;

    /**
     * errors.
     */
    private final List<ValidateError> errors;

    /**
     * status error.
     */
    private final Status statusError;

    /**
     * 
     * @param entityName
     * @param statusError
     * @param errors
     */
    public ValidationErrorException(String entityName, Status statusError, List<ValidateError> errors) {
        this(ErrorConstants.DEFAULT_TYPE, statusError, entityName, errors);
    }
    
    /**
     * 
     * @param entityName
     * @param statusError
     * @param error
     */
    public ValidationErrorException(String entityName, Status statusError, ValidateError error) {
        this(ErrorConstants.DEFAULT_TYPE, statusError, entityName, Arrays.asList(error));
    }
    
    public ValidationErrorException(URI type, Status statusError, String entityName, List<ValidateError> errors) {
        this.type = type;
        this.entityName = entityName;
        this.errors = Collections.unmodifiableList(errors);
        this.statusError = statusError;
    }
	/**
     * Lay type.
     * @return type
     */
    @Override
    public URI getType() {
        return type;
    }

    /**
     * Lay title.
     * @return title
     */
    @Override
    public String getTitle() {
        return "Validation Violation";
    }

    /**
     * Lay entity name.
     * @return entity name
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * Lay errors.
     * @return errors
     */
    public List<ValidateError> getErrors() {
        return errors;
    }

    /**
     * Lay status.
     * @return status
     */
    @Override
    public StatusType getStatus() {
        return this.statusError;
    }
}
