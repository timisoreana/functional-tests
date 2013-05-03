/**
 * Copyright (C) 2011  JTalks.org Team
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package org.jtalks.tests.jcommune.mail.mailtrap.exceptions;

import java.io.IOException;

/**
 * Thrown if some I/O errors happened when getting data from Mailtrap API messages endpoint
 *
 * @author Guram Savinov
 */
public class CouldNotGetMessagesException extends IOException {

    public CouldNotGetMessagesException(Throwable cause) {
        super("Could not get data from Mailtrap messages endpoint", cause);
    }

}