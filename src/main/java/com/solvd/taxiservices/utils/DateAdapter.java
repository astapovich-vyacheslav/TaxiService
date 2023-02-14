package com.solvd.taxiservices.utils;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends XmlAdapter<String, Date> {
    public Date unmarshal (String date) {
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public String marshal (Date date) {
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }
}
