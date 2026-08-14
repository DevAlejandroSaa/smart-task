package com.smarttask.validation;

import com.smarttask.core.resources.MessageResources;
import com.smarttask.utils.NumberRangeUtils;

public class NumberRangeValidator {

    private static final NumberRangeValidator INSTANCE = new NumberRangeValidator();

    private final MessageResources messageResources;
    private final NumberRangeUtils numberRangeUtils;

    private NumberRangeValidator() {
        this.messageResources = MessageResources.getInstance();
        this.numberRangeUtils = NumberRangeUtils.getInstance();
    }

    public static NumberRangeValidator getInstance() {
        return INSTANCE;
    }

    public void validateOption(String option, int min, int max) {
        if (!this.numberRangeUtils.isNumericInRange(option, min, max)) {
            throw new IllegalArgumentException(this.messageResources.getMessage("valid.menu.option.invalid"));
        }
    }

    public void validatePositiveInteger(String value) {
        if (!this.numberRangeUtils.isNumericInRange(value, 1, Integer.MAX_VALUE)) {
            throw new IllegalArgumentException(this.messageResources.getMessage("valid.number.positive.invalid"));
        }
    }

}
