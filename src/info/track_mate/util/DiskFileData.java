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
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;

/**
 * An implementation of the FileData interface that holds the data on disk rather than in memory. This allows for larger files to be passed around locally
 * more easily.
 *
 * @author Gareth Smith <gareth@track-mate.info>
 */
public class DiskFileData  implements WritableFileData, Serializable {

  /** Logger instance for this class. */
  private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DiskFileData.class);

  /** The name of the properties file containing the config values for the file store. */
  private static final String FILE_STORE_CONFIG_FILENAME = "fileStoreConfig.properties";

  /** The key to extract the path to the file store on disk. */
  private static final String CONFIG_KEY_DIRECTORY_PATH = "fileStore.directory.path";

  /** The path to the data store directory. */
  private String fileStoreDirectoryPath;

  /** The name of the file. */
  private String name;
  /** The File object representing the file on disk. */
  private File dataFile;

  /**
   * Creates a new DiskFileData.
   * @param name The filename.
   */
  public DiskFileData(String name) throws Exception {
    this.name = name;
    Config fileStoreConfig = new Config(FILE_STORE_CONFIG_FILENAME);
    fileStoreDirectoryPath = fileStoreConfig.getProperty(CONFIG_KEY_DIRECTORY_PATH);
    dataFile = new File(fileStoreDirectoryPath, name);
  }

  public DiskFileData(File file) throws Exception {
    dataFile = file;
    name = file.getName();
    fileStoreDirectoryPath = file.getParent();
  }

  @Override
  public byte[] getData() throws Exception {
    if (!dataFile.canRead()) {
      throw new Exception("Cannot read from file: " + fileStoreDirectoryPath + name);
    }
    BufferedInputStream fileIn = new BufferedInputStream(new FileInputStream(dataFile));
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    MiscHelper.copyStream(fileIn, outputStream);
    fileIn.close();
    return outputStream.toByteArray();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public long getFileSize() {
    return dataFile.length();
  }

  @Override
  public void setData(final byte[] data) throws Exception {
    if (!dataFile.exists()) {
      logger.debug("Data file does not yet exist: " + fileStoreDirectoryPath + name);
      dataFile.createNewFile();
    }
    if (!dataFile.canWrite()) {
      throw new Exception("Cannot write to file: " + fileStoreDirectoryPath + name);
    }
    BufferedOutputStream fileOut = new BufferedOutputStream(new FileOutputStream(dataFile));
    fileOut.write(data);
    fileOut.flush();
    fileOut.close();
  }

  @Override
  public InputStream getInputStream() throws Exception {
    if (!dataFile.canRead()) {
      throw new Exception("Cannot read from file: " + fileStoreDirectoryPath + name);
    }
    BufferedInputStream fileIn = new BufferedInputStream(new FileInputStream(dataFile));
    return fileIn;
  }

}
