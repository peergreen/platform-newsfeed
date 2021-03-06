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

/**
 * If there is an exception in parsing the RSS because we're not connected to internet.
 * @author Florent Benoit
 */
public class RssServiceNotConnectedException extends RssServiceException {

    /**
     *
     */
    private static final long serialVersionUID = 7334683560915144095L;

    public RssServiceNotConnectedException(String message, Throwable exception) {
        super(message, exception);
    }
}
