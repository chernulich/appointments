package appointer;

import java.util.Locale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;

/**
 * Library for parsing phone numbers
 * https://github.com/googlei18n/libphonenumber
 */
public class PhoneParcerTest {
	public static void test() {

		printPhoneNumber("+7 999 078 69 99");
		printPhoneNumber("+972 054 878 29 39");
		printPhoneNumber("+7 495 379 69 49");
		printPhoneNumber("+7 831 604 24 39");
		
	}
	
	public static void read(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	}

	private static void printPhoneNumber(String number) {
		
		System.out.println("\nPHONE NUMBER IS " + number);
		PhoneNumberUtil phoneUtil = 
				PhoneNumberUtil.getInstance();
		PhoneNumberOfflineGeocoder geocoder = 
				PhoneNumberOfflineGeocoder.getInstance();
		try {
			PhoneNumber parsed = phoneUtil.parse(number, "CH");
			parsedPrint(geocoder, parsed);
		} catch (NumberParseException e) {
			System.err.println(
					"NumberParseException was thrown: " + e.toString());
		}

	}

	private static void parsedPrint(PhoneNumberOfflineGeocoder geocoder, PhoneNumber parsed) {
		System.out.println("COUNTRY CODE " + parsed.getCountryCode());
		System.out.println("NATIONAL NUMBER " + parsed.getNationalNumber());
		System.out.println("LOCATION " + geocoder.getDescriptionForNumber(parsed, Locale.ENGLISH));
	}
	
	   /**
	     * @return line from input that passed authentication 
	     * @param data IAuthData object
	     * Adapter over BufferedReader that takes coupled Command IAuthData; 
	     */
	     */
/*	    public static String readOneLine(promptData data){
		String line = null;
		while(true){
		    System.out.println(data.getHint());
		    try {
			line = data.getReader().readLine();
		    } catch (IOException e) {}
		    if (line.equals(data.getExitWord())) return null;
		    if (data.authPassed(line)) break;
		}
		return line;
	    }*/
}
