/**
 * Copyright 2013 Peergreen S.A.S.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.peergreen.newsfeed;

import java.io.Reader;
import java.net.URL;

/**
 * Allows to parse RSS.
 * @author Florent Benoit
 */
public interface RssService {

    /**
     * Parse the given reader.
     * @param reader the object used to read the stream.
     * @return the parsed object.
     * @throws RssServiceException
     */
    Rss parse(Reader reader) throws RssServiceException;

    /**
     * Parse the given URL.
     * @param url the url to parse
     * @return the parsed object.
     * @throws RssServiceException
     */
    Rss parse(URL url) throws RssServiceException;
}
