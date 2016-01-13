package vic.test.jdk.jdk8.time;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * @author Vic Liu
 */
public class DaylightSaving {
    public static void main(String[] args) {

        // New Zealand
        //    daylight saving starts at: 2015-09-27 2:00am  +1 hrs
        //    daylight saving ends at:   2016-04-03 3:00am  -1 hrs
        ZoneId auckland = ZoneId.of("Pacific/Auckland");
        ZonedDateTime beforeTimeAdjustment = ZonedDateTime.of(2015, 9, 27, 1, 0, 0, 0, auckland); // 2015-9-27 1:00am
        // + 1 at 2:00am; 2:00am and 3:00am are same instants
        ZonedDateTime atTimeAdjustment = ZonedDateTime.of(2015, 9, 27, 2, 0, 0, 0, auckland);    // 2015-9-27 2:00am
        ZonedDateTime afterTimeAdjustment = ZonedDateTime.of(2015, 9, 27, 3, 0, 0, 0, auckland); // 2015-9-27 3:00am

        Duration d = Duration.between(beforeTimeAdjustment.toInstant(), afterTimeAdjustment.toInstant()); // should be 1 hour
        System.out.println("Expected duration is 1 hour, actual is " + d.toHours() + " hour(s)");
        d = Duration.between(atTimeAdjustment.toInstant(), afterTimeAdjustment.toInstant());
        System.out.println("Expected duration is 0 seconds, actual is " + d.getSeconds() + " second(s)");

        //
        // No daylight saving
        //
        OffsetDateTime before = OffsetDateTime.of(2015, 9, 27, 1, 0, 0, 0, ZoneOffset.ofHours(13));
        OffsetDateTime at = OffsetDateTime.of(2015, 9, 27, 2, 0, 0, 0, ZoneOffset.ofHours(13));    // 2015-9-27 2:00am
        OffsetDateTime after = OffsetDateTime.of(2015, 9, 27, 3, 0, 0, 0, ZoneOffset.ofHours(13)); // 2015-9-27 3:00am
        System.out.println(Duration.between(before, after).toHours()); // 2 hrs
        System.out.println(Duration.between(at, after).toHours());     // 1 hr
    }
}
