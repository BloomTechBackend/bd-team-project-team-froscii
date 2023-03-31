package com.froscii.drawing.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.froscii.drawing.Exceptions.DrawingNotFoundException;

import javax.inject.Inject;

public class DrawingDao {
    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Creates a DrawingCollection object from an item.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the album_track table
     */
    @Inject
    public DrawingDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }
    public Drawing getDrawing(int id) {
        Drawing drawing = this.dynamoDbMapper.load(Drawing.class, id);
        if (drawing == null) {
            throw new DrawingNotFoundException();
        }
        return drawing;
    }
}
