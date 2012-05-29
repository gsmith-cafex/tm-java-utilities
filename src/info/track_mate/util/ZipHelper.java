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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author Gareth Smith <gareth@track-mate.info>
 */
public class ZipHelper {

  /** Logger instance for this class. */
  private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ZipHelper.class);

  /** The standard file extension for a zip file. */
  public static final String ZIP_FILE_EXTENSION = ".zip";

  /**
   * Unzip the specified byte[].
   * @param zippedBytes The byte[] to unzip.
   * @return The unzipped version of the byte[].
   * @throws java.lang.Exception If the byte[] could not be unzipped.
   */
  public static byte[] unzipBytes(byte[] zippedBytes) throws Exception {
    return unzipBytes(new ByteArrayInputStream(zippedBytes));
  }

  /**
   * Unzip the specified byte[].
   * @param zippedBytes The byte[] to unzip.
   * @return The unzipped version of the byte[].
   * @throws java.lang.Exception If the byte[] could not be unzipped.
   */
  public static byte[] unzipBytes(InputStream inStream) throws Exception {
    ZipInputStream zipIn = new ZipInputStream(inStream);
    byte[] toReturn = loadZippedFile(zipIn);
    return toReturn;
  }

  // ------ Private Helpers ------

  /**
   * Read the contents of the supplied input stream into a byte array.
   * @param zipInputStream The input stream to read the data from.
   * @return The unzipped file contents.
   * @throws java.lang.Exception If the data couldn't be unzipped.
   * @todo Currently this method expects exactly 1 file inside the zip.
   */
  private static byte[] loadZippedFile(ZipInputStream zipInputStream) throws Exception {
    boolean foundFile = false;
    ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
    ZipEntry zipEntry = zipInputStream.getNextEntry();
    while (zipEntry != null) {
      if (logger.isDebugEnabled()) {
        logger.debug("Found zipped " + (zipEntry.isDirectory()? "directory" : "file") + ": " + zipEntry.getName());
      }
      if (zipEntry.isDirectory()) {
        logger.debug("  Ignoring directory.");
        continue;
      }

      if (foundFile) {
        throw new Exception("Found multiple zipped file entries in zip file.");
      }
      foundFile = true;

      MiscHelper.copyStream(zipInputStream, bytesOut);
      zipEntry = zipInputStream.getNextEntry();
    }
    zipInputStream.close();
    bytesOut.flush();
    bytesOut.close();
    return bytesOut.toByteArray();
  }
}
