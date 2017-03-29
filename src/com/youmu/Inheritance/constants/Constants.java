package com.youmu.Inheritance.constants;

public class Constants {


	private static final int MAX_CHROMO_LEN = 10;

	private static final float RATE_MUTATION = 0.001f;

	private static final float RATE_CROSS_OVER = 0.7f;

	/**
	 * 染色体长度
	 */
	public static int getMaxChromoLen() {
		return MAX_CHROMO_LEN;
	}

	/**
	 * 变异概率
	 */
	public static float getRateMutation() {
		return RATE_MUTATION;
	}

	/**
	 * 杂交概率
	 */
	public static float getRateCrossOver() {
		return RATE_CROSS_OVER;
	}

}
