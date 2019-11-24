package io.github.arnabmaji19.model;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

//Singleton class for Database Operations
public class Database {

    private static Database instance = new Database();

    private static final String DATABASE = "FilesDriveDatabase";
    private MongoDatabase database;

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
    }

    public MongoDatabase getDatabase(){
        return this.database;
    }

    public static Database getInstance() {
        return instance;
    }
}
