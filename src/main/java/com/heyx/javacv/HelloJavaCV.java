package com.heyx.javacv;

import static org.bytedeco.javacpp.opencv_highgui.imshow;
import static org.bytedeco.javacpp.opencv_highgui.waitKey;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;
import static org.bytedeco.javacpp.opencv_imgproc.adaptiveThreshold;
import static org.bytedeco.javacpp.opencv_imgproc.threshold;

import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


/**
 * @description:
 * @author: heyx
 * @create: 2019-02-17 09:32
 * @email; 1064042411@qq.com
 */
public class HelloJavaCV {
    public static void main(String[] args) {
        Mat image = imread("E:/pdf/2_1.png", Imgcodecs.IMREAD_GRAYSCALE);
        if (image.empty()) {
            System.err.println("加载图片出错，请检查图片路径！");
            return;
        }
        int heigh = image.rows();
        int width = image.cols();

        Rect rect = new Rect(width*3/4,heigh*3/4,width/4,heigh/4);
        Mat src = image.apply(rect);
        imshow("output",src);

        Mat target = new Mat();
        threshold(src, target, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);//灰度图像二值化
        imshow("2", target);
        adaptiveThreshold(src, target, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 5, 0);
        imshow("3", target);
        waitKey(0);
    }
}
