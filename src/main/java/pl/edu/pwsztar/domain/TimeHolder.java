package pl.edu.pwsztar.domain;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
@Component
public final class TimeHolder {
    private static LocalDateTime localDateTime = LocalDateTime.now();

    public static void setTimeWithExtraTime(long secund){

        localDateTime = LocalDateTime.now().plusSeconds(secund);
    }
    public static long getExpireTime(){

        return ChronoUnit.SECONDS.between(localDateTime,LocalDateTime.now());
    }

}
