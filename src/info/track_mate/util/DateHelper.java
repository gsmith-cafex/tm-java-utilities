/**
 * Copyright 2000-2012 TrackMate
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package info.track_mate.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author Gareth Smith <gareth@track-mate.info>
 */
public final class DateHelper {


  /** Logger instance for this class. */
  private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DateHelper.class);

  /** The last hour of the day (23). */
  private static final int LAST_HOUR_IN_DAY = 23;
  /** The last minute of the hour (59). */
  private static final int LAST_MINUTE_IN_HOUR = 59;
  /** The last minute of the hour (59). */
  private static final int LAST_SECOND_IN_MINUTE = 59;
  /** The last millisecond of the second (999). */
  private static final int LAST_MILLI_IN_SECOND = 999;

  /** The number of milliseconds in a day. */
  public static final int MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;

  /**
   * Creates a new RacingHelper.
   */
  private DateHelper() {
  }

  /**
   * Checks if the target date is valid within the specified timeframe. The date
   * is valid if one of the following criteria is true:
   * <ul>
   * <li>Both start date and end date are null.</li>
   * <li>Start date is null and end date is after the target date.</li>
   * <li>Start date is before or equal to target date and end date is null.</li>
   * <li>Start date is before or equal to target date and end date is after
   * target date.</li>
   * </ul>
   *
   * @param startDate
   *          The start date for the time frame.
   * @param endDate
   *          The end date for the time frame.
   * @param targetDate
   *          The target date to check within the time frame.
   * @return true if the target date is valid within the time frame, else false.
   */
  public static boolean isDateValid(Date startDate, Date endDate, Date targetDate) {
    boolean valid = false;
    if (startDate == null && endDate == null) {
      valid = true;

    } else if (startDate == null) {
      valid = endDate.after(targetDate);

    } else if (endDate == null) {
      valid = !startDate.after(targetDate);

    } else if (!startDate.after(targetDate) && endDate.after(targetDate)) {
      valid = true;
    }
    return valid;
  }

  /**
   * Get a Date representing the start of the day for the specified date.
   *
   * @param date
   *          The date to get the day start date for.
   * @return The date for the start of the specified day.
   */
  public static Date getDayStart(final Date date) {
    return getDayStart(date, null);
  }

  /**
   * Get a Date representing the start of the day for the specified date.
   *
   * @param date
   *          The date to get the day start date for.
   * @param timezone The timezone that the Date should have.
   * @return The date for the start of the specified day.
   */
  public static Date getDayStart(final Date date, TimeZone timezone) {
    Calendar calendar = Calendar.getInstance();
    if (timezone != null) {
      calendar.setTimeZone(timezone);
    }
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
  }

  /**
   * Get a Date representing the end of the day for the specified date.
   *
   * @param date
   *          The date to get the day end date for.
   * @return The date for the end of the specified day.
   */
  public static Date getDayEnd(final Date date) {
    return getDayEnd(date, null);
  }

  /**
   * Get a Date representing the end of the day for the specified date.
   *
   * @param date
   *          The date to get the day end date for.
   * @param timezone The timezone that the Date should have.
   * @return The date for the end of the specified day.
   */
  public static Date getDayEnd(final Date date, TimeZone timezone) {
    Calendar calendar = Calendar.getInstance();
    if (timezone != null) {
      calendar.setTimeZone(timezone);
    }
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, LAST_HOUR_IN_DAY);
    calendar.set(Calendar.MINUTE, LAST_MINUTE_IN_HOUR);
    calendar.set(Calendar.SECOND, LAST_SECOND_IN_MINUTE);
    calendar.set(Calendar.MILLISECOND, LAST_MILLI_IN_SECOND);
    return calendar.getTime();
  }

  /**
   * Get the date for the day previous to that specified.
   *
   * @param date
   *          The date to get the previous date for.
   * @return The previous date to the one specified.
   */
  public static Date getPreviousDate(final Date date) {
    return getOffsetDate(date, -1, Calendar.DATE);
  }

  /**
   * Get the date for the day subsequent to that specified.
   *
   * @param date The date to get the subsequent date for.
   * @return The subsequent date to the one specified.
   */
  public static Date getNextDate(final Date date) {
    return getOffsetDate(date, 1, Calendar.DATE);
  }

  /** Get the date for tomorrow (based on system time).
   *
   * @return The date representing the start of the following day based on server time.
   */
  public static Date getTommorrowStartDate() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    calendar.add(Calendar.DATE, 1);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
  }

  /** Get the start date for today (based on system time).
   *
   * @return The date representing the start of the current day based on server time.
   */
  public static Date getTodayStartDate() {
    return getDayStart(new Date());
  }

  /** Get the date for yesterday (based on system time).
   *
   * @return The date representing the start of the previous day based on server time.
   */
  public static Date getYesterdayStartDate() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    calendar.add(Calendar.DATE, -1);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
  }

  /**
   * Offset the given date by the specified amount.
   * @param startDate The date to offset.
   * @param offsetQuantity The amount to offset. Note that negative numbers will result in the returned date being earlier than the supplied date.
   * @param offsetQualifier The unit to offset the date by. This should be taken from the Calendar constants.
   * @return The offset Date.
   */
  public static Date getOffsetDate(Date startDate, int offsetQuantity, int offsetQualifier) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(startDate);
    calendar.add(offsetQualifier, offsetQuantity);
    return calendar.getTime();
  }

}
