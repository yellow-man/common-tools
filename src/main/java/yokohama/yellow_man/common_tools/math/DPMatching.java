package yokohama.yellow_man.common_tools.math;

import yokohama.yellow_man.common_tools.math.common.CommonInfo;
import yokohama.yellow_man.common_tools.math.interpolation.Interpolation;
import yokohama.yellow_man.common_tools.math.model.MatchingArray;

/**
 * DPマッチング機能を提供します。
 *
 * @author none
 * @since 1.2.0
 */
public final class DPMatching {
	/** マッチング対象の配列オブジェクト */
	private MatchingArray array;
	/** 補間ユニット */
	private Interpolation imp;

	/** 文字ズレについての重み */
	private double zurePenalty = 1;
	/** 合致しなかった場合の重み */
	private double awazuPenalty = 5;

	/**
	 * コンストラクタ。
	 * @param array マッチング対象の配列オブジェクト
	 * @param interpolation 補間ユニット
	 * @since 1.2.0
	 */
	public DPMatching(final MatchingArray array, final Interpolation interpolation) {
		this.array = array;
		this.imp = interpolation;
	}

	/**
	 * DPマッチングを行います。
	 * @return 不一致度を返却します。
	 * @since 1.2.0
	 */
	public double matching() {
		return this.matching(this.array);
	}

	/**
	 * DPマッチングを行います。
	 * @param tmpArray マッチング対象の配列オブジェクト
	 * @return 不一致度を返却します。
	 * @since 1.2.0
	 */
	private double matching(final MatchingArray tmpArray) {
		MatchingArray array = tmpArray;
		// Phase1: 補間方法が使用できない場合
		if (!this.imp.validation(array)) {
			// 差異の最大値（doubleの最大値）を戻す
			return Double.MAX_VALUE;
		}
		// Phase2: 補間方法が使用できない場合
		if (array.getArrayA().length != array.getArrayB().length) {
			// 再計算し、補間する
			array = this.imp.calcurate(array);
		}
		// Phase3: 出力行列の確保
		int scalerLen = array.getArrayA().length;
		Double[][] missMatch = new Double[scalerLen][scalerLen];
		Double[][] cost = new Double[scalerLen][scalerLen];
		Double[][] from = new Double[scalerLen][scalerLen];
		// Phase4: 誤差計算を行うため、総当たりで分母とする最大誤差を取得する
		boolean isNumber = CommonInfo.getInstance().isNumber(array.getArrayA()[0], array.getArrayB()[0]);
		double maximumDiff = 1.0;
		for (int a=0; a < array.getArrayA().length; a++) {
			for (int b=0; b < array.getArrayB().length; b++) {
				if (isNumber) {
					// 数値である場合は差分計算をし、重みとする
					missMatch[a][b] = Math.abs((Double)array.getArrayA()[a] - (Double)array.getArrayB()[b]);
					maximumDiff = Math.max(missMatch[a][b], maximumDiff);
				} else {
					// 数値でない場合は合致するか否かを重みとする
					missMatch[a][b] = (array.getArrayA()[a].equals(array.getArrayB()[b])) ? 0.0 : 1.0;
				}
			}
		}
		// Phase5: 数値型であった場合は差分計算結果を最大誤差で割り、0.0～1.0の値に調整する
		if (isNumber) {
			for (int a=0; a < array.getArrayA().length; a++) {
				for (int b=0; b < array.getArrayB().length; b++) {
					missMatch[a][b] /= maximumDiff;
				}
			}
		}
		// Phase6:コスト計算を行う
		cost[0][0] = missMatch[0][0];
		from[0][0] = 0.0;
		// Phase6-1:Array-A側の縁を計算する
		for (int a = 1; a < array.getArrayA().length; a++) {
			cost[a][0] = cost[a-1][0] + zurePenalty + missMatch[a][0] * awazuPenalty;
			from[a][0] = 1.0;
		}
		// Phase6-2:Array-B側の縁を計算する
		for (int b = 1; b < array.getArrayB().length; b++) {
			cost[0][b] = cost[0][b-1] + zurePenalty + missMatch[0][b] * awazuPenalty;
			from[0][b] = 2.0;
		}
		// Phase6-3:中間部を計算する
		for (int a = 1; a < array.getArrayA().length; a++) {
			for (int b = 1; b < array.getArrayB().length; b++) {
				// 斜めで来た場合のコスト
				double dtemp1 = cost[a-1][b-1] + missMatch[a][b] * awazuPenalty;
				// a増えで来た場合のコスト
				double dtemp2 = cost[a-1][b]   + missMatch[a][b] * awazuPenalty + zurePenalty;
				// b増えで来た場合のコスト
				double dtemp3 = cost[a][b-1]   + missMatch[a][b] * awazuPenalty + zurePenalty;
				if (dtemp1 <= dtemp2 && dtemp1 <= dtemp3) {
					cost[a][b] = dtemp1;
					from[a][b] = 0.0;
				} else if(dtemp2 <= dtemp3) {
					cost[a][b] = dtemp2;
					from[a][b] = 1.0;
				} else {
					cost[a][b] = dtemp3;
					from[a][b] = 2.0;
				}
			}
		}
		// Phase7: DPマッチングの不一致度を返します
		return cost[array.getArrayA().length - 1][array.getArrayB().length - 1];
	}
}
