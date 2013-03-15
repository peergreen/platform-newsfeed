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
package com.peergreen.newsfeed.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;

import com.peergreen.newsfeed.Rss;
import com.peergreen.newsfeed.RssService;
import com.peergreen.newsfeed.RssServiceException;
import com.peergreen.newsfeed.RssServiceNotConnectedException;

/**
 * Implementation of RSS parser.
 * @author Florent Benoit
 */
@Component
@Provides
@Instantiate
public class BasicRssService implements RssService {

    /**
     * Rss parser.
     */
    private final RssParser rssParser;

    /**
     * Creates the RSS parser.
     */
    public BasicRssService() {
        this.rssParser = new RssParser();
    }

    /**
     * Parse the given URL.
     * @param url
     * @return
     * @throws RssServiceException
     */
    @Override
    public Rss parse(URL url) throws RssServiceException {
        URLConnection urlConnection;
        try {
            urlConnection = url.openConnection();
        } catch (IOException e) {
            throw new RssServiceException(String.format("Unable to open connection on URL %s", url), e);
        }
        // Timeout
        urlConnection.setConnectTimeout(500);
        urlConnection.setReadTimeout(500);
        urlConnection.setDefaultUseCaches(false);
        try (InputStream inputStream = urlConnection.getInputStream(); InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
            return parse(inputStreamReader);
        } catch (UnknownHostException e) {
            throw new RssServiceNotConnectedException(String.format("Unable to parse URL %s", url), e);
        } catch (IOException e) {
            throw new RssServiceException(String.format("Unable to parse URL %s", url), e);
        }
    }


    @Override
    public Rss parse(Reader reader) throws RssServiceException {
        // Create XML Stream reader
        XMLStreamReader xmlStreamReader;
        try {
            xmlStreamReader = XMLInputFactory.newFactory().createXMLStreamReader(reader);
        } catch (XMLStreamException | FactoryConfigurationError e) {
            throw new RssServiceException("Unable to parse", e);
        }

        // Compute messages
        try {
            return rssParser.getRss(xmlStreamReader);
        } catch (XMLStreamException e) {
            throw new RssServiceException("Unable to parse", e);
        }
    }


}
