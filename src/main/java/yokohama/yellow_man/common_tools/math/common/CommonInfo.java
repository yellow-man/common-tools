package yokohama.yellow_man.common_tools.math.common;

/**
 * ユーティリティクラス。
 * @author none
 * @since 1.2.0
 */
public final class CommonInfo {
	/** このクラスのインスタンス */
	private static CommonInfo _instance = new CommonInfo();

	/**
	 * このクラスのインスタンスを取得します。
	 * @return このクラスのインスタンスを返却します。
	 * @since 1.2.0
	 */
	public static CommonInfo getInstance() {
		return CommonInfo._instance;
	}

	/**
	 * 引数の数値チェックを行います。
	 * @param a チェック対象のオブジェクト
	 * @param b チェック対象のオブジェクト
	 * @return チェック結果を返却します。
	 * @since 1.2.0
	 */
	public boolean isNumber(final Object a, final Object b) {
		return isNumber(a) && isNumber(b);
	}

	/**
	 * 引数の数値チェックを行います。
	 * @param a チェック対象のオブジェクト
	 * @param b チェック対象のオブジェクト
	 * @return チェック結果を返却します。
	 * @since 1.2.0
	 */
	public boolean isNumber(final Object a) {
		return (a instanceof Integer) || (a instanceof Float)
				|| (a instanceof Double) || (a instanceof Long);
	}
}
