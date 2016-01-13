package vic.test.jdk.jdk8.time;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.zone.ZoneRules;
import java.util.Locale;

/**
 * @author Vic Liu
 */
public class Test {

    public static void main(String[] args) {
        ZonedDateTime zdt = ZonedDateTime.parse("2015-11-16T00:00:30.00+11:00");


        Clock nz = Clock.systemDefaultZone();
        Clock sh = Clock.system(ZoneId.of("Asia/Shanghai"));

        System.out.println("local: " + LocalDateTime.now(nz) + ", " + LocalDateTime.now(sh));
        System.out.println("offset: " + OffsetDateTime.now(nz) + ", " + OffsetDateTime.now(sh));
        System.out.println("zoned: " + ZonedDateTime.now(nz) + ", " + ZonedDateTime.now(sh));

    }


    public static void testZonedDateTime() {
        ZoneId departureZone = ZoneId.of("Asia/Shanghai");
        ZoneId arrivalZone = ZoneId.of("Pacific/Auckland");
        int flyingHours = 11;
        DateTimeFormatter departureDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy MMM dd HH:mm:ss", Locale.CHINA);
        DateTimeFormatter arrivalDateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy HH:mm:ss");

        ZonedDateTime departureDateTime = ZonedDateTime.now(departureZone);
        System.out.println("Departure:");
        System.out.println(">> shanghai time: " + departureDateTimeFormatter.format(departureDateTime));
        System.out.println(">> auckland time: " + arrivalDateTimeFormatter.format(departureDateTime.withZoneSameInstant(arrivalZone)));

        ZonedDateTime arrivalDateTime = departureDateTime.plusHours(flyingHours);
        System.out.println("Arrival:");
        System.out.println(">> shanghai time: " + departureDateTimeFormatter.format(arrivalDateTime));
        System.out.println(">> auckland time: " + arrivalDateTimeFormatter.format(arrivalDateTime.withZoneSameInstant(arrivalZone)));
    }


}
