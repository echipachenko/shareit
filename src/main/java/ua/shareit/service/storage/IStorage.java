package ua.shareit.service.storage;

import java.io.InputStream;
import java.util.UUID;

public interface IStorage {
    UUID saveFile(InputStream is);
}
