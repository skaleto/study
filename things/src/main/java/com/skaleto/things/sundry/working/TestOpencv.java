package com.skaleto.things.sundry.working;

import org.opencv.videoio.VideoCapture;

public class TestOpencv {

    static {
        System.load("/Users/yaoyibin/Downloads/opencv-4.4.0/build/lib/libopencv_java440.dylib");
    }

    public static void main(String[] args) {
        VideoCapture videoCapture=new VideoCapture();
    }


}
