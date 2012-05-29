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

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Gareth Smith <gareth@track-mate.info>
 */
public class DefaultFTPClient implements FTPClient {

  /** The timeout in milliseconds for the socket connection (value = 2 minutes). */
  private static final int SOCKET_TIMEOUT_MILLIS = 120000;
  
  /** The FTP connection that this FTPClient wraps. */
  private org.apache.commons.net.ftp.FTPClient ftpClient;
  
  /** {@inheritDoc} */
  public void connect(String hostname, String username, String password) throws Exception {
    try {
      ftpClient = new org.apache.commons.net.ftp.FTPClient();
      ftpClient.connect(hostname);
      ftpClient.setSoTimeout(SOCKET_TIMEOUT_MILLIS);
      ftpClient.login(username, password);
    } catch (Exception e) {
      throw new Exception("Failed to connect to FTP server", e);
    }
  }

  /** {@inheritDoc} */
  public void disconnect() throws Exception {
    try {
      if (ftpClient != null && ftpClient.isConnected()) {
        ftpClient.disconnect();
      }
    } catch (Exception e) {
      throw new Exception("Failed to disconnect from FTP server", e);
    } finally {
      ftpClient = null;
    }
  }

  /** {@inheritDoc} */
  public FileData download(FTPFile remoteFile) throws Exception {
    return download(remoteFile.getName());
  }

  /** {@inheritDoc} */
  public FileData download(String remoteFileName) throws Exception {
    byte[] dataBytes;
    ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
    try {
      downloadFile(remoteFileName, bytesOut);
      dataBytes = bytesOut.toByteArray();
    } catch (Exception e) {
      throw new Exception("Failed to download file", e);
    } finally {
      bytesOut.flush();
      bytesOut.close();
    }
    
    DefaultFileData fileData = new DefaultFileData();
    fileData.setName(remoteFileName);
    fileData.setData(dataBytes);
    
    return fileData;
  }

  /** {@inheritDoc} */
  public File downloadToDisk(FTPFile remoteFile, String localFilePath) throws Exception {
    return downloadToDisk(remoteFile.getName(), localFilePath);
  }

  /** {@inheritDoc} */
  public File downloadToDisk(String remoteFileName, String localFilePath) throws Exception {

    OutputStream fileOut = null;
    try {
      fileOut = new BufferedOutputStream(new FileOutputStream(localFilePath));
      downloadFile(remoteFileName, fileOut);
      
    } catch (Exception e) {
      throw new Exception("Failed to download file to disk", e);
    } finally {
      if (fileOut != null) {
        fileOut.flush();
        fileOut.close();
      }
    }
    
    return new File(localFilePath);
  }

  /** {@inheritDoc} */
  public Collection<FTPFile> listFiles() throws Exception {
    org.apache.commons.net.ftp.FTPFile[] ftpFiles = ftpClient.listFiles();
    Collection<FTPFile> toReturn = new ArrayList<FTPFile>();
    for (org.apache.commons.net.ftp.FTPFile ftpFile : ftpFiles) {
      toReturn.add(new DefaultFTPFile(ftpFile.getName(), ftpFile.getSize()));
    }
    return toReturn;
  }

  /** {@inheritDoc} */
  public void changeDirectory(String directoryName) throws Exception {
    try {
      ftpClient.changeWorkingDirectory(directoryName);
    } catch (Exception e) {
      throw new Exception("Failed to change working directory", e);
    }
  }

  // ------ Private Helpers ------
  
  /**
   * Download the specified remote file to the provided output stream.
   * @param remoteFileName The name of the remote file to download.
   * @param outputStream The stream to write the remote file's contents to.
   * @throws Exception If the operation fails.
   */
  private void downloadFile(String remoteFileName, OutputStream outputStream) throws Exception {
    ftpClient.retrieveFile(remoteFileName, outputStream);
  }
}
