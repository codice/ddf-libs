/**
 * Copyright (c) Codice Foundation
 * <p>
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or any later version.
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details. A copy of the GNU Lesser General Public License
 * is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 */
package org.codice.ddf.checksum.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;

import org.apache.commons.io.IOUtils;
import org.codice.ddf.checksum.AbstractChecksumProvider;
import org.slf4j.LoggerFactory;
import org.slf4j.ext.XLogger;

/**
 * The Adler32 checksum algorithm is nearly as reliable as CRC32 but is significantly faster.
 * For the purposes of identifying potential duplicate content in DDF, Adler32 is sufficiently
 * accurate.
 */
public class Adler32ChecksumProvider extends AbstractChecksumProvider {

    private static final String DIGEST_ALGORITHM = "Adler32";

    @Override
    public String calculateChecksum(InputStream inputStream) throws IOException {

        if (inputStream == null) {
            throw new IllegalArgumentException("InputStream cannot be null");
        }

        long checkSumValue = 0L;

        try (CheckedInputStream cis = new CheckedInputStream(inputStream, new Adler32())) {
            byte[] buffer = new byte[128];
            while (cis.read(buffer) >= 0) {
                // read the input stream to calculate the checksum
            }
            checkSumValue = cis.getChecksum()
                    .getValue();
        }

        return Long.toHexString(checkSumValue);
    }

    @Override
    public String getChecksumAlgorithm() {
        return DIGEST_ALGORITHM;
    }

}