package com.youmu.Inheritance.impl.maze;

import com.youmu.Inheritance.Gene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 基因随机生成器
 */
public class GeneGenerator {

	public static Gene generator(int length) {
		List<Integer> chromo = new ArrayList<Integer>();
		length *= 2;
		while (length-- != 0) {
			chromo.add((int) (new Random().nextFloat() * 2));
		}
		Gene gene = new Gene();
		gene.setChromosome(chromo);
		return gene;
	}
}
