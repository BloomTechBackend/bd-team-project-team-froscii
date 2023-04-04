package com.froscii.drawing.lambda.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.froscii.drawing.Exceptions.CollectionExistsException;
import com.froscii.drawing.Exceptions.CollectionNotFoundException;
import com.froscii.drawing.Models.CollectionModel;
import com.froscii.drawing.Models.ModelConverter;
import com.froscii.drawing.dynamodb.CollectionDao;
import com.froscii.drawing.dynamodb.DrawingCollection;
import com.froscii.drawing.lambda.request.CreateCollectionRequest;
import com.froscii.drawing.lambda.request.GetCollectionRequest;
import com.froscii.drawing.lambda.result.CreateCollectionResult;
import com.froscii.drawing.lambda.result.GetCollectionResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetCollectionActivity implements RequestHandler<GetCollectionRequest, GetCollectionResult> {
    private final Logger log = LogManager.getLogger();
    private final CollectionDao collectionDao;

    /**
     * Create new CreateCollectionActivity
     */
    @Inject
    public GetCollectionActivity(CollectionDao collectionDao) {
        this.collectionDao = collectionDao;
    }
    @Override
    public GetCollectionResult handleRequest(final GetCollectionRequest request, Context context) {
        log.info("Received GetCollectionRequest {} ", request);
        // Check to make sure the given collection name already exists
        DrawingCollection collection = collectionDao.getCollection(request.getName());
        if (collection == null) {
            throw new CollectionNotFoundException();
        }
        // Convert collection into a collectionModel
        CollectionModel collectionModel = new ModelConverter().toDrawingCollectionModel(collection);
        return new GetCollectionResult.Builder().withModel(collectionModel).build();
    }
}
