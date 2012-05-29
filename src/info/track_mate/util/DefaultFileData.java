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

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

/**
 * Default implementation of FileData.
 * @author Gareth Smith <gareth@track-mate.info>
 */
public class DefaultFileData implements WritableFileData, Serializable {

  /** The name of the file. */
  private String name;
  /** The data held by the file. */
  private byte[] data;

  /** {@inheritDoc} */
  @Override
  public byte[] getData() {
    return data;
  }

  /** {@inheritDoc} */
  @Override
  public String getName() {
    return name;
  }

  @Override
  public long getFileSize() {
    if (data == null) {
      return 0;
    }
    return data.length;
  }

  /**
   * Set the file {@link #name}.
   * @param value
   */
  public void setName(final String value) {
    this.name = value;
  }
  
  @Override
  public void setData(final byte[] value) {
    this.data = value;
  }

  @Override
  public InputStream getInputStream() throws Exception {
    BufferedInputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
    return inputStream;
  }

}
