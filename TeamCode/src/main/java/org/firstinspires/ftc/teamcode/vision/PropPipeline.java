package org.firstinspires.ftc.teamcode.vision;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class PropPipeline extends OpenCvPipeline {
    Telemetry telemetry;
    public enum Location {
        LEFT,
        RIGHT
    }
    private Location location;
    public PropPipeline(Telemetry t) { telemetry = t;}

    private Mat hsv = new Mat(); //matrix that handles the color conversion to hsv (removes intensity from image
    private Mat mask = new Mat(); // matrix that handles color segmentation (separating the coloring desired)

    private boolean RED = true; //default is RED
    public boolean LEFT = true; //default is RED
    public static int minWidthRed = (int) (20);
    public static int maxWidthRed = (int) (260);
    public static int minHeightRed = (int) (0);
    public static int maxHeightRed = (int) (200);
    public static int minAreaRed = (int) (17000);
    public static int maxAreaRed = (int) (23000);
    public static int minWidthBlue = (int) 20;
    public static int maxWidthBlue = (int) (200);
    public static int minHeightBlue = (int) (30);
    public static int maxHeightBlue = (int) (100);
    public static int minAreaBlue = (int) (0);
    public static int maxAreaBlue = (int) (25000);


    public boolean setREDProp(boolean RED) {
        this.RED = RED;
        return RED;
    }

    @Override
    public Mat processFrame(Mat input) {


        //converting to HSV color space
        //the logitech c720 camera outputs images in RGB
        //this takes the "input" matrix and outputs the "hsv" matrix
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2HSV);

        //Color segmentation
        if (RED) {
            //RED DETECTION
            Core.inRange(hsv, new Scalar(0, 100, 100), new Scalar(10, 255, 255), mask);
            //the inRange looks for the color set from the hsv matrix
            // this stores the image after the color segmentation in the mask matrix, hence the "mask" at the end
        } else {
            //BLUE DETECTION
            Core.inRange(hsv, new Scalar(110, 150, 50), new Scalar(130, 255, 255), mask);
            // this stores the image after the color segmentation in the mask matrix, hence the "mask" at the end
        }
       /*
        if () {
            //right side
            location = location.RIGHT;
            telemetry.addData("Prop Location", "right");
        } else {
            //left
            location = location.LEFT;
            telemetry.addData("Prop Location", "left");
        }
        */
        telemetry.update();

        Imgproc.morphologyEx(mask, mask, Imgproc.MORPH_CLOSE, new Mat());
        Imgproc.morphologyEx(mask, mask, Imgproc.MORPH_GRADIENT, new Mat());


        //contour detection
        List<MatOfPoint> contours = new ArrayList<>(); //stores all the contours into a list
        Imgproc.findContours(mask, contours, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_TC89_L1);
        //this takes the mask matrix, finds the contours, and stores them into a list called contours
        //the "new Mat()" is there in case you wanted to order the contours in a specific way
        //the RETRE_EXTERNAL prioritizes the outside portion of the color being detected
        //the CHAIN_APPROX_SIMPLE makes points on the very edges/corners of the contour



        List<MatOfPoint> filteredContours = new ArrayList<>(); // this is disregard the contours that do not match our desired object
        for (MatOfPoint contour : contours) {

            //create a bounding box around all the contours found
            Rect boundingBox = Imgproc.boundingRect(contour);
            //then you test if it fits the dimensions and parameters of the desired object
            if (RED) {
                if (boundingBox.width >= minWidthRed && boundingBox.width <= maxWidthRed &&
                        boundingBox.height >= minHeightRed && boundingBox.height <= maxHeightRed &&
                        boundingBox.area() >= minAreaRed && boundingBox.area() <= maxAreaRed
                ) {

                    //if it does, then it gets fed through
                    filteredContours.add(contour);


                }
            }
            if (!RED) {
                if (boundingBox.width >= minWidthBlue && boundingBox.width <= maxWidthBlue &&
                    boundingBox.height >= minHeightBlue && boundingBox.height <= maxHeightBlue //&&
                    //boundingBox.area() >= minAreaBlue && boundingBox.area() <= maxAreaBlue
                ) {

                    filteredContours.add(contour);

                }
            }

            //drawing the contour
            Imgproc.drawContours(input, filteredContours, -1, new Scalar(0, 255, 0), 2);


        }

        return input;

    }

    public Location getLocation() {
        return location;
    }

}