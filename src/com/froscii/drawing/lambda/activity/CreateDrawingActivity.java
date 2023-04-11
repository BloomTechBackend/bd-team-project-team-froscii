package com.froscii.drawing.lambda.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.froscii.drawing.Exceptions.CollectionNotFoundException;
import com.froscii.drawing.Exceptions.DrawingExistsException;
import com.froscii.drawing.Exceptions.DrawingNotFoundException;
import com.froscii.drawing.Models.DrawingModel;
import com.froscii.drawing.Models.ModelConverter;
import com.froscii.drawing.dynamodb.CollectionDao;
import com.froscii.drawing.dynamodb.Drawing;
import com.froscii.drawing.dynamodb.DrawingCollection;
import com.froscii.drawing.dynamodb.DrawingDao;
import com.froscii.drawing.lambda.request.CreateDrawingRequest;
import com.froscii.drawing.lambda.result.CreateDrawingResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateDrawingActivity implements RequestHandler<CreateDrawingRequest, CreateDrawingResult>{
    private final Logger log = LogManager.getLogger();
    private final DrawingDao drawingDao;
    private final CollectionDao collectionDao;

    @Inject
    public CreateDrawingActivity(DrawingDao drawingDao, CollectionDao collectionDao) {
        this.drawingDao = drawingDao;
        this.collectionDao = collectionDao;
    }
    @Override
    public CreateDrawingResult handleRequest(final CreateDrawingRequest request, Context context) {
        log.info("Received CreateDrawingRequest {} ", request);
        // Check to make sure the given drawing does not already exist
        try {
            if (drawingDao.getDrawing(request.getName()) != null) {
                throw new DrawingExistsException();
            }
        } catch (DrawingNotFoundException ignore) {}
        if (collectionDao.getCollection("home") == null) {
            throw new CollectionNotFoundException("The Home collection is missing.");
        }

        // Create a drawing
        Drawing drawing = new Drawing();
        drawing.setName(request.getName());
        drawing.setText(request.getText());
        drawingDao.saveDrawing(drawing);

        // Save drawing to the Home Collection
        DrawingCollection home = collectionDao.getCollection("home");
        home.addDrawing(drawing);
        collectionDao.saveCollection(home);

        DrawingModel drawingModel = new ModelConverter().toDrawingModel(drawing);
        return new CreateDrawingResult.Builder().withModel(drawingModel).build();
    }
}
