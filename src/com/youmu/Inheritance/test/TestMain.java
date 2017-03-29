package com.youmu.Inheritance.test;

import com.youmu.Inheritance.Gene;
import com.youmu.Inheritance.constants.Constants;
import com.youmu.Inheritance.impl.maze.GeneGenerator;
import com.youmu.Inheritance.impl.maze.Maze;

import java.util.ArrayList;
import java.util.List;

public class TestMain {

//	 static char[][] map = {
//	 { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
//	 '0', '0' },
//	 { '0', ' ', '0', ' ', ' ', ' ', ' ', ' ', '0', '0', '0', ' ', ' ',
//	 ' ', '0' },
//	 { '@', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '0', '0', '0', ' ', ' ',
//	 ' ', '0' },
//	 { '0', ' ', ' ', ' ', '0', '0', '0', ' ', ' ', '0', ' ', ' ', ' ',
//	 ' ', '0' },
//	 { '0', ' ', ' ', ' ', '0', '0', '0', ' ', ' ', ' ', ' ', ' ', '0',
//	 ' ', '0' },
//	 { '0', '0', ' ', ' ', '0', '0', '0', ' ', ' ', ' ', ' ', ' ', '0',
//	 ' ', '0' },
//	 { '0', ' ', ' ', ' ', ' ', '0', ' ', ' ', ' ', ' ', '0', '0', '0',
//	 ' ', '0' },
//	 { '0', ' ', '0', '0', ' ', ' ', ' ', '0', ' ', ' ', ' ', ' ', ' ',
//	 ' ', '#' },
//	 { '0', ' ', '0', '0', ' ', ' ', ' ', '0', ' ', ' ', ' ', ' ', ' ',
//	 ' ', '0' },
//	 { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
//	 '0', '0' } };

	static char[][] map = { { '0', '0', '0', '0', '0' },
			{ '@', ' ', ' ', '0', '0' }, { '0', ' ', ' ', ' ', '0' },
			{ '0', '0', '0', '#', '0' }, };

	public static void main(String[] args) throws Exception {
		List<Gene> genes = new ArrayList<Gene>();
		for (int i = 0; i < 140; i++) {
			genes.add(GeneGenerator.generator(Constants.getMaxChromoLen()));
		}

		// List<Integer> chromo = new ArrayList<Integer>();
		// Collections.addAll(chromo, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1,
		// 1,
		// 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1,
		// 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
		// System.out.println(chromo.size());
		// for (int i = 0; i < map.length; i++) {
		// char[] line = map[i];
		// for (int j = 0; j < line.length; j++) {
		// System.out.print(map[i][j]);
		// }
		// System.out.println();
		// }
		// Gene g = new Gene();
		// g.setChromosome(chromo);
		// genes.add(g);
		Maze maze = new Maze(map, genes, Constants.getRateCrossOver(),
				Constants.getRateMutation());
		Long start = System.currentTimeMillis();
		maze.evolve();
		System.out.println(System.currentTimeMillis() - start);
	}
}
