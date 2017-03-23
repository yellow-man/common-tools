package yokohama.yellow_man.common_tools.math.interpolation;

import yokohama.yellow_man.common_tools.math.common.CommonInfo;
import yokohama.yellow_man.common_tools.math.model.MatchingArray;

/**
 * 線形補間クラス。
 * @author none
 * @since 1.2.0
 */
public final class LinearInterpolation implements Interpolation {

	/* (非 Javadoc)
	 * @see yokohama.yellow_man.matching_tools.interpolation.Interpolation#calcurate(yokohama.yellow_man.matching_tools.model.MatchingArray)
	 */
	@Override
	public MatchingArray calcurate(final MatchingArray array) {
		int lenA = array.getArrayA().length;
		int lenB = array.getArrayB().length;
		int diff = Math.abs(lenA - lenB);

		boolean aIsMaxLen = (lenA > lenB);
		int maxLen = aIsMaxLen ? lenA: lenB;
		Object[] longer  = aIsMaxLen ? array.getArrayA() : array.getArrayB();
		Object[] shorter = aIsMaxLen ? array.getArrayB() : array.getArrayA();
		int step = (longer.length / diff);

		// Phase1: 補間対象となる数値について、Nanにより埋める
		MatchingArray tmpArr = new MatchingArray(new Object[maxLen], new Object[maxLen]);
		int shortIdx = 0, addCnt = 0;
		for (int idx = 0; idx < maxLen ; idx++) {
			tmpArr.getArrayA()[idx] = longer[idx];
			if (addCnt == step) {
				addCnt = 0;
				// 補間対象となるものはNanで埋めておく
				tmpArr.getArrayB()[idx] = Double.NaN;
			} else {
				shortIdx++;
				addCnt++;
				int curIdx = shortIdx - 1;
				tmpArr.getArrayB()[idx] = shorter[ (curIdx >= shorter.length) ? shorter.length - 1 : curIdx ];
			}
		}

		// Phase2: 補間対象数値を逆算し、Nanを置き換える
		int beforeIdx = 0;
		boolean isFirst = false;
		for (int idx =0 ; idx < tmpArr.getArrayB().length; idx++) {
			if (tmpArr.getArrayB()[idx].equals(Double.NaN)) {
				if (!isFirst) {
					isFirst = true;
					beforeIdx = idx;
				}
			} else {
				// 末端に来た場合
				if (isFirst) {
					Double between  = (double)(idx - beforeIdx);
					Double startVal = (Double) tmpArr.getArrayB()[beforeIdx-1];
					Double endVal   = (Double) tmpArr.getArrayB()[idx];
					Double arg      = (endVal - startVal) / (between+1);
					for (int i = beforeIdx ; i < idx ; i++) {
						tmpArr.getArrayB()[i] = startVal + arg;
					}
					isFirst = false;
				}
			}
		}
		return tmpArr;
	}

	/* (非 Javadoc)
	 * @see yokohama.yellow_man.matching_tools.interpolation.Interpolation#validation(yokohama.yellow_man.matching_tools.model.MatchingArray)
	 */
	@Override
	public boolean validation(final MatchingArray array) {
		boolean tmp = true;
		if (array.getArrayA().length == 0 || array.getArrayB().length == 0) {
			return false;
		}
		for (Object obj : array.getArrayA()) {
			tmp &= CommonInfo.getInstance().isNumber(obj);
			if (!tmp) {
				return false;
			}
		}
		for (Object obj : array.getArrayB()) {
			tmp &= CommonInfo.getInstance().isNumber(obj);
			if (!tmp) {
				return false;
			}
		}
		return true;
	}
}
