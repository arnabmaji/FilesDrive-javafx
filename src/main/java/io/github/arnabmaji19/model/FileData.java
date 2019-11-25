package io.github.arnabmaji19.model;

import org.bson.types.ObjectId;

public class FileData {
    private ObjectId id;
    private ObjectId fileId;
    private String fileName;
    private String owner;
    private String uploadDate;
    private double sizeInMB;

    public FileData(){}

    public FileData(ObjectId id, ObjectId fileId, String fileName, String owner, String uploadDate, double sizeInMB) {
        this.id = id;
        this.fileId = fileId;
        this.fileName = fileName;
        this.owner = owner;
        this.uploadDate = uploadDate;
        this.sizeInMB = sizeInMB;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getFileId() {
        return fileId;
    }

    public void setFileId(ObjectId fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public double getSizeInMB() {
        return sizeInMB;
    }

    public void setSizeInMB(double sizeInMB) {
        this.sizeInMB = sizeInMB;
    }
}
