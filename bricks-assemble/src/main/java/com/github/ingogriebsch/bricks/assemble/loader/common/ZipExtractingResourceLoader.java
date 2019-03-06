package com.github.ingogriebsch.bricks.assemble.loader.common;

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
        InputStream zipInputStream = delegate.load(id);
        if (zipInputStream == null) {
            return null;
        }

        File tempFile = createTempFile(randomAlphabetic(10), null);
        try {
            try (OutputStream fos = new FileOutputStream(tempFile)) {
                copy(zipInputStream, fos);
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
