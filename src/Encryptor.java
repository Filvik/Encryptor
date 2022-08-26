import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Encryptor {

    private final static long CONST = 999999999999999999L;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MM dd HH:mm:ss");

    public String testString;
    public String encryptedString;
    public String decodedString;
    public String encryptedIntTime;
    public String decodedIntTime;
    public long unixTimestamp;


    private final String[] encodingKey = {"с", "е", "а", "ф", "о", "и", "м", "к", "б", "в", "ъ", "ц", "ё", "н"};
    private final String[] decodingKey = {"ж", "з", "п", "т", "у", "й", "я", "ч", "ш", "щ", "э", "ю", "д", "ь"};


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

        encryptedString = testString.toLowerCase();

        for (int i = 0; i < encodingKey.length; i++) {
            encryptedString = encryptedString.replaceAll(encodingKey[i], (decodingKey[i].toUpperCase()));
        }

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

        for (int i = 0; i < decodingKey.length; i++) {

            encryptedString = encryptedString.replaceAll((decodingKey[i].toUpperCase()), encodingKey[i]);
        }

        decodedString = encryptedString.toLowerCase();
        decodedString = decodedString.substring(0, 1).toUpperCase() + decodedString.substring(1);


        Calendar calendar = Calendar.getInstance();
        unixTimestamp -= CONST;
        calendar.setTimeInMillis(unixTimestamp);
        decodedIntTime = dateFormat.format(calendar.getTime());

        return (decodedString + " Создан " + decodedIntTime);
    }
}
