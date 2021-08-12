package com.example.restservice.entity;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class QREncrypter {

    private String InvoiceNumber = "PL49821416";
    private String RandomNumber = "5871";
    private String AESKey = "F1A618ED8685B1A3B81E6CB6884617F4";
    private String SECKey = "E95eNqkzodO49TnX0I8vcg==";

    public QREncrypter() {
    }

    public String getInoiceNumber() {
        return InvoiceNumber;
    }
    public void setInvoiceNumber(String InvoiceNumber) {
        this.InvoiceNumber = InvoiceNumber;
    }

    public String getRandomNumber() {
        return RandomNumber;
    }
    public void setRandomNumber(String RandomNumber) {
        this.RandomNumber = RandomNumber;
    }

    public String getAESKey() {
        return AESKey;
    }
    public void setAESKey(String AESKey) {
        this.AESKey = AESKey;
    }
    public String getSECKey() {
        return SECKey;
    }
    public void setSECKey(String SECKey) {
        this.SECKey = SECKey;
    }

    public QREncrypter(String s) throws Exception {
        ivParameterSpec = new IvParameterSpec(DatatypeConverter.parseBase64Binary("Dt8lyToo17X/XkXaQvihuA=="));
        secretKeySpec = new SecretKeySpec(DatatypeConverter.parseHexBinary(s), "AES");
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    }
    public QREncrypter(String AESKey,String SECKey) {
        this.AESKey = AESKey;
        this.SECKey = SECKey;
    }

    public QREncrypter(String InvoiceNumber, String RandomNumber, String AESKey) {
        this.InvoiceNumber = InvoiceNumber;
        this.RandomNumber = RandomNumber;
        this.AESKey = AESKey;
    }
   
    public String encode(String s) throws Exception {
        cipher.init(1, secretKeySpec, ivParameterSpec);
        byte abyte0[] = cipher.doFinal(s.getBytes());
        String s1 = DatatypeConverter.printBase64Binary(abyte0);
        return s1;
    }

    public String decode(String s) throws Exception {
        cipher.init(2, secretKeySpec, ivParameterSpec);
        byte abyte0[] = DatatypeConverter.parseBase64Binary(s);
        String s1 = new String(cipher.doFinal(abyte0));
        return s1;
    }

    private static final String TYPE_SPEC = "AES";
    private static final String TYPE_INIT = "AES/CBC/PKCS5Padding";
    private static final String SPEC_KEY = "Dt8lyToo17X/XkXaQvihuA==";
    private SecretKeySpec secretKeySpec;
    private Cipher cipher;
    private IvParameterSpec ivParameterSpec;

    public String CiphertGet() throws NoSuchPaddingException, NoSuchAlgorithmException, Exception {
        QREncrypter encrypter = new QREncrypter(this.AESKey);
        String encode = encrypter.encode(this.InvoiceNumber + this.RandomNumber);
        return (encode);
    }

    public String deCiphertGet() throws NoSuchPaddingException, NoSuchAlgorithmException, Exception {
        QREncrypter decrypter = new QREncrypter(this.AESKey);
        String decode = decrypter.decode(this.SECKey);
        return (decode);
    }

}
