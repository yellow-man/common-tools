package yokohama.yellow_man.common_tools.math.interpolation;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import yokohama.yellow_man.common_tools.math.model.MatchingArray;

/**
 * コピー補間クラス。
 * <p>多い方の配列の長さに合わせて、少ない方の配列をコピーし足りない要素は適宜補間する。
 * @author none
 * @since 1.2.0
 */
public final class CopyInterpolation implements Interpolation {

	/** Logger定義 */
	private static Logger LOGGER = (Logger) LoggerFactory.getLogger(CopyInterpolation.class.getName());

	/* (非 Javadoc)
	 * @see yokohama.yellow_man.matching_tools.interpolation.Interpolation#calcurate(yokohama.yellow_man.matching_tools.model.MatchingArray)
	 */
	@Override
	public MatchingArray calcurate(final MatchingArray array) {
		int lenA = array.getArrayA().length;
		int lenB = array.getArrayB().length;
		int diff = Math.abs(lenA - lenB);

		boolean aIsMaxLen = (lenA > lenB);
		int maxLen        = aIsMaxLen ? lenA: lenB;
		Object[] longer   = aIsMaxLen ? array.getArrayA() : array.getArrayB();
		Object[] shorter  = aIsMaxLen ? array.getArrayB() : array.getArrayA();
		int step          = (longer.length / diff);

		MatchingArray tmpArr = new MatchingArray(new Object[maxLen], new Object[maxLen]);
		int shortIdx = 0, addCnt = 0;
		for (int idx = 0; idx < maxLen ; idx++) {
			tmpArr.getArrayA()[idx] = longer[idx];
			addCnt     = (addCnt == step) ? 0 : addCnt + 1;
			shortIdx  += (addCnt == step) ? 0 : 1;
			int curIdx = shortIdx - 1;
			tmpArr.getArrayB()[idx] = shorter[ (curIdx >= shorter.length) ? shorter.length - 1 : curIdx ];
		}
		return tmpArr;
	}

	/* (非 Javadoc)
	 * @see yokohama.yellow_man.matching_tools.interpolation.Interpolation#validation(yokohama.yellow_man.matching_tools.model.MatchingArray)
	 */
	@Override
	public boolean validation(final MatchingArray array) {
		return !(array.getArrayA().length == 0 || array.getArrayB().length == 0);
	}

}
