package com.froscii.drawing.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.froscii.drawing.Exceptions.CollectionNotFoundException;

import javax.inject.Inject;

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
}
