

public class Main {
    public static void main(String[] args) {

        Encryptor encryptor = new Encryptor("Аэрофотосъёмка ландшафта уже выявила земли богачей и процветающих крестьян.");

        System.out.println(encryptor.testString);
        System.out.println(encryptor.stringEncoder(encryptor.testString));
        System.out.println(encryptor.stringDecoder(encryptor.encryptedString));
    }
}