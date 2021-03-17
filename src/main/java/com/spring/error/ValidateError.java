package com.spring.error;

import java.io.Serializable;

public class ValidateError implements Serializable {

    /**
     * Constant serialVersionUID.
     */
    private static final long serialVersionUID = 3509334171968614635L;

    /**
     * field name.
     */
    private String fieldName;

    /**
     * message error.
     */
    private String messageError;

    /**
     * Instantiates a new validate error.
     * @param fieldName    the field name
     * @param messageError the message error
     */
    public ValidateError(String fieldName, String messageError) {
        super();
        this.fieldName = fieldName;
        this.messageError = messageError;
    }

    /**
     * Lay field name.
     *
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Sets the field name.
     *
     * @param fieldName the new field name
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * Lay message error.
     * @return message error
     */
    public String getMessageError() {
        return messageError;
    }

    /**
     * Sets the message error.
     *
     * @param messageError the new message error
     */
    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }
}