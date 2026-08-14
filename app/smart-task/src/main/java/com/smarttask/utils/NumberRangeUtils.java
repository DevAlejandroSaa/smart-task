package com.smarttask.utils;

public class NumberRangeUtils {

    private static final NumberRangeUtils INSTANCE = new NumberRangeUtils();

    private NumberRangeUtils() {
    }

    public static NumberRangeUtils getInstance() {
        return INSTANCE;
    }

    public boolean isNumeric(String value) {
        return value != null && value.matches("\\d+");
    }

    public boolean isNumericInRange(String value, int min, int max) {
        if (!isNumeric(value)) {
            return false;
        }

        int number = Integer.parseInt(value);

        return number >= min && number <= max;
    }

}
