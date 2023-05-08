package com.froscii.drawing.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.TransactionLoadRequest;
import com.froscii.drawing.Exceptions.CollectionNotFoundException;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CollectionDao {
    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Creates a DrawingCollection object from an item.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the album_track table
     */
    @Inject
    public CollectionDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }
    public DrawingCollection getCollection(String name) {
        DrawingCollection collection = this.dynamoDbMapper.load(DrawingCollection.class, name);
        if (collection == null) {
            throw new CollectionNotFoundException();
        }
        return collection;
    }
    public List<DrawingCollection> getAllCollections() {
        // Create an empty scan expression to return all items.
        List<DrawingCollection> scanResult = dynamoDbMapper.scan(DrawingCollection.class, new DynamoDBScanExpression());
        System.out.println(scanResult.size());
        return scanResult;
    }

    public DrawingCollection saveCollection(DrawingCollection collection) {
        this.dynamoDbMapper.save(collection);
        return this.dynamoDbMapper.load(DrawingCollection.class, collection.getName());
    }

}
