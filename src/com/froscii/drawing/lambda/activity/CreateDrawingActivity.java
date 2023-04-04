package com.froscii.drawing.lambda.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.froscii.drawing.Exceptions.CollectionExistsException;
import com.froscii.drawing.Exceptions.DrawingExistsException;
import com.froscii.drawing.Models.CollectionModel;
import com.froscii.drawing.Models.DrawingModel;
import com.froscii.drawing.dynamodb.CollectionDao;
import com.froscii.drawing.dynamodb.DrawingDao;
import com.froscii.drawing.lambda.request.CreateCollectionRequest;
import com.froscii.drawing.lambda.request.CreateDrawingRequest;
import com.froscii.drawing.lambda.result.CreateCollectionResult;
import com.froscii.drawing.lambda.result.CreateDrawingResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateDrawingActivity implements RequestHandler<CreateDrawingRequest, CreateDrawingResult>{
    private final Logger log = LogManager.getLogger();
    private final DrawingDao drawingDao;

    /**
     * Create new CreateCollectionActivity
     */
    @Inject
    public CreateDrawingActivity(DrawingDao drawingDao) {
        this.drawingDao = drawingDao;
    }
    @Override
    public CreateDrawingResult handleRequest(final CreateDrawingRequest request, Context context) {
        log.info("Received CreateDrawingRequest {} ", request);
        // Check to make sure the given drawing does not already exist
        if (drawingDao.getDrawing(request.getId()) != null) {
            throw new DrawingExistsException();
        }
        // Create a drawing
        DrawingModel drawingModel = new DrawingModel.Builder()
                .withName(request.getName())
                .withText(request.getText())
                .build();
        return new CreateDrawingResult.Builder().withModel(drawingModel).build();
    }
}
