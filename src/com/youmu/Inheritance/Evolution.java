package com.youmu.Inheritance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 一代人
 */
public abstract class Evolution {

	protected List<Gene> genes = new ArrayList<Gene>();

	/**
	 * 总适应权重
	 */
	private float totalFit = 0;

	/**
	 * 杂交概率
	 */
	protected float crossOverRate;

	/**
	 * 变异概率
	 */
	protected float mutationRate;

	/**
	 * 代数
	 */
	protected int generation = 0;

	public Evolution(List<Gene> genes, float crossOverRate, float mutationRate) {
		this.genes = genes;
		this.crossOverRate = crossOverRate;
		this.mutationRate = mutationRate;
	}

	/**
	 * 基因突变（这是染色体的突变）
	 * @param gene 突变基因
	 * @param mutationRate 突变概率（随机比突变概率小突变）
	 * @return
	 */
	private Gene mutate(Gene gene, float mutationRate) {
		Gene mutatedGene = new Gene();
		List<Integer> chromosome = new ArrayList<Integer>(gene.getChromosome()
				.size());

		for (int i = 0; i < gene.getChromosome().size(); i++) {
			if (mutationRate > new Random().nextFloat()) {
				chromosome.add(gene.getChromosome().get(0) == 0 ? 1 : 0);
			} else {
				chromosome.add(gene.getChromosome().get(i).intValue());
			}
		}
		mutatedGene.setChromosome(chromosome);
		return mutatedGene;
	}

	/**
	 * 杂交 染色体的杂交
	 * @param mum 母
	 * @param dad 父
	 * @param crossoverRate 杂交率（小于杂交率则杂交）
	 * @return
	 * @throws Exception
	 */
	private List<Gene> crossover(Gene mum, Gene dad, float crossoverRate)
			throws Exception {
		List<Gene> genes = new ArrayList<Gene>();
		if (mum.getChromosome().size() != dad.getChromosome().size()) {
			throw new Exception("can not  cross over");
		}
		if ((crossoverRate > new Random().nextFloat())
				|| (mum.getChromosome().equals(dad.getChromosome()))) {
			genes.add(dad);
			genes.add(mum);
			return genes;
		}
		int len = mum.getChromosome().size();
		List<Integer> baby1Chromosome = new ArrayList<Integer>(len);
		List<Integer> baby2Chromosome = new ArrayList<Integer>(len);
		int crossoverBit = new Random().nextInt(len);
		int i = 0;
		for (; i < crossoverBit; i++) {
			baby1Chromosome.add(dad.getChromosome().get(i).intValue());
			baby2Chromosome.add(mum.getChromosome().get(i).intValue());
		}
		for (; i < len; i++) {
			baby1Chromosome.add(mum.getChromosome().get(i).intValue());
			baby2Chromosome.add(dad.getChromosome().get(i).intValue());
		}
		Gene baby1 = new Gene();
		Gene baby2 = new Gene();
		baby1.setChromosome(baby1Chromosome);
		baby2.setChromosome(baby2Chromosome);
		genes.add(baby1);
		genes.add(baby2);
		return genes;
	}

	/**
	 * 进化
	 * @throws Exception
	 */
	public void evolve() throws Exception {
		while (epoch() == -1) {
		}
	}

	/**
	 * 演化一代
	 * @return
	 * @throws Exception
	 */
	private int epoch() throws Exception {
		System.out.println(generation);
		// 更新适配值
		int rtn = updateFitness();
		if (rtn != -1) {

			System.out.println(Arrays.toString(genes.get(rtn).getChromosome()
					.toArray()));
			print(genes.get(rtn));
			return rtn;
		}
		//
		List<Gene> newGenes = new ArrayList<Gene>();
		while (newGenes.size() < genes.size()) {
			Gene dad = genes.get(rand());
			Gene mum = genes.get(rand());
			List<Gene> pair = crossover(mum, dad, crossOverRate);
			newGenes.addAll(pair);
		}

		for (int i = 0; i < newGenes.size(); i++) {
			newGenes.set(i, mutate(newGenes.get(i), mutationRate));
		}
		setGenes(newGenes);
		generation++;
		return -1;
	}

	/**
	 * 更新所有基因的适应指数
	 * @return
	 */
	public int updateFitness() {
		float total = 0;
		int rtn = -1;
		for (int i = 0; i < genes.size(); i++) {
			float fitness = caculateFitness(genes.get(i));
			total += fitness;
			genes.get(i).setFitness(fitness);
			if (fitness == 1) {
				rtn = i;
			}
		}
		setTotalFit(total);
		return rtn;
	}

	/**
	 * 随机取基因（车轮片随机法）
	 * @return
	 */
	public int rand() {
		float slice = new Random().nextFloat() * getTotalFit();

		float total = 0;
		for (int i = 0; i < genes.size(); i++) {
			total += genes.get(i).getFitness();
			if (total > slice) {
				return i;
			}
		}
		return 0;

	}

	public List<Gene> getGenes() {
		return genes;
	}

	public void setGenes(List<Gene> genes) {
		this.genes = genes;
	}

	public float getTotalFit() {
		return totalFit;
	}

	public void setTotalFit(float totalFit) {
		this.totalFit = totalFit;
	}

	/**
	 * 计算某个基因的适应度
	 * @param gene
	 * @return 适应度
	 */
	public abstract float caculateFitness(Gene gene);

	/**
	 * 输出这一代基因（仅仅用来显示）
	 * @param gene
	 *
	 */
	public abstract void print(Gene gene);

}
