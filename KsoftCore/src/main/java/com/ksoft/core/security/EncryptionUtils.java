package com.ksoft.core.security;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class EncryptionUtils {
	
    // Salt
    private static final byte[] SALT = {
        (byte)0xc2, (byte)0x74, (byte)0x24, (byte)0x4c,
        (byte)0x7c, (byte)0xd8, (byte)0xee, (byte)0x99
    };

    private static final int ITERATIONS = 21;

    private PBEParameterSpec pbeParamSpec = new PBEParameterSpec(SALT, ITERATIONS);
    private SecretKey pbeKey;

    public String encrypt(String data)
    {
        try
        {
            Cipher pbeCipher = createCipher(true);

            // Encrypt the cleartext
            return encode(pbeCipher.doFinal(data.getBytes()));
        } catch (Exception ex)
        {
            // Should never happen
            throw new RuntimeException(ex);
        }
    }

    private Cipher createCipher(boolean encrypt) throws GeneralSecurityException
    {
        if (pbeKey == null)
            throw new IllegalStateException("The password has not be set");

        // Create PBE Cipher
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");

        // Initialize PBE Cipher with key and parameters
        pbeCipher.init((encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE), pbeKey, pbeParamSpec);
        return pbeCipher;
    }

    public String decrypt(String encData)
    {
        try
        {
            Cipher pbeCipher = createCipher(false);
            return new String(pbeCipher.doFinal(decode(encData)));
        } catch (Exception e)
        {
            // Should not happen in production
            throw new RuntimeException(e);
        }
    }

    /**
     * Decodes a string into a series of bytes where the string has been encoded with a page-with-offset scheme and
     * base-64 encoding algorithm as described by {@link #encode(byte[])}.
     *
     * Invalid page numbers are ignored, resulting in null (zero-valued) bytes in the output. Invalid offsets use the error
     * handling of {@link #decodeOffset(char)}.
     *
     * @param encoded the string to decode
     * @return the decoded bytes
     */
    static byte[] decode(String encoded)
    {
        int offsetsIndex = encoded.length() / 2;
        char[] encodedPageNumbers = encoded.substring(0, offsetsIndex).toCharArray();
        char[] encodedOffsets = encoded.substring(offsetsIndex).toCharArray();

        byte[] bytes = new byte[offsetsIndex];
        for (int i = 0; i < bytes.length; i++)
        {
            int pageNumber = decodePageNumber(encodedPageNumbers[i]);
            byte offset = decodeOffset(encodedOffsets[i]);
            if (pageNumber < 0 || pageNumber > 3)
            {
               
                continue;
            }
            bytes[i] = (byte) ((pageNumber * 64 - 128) + offset);
        }
        return bytes;
    }

    /**
     * Decode a character into a byte, based on an unusual base-64 encoding.
     * <p/>
     * The characters for digits 0-9 correspond with byte values 0-9, capital letters A-Z are 10-35, small letters A-Z are 36-61,
     * open angle bracket (&lt;) is 62 and close angle bracket (&gt;) is 63. Characters which are not recognised are returned
     * as byte value 2 (the same as a digit '2').
     *
     * @param encodedOffset the character to decode
     * @return the decoded value of the character as a byte between 0 and 63.
     * @see #encodeOffset(int) for the encoder
     */
    static byte decodeOffset(char encodedOffset)
    {
        if ('0' <= encodedOffset && encodedOffset <= '9')
            return (byte) (encodedOffset - '0');
        else if ('A' <= encodedOffset && encodedOffset <= 'Z')
            return (byte) ((encodedOffset - 'A') + 10);
        else if ('a' <= encodedOffset && encodedOffset <= 'z')
            return (byte) ((encodedOffset - 'a') + 36);
        else if (encodedOffset == '<')
            return (byte) (62);
        else if (encodedOffset == '>')
            return (byte) (63);
        else
        {
           
            return 2;
        }
    }

    /**
     * Encodes a series of byte values in a string using a page-with-offset scheme and a base-64 encoding algorithm.
     * <p/>
     * First, the byte value is converted into a page number and offset relative to the starting value of the page.
     * Page 0 contains values -128 to -65, page 1 has -64 to -1, page 2 has 0 to 63, and page 3 has 64 to 127.
     * For example, the byte value 96 will be converted into page 3 and offset 32.
     * <p/>
     * The offset is encoded using the unusual base-64 encoding implemented by {@link #encodeOffset(int)}. The
     * page is encoded using {@link #encodePageNumber(int)}.
     * <p/>
     * The output string consists of the characters for all encoded page numbers first, followed by the characters
     * for all the encoded offsets.
     *
     * @param plainBytes the binary data to encode
     * @return an encoded string representing the binary data, where the first half consists of encoded page numbers
     * and the second half consists of encoded offsets
     */
    static String encode(byte[] plainBytes)
    {
        StringBuffer encodedPageNumbers = new StringBuffer(plainBytes.length);
        StringBuffer encodedOffsets = new StringBuffer(plainBytes.length);
        for (int i = 0; i < plainBytes.length; i++)
        {
            byte aByte = plainBytes[i];

            int pageNumber = ((int) aByte + 128) / 64;
            encodedPageNumbers.append(encodePageNumber(pageNumber));
            
            int offset = ((int) aByte + 128) % 64;
            encodedOffsets.append(encodeOffset(offset));
        }
        return encodedPageNumbers.toString() + encodedOffsets.toString();
    }

    /**
     * Generates a random character which encodes a page number between 0 and 3 inclusive. The character is derived by the following formula:
     * <ol>
     * <li>Multiply the page number by 6, add a random integer between 0 and 5.</li>
     * <li>Convert the resulting value into a character, where a = 0, b = 1, etc.</li>
     * <li>Select a random Boolean true/false value. Make the character upper-case if this random value is true.</li>
     * </ol>
     * For example, if the page number is 0, the resulting character will be between 'a' and 'f' inclusive, and could be either upper or lower case.
     *
     * @param pageNumber the page number to encode, which should be between 0 and 3 inclusive
     * @return a random character between (pageNumber x 6) and (pageNumber x 6 + 5) where a = 0, b = 2, etc. and upper or lower case is randomly selected
     */
    static char encodePageNumber(int pageNumber)
    {
        int randomChar = (pageNumber * 6) + (int) (Math.random() * 6);
        boolean upperCase = (int) (Math.random() * 2) < 1;
        return (char) (randomChar + (int) (upperCase ? 'A' : 'a'));
    }

    /**
     * Decodes a page number generated by {@link #encodePageNumber(int)} by converting to lower case, and dividing the number
     * value of the letter (where a = 0, b = 1, etc.) by 6.
     *
     * @param encodedPageNumber the encoded page number
     * @return a page number which will be either 0, 1, 2 or 3 if the input is a properly encoded page number
     */
    static int decodePageNumber(char encodedPageNumber)
    {
        return ((int) Character.toLowerCase(encodedPageNumber) - 'a') / 6;
    }

    /**
     * Encode a base-64 offset into a character, using a quite unusual base-64 encoding.
     * <p/>
     * The characters for digits 0-9 are returned for values 0-9, capital letters A-Z represent 10-35, small letters a-z represent 36-61,
     * open angle bracket (&lt;) is 62 and close angle bracket (&gt;) is 63. Values greater than 63 or less than zero return the null character
     * (character 0).
     *
     * @param offset the offset to encode
     * @return a character representing the offset in the encoding scheme described above
     * @see #decodeOffset(char) for the decoder
     */
    static char encodeOffset(int offset)
    {
        if (offset < 0 || offset > 63)
        {
           
            return (char) 0;
        }

        if (offset < 10)
            return (char) (offset + '0');

        if (offset < 36)
            return (char) (offset - 10 + 'A');

        if (offset < 62)
            return (char) (offset - 36 + 'a');

        if (offset < 63)
            return '<';

        return '>';
    }

    public void setPassword(String password)
    {
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
        try
        {
            SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            pbeKey = keyFac.generateSecret(pbeKeySpec);
        } catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException("Encryption algorithm not found", e);
        } catch (InvalidKeySpecException e)
        {
            throw new RuntimeException("Invalid passphrase", e);
        }


    }

 
}
