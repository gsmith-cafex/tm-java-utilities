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

/**
 * Represents a remote file accessed via FTP.
 * @author Gareth Smith <gareth@track-mate.info>
 */
public interface FTPFile {

  /** Get the name of the remote file.
   * @return The filename.
   */
  String getName();

  /** Get the size in bytes of the remote file.
   * @return The size of the file.
   */
  long getSize();
}
