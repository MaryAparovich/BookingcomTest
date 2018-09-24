package com.epam.aparovich.utils;

public class ConverterUtils {

    public static Double stringToDouble(String value) {

        try {
            return Double.valueOf(value);
        } catch (NumberFormatException e) {
            try {
                return Double.valueOf(value.replace(',', '.'));
            } catch (NumberFormatException e1) {
                try {
                    return Double.valueOf(value.replace('.', ','));
                } catch (NumberFormatException e2) {
                    return null;
                }
            }
        }
    }
}
