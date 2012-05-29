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
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * A class representing a configuration properties file that is read from the
 * classpath.
 *
 * @author Gareth Smith <gareth@track-mate.info>
 */
public class Config implements Serializable {

  /** The logger for this class. */
  private static Logger logger = Logger.getLogger(Config.class); 
  
  /**
   * The default configuration properties file name. Value is
   * 'config.propeties'.
   */
  private static final String DEFAULT_CONFIG_FILENAME = "config.properties";

  /** The name of the configuration properties file that this class represents. */
  private final String configFileName;

  /**
   * The properties held within the configuration file that this class
   * represents.
   */
  private Properties configProperties;

  /**
   * Create a new instance of this class, using the
   * {@link #DEFAULT_CONFIG_FILENAME}.
   */
  public Config() {
    this(DEFAULT_CONFIG_FILENAME);
  }

  /**
   * Create a new instance of this class using the names configuration
   * properties file.
   *
   * @param configFileName
   *          {@link #configFileName}
   */
  public Config(final String configFileName) {
    this.configFileName = configFileName;
  }

  /**
   * Get the value of the specified property from the configuration properties
   * file.
   *
   * @param key
   *          The key of which to get the value.
   * @return The Value for the specified key, or null if no value exists for
   *         that key.
   * @throws Exception
   *           If the specified configuration properties file could not be read
   *           from the classpath.
   */
  public final String getProperty(final String key) throws Exception {
    loadProperties();
    return configProperties.getProperty(key);
  }
  
  @Override
  public String toString() {
    String toReturn = "-- ERROR --";
    try {
      loadProperties();
      toReturn = configProperties.toString();
    } catch (Exception e) {
      logger.fatal("Failed to load config properties.", e);
    }
    return toReturn;
  }

  // ------ Private Helpers ------
  
  /**
   * Load the properties for this Config from the underlying properties file.
   */
  private void loadProperties() throws Exception {
    if (configProperties == null) {
      InputStream propsStream;
      File configFile = new File(configFileName);
      if (configFile.exists()) {
        propsStream = new BufferedInputStream(new FileInputStream(configFile));
      } else {
        propsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(configFileName);
      }
      if (propsStream == null) {
        throw new Exception("Failed to find properties file '" + configFileName + "' on the classpath.");
      }
  
      configProperties = new Properties();
      configProperties.load(propsStream);
    }
  }
  
}
