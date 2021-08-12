# Spring Boot for InvoiceQREncrypter
Cipher AESkey and invoicenumber+Random

Example:
There is default paramater encrypter

http://localhost:8080/aesa

E95eNqkzodO49TnX0I8vcg==

There is default paramater decrypter

http://localhost:8080/desa

PL49821416&rn5871



AES paramater example:

http://localhost:8080/aes?kn=F1A618ED8685B1A3B81E6CB6884617F4&in=PL49821416&rn=5871

E95eNqkzodO49TnX0I8vcg==

parameter description:


aes for @RequestParam Map<String, String> allParams

kn = genkey

in = invoice numner

rn = random

return seckey



DES paramater Example:

http://localhost:8080/des?kn=F1A618ED8685B1A3B81E6CB6884617F4&seckey=E95eNqkzodO49TnX0I8vcg==

PL498214165871

des for @RequestParam Map<String, String> allParams

kn = genkey

seckey = E95eNqkzodO49TnX0I8vcg==



AES2 example:

http://localhost:8080/aes2?kn=F1A618ED8685B1A3B81E6CB6884617F4&in=PL49821416&rn=5871

aes2 for   

@RequestParam(value = "in", defaultValue = "PL49821416") String in

@RequestParam(value = "rn", defaultValue = "5871") String rn,

@RequestParam(value = "kn", defaultValue = "F1A618ED8685B1A3B81E6CB6884617F4") String kn



DES2 example:

http://localhost:8080/aes2?kn=F1A618ED8685B1A3B81E6CB6884617F4&seckey=E95eNqkzodO49TnX0I8vcg==

des2 for   

@RequestParam(value = "kn", defaultValue = "F1A618ED8685B1A3B81E6CB6884617F4") String kn

@RequestParam(value = "seckey", defaultValue = "E95eNqkzodO49TnX0I8vcg==) String in
