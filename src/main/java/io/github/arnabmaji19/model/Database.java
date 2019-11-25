package io.github.arnabmaji19.model;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import java.io.*;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

//Singleton class for Database Operations
public class Database {

    private static Database instance = new Database();

    private static final String DATABASE = "FilesDriveDatabase";
    private MongoDatabase database;
    private GridFSBucket gridFSBucket;

    private Database(){}

    public void connect(){
        //Setting Up MongoDB for POJOs
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .build();
        MongoClient client = MongoClients.create(settings);
        this.database = client.getDatabase(DATABASE);
        gridFSBucket = GridFSBuckets.create(database);
    }

    public MongoCollection<User> getUsersCollection(){
        return database.getCollection("users", User.class);
    }

    public MongoDatabase getDatabase(){
        return this.database;
    }

    public ObjectId uploadToDatabase(File file) throws IOException{
        InputStream stream = new FileInputStream(file);
        return gridFSBucket.uploadFromStream(file.getName(), stream);
    }

    public void downloadFromDatabase(ObjectId fileId, String filePath) throws IOException{
        FileOutputStream stream = new FileOutputStream(filePath);
        gridFSBucket.downloadToStream(fileId, stream);
        stream.close();
    }

    public MongoCollection<FileData> getUserDrive(String driveId){
        return database.getCollection(driveId, FileData.class);
    }

    public static Database getInstance() {
        return instance;
    }


}
