package com.spring.error;

import java.net.URI;

/**
 * Class ErrorConstants.
 */
public final class ErrorConstants {
    /**
     * Constant ERR_CONCURRENCY_FAILURE.
     */
    public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";

    /**
     * Constant ERR_VALIDATION.
     */
    public static final String ERR_VALIDATION = "error.validation";

    /**
     * Constant PROBLEM_BASE_URL.
     */
    public static final String PROBLEM_BASE_URL = "https://www.jhipster.tech/problem";

    /**
     * Constant DEFAULT_TYPE.
     */
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");

    /**
     * Constant CONSTRAINT_VIOLATION_TYPE.
     */
    public static final URI CONSTRAINT_VIOLATION_TYPE = URI.create(PROBLEM_BASE_URL + "/constraint-violation");

    /**
     * Constant INVALID_PASSWORD_TYPE.
     */
    public static final URI INVALID_PASSWORD_TYPE = URI.create(PROBLEM_BASE_URL + "/invalid-password");

    /**
     * Constant EMAIL_ALREADY_USED_TYPE.
     */
    public static final URI EMAIL_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/email-already-used");

    /**
     * Constant LOGIN_ALREADY_USED_TYPE.
     */
    public static final URI LOGIN_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/login-already-used");

    /**
     * Constant EMAIL_NOT_FOUND_TYPE.
     */
    public static final URI EMAIL_NOT_FOUND_TYPE = URI.create(PROBLEM_BASE_URL + "/email-not-found");

    /**
     * Instantiates a new error constants.
     */
    private ErrorConstants() {
    }
}
