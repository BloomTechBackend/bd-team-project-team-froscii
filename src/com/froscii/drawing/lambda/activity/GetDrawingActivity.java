package com.froscii.drawing.lambda.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.froscii.drawing.Exceptions.DrawingExistsException;
import com.froscii.drawing.Exceptions.DrawingNotFoundException;
import com.froscii.drawing.Models.DrawingModel;
import com.froscii.drawing.Models.ModelConverter;
import com.froscii.drawing.dynamodb.Drawing;
import com.froscii.drawing.dynamodb.DrawingDao;
import com.froscii.drawing.lambda.request.CreateDrawingRequest;
import com.froscii.drawing.lambda.request.GetDrawingRequest;
import com.froscii.drawing.lambda.result.CreateDrawingResult;
import com.froscii.drawing.lambda.result.GetDrawingResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetDrawingActivity implements RequestHandler<GetDrawingRequest, GetDrawingResult> {
    private final Logger log = LogManager.getLogger();
    private final DrawingDao drawingDao;

    /**
     * Create new CreateCollectionActivity
     */
    @Inject
    public GetDrawingActivity(DrawingDao drawingDao) {
        this.drawingDao = drawingDao;
    }
    @Override
    public GetDrawingResult handleRequest(final GetDrawingRequest request, Context context) {
        log.info("Received GetDrawingRequest {} ", request);
        // Check to make sure the given drawing already exists
        Drawing drawing = drawingDao.getDrawing(request.getId());
        if (drawing == null) {
            throw new DrawingNotFoundException();
        }
        // Create drawingModel from drawing
        DrawingModel drawingModel = new ModelConverter().toDrawingModel(drawing);
        return new GetDrawingResult.Builder().withModel(drawingModel).build();
    }
}
