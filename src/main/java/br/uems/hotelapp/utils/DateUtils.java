/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author Jackson
 */
public class DateUtils {
    
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
}
