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

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 *
 * @author Gareth Smith <gareth@track-mate.info>
 */
public class FileUtils {

  /**
   * Gets the absolute path to the directory containing the specified file on the classpath.
   * @param fileName The name of the file on the classpath to get the path to.
   * @return The path to the file, not including the file name.
   * @throws FileNotFoundException If the file couldn't be found on the classpath.
   * @throws URISyntaxException If the file's URI is not valid.
   */
  public static String getClasspathFileLocationFromName(String fileName) throws FileNotFoundException, URISyntaxException {
    URL fileURL = Thread.currentThread().getContextClassLoader().getResource(fileName);
    if (fileURL == null) {
      throw new FileNotFoundException("Failed to find file on classpath: " + fileName);
    }
    URI fileURI = fileURL.toURI();
    File file = new File(fileURI);
    String path = file.getParent();
    return path + "/";
  }

  /**
   * Gets the absolute path to the specified file on the classpath.
   * @param fileName The name of the file on the classpath to get the path to.
   * @return The path to the file, including the file name.
   * @throws FileNotFoundException If the file couldn't be found on the classpath.
   * @throws URISyntaxException If the file's URI is not valid.
   */
  public static String getClasspathFilePathFromName(String fileName) throws FileNotFoundException, URISyntaxException {
    URL fileURL = Thread.currentThread().getContextClassLoader().getResource(fileName);
    if (fileURL == null) {
      throw new FileNotFoundException("Failed to find file on classpath: " + fileName);
    }
    URI fileURI = fileURL.toURI();
    File file = new File(fileURI);
    return file.getAbsolutePath();
  }
}
