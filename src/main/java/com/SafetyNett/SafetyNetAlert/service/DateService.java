package com.SafetyNett.SafetyNetAlert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class DateService {


    public LocalDate now() {
        LocalDate today = LocalDate.now();
        return today;
    }
}
