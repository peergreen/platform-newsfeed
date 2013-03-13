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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Helper for reading date of RSS.
 * @author Florent Benoit
 */
public class DateHelper {

    /**
     * Needs to be compliant with RFC 822 Date
     */
    private final SimpleDateFormat rfc822DateFormat;

    /**
     * 8601 Date format.
     */
    private final SimpleDateFormat rfc8601DateFormat;

    public DateHelper() {
        this.rfc822DateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        this.rfc8601DateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz" );
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        rfc8601DateFormat.setTimeZone(timeZone);
    }

    /**
     * Try to parse the given date by using different RFC
     * @param date the date to parse
     * @return the built date
     */
    public Date getDate(String date) {
        try {
            return rfc822DateFormat.parse(date);
        } catch (ParseException e) {
            try {
                // Cleanup the date
                if (date.endsWith("Z") ) {
                    date = date.substring( 0, date.length() - 1) + "GMT-00:00";
                } else {
                    int delta = 6;
                    String dateValue = date.substring( 0, date.length() - delta);
                    String deltaGMT = date.substring( date.length() - delta, date.length() );
                    date = dateValue.concat("GMT").concat(deltaGMT);
                }
                return rfc8601DateFormat.parse(date);
            } catch (ParseException e1) {
                e1.printStackTrace();
                return null;
            }
        }
    }
}
