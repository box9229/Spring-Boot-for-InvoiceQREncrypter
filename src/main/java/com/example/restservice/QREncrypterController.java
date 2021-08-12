package com.example.restservice;

import com.example.restservice.entity.QREncrypter;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Optional;
import javax.crypto.NoSuchPaddingException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QREncrypterController {

    String AESKey = "F1A618ED8685B1A3B81E6CB6884617F4";
    String InvoiceNumber = "PL49821416";
    String RandomNumber = "5871";

    String SECKey = "E95eNqkzodO49TnX0I8vcg==";

    @RequestMapping("/aesa")
    public String Test() throws Exception {

        QREncrypter ciper = new QREncrypter(InvoiceNumber,RandomNumber,AESKey);
        return ciper.CiphertGet();
    }

    @RequestMapping("/desa")
    public String Test2() throws Exception {

        QREncrypter deciper = new QREncrypter(AESKey,SECKey);
        return deciper.deCiphertGet();
    }
  
    @GetMapping("/data9")
    public String map(
        @RequestParam Map<String, String> dataQuery) {
        return dataQuery.toString();
    }
   
    //         http://localhost:8080/data2?in=987654AA&rn=5872&kn=F1A618ED8685B1A3B81E6CB6884617F4
    @GetMapping("/aes2")
    
    public String aes2data(
            @RequestParam(value = "in", defaultValue = "PL49821416") String in,
            @RequestParam(value = "rn", defaultValue = "5871") String rn,
            @RequestParam(value = "kn", defaultValue = "F1A618ED8685B1A3B81E6CB6884617F4") String kn) throws Exception {
      
        QREncrypter encrypter = new QREncrypter();
        encrypter.setInvoiceNumber(in);
        encrypter.setRandomNumber(rn);
        encrypter.setAESKey(kn);
        return encrypter.CiphertGet();
        //return String.format("%s:%s:%s",in, rn, kn);
    }
    @GetMapping("/des2")
    
    public String des2data(
            @RequestParam(value = "seckey", defaultValue = "E95eNqkzodO49TnX0I8vcg==") String seckey,
            @RequestParam(value = "kn", defaultValue = "F1A618ED8685B1A3B81E6CB6884617F4") String kn) throws Exception {
      
        QREncrypter decrypter = new QREncrypter();
        decrypter.setSECKey(seckey);
        decrypter.setAESKey(kn);
        return decrypter.deCiphertGet();
        //return String.format("%s:%s",seckey, kn);
    }
   
    @GetMapping(value = "/aes")
    public String aesData(
        @RequestParam Map<String, String> allParams) throws NoSuchPaddingException, NoSuchAlgorithmException, Exception {
        String in = Optional.ofNullable(allParams.get("in")).orElse(null);
        String rn = Optional.ofNullable(allParams.get("rn")).orElse(null);
        String kn = Optional.ofNullable(allParams.get("kn")).orElse(null);
        QREncrypter encrypter = new QREncrypter();
        encrypter.setInvoiceNumber(in);
        encrypter.setRandomNumber(rn);
        encrypter.setAESKey(kn);
        return encrypter.CiphertGet();
    }
        @GetMapping(value = "/des")
    public String desData(
        @RequestParam Map<String, String> allParams) throws NoSuchPaddingException, NoSuchAlgorithmException, Exception {
        String kn = Optional.ofNullable(allParams.get("kn")).orElse(null);
        String seckey = Optional.ofNullable(allParams.get("seckey")).orElse(null);
        QREncrypter decrypter = new QREncrypter();
        decrypter.setAESKey(kn);
        decrypter.setSECKey(seckey);
        return decrypter.deCiphertGet();
    }

}
