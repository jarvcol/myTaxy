package test.utils;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class VerificationMethods {

    public static boolean checkEmailFormat(String email){
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
            return true;
        } catch (AddressException ex) {
            return false;
        }
    }
}
