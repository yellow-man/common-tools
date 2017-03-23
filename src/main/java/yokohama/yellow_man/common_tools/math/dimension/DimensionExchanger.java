package yokohama.yellow_man.common_tools.math.dimension;

import yokohama.yellow_man.common_tools.math.model.Acceleration3dPoint;

/**
 * 次元交換器を提供するインタフェース。
 * @author none
 * @since 1.2.0
 */
public interface DimensionExchanger {

	/**
	 * 3次元を1次元に交換します。
	 * @param points 3次元オブジェクトの配列
	 * @return 1次元に交換した配列を返却します。
	 * @since 1.2.0
	 */
	public double[] exchange(final Acceleration3dPoint[] points);
}
