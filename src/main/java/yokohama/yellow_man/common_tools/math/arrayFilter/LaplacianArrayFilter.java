package yokohama.yellow_man.common_tools.math.arrayFilter;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import yokohama.yellow_man.common_tools.math.common.CommonInfo;

/**
 * 勾配フィルタークラス。
 * @author none
 * @since 1.2.0
 */
public class LaplacianArrayFilter implements ArrayFilter {

	/** Logger定義 */
	private static Logger LOGGER = (Logger) LoggerFactory.getLogger(LaplacianArrayFilter.class.getName());

	/** 勾配フィルタベース情報 */
	private static double[] filterBase = {-1.0, 2, -1.0};

	/* (非 Javadoc)
	 * @see yokohama.yellow_man.matching_tools.arrayFilter.ArrayFilter#convert(java.lang.Object[])
	 */
	@Override
	public Object[] convert(final Object[] target) {
		for (int idx = 1; idx < target.length - 1; idx++) {
			target[idx-1] = (Double)target[idx-1] * filterBase[0];
			target[idx]   = (Double)target[idx]   * filterBase[1];
			target[idx+1] = (Double)target[idx+1] * filterBase[2];
		}
		return target;
	}

	/* (非 Javadoc)
	 * @see yokohama.yellow_man.matching_tools.arrayFilter.ArrayFilter#validation(java.lang.Object[])
	 */
	@Override
	public boolean validation(final Object[] target) {
		if (target.length > filterBase.length) {
			return false;
		}
		if (!CommonInfo.getInstance().isNumber(target[0])) {
			return false;
		}
		return true;
	}
}
