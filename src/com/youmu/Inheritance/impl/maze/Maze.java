package com.youmu.Inheritance.impl.maze;

import com.youmu.Inheritance.Evolution;
import com.youmu.Inheritance.Gene;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用遗传算法来解决迷宫
 */
public class Maze extends Evolution {

	public static final char MAP_ROUND = ' ';

	public static final char MAP_BAR = '0';

	public static final char MAP_START = '@';

	public static final char MAP_END = '#';

	private char[][] map;

	private static final int _DIRECT_UP = 0;
	private static final int _DIRECT_DOWN = 1;
	private static final int _DIRECT_LEFT = 2;
	private static final int _DIRECT_RIGHT = 3;

	private Point2D<Integer> startPoint;

	private Point2D<Integer> endPoint;

	public Maze(char[][] map, List<Gene> genes, float crossOverRate,
			float mutationRate) {
		super(genes, crossOverRate, mutationRate);
		// TODO Auto-generated constructor stub
		this.map = map;
		init();
	}

	/**
	 * 计算适应度 迷宫的适应度取决于当前位置到目的地的距离
	 * @param gene
	 * @return
	 */
	@Override
	public float caculateFitness(Gene gene) {
		// TODO Auto-generated method stub
		Point2D<Integer> lastPoint = testMove(gene);
		int diffX = Math.abs(lastPoint.getX() - endPoint.getX());
		int diffY = Math.abs(lastPoint.getY() - endPoint.getY());

		float fitness = (1.0f / (diffX + diffY + 1));

		return fitness;
	}

	public static List<Integer> binaryToIntArray(List<Integer> binary) {
		List<Integer> intArray = new ArrayList<Integer>();
		for (int i = 1; i < binary.size(); i += 2) {
			int d = 0;
			d |= binary.get(i - 1);
			d = d << 1;
			d |= binary.get(i);
			intArray.add(d);
		}
		return intArray;
	}

	private void init() {
		for (int i = 0; i < map.length; i++) {
			char[] line = map[i];
			for (int j = 0; j < line.length; j++) {
				if (map[i][j] == MAP_START) {
					startPoint = new Point2D<Integer>(j, i);
				}
				if (map[i][j] == MAP_END) {
					endPoint = new Point2D<Integer>(j, i);
				}
			}
		}
	}

	private Point2D<Integer> testMove(Gene gene) {
		List<Integer> directions = binaryToIntArray(gene.getChromosome());
		Point2D<Integer> point = new Point2D<Integer>(startPoint.getX(),
				startPoint.getY());
		for (int i = 0; i < directions.size(); i++) {

			Point2D<Integer> movePoint = new Point2D<Integer>(point.getX(),
					point.getY());
			switch (directions.get(i).intValue()) {
			case _DIRECT_UP:
				movePoint.setX(movePoint.getX());
				movePoint.setY(point.getY() - 1);
				break;
			case _DIRECT_DOWN:
				movePoint.setX(movePoint.getX());
				movePoint.setY(point.getY() + 1);
				break;
			case _DIRECT_LEFT:
				movePoint.setX(movePoint.getX() - 1);
				movePoint.setY(point.getY());
				break;
			case _DIRECT_RIGHT:
				movePoint.setX(movePoint.getX() + 1);
				movePoint.setY(point.getY());
				break;
			}

			if (movePoint.getY() < 0 || movePoint.getY() > map.length
					|| movePoint.getX() < 0 || movePoint.getX() > map[0].length) {
				return point;
			}

			char p = map[movePoint.getY()][movePoint.getX()];
			if (p == MAP_END) {
				return movePoint;
			} else if (p != MAP_ROUND) {
				return point;
			} else {
				point = movePoint;
			}
		}

		return point;
	}

	@Override
	public void print(Gene gene) {

		List<Integer> directions = binaryToIntArray(gene.getChromosome());
		Point2D<Integer> point = new Point2D<Integer>(startPoint.getX(),
				startPoint.getY());
		List<Point2D<Integer>> line = new ArrayList<Point2D<Integer>>();
		line.add(point);
		for (int i = 0; i < directions.size(); i++) {

			Point2D<Integer> movePoint = new Point2D<Integer>(point.getX(),
					point.getY());
			switch (directions.get(i).intValue()) {
			case _DIRECT_UP:
				movePoint.setX(movePoint.getX());
				movePoint.setY(point.getY() - 1);
				break;
			case _DIRECT_DOWN:
				movePoint.setX(movePoint.getX());
				movePoint.setY(point.getY() + 1);
				break;
			case _DIRECT_LEFT:
				movePoint.setX(movePoint.getX() - 1);
				movePoint.setY(point.getY());
				break;
			case _DIRECT_RIGHT:
				movePoint.setX(movePoint.getX() + 1);
				movePoint.setY(point.getY());
				break;
			}

			if (movePoint.getY() < 0 || movePoint.getY() > map.length
					|| movePoint.getX() < 0 || movePoint.getX() > map[0].length) {
			}

			char p = map[movePoint.getY()][movePoint.getX()];
			point = movePoint;
			line.add(point);
			if (p == MAP_END) {
				break;
			}
		}

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				boolean isLine = false;
				for (Point2D<Integer> p : line) {
					if (p.getX() == j && p.getY() == i) {
						isLine = true;
					}
				}
				if (isLine) {
					System.out.print('*');
				} else {
					System.out.print(map[i][j]);
				}
			}
			System.out.println();
		}

	}

	public char[][] getMap() {
		return map;
	}

}
