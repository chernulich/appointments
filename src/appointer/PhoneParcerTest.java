package appointer;

import java.util.Locale;

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

		printPhoneNumber("+7 999 078 62 92");
		printPhoneNumber("+972 054 878 20 38");
		printPhoneNumber("+7 495 379 68 44");
		printPhoneNumber("+7 831 604 24 38");
		
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
}
