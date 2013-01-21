package org.skyranger.lifegame;

import java.util.Arrays;

/**
 * This is simple realization of math game - Life
 * It allow u to define game grid, seeds it with life 
 * and than iterate it step by step, and watch what 
 * happens with it thru generations
 * 
 * @author Vasiliy Altunin
 * @version %I%, %G%
 * @since 1.0
 * 
 * This is main class. It creates grid and maintain it
 */
public class LifeGame {

	private int fieldWidth;
	private int fieldHeight;
	private int[][] grid;

	/**
	 * Class constructor
	 * @param width  width of grid 
	 * @param height  height of grid
	 */
	public LifeGame(int width, int height) {
		this.setGrid(new int[width][height]);
		this.fieldWidth = width;
		this.fieldHeight = height;
	}

	/**
	 * Return field width 
	 * @return the fieldWidth
	 */
	public int getFieldWidth() {
		return fieldWidth;
	}

	/**
	 * Return field height
	 * @return the fieldHeight
	 */
	public int getFieldHeight() {
		return fieldHeight;
	}

	/**
	 * Return grid array
	 * @return the grid
	 */
	public int[][] getGrid() {
		return grid;
	}

	/**
	 * Sets grid array
	 * @param grid  array to set
	 *            
	 */
	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

	/**
	 * Wipes grid with 0
	 */
	public void wipe() {
		for (int[] row : this.grid)
			Arrays.fill(row, 0);
	}

	/**
	 * @param row  row index
	 * @param col  column index
	 * @return void	 * 
	 */
	public void seed(int row, int col) {
		this.grid[row][col] = 1;
	}

	
	/**
	 * 
	 */
	public void doLife() {
		int[][] tmpgrid = new int[this.fieldWidth][this.fieldHeight];

		int neigh = 0;

		for (int i = 0; i <= this.fieldWidth - 1; i++) {
			for (int j = 0; j <= this.fieldHeight - 1; j++) {

				neigh=this.getNeighboursCount(i, j);

				if (this.isLive(i, j)) {
					//System.out.println(neigh);
					if ((neigh>3) || (neigh<=1)){
						tmpgrid[i][j]=0;
					}else{
						tmpgrid[i][j]=1;
					}
				} else {
					if (neigh==3){
						tmpgrid[i][j]=1;
					}
				}

			}
		}
		this.grid=tmpgrid;				
	}

	public boolean isLive(int x, int y) {
		if (this.grid[x][y] == 1) {
			return true;
		} else {
			return false;
		}
	}

	private boolean[] checkNeighbours(int row, int col) {
		boolean isUp = false;
		boolean isDown = false;
		boolean isLeft = false;
		boolean isRight = false;

		if (row == 0) {
			isDown = true;
		}

		if (col == 0) {
			isRight = true;
		}

		if (row == this.getFieldWidth() - 1) {
			isUp = true;
			isDown = false;
		}

		if (col == this.getFieldHeight() - 1) {
			isLeft = true;
			isRight = false;
		}

		if ((row < this.getFieldWidth() - 1) && (row != 0)) {
			isDown = true;
			isUp = true;
		}

		if ((col < this.getFieldHeight() - 1) && (col != 0)) {
			isLeft = true;
			isRight = true;
		}

		return new boolean[] { isUp, isDown, isLeft, isRight };
	}

	private int getNeighboursCount(int row, int col) {
		boolean[] neig = checkNeighbours(row, col); // up,down,left,right (0,1,2,3)

		int cnt = 0;

		if (neig[0]) { //up-left
			if (isLive(row - 1, col)) { //up
				cnt++;
			}		
		}

		if (neig[2]) { //up-left
			if (isLive(row, col-1)) { //left
				cnt++;
			}
		}

		if (neig[3]) {
			if (isLive(row, col+1)) { //right
				cnt++;
			}
		}

		if (neig[1]) {
			if (isLive(row + 1, col)) { //down
				cnt++;
			}
		}


		if ((neig[0]) && (neig[2])) {
			if (isLive(row - 1, col-1)) { //up-left
				cnt++;
			}
		}

		if ((neig[0]) && (neig[3])) {
			if (isLive(row - 1, col+1)) { //up-right
				cnt++;
			}
		}

		if ((neig[1]) && (neig[2])) {
			if (isLive(row + 1, col-1)) { //down-left
				cnt++;
			}
		}

		if ((neig[1]) && (neig[3])) {
			if (isLive(row + 1, col+1)) { //down-right
				cnt++;
			}
		}


		return cnt;

	}

	public void printLife() {
		System.out.println("-------");
		for (int[] row : this.grid) {
			for (int cell : row) {
				System.out.print(cell);
			}
			System.out.print("\n");
		}
		System.out.println("-------");
	}

}
