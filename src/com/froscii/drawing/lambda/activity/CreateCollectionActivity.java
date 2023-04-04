package com.froscii.drawing.lambda.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.froscii.drawing.Exceptions.CollectionExistsException;
import com.froscii.drawing.Models.CollectionModel;
import com.froscii.drawing.dynamodb.CollectionDao;
import com.froscii.drawing.dynamodb.DrawingCollection;
import com.froscii.drawing.lambda.request.CreateCollectionRequest;
import com.froscii.drawing.lambda.result.CreateCollectionResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateCollectionActivity implements RequestHandler<CreateCollectionRequest, CreateCollectionResult> {
    private final Logger log = LogManager.getLogger();
    private final CollectionDao collectionDao;

    /**
     * Create new CreateCollectionActivity
     */
    @Inject
    public CreateCollectionActivity(CollectionDao collectionDao) {
        this.collectionDao = collectionDao;
    }
    @Override
    public CreateCollectionResult handleRequest(final CreateCollectionRequest request, Context context) {
        log.info("Received CreateCollectionRequest {} ", request);
        // Check to make sure the given collection name does not already exist
        if (collectionDao.getCollection(request.getName()) != null) {
            throw new CollectionExistsException();
        }
        // Create a collection
        CollectionModel collectionModel = new CollectionModel.Builder().withName(request.getName()).build();
        return new CreateCollectionResult.Builder().withModel(collectionModel).build();
    }
}
