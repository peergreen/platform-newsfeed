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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.peergreen.newsfeed.FeedMessage;
import com.peergreen.newsfeed.Rss;

/**
 * RSS object with its items.
 * @author Florent Benoit
 */
public class DefaultRss extends DefaultElement implements Rss {

    private final List<FeedMessage> items;

    public DefaultRss() {
        this.items= new ArrayList<>();
    }

    @Override
    public Collection<FeedMessage> getItems() {
        return items;
    }

    @Override
    public Collection<FeedMessage> getItems(int maxNumber) {
        Collection<FeedMessage> list = new ArrayList<>();
        int i = 0;
        while (i < maxNumber && i < items.size()) {
            list.add(items.get(i));
            i++;
        }
        return list;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\nitems :\n");
        for (FeedMessage message : items) {
            sb.append(message).append("\n");
        }
        return sb.toString();
    }


}
