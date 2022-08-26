import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Encryptor {

    private final static int CONST = 2400;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MM dd HH:mm:ss");

    public String testString;
    public String encryptedString;
    public String decodedString;
    public String encryptedIntTime;
    public String decodedIntTime;


    private final String[] encodingKey = {"с", "е", "а", "ф", "о", "и", "м", "к", "б", "в", "ъ", "ц", "ё", "н"};
    private final String[] decodingKey = {"ж", "з", "п", "т", "у", "й", "я", "ч", "ш", "щ", "э", "ю", "д", "ь"};


    public Encryptor(String testString) {

        this.testString = testString;
    }

    /** Кодирует исходную строку.
     *
     * @param testString Исходная строка для кодировки.
     * @return Закодированную строку с искаженной датой.
     */
    public String stringEncoder(String testString) {

        encryptedString = testString.toLowerCase();

        for (int i = 0; i < encodingKey.length; i++) {
            encryptedString = encryptedString.replaceAll(encodingKey[i], (decodingKey[i].toUpperCase()));
        }

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, CONST);
        encryptedIntTime = dateFormat.format(calendar.getTime());

        return (encryptedString + " Создан " + encryptedIntTime);
    }

    /** Раскодирует закодированную строку.
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

        int year = Integer.parseInt(encryptedIntTime.substring(0,4)) ;
        int month = Integer.parseInt(encryptedIntTime.substring(5,7)) ;
        int day = Integer.parseInt(encryptedIntTime.substring(8,10)) ;
        int hour = Integer.parseInt(encryptedIntTime.substring(11,13)) ;
        int minute = Integer.parseInt(encryptedIntTime.substring(14,16)) ;
        int second = Integer.parseInt(encryptedIntTime.substring(17,19)) ;

        Calendar calendar = Calendar.getInstance();
        calendar.set(year,(month-1),day,hour,minute,second);

        calendar.add(Calendar.HOUR_OF_DAY, -CONST);
        decodedIntTime = dateFormat.format(calendar.getTime());

        return (decodedString + " Создан " + decodedIntTime);
    }
}
