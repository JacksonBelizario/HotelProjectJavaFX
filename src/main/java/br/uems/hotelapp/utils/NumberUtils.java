/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 *
 * @author Jackson
 */
public class NumberUtils {
    private static final char GROUPING_SEPARATOR = DecimalFormatSymbols.getInstance().getGroupingSeparator();
    private static final char DECIMAL_SEPARATOR = DecimalFormatSymbols.getInstance().getDecimalSeparator();
    private static final String NUMBER_FILTER_REGEX = "[^\\d\\" + DECIMAL_SEPARATOR + "]";
    private static final char M_DECIMAL_SEPARATOR = DECIMAL_SEPARATOR;
    private static final String LEADING_ZERO_FILTER_REGEX = "^0+(?!$)";

    /**
     * Add grouping separators to string
     *
     * @param original original string, may already contains incorrect grouping separators
     * @return string with correct grouping separators
     */
    public static String format(final String original) {
        final String[] parts = original.split("\\" + M_DECIMAL_SEPARATOR, -1);
        String number = parts[0] // since we split with limit -1 there will always be at least 1 part
                .replaceAll(NUMBER_FILTER_REGEX, "")
                .replaceFirst(LEADING_ZERO_FILTER_REGEX, "");
        // add grouping separators, need to reverse back and forth since Java regex does not support
        // right to left matching
        number = StringUtils.reverse(StringUtils.reverse(number).replaceAll("(.{3})", "$1" + GROUPING_SEPARATOR));
        // remove leading grouping separator if any
        number = StringUtils.removeStart(number, String.valueOf(GROUPING_SEPARATOR));

        // add fraction part if any
        if (parts.length > 1) {
            number += M_DECIMAL_SEPARATOR + parts[1];
        }

        return number;
    }

    /**
     * Return numeric value repesented by the text field
     *
     * @param numero
     * @return numeric value or {@link Double.NaN} if not a number
     */
    public static Double getNumericValue(final String numero) {
        if (numero == null) {
            return 0.0;
        }
        String original = numero.trim().replaceAll(NUMBER_FILTER_REGEX, "");
        original = StringUtils.replace(
            original,
            String.valueOf(M_DECIMAL_SEPARATOR),
            String.valueOf(DECIMAL_SEPARATOR)
        );

        try {
            return NumberFormat.getInstance().parse(original).doubleValue();
        } catch (ParseException e) {
            return 0.0;
        }
    }
    
    public static Double parseDouble(final String numero) {
        if (numero == null) {
            return 0.0;
        }
        try {
            return NumberFormat.getInstance().parse(numero).doubleValue();
        } catch (ParseException e) {
            return 0.0;
        }
    }
    
    public static Integer parseInt(final String numero) {
        if (numero == null) {
            return 0;
        }
        try {
            return NumberFormat.getInstance().parse(numero).intValue();
        } catch (ParseException e) {
            return 0;
        }
    }
    
    public static String validateDouble(String str) {
        if (!str.matches("\\d*(\\.\\d{0,2})?")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    public static void mascaraDinheiro(TextField textField) {

        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (newValue.length() > 0) {
                
                newValue = newValue.toString().trim();
                // valid decimal number should not have more than 2 decimal separators
                if (StringUtils.countMatches(newValue, String.valueOf(M_DECIMAL_SEPARATOR)) > 1) {
                    textField.setText(oldValue); // cancel change and revert to previous input
                    return;
                }
                if (newValue.charAt(newValue.length() - 1) == GROUPING_SEPARATOR
                        && newValue.indexOf(M_DECIMAL_SEPARATOR) < newValue.length()) {
                    textField.setText(oldValue); // cancel change and revert to previous input
                    return;
                }
                
                textField.setText(format(newValue));
            }
        });

    } 
    
    public static String formatNumber(Double value) {
        String valueStr =  "0";
        try {
            valueStr = NumberFormat.getNumberInstance().format(value);
        } catch(Exception ex) {
            //
        }
        return valueStr;
    }
    
    public static String formatCurrency(Double value) {
        return NumberFormat.getCurrencyInstance().format(value);
    }
}
