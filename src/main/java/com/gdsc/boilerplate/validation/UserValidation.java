package com.gdsc.boilerplate.validation;

import com.gdsc.boilerplate.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserValidation {
    
    
    public static void validateCreate(User user) {
        if(user.getFullname().length() < 3 || user.getFullname().length() > 50) {
            throw new IllegalArgumentException("Fullname must be between 3 and 50 characters long");
        }

        if(user.getIdentityNumber() == null || user.getIdentityNumber().length() != 16) {
            throw new IllegalArgumentException("Identity number must be exactly 16 characters long");
        }

        if(user.getPhoneNumber() == null || user.getPhoneNumber().length() < 9 || user.getPhoneNumber().length() > 15) {
            throw new IllegalArgumentException("Phone number must be between 9 and 15 characters long");
        }
    }
    
    public static void validateUpdate(User user) {
        if (user.getFullname() != null && (user.getFullname().length() < 3 || user.getFullname().length() > 50)) {
            throw new IllegalArgumentException("Fullname must be between 3 and 50 characters long");
        }
        
        if (user.getIdentityNumber() != null && user.getIdentityNumber().length() != 16) {
            throw new IllegalArgumentException("Identity number must be exactly 16 characters long");
        }
        
        if (user.getPhoneNumber() != null && (user.getPhoneNumber().length() < 9 || user.getPhoneNumber().length() > 15)) {
            throw new IllegalArgumentException("Phone number must be between 9 and 15 characters long");
        }
    }
}
