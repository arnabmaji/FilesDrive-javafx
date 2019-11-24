package io.github.arnabmaji19.model;

import java.util.regex.Pattern;

public class Validations {

    public static boolean isEmailValid(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean isPhoneNumberValid(String number){
        try{
            Long.parseLong(number);
        } catch (NumberFormatException e){
            return false;
        }

        return number.length() == 10;
    }
}
