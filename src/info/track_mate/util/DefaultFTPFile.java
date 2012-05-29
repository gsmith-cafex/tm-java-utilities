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
 * Default POJO implementation of FTPFile.
 * @author Gareth Smith <gareth@track-mate.info>
 */
public class DefaultFTPFile implements FTPFile {

  /** The file name. */
  private String name;
  /** The file size in bytes. */
  private long size;

  public DefaultFTPFile(String name, long size) {
    this.name = name;
    this.size = size;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public long getSize() {
    return size;
  }


}