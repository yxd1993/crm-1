package com.hoau.crm.module.common.server.util;

import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 275636
 *判断点是否在多边形里面的工具类
 */
public class PolygonUtil {

	/**
	 * @param point
	 * @param polygon
	 * @return 判断是否在多边形 使用2d图形方式
	 */
	public static boolean checkWithJdkGeneralPath(Point2D.Double point, List<Point2D.Double> polygon) {
		java.awt.geom.GeneralPath p = new java.awt.geom.GeneralPath();
		   Point2D.Double first = polygon.get(0);
		   p.moveTo(first.x, first.y);

		   for (Point2D.Double d : polygon) {
		      p.lineTo(d.x, d.y);
		   }

		   p.lineTo(first.x, first.y);

		   p.closePath();

		   return p.contains(point);
	}
	
	/**
	 * @param point
	 * @param polygon
	 * @return
	 * 判断是否在多边形使用2d图形方式  只适合整数型坐标
	 */
	public static boolean checkWithJdkPolygon(Point2D.Double point, List<Point2D.Double> polygon) {
	    java.awt.Polygon p = new Polygon();
	    // java.awt.geom.GeneralPath
	    final int TIMES = 1000;
	    for (Point2D.Double d : polygon) {
	        int x = (int) d.x * TIMES;
	        int y = (int) d.y * TIMES;
	        p.addPoint(x, y);
	    }
	    int x = (int) point.x * TIMES;
	    int y = (int) point.y * TIMES;
	    return p.contains(x, y);
	}
	
	public static void main(String[] args) {
		Point2D.Double point2d = new Point2D.Double(121.309625,31.210533) ;

		List<Point2D.Double> doubles = new ArrayList<Point2D.Double>();
		Point2D.Double double1 = new Point2D.Double(121.310616,31.211166) ;
		Point2D.Double double2 = new Point2D.Double(121.310831,31.210533) ;
		Point2D.Double double3 = new Point2D.Double(121.312439,31.210787) ;
		Point2D.Double double4 = new Point2D.Double(121.311631,31.211753) ;
		Point2D.Double double5 = new Point2D.Double(121.310625,31.211181) ;
		doubles.add(double1);
		doubles.add(double2);
		doubles.add(double3);
		doubles.add(double4);
		doubles.add(double5);
		System.out.println(new PolygonUtil().checkWithJdkGeneralPath(point2d, doubles));
		
		double px = 121.309625;
		double py = 31.210533;
		ArrayList<Double> polygonXA = new ArrayList<Double>();
		ArrayList<Double> polygonYA = new ArrayList<Double>();
		polygonXA.add(121.310616);
		polygonXA.add(121.310831);
		polygonXA.add(121.312439);
		polygonXA.add(121.311631);
		polygonXA.add(121.310625);
//		polygonXA.add(121.320515);
//		polygonXA.add(121.318071);
//		polygonXA.add(121.309232);
//		polygonXA.add(121.298165);
//		polygonXA.add(121.293709);
		
		polygonYA.add(31.211166);
		polygonYA.add(31.210533);
		polygonYA.add(31.210787);
		polygonYA.add(31.211753);
		polygonYA.add(31.211181);
//		polygonYA.add(31.213421);
//		polygonYA.add(31.224415);
//		polygonYA.add(31.22426);
//		polygonYA.add(31.219504);
//		polygonYA.add(31.217867);
		System.out.println(new PolygonUtil().isPointInPolygon(px, py, polygonXA, polygonYA));
	}

	/**
	 * @param px
	 * @param py
	 * @param polygonXA
	 * @param polygonYA
	 * @return 判断是否在多边形java折射线
	 */
	public static boolean isPointInPolygon(double px, double py,
			ArrayList<Double> polygonXA, ArrayList<Double> polygonYA) {
		boolean isInside = false;
		double ESP = 1e-9;
		int count = 0;
		double linePoint1x;
		double linePoint1y;
		double linePoint2x = 180;
		double linePoint2y;

		linePoint1x = px;
		linePoint1y = py;
		linePoint2y = py;

		for (int i = 0; i < polygonXA.size() - 1; i++) {
			double cx1 = polygonXA.get(i);
			double cy1 = polygonYA.get(i);
			double cx2 = polygonXA.get(i + 1);
			double cy2 = polygonYA.get(i + 1);
			if (isPointOnLine(px, py, cx1, cy1, cx2, cy2)) {
				return true;
			}
			if (Math.abs(cy2 - cy1) < ESP) {
				continue;
			}

			if (isPointOnLine(cx1, cy1, linePoint1x, linePoint1y, linePoint2x,
					linePoint2y)) {
				if (cy1 > cy2)
					count++;
			} else if (isPointOnLine(cx2, cy2, linePoint1x, linePoint1y,
					linePoint2x, linePoint2y)) {
				if (cy2 > cy1)
					count++;
			} else if (isIntersect(cx1, cy1, cx2, cy2, linePoint1x,
					linePoint1y, linePoint2x, linePoint2y)) {
				count++;
			}
		}
		System.out.println(count);
		if (count % 2 == 1) {
			isInside = true;
		}
		return isInside;
	}

	public static double Multiply(double px0, double py0, double px1, double py1,
			double px2, double py2) {
		return ((px1 - px0) * (py2 - py0) - (px2 - px0) * (py1 - py0));
	}

	public static boolean isPointOnLine(double px0, double py0, double px1,
			double py1, double px2, double py2) {
		boolean flag = false;
		double ESP = 1e-9;
		if ((Math.abs(Multiply(px0, py0, px1, py1, px2, py2)) < ESP)
				&& ((px0 - px1) * (px0 - px2) <= 0)
				&& ((py0 - py1) * (py0 - py2) <= 0)) {
			flag = true;
		}
		return flag;
	}

	public static  boolean isIntersect(double px1, double py1, double px2, double py2,
			double px3, double py3, double px4, double py4) {
		boolean flag = false;
		double d = (px2 - px1) * (py4 - py3) - (py2 - py1) * (px4 - px3);
		if (d != 0) {
			double r = ((py1 - py3) * (px4 - px3) - (px1 - px3) * (py4 - py3))
					/ d;
			double s = ((py1 - py3) * (px2 - px1) - (px1 - px3) * (py2 - py1))
					/ d;
			if ((r >= 0) && (r <= 1) && (s >= 0) && (s <= 1)) {
				flag = true;
			}
		}
		return flag;
	}

}
