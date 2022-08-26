import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Encryptor {

    private final static long CONST = 9999999999999L;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MM dd HH:mm:ss");

    public String testString;
    public String encryptedString;
    public String decodedString;
    public String encryptedIntTime;
    public String decodedIntTime;
    public long unixTimestamp;


    private final String[] encodingKey = {"с", "е", "а", "ф", "о", "и", "м", "к", "б", "в", "ъ", "ц", "ё", "н"};
    private final String[] decodingKey = {"ж", "з", "п", "т", "у", "й", "я", "ч", "ш", "щ", "э", "ю", "д", "ь"};
    private final char[] caseCheck = new char[10000];

    public Encryptor(String testString) {

        this.testString = testString;
    }

    /**
     * Кодирует исходную строку.
     *
     * @param testString Исходная строка для кодировки.
     * @return Закодированную строку с искаженной датой.
     */
    public String stringEncoder(String testString) {

        encryptedString = testString;
        String[] result = encryptedString.split("");

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < encodingKey.length; j++) {

                if (result[i].equals(encodingKey[j])) {
                    result[i] = decodingKey[j];
                    caseCheck[i] = '-';

                } else if ((result[i].toLowerCase().equals(encodingKey[j]))) {
                    result[i] = decodingKey[j].toUpperCase();
                    caseCheck[i] = '^';
                }
            }
        }

        encryptedString =  joinArray(result);

        Date date1 = new Date();
        unixTimestamp = (date1.getTime() + CONST);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(unixTimestamp);
        encryptedIntTime = dateFormat.format(calendar.getTime());

        return (encryptedString + " Создан " + encryptedIntTime);
    }

    /**
     * Раскодирует закодированную строку.
     *
     * @param encryptedString Закодированная строка.
     * @return Раскодированную строку с нормальной датой.
     */
    public String stringDecoder(String encryptedString) {


        String[] result = encryptedString.split("");

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < decodingKey.length; j++) {

                if (result[i].equals(decodingKey[j]) && caseCheck[i] == '-') {
                    result[i] = encodingKey[j];

                } else if ((result[i].toLowerCase().equals(decodingKey[j]) && caseCheck[i]== '^')) {
                    result[i] = encodingKey[j].toUpperCase();
                }
            }
        }
        decodedString =  joinArray(result);

        Calendar calendar = Calendar.getInstance();
        unixTimestamp -= CONST;
        calendar.setTimeInMillis(unixTimestamp);
        decodedIntTime = dateFormat.format(calendar.getTime());

        return (decodedString + " Создан " + decodedIntTime);
    }

    private String joinArray(String[] result){
        StringBuilder local = new StringBuilder();
        for (String s : result) {
            local.append(s);
        }
        return local.toString();
    }
}
