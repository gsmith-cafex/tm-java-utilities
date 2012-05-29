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
import java.util.Collection;

/**
 * A client for connecting to an FTP server.
 * @author Gareth Smith <gareth@track-mate.info>
 */
public interface FTPClient {
  
  /** Connect to the specified server using the credentials provided.
   * 
   * @param hostname The hostname to connect to.
   * @param username The username to authenticate with.
   * @param password The password to authenticate with. 
   * @throws Exception If the operation fails.
   */
  void connect(final String hostname, final String username, final String password) throws Exception;
  
  /** Disconnect from the server.
   * 
   * @throws Exception If the operation fails.
   */
  void disconnect() throws Exception;
  
  /** Change the working directory.
   * 
   * @param directoryName The name of the directory to change to. 
   * @throws Exception If the operation fails.
   */
  void changeDirectory(final String directoryName) throws Exception;
  
  /** List the files in the current working directory.
   * 
   * @return A collection of the file names in the current working directory.
   * @throws Exception If the operation fails.
   */
  Collection<FTPFile> listFiles() throws Exception;

  /** Download the specified remote file.
   *
   * @param remoteFile The remote file to download.
   * @return A FileData representing the remote file.
   * @throws Exception If the operation fails.
   */
  FileData download(final FTPFile remoteFile) throws Exception;
  
  /** Download the specified remote file.
   * 
   * @param remoteFileName The name of the file to download.
   * @return A FileData representing the remote file.
   * @throws Exception If the operation fails.
   */
  FileData download(final String remoteFileName) throws Exception;
  
  /** Download the specified remote file to a location on disk.
   *
   * @param remoteFile The remote file to download.
   * @param localFilePath The path to the location on disk to save the downloaded file.
   * @return A File representing the location of the downloaded file.
   * @throws Exception If the operation fails.
   */
  File downloadToDisk(final FTPFile remoteFile, final String localFilePath) throws Exception;

  /** Download the specified remote file to a location on disk.
   * 
   * @param remoteFileName The name of the remote file to download.
   * @param localFilePath The path to the location on disk to save the downloaded file.
   * @return A File representing the location of the downloaded file.
   * @throws Exception If the operation fails.
   */
  File downloadToDisk(final String remoteFileName, final String localFilePath) throws Exception;
}
