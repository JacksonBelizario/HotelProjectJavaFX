/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

/**
 *
 * @author Jackson
 */
public class DateUtils {
    
    public static SimpleDateFormat simpleDateFormat;
    
    public static SimpleDateFormat getDateFormat() {
        if (simpleDateFormat == null) {
            setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        }
        return simpleDateFormat;
    }

    private static void setDateFormat(SimpleDateFormat controller) {
        DateUtils.simpleDateFormat = controller;
    }
    
    public static LocalDate toLocalDate(Date dateToConvert) {
        try {
            return dateToConvert.toInstant()
              .atZone(ZoneId.systemDefault())
              .toLocalDate();
        } catch(Exception ex) {
            return null;
        }
    }
    
    public static Date toDate(LocalDate dateToConvert) {
        try {
            return java.util.Date.from(dateToConvert.atStartOfDay()
              .atZone(ZoneId.systemDefault())
              .toInstant());
        } catch(Exception ex) {
            return null;
        }
    }
    
    public static void setDatePickerLimit(DatePicker datePicker, LocalDate minDate, LocalDate maxDate) {
        datePicker.setDayCellFactory(d -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (minDate != null && maxDate != null) {
                    setDisable(item.isBefore(minDate) || item.isAfter(maxDate));
                }
                else if (minDate != null) {
                    setDisable(item.isBefore(minDate));
                }
                else if (maxDate != null) {
                    setDisable(item.isAfter(maxDate));
                }
            }
        });
    }
    
    public static String format(Date date) {
        return getDateFormat().format(date);
    }
}
