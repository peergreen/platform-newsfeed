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

import static com.peergreen.newsfeed.internal.RssConstants.DATE;
import static com.peergreen.newsfeed.internal.RssConstants.DESCRIPTION;
import static com.peergreen.newsfeed.internal.RssConstants.LINK;
import static com.peergreen.newsfeed.internal.RssConstants.PUB_DATE;
import static com.peergreen.newsfeed.internal.RssConstants.TITLE;
import static javax.xml.stream.XMLStreamConstants.CHARACTERS;
import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Parser of all RSS elements.
 * @author Florent Benoit
 */
public abstract class ElementParser<T extends DefaultElement> {

    /**
     * Date helper.
     */
    private final DateHelper dateHelper;

    /**
     * Default constructor.
     */
    public ElementParser() {
        this.dateHelper = new DateHelper();
    }


    /**
     * Read basic RSS stuff and apply it on the given element.
     * @param xmlStreamReader the stream to parse
     * @param element the element on which apply items.
     * @throws XMLStreamException if there is an exception while reading the stream
     */
    protected void buildElement(XMLStreamReader xmlStreamReader, T element) throws XMLStreamException {

        // Content of the characters type
        String content = "";

        // parse the feed message
        while(xmlStreamReader.hasNext()) {
            int eventType = xmlStreamReader.next();

            switch (eventType) {

            case START_ELEMENT:
                // callback
                onStartElement(xmlStreamReader, element);
                break;

            // Characters
            case CHARACTERS :
                if (xmlStreamReader.isWhiteSpace()) {
                    break;
                }
                content = content.concat(xmlStreamReader.getText());
                break;

            case END_ELEMENT:
                // Ending element

                if (TITLE.equals(xmlStreamReader.getLocalName())) {
                    element.setTitle(content);
                }
                if (LINK.equals(xmlStreamReader.getLocalName())) {
                    element.setLink(content);
                }
                if (DESCRIPTION.equals(xmlStreamReader.getLocalName())) {
                    element.setDescription(content);
                }
                if (DATE.equals(xmlStreamReader.getLocalName())) {
                    element.setDate(dateHelper.getDate(content));
                }
                if (PUB_DATE.equals(xmlStreamReader.getLocalName())) {
                    element.setPubDate(dateHelper.getDate(content));
                }

                // reset content for the next element
                content = "";

                // callback
                onEndElement(xmlStreamReader, element, content);

                // stop the process ?
                if (terminateElement(xmlStreamReader)) {
                    return;
                }
            }
        }
    }

    /**
     * Callback on starting element
     * @param xmlStreamReader
     * @param element
     * @throws XMLStreamException
     */
    protected abstract void onStartElement(XMLStreamReader xmlStreamReader, T element) throws XMLStreamException;

    /**
     * Callback on ending element
     * @param xmlStreamReader
     * @param element
     * @param content
     * @throws XMLStreamException
     */
    protected abstract void onEndElement(XMLStreamReader xmlStreamReader, T element, String content) throws XMLStreamException;

    /**
     * Element is being finished ? stopped to analyse more items.
     * @param xmlStreamReader
     * @return
     * @throws XMLStreamException
     */
    protected abstract boolean terminateElement(XMLStreamReader xmlStreamReader) throws XMLStreamException;
}
