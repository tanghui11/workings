
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoint;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import static java.lang.Math.toRadians;

/**
 * @author Administrator
 * @Title: ${FILE_NAME}
 * @Package PACKAGE_NAME
 * @Description: ${TODO}
 * @date 2018-02-25 9:44
 */
public class Test {
    public static void main(String[] args) {
        double[] doubles = trainPolyFit(3, 10000);
        //下面是测试
        System.out.println("a1: "+doubles[0]);
        System.out.println("a2: "+doubles[1]);
        System.out.println("a3: "+doubles[2]);
        System.out.println("a4: "+doubles[3]);
        System.out.println("double的个数"+doubles.length);
        double hehe = Math.cos(toRadians(40));

        double fo = doubles[3]*40*40*40+doubles[2]*40*40+doubles[1]*40+doubles[0];
        System.out.println("hehe: "+hehe);
        System.out.println("fo: "+fo);
        double sub = hehe-fo;
        System.out.println(sub*300000);

        String price = "1631.2335556656565656565165654646468";
        BigDecimal price1 = new BigDecimal(price);
        System.out.println(price1);


    }

    /**
     *
     * @param degree 代表你用几阶去拟合
     * @param Length  把10 --60 分成多少个点去拟合，越大应该越精确
     * @return
     */
    public static double[] trainPolyFit(int degree, int Length){
        PolynomialCurveFitter polynomialCurveFitter = PolynomialCurveFitter.create(degree);
        double minLat = 10.0; //中国最低纬度
        double maxLat = 60.0; //中国最高纬度
        double interv = (maxLat - minLat) / (double)Length;
        ArrayList weightedObservedPoints = new ArrayList();
        for(int i = 0; i < Length; i++) {
            WeightedObservedPoint weightedObservedPoint = new WeightedObservedPoint(1,  minLat + (double)i*interv, Math.cos(toRadians(minLat + (double)i*interv)));
            weightedObservedPoints.add(weightedObservedPoint);
        }
        return polynomialCurveFitter.fit(weightedObservedPoints);
    }

    public static double distanceSimplifyMore(double lat1, double lng1, double lat2, double lng2, double[] a) {
        //1) 计算三个参数
        double dx = lng1 - lng2; // 经度差值
        double dy = lat1 - lat2; // 纬度差值
        double b = (lat1 + lat2) / 2.0; // 平均纬度
        //2) 计算东西方向距离和南北方向距离(单位：米)，东西距离采用三阶多项式
        double Lx = (a[3] * b*b*b  + a[2]* b*b  +a[1] * b + a[0] ) * toRadians(dx) * 6367000.0; // 东西距离
        double Ly = 6367000.0 * toRadians(dy); // 南北距离
        //3) 用平面的矩形对角距离公式计算总距离
        return Math.sqrt(Lx * Lx + Ly * Ly);
    }
}
