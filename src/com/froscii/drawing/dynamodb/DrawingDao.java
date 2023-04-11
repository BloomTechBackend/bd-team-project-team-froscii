package com.froscii.drawing.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.froscii.drawing.Exceptions.DrawingNotFoundException;

import javax.inject.Inject;
import java.util.Objects;

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
    public Drawing getDrawing(String name) {
        Drawing drawing;
        drawing = this.dynamoDbMapper.load(Drawing.class, name);
        if (drawing == null) {
            throw new DrawingNotFoundException("Could not find Drawing with name '" + name + "'");
        }
        return drawing;
    }
    public Drawing saveDrawing(Drawing drawing) {
        this.dynamoDbMapper.save(drawing);
        return this.dynamoDbMapper.load(Drawing.class, drawing.getName());
    }
}
