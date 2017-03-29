package com.youmu.Inheritance;

import java.util.ArrayList;
import java.util.List;

/**
 * 基因类
 */
public class Gene {
	/**
	 * 染色体
	 */
	private List<Integer> chromosome;
	/**
	 * 适应度权重
	 */
	private float fitness;

	public Gene() {
		chromosome = new ArrayList<Integer>();
	}

	public List<Integer> getChromosome() {
		return chromosome;
	}

	public void setChromosome(List<Integer> chromosome) {
		this.chromosome = chromosome;
	}

	public float getFitness() {
		return fitness;
	}

	public void setFitness(float fitness) {
		this.fitness = fitness;
	}

}
