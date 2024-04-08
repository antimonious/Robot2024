package org.firstinspires.ftc.teamcode.vision;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class AnthonyLecturePipeline extends OpenCvPipeline {

    @Override
    public Mat processFrame(Mat input) {

        Imgproc.cvtColor(input, input, Imgproc.COLOR_RGB2GRAY);

        return input;
    }
}
