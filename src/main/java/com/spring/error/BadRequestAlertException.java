package com.spring.error;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

/**
 * Class BadRequestAlertException.
 */
public class BadRequestAlertException extends AbstractThrowableProblem {
    /**
     * Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * entity name.
     */
    private final String entityName;

    /**
     * error key.
     */
    private final String errorKey;

    /**
     * Instantiates a new bad request alert exception.
     * @param defaultMessage the default message
     * @param entityName     the entity name
     * @param errorKey       the error key
     */
    public BadRequestAlertException(String defaultMessage, String entityName, String errorKey) {
        this(ErrorConstants.DEFAULT_TYPE, defaultMessage, entityName, errorKey);
    }

    /**
     * Instantiates a new bad request alert exception.
     * @param type           the type
     * @param defaultMessage the default message
     * @param entityName     the entity name
     * @param errorKey       the error key
     */
    public BadRequestAlertException(URI type, String defaultMessage, String entityName, String errorKey) {
        super(type, defaultMessage, Status.BAD_REQUEST, null, null, null, getAlertParameters(entityName, errorKey));
        this.entityName = entityName;
        this.errorKey = errorKey;
    }

    /**
     * Lay entity name.
     * @return entity name
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * Lay error key.
     * @return error key
     */
    public String getErrorKey() {
        return errorKey;
    }

    /**
     * Lay alert parameters.
     * @param entityName the entity name
     * @param errorKey   the error key
     * @return alert parameters
     */
    private static Map<String, Object> getAlertParameters(String entityName, String errorKey) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", "error." + errorKey);
        parameters.put("params", entityName);
        return parameters;
    }
}
