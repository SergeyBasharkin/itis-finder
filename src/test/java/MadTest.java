import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MadTest {
    @Test
    public void test(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime date = LocalDateTime.parse("2011-12-03T10:15:30Z",dateTimeFormatter);
        System.out.println(date);
    }
}
