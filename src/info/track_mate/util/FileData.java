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

import java.io.InputStream;

/**
 * Represents a File.
 * @author Gareth Smith <gareth@track-mate.info>
 */
public interface FileData {

  /** Get the name of the file.
   * 
   * @return The name of the file.
   */
  public String getName();

  /** Get the size of the file data.
   *
   * @return The size of the file data.
   */
  public long getFileSize();

  /** Get the contents of the file.
   * 
   * @return The contents of the file.
   * @throws Exception If the file data couldn't be returned.
   */
  public byte[] getData() throws Exception;

  /** Get an input stream for the file data.
   *
   * @return An input stream for the file data.
   * @throws java.lang.Exception If an input stream could not be returned.
   */
  public InputStream getInputStream() throws Exception;
}
