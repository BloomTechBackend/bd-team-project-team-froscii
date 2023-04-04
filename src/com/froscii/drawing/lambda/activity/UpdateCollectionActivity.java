package com.froscii.drawing.lambda.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.froscii.drawing.Exceptions.CollectionNotFoundException;
import com.froscii.drawing.Models.CollectionModel;
import com.froscii.drawing.Models.ModelConverter;
import com.froscii.drawing.dynamodb.CollectionDao;
import com.froscii.drawing.dynamodb.DrawingCollection;
import com.froscii.drawing.lambda.request.GetCollectionRequest;
import com.froscii.drawing.lambda.request.UpdateCollectionRequest;
import com.froscii.drawing.lambda.result.GetCollectionResult;
import com.froscii.drawing.lambda.result.UpdateCollectionResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class UpdateCollectionActivity implements RequestHandler<UpdateCollectionRequest, UpdateCollectionResult> {
    private final Logger log = LogManager.getLogger();
    private final CollectionDao collectionDao;

    /**
     * Create new CreateCollectionActivity
     */
    @Inject
    public UpdateCollectionActivity(CollectionDao collectionDao) {
        this.collectionDao = collectionDao;
    }
    @Override
    public UpdateCollectionResult handleRequest(final UpdateCollectionRequest request, Context context) {
        log.info("Received UpdateCollectionRequest {} ", request);
        // Check to make sure the given collection name already exists
        DrawingCollection collection = collectionDao.getCollection(request.getCollectionName());
        if (collection == null) {
            throw new CollectionNotFoundException();
        }
        // If the drawingId is not already in the list, add it to the collection
        if (!collection.getDrawingIds().contains(request.getDrawingId())) {
            collection.addDrawing(request.getDrawingId());
        }
        // Convert collection into a collectionModel
        CollectionModel collectionModel = new ModelConverter().toDrawingCollectionModel(collection);
        return new UpdateCollectionResult.Builder().withModel(collectionModel).build();
    }
}
