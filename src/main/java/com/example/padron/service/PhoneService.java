package com.example.padron.service;

import com.example.padron.models.Phone;

public interface PhoneService {
    Phone createPhone(Phone phone);
    Phone readPhone(int id);
    void deletePhone(int id);
}
