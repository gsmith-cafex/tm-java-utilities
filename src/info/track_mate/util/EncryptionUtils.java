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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;

/**
 * Helper class for encryption.
 * @author Gareth Smith <gareth@track-mate.info>
 */
public final class EncryptionUtils {

  /** Logger for this class. */
  private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EncryptionUtils.class);

  /** The default algorithm used to encrypt Strings. */
  public static final String DEFAULT_ENCRYPTION_ALGORITHM = "MD5";

  /**
   * Encrypt the specified String. This method is equivalent to calling encryptString(toEncrypt, "MD5", true);
   * @param toEncrypt The String to encrypt.
   * @return The encrypted String.
   * @throws Exception
   */
  public static String encryptString(String toEncrypt) {
    try {
      return encryptString(toEncrypt, DEFAULT_ENCRYPTION_ALGORITHM, true);
    } catch (NoSuchAlgorithmException e) {
      logger.fatal("Failed to find default encryption algorithm", e);
      return null;
    }
  }

  /**
   * Encrypt the specified String.
   * @param toEncrypt The String to encrypt.
   * @param algorithm The algorithm to use for the encryption. See Appendix A in the
   * <a href="../../../technotes/guides/security/crypto/CryptoSpec.html#AppA">Java Cryptography Architecture API Specification &amp; Reference </a>
   * for information about standard algorithm names.
   * @param encodeAsBase64 If true then encode the output of the encryption algorithm as Base64.
   * @return The encrypted String.
   * @throws NoSuchAlgorithmException If the specified algorithm doesn't exist or is unavailable.
   */
  public static String encryptString(String toEncrypt, String algorithm, boolean encodeAsBase64) throws NoSuchAlgorithmException {
    MessageDigest md5Digest = MessageDigest.getInstance(algorithm);
    byte[] encryptedBytes = md5Digest.digest(toEncrypt.getBytes());
    if (encodeAsBase64) {
      encryptedBytes = Base64.encodeBase64(encryptedBytes);
    }
    String encryptedString = new String(encryptedBytes);
    return encryptedString;
  }

}
