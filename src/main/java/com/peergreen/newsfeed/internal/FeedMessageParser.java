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

import static com.peergreen.newsfeed.internal.RssConstants.ITEM;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.peergreen.newsfeed.FeedMessage;

public class FeedMessageParser extends ElementParser<DefaultFeedMessage>  {

    /**
     * Read the feed item message and return it.
     * @param xmlStreamReader the stream to parse
     * @return the feedmessage
     * @throws XMLStreamException if there is an exception while reading the stream
     */
    public FeedMessage getFeedMessage(XMLStreamReader xmlStreamReader) throws XMLStreamException {

        DefaultFeedMessage feedMessage = new DefaultFeedMessage();

        buildElement(xmlStreamReader, feedMessage);

        return feedMessage;
    }



    @Override
    protected void onStartElement(XMLStreamReader xmlStreamReader, DefaultFeedMessage element)
            throws XMLStreamException {

    }


    @Override
    protected void onEndElement(XMLStreamReader xmlStreamReader, DefaultFeedMessage element, String content)
            throws XMLStreamException {

    }


    @Override
    protected boolean terminateElement(XMLStreamReader xmlStreamReader) throws XMLStreamException {
        return (ITEM.equals(xmlStreamReader.getLocalName()));
    }


}
