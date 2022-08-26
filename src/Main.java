

public class Main {
    public static void main(String[] args) {

        Encryptor encryptor = new Encryptor("Аэрофотосъёмка, Ландшафта Уже Выявила ЗемЛи богачей - и ПроЦветающих крестЬян.");

        System.out.println(encryptor.testString);
        System.out.println(encryptor.stringEncoder(encryptor.testString));
        System.out.println(encryptor.stringDecoder(encryptor.encryptedString));
    }
}