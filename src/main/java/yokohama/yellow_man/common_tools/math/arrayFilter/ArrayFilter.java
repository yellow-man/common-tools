package yokohama.yellow_man.common_tools.math.arrayFilter;

/**
 * 配列フィルターのインタフェース。
 * @author none
 * @since 1.2.0
 */
public interface ArrayFilter {

	/**
	 * フィルター可能かチェックします。
	 * @param target チェック対象の配列
	 * @return フィルター可能化チェックした結果を返却します。（true：フィルター可能、false：フィルター不可。）
	 * @since 1.2.0
	 */
	public boolean validation(final Object[] target);

	/**
	 * フィルターを適用します。
	 * @param target フィルター対象の配列
	 * @return フィルター適用後の配列を返却します。
	 * @since 1.2.0
	 */
	public Object[] convert(final Object[] target);
}
