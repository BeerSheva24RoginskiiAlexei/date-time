package telran.time;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.util.Locale;

import org.junit.jupiter.api.Test;

public class DateTimeTest {
    @Test
    void localDateTest() {
        LocalDate current = LocalDate.now();
        LocalDateTime currentTime = LocalDateTime.now();
        ZonedDateTime currentZonedTime = ZonedDateTime.now();
        Instant currentInstant = Instant.now();
        LocalTime currentLocalTime = LocalTime.now();
        System.out.printf("Current date is %s in ISO format \n",current);
        System.out.printf("Current date & time is %s in ISO format \n",currentTime);
        System.out.printf("Current zoned date & time is %s in ISO format \n",currentZonedTime);
        System.out.printf("Current instantr %s in ISO format \n",currentInstant);
        System.out.printf("Current time  %s in ISO format \n",currentLocalTime);
        System.out.printf("Current date is %s in dd/mm/yyyy \n",current.format(DateTimeFormatter.ofPattern("dd/MMMM/yyyy", Locale.forLanguageTag("he"))));
    }
    @Test
    void nextFriday13Test() {
        LocalDate current = LocalDate.of(2024,8,11);
        LocalDate expected = LocalDate.of(2024, 9, 13);
        TemporalAdjuster adjuster = new NextFriday13();
        assertEquals(expected, current.with(adjuster));
        assertThrows(RuntimeException.class, () -> LocalTime.now().with(adjuster));

    }

    @Test
    void PastTemporalDateProximityTest() {
        LocalDate date1 = LocalDate.of(2024,4,24);
        LocalDate date2 = LocalDate.of(2024,11,3);
        LocalDate date3 = LocalDate.of(2024,4,4);
        LocalDate date4 = LocalDate.of(2024,1,12);
        LocalDate date5 = LocalDate.of(2024,6,8);
        LocalDate date6 = LocalDate.of(2022,9,1);

        LocalDate[] dates = {date1, date2, date3, date4, date5, date6};

        TemporalAdjuster adjuster = new PastTemporalDateProximity(dates);
        assertEquals(date3, adjuster.adjustInto(date1));
        assertEquals(date1, adjuster.adjustInto(date5));
        assertEquals(date6, adjuster.adjustInto(date4));
        assertEquals(null, adjuster.adjustInto(date6));

    }
}