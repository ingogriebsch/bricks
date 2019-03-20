/*-
 * #%L
 * Bricks Assemble
 * %%
 * Copyright (C) 2018 - 2019 Ingo Griebsch
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.github.ingogriebsch.bricks.assemble.loader.zip;

import static java.io.File.createTempFile;

import static org.apache.commons.io.IOUtils.copy;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.github.ingogriebsch.bricks.assemble.loader.ResourceLoader;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ZipExtractingResourceLoader implements ResourceLoader {

    @NonNull
    private final ResourceLoader delegate;
    @NonNull
    private final ZipEntryLocationProvider zipEntryLocationProvider;

    @Override
    public InputStream load(@NonNull String id) throws IOException {
        InputStream resource = delegate.load(id);
        if (resource == null) {
            return null;
        }

        File tempFile = createTempFile(randomAlphabetic(10), null);
        try {
            try (OutputStream fos = new FileOutputStream(tempFile)) {
                copy(resource, fos);
            }

            try (ZipFile zipFile = new ZipFile(tempFile)) {
                ZipEntry entry = zipFile.getEntry(zipEntryLocationProvider.get(id));
                if (entry == null) {
                    return null;
                }
                return zipFile.getInputStream(entry);
            }
        } finally {
            deleteQuietly(tempFile);
        }
    }

    private void deleteQuietly(File file) {
        try {
            file.delete();
        } catch (Exception e) {
        }
    }

}
