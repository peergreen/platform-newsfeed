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

import com.peergreen.newsfeed.Rss;

public class RssParser extends ElementParser<DefaultRss>  {

    private final FeedMessageParser feedMessageParser;


    public RssParser() {
        this.feedMessageParser = new FeedMessageParser();
    }


    public Rss getRss(XMLStreamReader xmlStreamReader) throws XMLStreamException {

        DefaultRss rss = new DefaultRss();

        buildElement(xmlStreamReader, rss);
        return rss;
    }

    @Override
    protected void onStartElement(XMLStreamReader xmlStreamReader, DefaultRss defaultRss) throws XMLStreamException {
        if (ITEM.equals(xmlStreamReader.getLocalName())) {
            defaultRss.getItems().add(feedMessageParser.getFeedMessage(xmlStreamReader));
        }

    }


    @Override
    protected void onEndElement(XMLStreamReader xmlStreamReader, DefaultRss element, String content) throws XMLStreamException {
        // TODO Auto-generated method stub

    }


    @Override
    protected boolean terminateElement(XMLStreamReader xmlStreamReader) throws XMLStreamException {
        // TODO Auto-generated method stub
        return false;
    }







}
