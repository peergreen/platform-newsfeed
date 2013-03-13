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

import java.util.Date;

import com.peergreen.newsfeed.Element;

/**
 * Element of a RSS.
 * @author Florent Benoit
 */
public class DefaultElement implements Element {

    private String title;
    private String link;
    private String description;

    private Date date;
    private Date pubDate;


    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getLink() {
        return link;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (title != null) {
            sb.append(title).append(": ");
        }
        if (link != null) {
            sb.append(link);
        }
        if (description != null) {
            sb.append(" Description =\"").append(description).append("\"");
        }
        if (date != null) {
            sb.append(", date=").append(date);
        }
        if (pubDate != null) {
            sb.append(", pubDate=").append(pubDate);
        }
        return sb.toString();
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public Date getPubDate() {
        return pubDate;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }


}
