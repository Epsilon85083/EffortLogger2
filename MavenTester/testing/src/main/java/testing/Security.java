package testing;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Security {

    // Below code based upon: https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
    public void check(String password)
        throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        System.out.println(password);

        String generatedSecuredPasswordHash = generateStrongPasswordHash(password);
        System.out.println(generatedSecuredPasswordHash);

        boolean matched = validatePassword(password, generatedSecuredPasswordHash);
        System.out.println(matched);

        matched = validatePassword(password + "1", generatedSecuredPasswordHash);
        System.out.println(matched);
    }

    public String generateStrongPasswordHash(String password)
        throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 *8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    public boolean validatePassword(String originalPassword, String storedPassword)
        throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String[] parts = storedPassword.split(":");

        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        int diff = hash.length ^ testHash.length; //hash.length XOR testHash.length
        for(int i = 0; i < hash.length && i < testHash.length; i++) {
            diff = diff | (hash[i] ^ testHash[i]);
        }
        return diff == 0;
    }

    public byte[] getSalt()
            throws NoSuchAlgorithmException
    {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public String toHex(byte[] array)
        throws NoSuchAlgorithmException
    {
        BigInteger bigint = new BigInteger(1, array);
        String hex = bigint.toString(16);

        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    public byte[] fromHex(String hex)
        throws NoSuchAlgorithmException
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
    
    //Limits injection of malicious code by placing lexical and symbolic restriction on user input processing
    //Returns true if it is a valid password, false if it is invalid.
    public boolean checkRequirements(TextField username) {
		if(username.getText().length() < 3 || 33 < username.getText().length()) { //ensure username is within secure character length and lexical rules
			return false;
		}
		// Define a regular expression pattern that matches the allowed characters
        Pattern pattern = Pattern.compile("^[A-Za-z0-9._-]+$");

        // Create a Matcher object and check if the password matches the pattern
        Matcher matcher = pattern.matcher(username.getText());
        return matcher.matches(); 
	}
	
    //Limits injection of malicious code by placing lexical and symbolic restriction on user input processing
    //Returns true if it is a valid password, false if it is invalid.
	public boolean checkRequirements(PasswordField password) {
		if(password.getText().length() < 3 || 33 < password.getText().length()) { //ensure password is within secure character length and lexical rules
			return false;
		}
		// Define a regular expression pattern that matches the allowed characters
        Pattern pattern = Pattern.compile("^[A-Za-z0-9._-]+$");

        // Create a Matcher object and check if the password matches the pattern
        Matcher matcher = pattern.matcher(password.getText());
        return matcher.matches(); 
	}

	public Object checkRequirements(String testPassword) {
		if(testPassword.length() < 3 || 33 < testPassword.length()) { //ensure username is within secure character length and lexical rules
			return false;
		}
		// Define a regular expression pattern that matches the allowed characters
        Pattern pattern = Pattern.compile("^[A-Za-z0-9._-]+$");

        // Create a Matcher object and check if the password matches the pattern
        Matcher matcher = pattern.matcher(testPassword);
        return matcher.matches(); 
	}
}
