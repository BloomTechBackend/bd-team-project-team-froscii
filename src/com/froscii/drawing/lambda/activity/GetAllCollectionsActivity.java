package com.froscii.drawing.lambda.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.froscii.drawing.Exceptions.CollectionNotFoundException;
import com.froscii.drawing.Models.CollectionModel;
import com.froscii.drawing.Models.ModelConverter;
import com.froscii.drawing.dynamodb.CollectionDao;
import com.froscii.drawing.dynamodb.DrawingCollection;
import com.froscii.drawing.lambda.request.GetAllCollectionsRequest;
import com.froscii.drawing.lambda.result.GetAllCollectionsResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GetAllCollectionsActivity implements RequestHandler<GetAllCollectionsRequest, GetAllCollectionsResult> {
    private final Logger log = LogManager.getLogger();
    private final CollectionDao collectionDao;

    /**
     * Create new CreateCollectionActivity
     */
    @Inject
    public GetAllCollectionsActivity(CollectionDao collectionDao) {
        this.collectionDao = collectionDao;
    }
    @Override
    public GetAllCollectionsResult handleRequest(final GetAllCollectionsRequest request, Context context) {
        log.info("Received GetCollectionRequest {} ", request);
        // Check to make sure the given collection name already exists
        List<DrawingCollection> collections = collectionDao.getAllCollections();
        if (collections == null) {
            throw new CollectionNotFoundException("There are no collections.");
        }
        // Convert each collection into a collectionModel
        List<CollectionModel> allCollectionsModel = new ArrayList<>();
        for (DrawingCollection collection: collections) {
            CollectionModel collectionModel = new ModelConverter().toDrawingCollectionModel(collection);
            // Add each collectionModel to allCollectionsModel
            allCollectionsModel.add(collectionModel);
        }
        return new GetAllCollectionsResult.Builder().withModel(allCollectionsModel).build();
    }
}
