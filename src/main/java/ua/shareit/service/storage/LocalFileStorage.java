package ua.shareit.service.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import ua.shareit.service.exception.StorageException;

@Service
public class LocalFileStorage implements IStorage {

    private static final File ROOT = new File("./storage/");

    @PostConstruct
    public void init(){
        ROOT.mkdirs();
    }

    @Override
    public UUID saveFile(InputStream is) {
        UUID uuid = UUID.randomUUID();
        File dest = new File(ROOT, uuid.toString() + ".dat");
        try (FileOutputStream fos = new FileOutputStream(dest)) {
            IOUtils.copy(is, fos);
            return uuid;
        } catch (IOException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void readFileToOutputStream(OutputStream os, UUID uuid) {
        File dest = new File(ROOT, uuid.toString() + ".dat");
        try (InputStream is = new FileInputStream(dest)) {
            IOUtils.copy(is, os);
        } catch (IOException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        FileUtils.deleteQuietly(new File(ROOT, uuid.toString() + ".dat"));
    }
}
