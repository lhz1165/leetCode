package org.test;

import java.sql.Timestamp;
import java.time.*;
import java.util.Date;

/**
 * Created by: hz.lai
 * Date: 2021/5/20
 * Description:
 */
public class TimeTest {
  public static void main(String[] args) {
    TimeTest t = new TimeTest();
    long timestamp = Instant.now().toEpochMilli();
    long ll = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    long l = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    System.out.println(new Date(ll));
    LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(ll), ZoneId.systemDefault());
    System.out.println(localDateTime);
    System.out.println(t.toLocalDateTime1(ll));
    System.out.println(t.toLocalDateTime1(ll));
    System.out.println(t.toLocalDateTime1(ll));
  }
  /**
   *
   * @param timestamp
   * @return
   */
  public LocalDateTime toLocalDateTime1(long timestamp) {
    return new Timestamp(timestamp).toLocalDateTime();
  }

  public LocalDateTime toLocalDateTime2(long timestamp) {
    //1
    Instant instant = Instant.ofEpochMilli(timestamp);
    ZoneOffset zoneOffset = ZoneOffset.of("+8");
    //2
    OffsetDateTime offsetDateTime = instant.atOffset(zoneOffset);
    //3
    return offsetDateTime.toLocalDateTime();
  }

  public LocalDateTime toLocalDateTime3(long timestamp) {
    //1
    Instant instant = Instant.ofEpochMilli(timestamp);
    ZoneOffset zoneOffset = OffsetDateTime.now().getOffset();
    //2
    OffsetDateTime offsetDateTime = instant.atOffset(zoneOffset);
    //3
    return offsetDateTime.toLocalDateTime();
  }

  public LocalDateTime toLocalDateTime4(long timestamp) {
    Instant instant = Instant.ofEpochMilli(timestamp);
    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
  }

}
