package yokohama.yellow_man.common_tools.math.interpolation;

import yokohama.yellow_man.common_tools.math.model.MatchingArray;

/**
 * 補間ユニットのインタフェース。
 * @author none
 * @since 1.2.0
 */
public interface Interpolation {

	/**
	 * マッチング配列に補間方法が使用できるかチェックします。
	 * @param array チェック対象のマッチング配列
	 * @return 補間方法が使用可能かチェックした結果を返却します。（true：補間可能、false：補間不可。）
	 * @since 1.2.0
	 */
	public boolean validation(final MatchingArray array);

	/**
	 * 補間を実施します。
	 * @param array 補間実施対象のマッチング配列
	 * @return 補間後のマッチング配列を返却します。
	 * @since 1.2.0
	 */
	public MatchingArray calcurate(final MatchingArray array);
}
