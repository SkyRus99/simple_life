package org.skyranger.lifegame;

import java.util.Arrays;

public class LifeGame {

	private int fieldWidth;
	private int fieldHeight;
	private int[][] grid;

	LifeGame (int width, int height)
	{
		this.setGrid(new int[width][height]);
		this.fieldWidth=width;
		this.fieldHeight=height;
	}

	/**
	 * @return the fieldWidth
	 */
	public int getFieldWidth() {
		return fieldWidth;
	}

	/**
	 * @return the fieldHeight
	 */
	public int getFieldHeight() {
		return fieldHeight;
	}

	/**
	 * @return the grid
	 */
	public int[][] getGrid() {
		return grid;
	}

	/**
	 * @param grid the grid to set
	 */
	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

	public void wipe() {
		for (int[] row : this.grid)
			Arrays.fill(row, 0);
	}

	public void seed(int i, int j) {
		this.grid[i][j] = 1;		
	}

	public void doLife() {
		for (int i=0;i<=this.fieldWidth-1;i++)
		{
			for (int j=0;j<=this.fieldHeight-1;j++)
			{
				if (this.isLive(i,j))
				{
					System.out.print("T");
				}
				else
				{
					System.out.print(".");					
				}
			}
			System.out.print("\n");
		}
	}

	//checkNeighbours

	private boolean isLive(int x, int y) {
		if (this.grid[x][y]==1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean[] checkNeighbours(int row, int col)
	{
		boolean isUp = false;
		boolean isDown = false;
		boolean isLeft = false;
		boolean isRight = false;

//		if ( (row==0) && (col == 0) ) //0,0 
//		{
//			isRight = true;
//			isDown = true;
//			return new boolean[]{isUp,isDown,isLeft,isRight};
//		}
//
//		
//		if ( (row==this.getFieldWidth()-1) && (col == this.getFieldHeight()-1) ) //max,max 
//		{
//			isLeft = true;
//			isUp = true;
//			return new boolean[]{isUp,isDown,isLeft,isRight};
//		}
//
		
		
		if (row==0) 
		{
			isDown = true;
		}
		
		if (col==0) 
		{
			isRight = true;
		}
		
		if (row==this.getFieldWidth()-1) 
		{
			isUp = true;
			isDown = false;
		}

		if (col==this.getFieldHeight()-1) 
		{
			isLeft = true;
			isRight = false;
		}
		
		if ( (row<this.getFieldWidth()-1) && (row!=0)) 
		{
			isDown = true;
			isUp = true;
		}

		if ((col<this.getFieldHeight()-1)  && (col!=0))
		{
			isLeft = true;
			isRight = true;
		}
		
		
//		if ((row>0) && (col >0) && (row<this.getFieldWidth()-1) && (col<this.getFieldHeight()-1)) //any cell inside
//		{
//			isLeft = true;
//			isRight = true;
//			isDown = true;
//			isUp = true;
//			return new boolean[]{isUp,isDown,isRight,isLeft};
//		}

		
		return new boolean[]{isUp,isDown,isLeft,isRight};
	}
	
	public void printLife() {
		for (int[] row : this.grid)
		{
			for(int cell : row)
			{
				System.out.print(cell);
			}
			System.out.print("\n");
		}
	}



}
