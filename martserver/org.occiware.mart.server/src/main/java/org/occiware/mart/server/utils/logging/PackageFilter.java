/**
 * Copyright (c) 2015-2017 Inria
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 * Contributors:
 * - Christophe Gourdin <christophe.gourdin@inria.fr>
 */
package org.occiware.mart.server.utils.logging;

import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Specific filter for root logger, this avoid to log org.quartz and and other classes to standard logs (info.log etc.).
 *
 * @author Christophe Gourdin
 */
public class PackageFilter extends Filter {

    /**
     * Example for quartz lib.
     */
    public static final String QUARTZ_LOGGER_NAME = "org.quartz";
    /**
     * Example for jclouds lib.
     */
    public static final String JCLOUD_BLOBSTORE_LOGGER_NAME = "jclouds.blobstore";

    public static final String COM_MCHANGE = "com.mchange";

    @Override
    public int decide(LoggingEvent event) {

        if (event.getLoggerName().contains(QUARTZ_LOGGER_NAME) || event.getLoggerName().contains(JCLOUD_BLOBSTORE_LOGGER_NAME)
                || event.getLoggerName().contains(COM_MCHANGE)) {
            return DENY;
        }
        return NEUTRAL;
    }

}
