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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Gareth Smith <gareth@track-mate.info>
 */
public class MiscHelper {

  /** The international date format (yyyy-MM-dd). */
  public static final DateFormat INTERNATIONAL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
  /** The UK date format (dd/MM/yyyy). */
  public static final DateFormat UK_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
  /** The verbose UK date format (dd MMM yyyy). */
  public static final DateFormat UK_VERBOSE_DATE_FORMAT = new SimpleDateFormat("dd MMM yyyy");
  /** The verbose UK date and time format (dd MMM yyyy HH:mm). */
  public static final DateFormat UK_VERBOSE_DATE_TIME_FORMAT = new SimpleDateFormat("dd MMM yyyy HH:mm");
  /** The international date & time format. (yyyy-MM-dd HH:mm) */
  public static final DateFormat INTERNATIONAL_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
  /** The international date & time format. (yyyy-MM-dd HH:mm:ss) */
  public static final DateFormat INTERNATIONAL_DATE_TIME_FORMAT_WITH_SECONDS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  /** The standard time format (HH:mm). */
  public static final DateFormat STANDARD_TIME_FORMAT = new SimpleDateFormat("HH:mm");

  /** Decimal format with no thousands delimiter and 2 decimal places (###0.##). */
  public static final DecimalFormat STANDARD_TWO_DECIMALS_FORMAT = new DecimalFormat("###0.##");
  /** Decimal format with a comma as the thousands delimiter and 2 decimal places (#,##0.##). */
  public static final DecimalFormat BRITISH_TWO_DECIMALS_FORMAT = new DecimalFormat("#,##0.##");

  /**
   * Copy the data from the InputStream to the OutputStream. This method will close the output stream after the copy is complete.
   * @param inputStream The inputStream to get the data from.
   * @param outputStream The ourputStream to write the data to.
   * @throws java.io.IOException If the streams could not be manipulated.
   */
  public static void copyStream(InputStream inputStream, OutputStream outputStream) throws IOException {
    byte[] buffer = new byte[8192];
    int length = 0;
    while ((length = inputStream.read(buffer)) != -1) {
      outputStream.write(buffer, 0, length);
    }
    outputStream.flush();
    outputStream.close();
  }

  /**
   * Return the maximum value from the specified arguments
   * @param values The values to return the maximum from.
   * @return The maximum value.
   */
  public static int max(int... values) {
    Integer result = null;
    for (int d : values) {
      if (result == null || d > result) {
        result = d;
      }
    }
    return result;
  }

  /**
   * Return the minimum value from the specified arguments
   * @param values The values to return the minimum from.
   * @return The minimum value.
   */
  public static int min(int... values) {
    Integer result = null;
    for (int d : values) {
      if (result == null || d < result) {
        result = d;
      }
    }
    return result;
  }

  /**
   * Return the maximum value from the specified arguments
   * @param values The values to return the maximum from.
   * @return The maximum value.
   */
  public static double maxDouble(double... values) {
    Double result = null;
    for (double d : values) {
      if (result == null || d > result) {
        result = d;
      }
    }
    return result;
  }

  /**
   * Return the minimum value from the specified arguments
   * @param values The values to return the minimum from.
   * @return The minimum value.
   */
  public static double minDouble(double... values) {
    Double result = null;
    for (double d : values) {
      if (result == null || d < result) {
        result = d;
      }
    }
    return result;
  }
}
