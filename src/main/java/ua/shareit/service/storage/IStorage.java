package ua.shareit.service.storage;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public interface IStorage {
    UUID saveFile(InputStream is);

    void readFileToOutputStream(OutputStream os, UUID uuid);
}
