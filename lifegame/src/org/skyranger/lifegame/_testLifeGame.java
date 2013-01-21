package org.skyranger.lifegame;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for a Life game
 * 
 * @author Vasiliy Altunin
 * @version %I%, %G%
 * @since 1.0
 */
public class _testLifeGame {

	//test figures source - http://life.written.ru/game_of_life_review_by_gardner
	
	private LifeGame lg;

	@Before
	public void setUp() throws Exception {
		lg = new LifeGame(5, 4);

		int[][] aGrid = new int[][] { 
				{ 0, 0, 0, 1 }, 
				{ 0, 0, 0, 0 },
				{ 0, 1, 1, 0 }, 
				{ 0, 0, 0, 0 }, 
				{ 0, 0, 0, 1 } };
		lg.setGrid(aGrid);
	}

	@After
	public void tearDown() throws Exception {
	}

	private Method getMethod(String name, Object o, Class<?>... args) {
		try {
			return o.getClass().getDeclaredMethod(name, args);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	private Object invokeMethod(Method m, Object o, Object... args) {
		try {
			m.setAccessible(true);
			return m.invoke(o, args);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Test
	public void test() {

		assertEquals(5, lg.getFieldWidth());
		assertEquals(4, lg.getFieldHeight());

		int[][] aGrid = new int[][] { 
				{ 0, 0, 0, 1 }, 
				{ 0, 0, 0, 0 },
				{ 0, 1, 1, 0 }, 
				{ 0, 0, 0, 0 }, 
				{ 0, 0, 0, 1 } };

		lg.setGrid(aGrid);

		assertEquals(1, lg.getGrid()[0][3]); // [row][column], from 0
		assertEquals(1, lg.getGrid()[2][1]);
		assertEquals(1, lg.getGrid()[2][2]);
		assertEquals(1, lg.getGrid()[4][3]);
		assertEquals(0, lg.getGrid()[0][0]);
		assertEquals(0, lg.getGrid()[3][1]);

		lg.wipe();

		assertEquals(0, lg.getGrid()[0][3]);
		assertEquals(0, lg.getGrid()[2][1]);
		assertEquals(0, lg.getGrid()[2][2]);
		assertEquals(0, lg.getGrid()[4][3]);

		lg.seed(0, 3);
		lg.seed(2, 1);
		lg.seed(2, 2);
		lg.seed(4, 3);

		assertEquals(1, lg.getGrid()[0][3]); // [row][column], from 0
		assertEquals(1, lg.getGrid()[2][1]);
		assertEquals(1, lg.getGrid()[2][2]);
		assertEquals(1, lg.getGrid()[4][3]);
		assertEquals(0, lg.getGrid()[0][0]);
		assertEquals(0, lg.getGrid()[3][1]);

		//lg.printLife();

		Method m = getMethod("isLive", lg, int.class, int.class);

		assertEquals(true, this.invokeMethod(m, lg, 4, 3)); // [row][column],
															// from 0
		assertEquals(false, this.invokeMethod(m, lg, 0, 0)); // [row][column],
																// from 0
		assertEquals(true, this.invokeMethod(m, lg, 2, 2)); // [row][column],
															// from 0

		m = getMethod("checkNeighbours", lg, int.class, int.class);

		// 0,0
		boolean[] x0y0 = new boolean[] { false, true, false, true };// Up,Down,Left,Right
		boolean[] result = (boolean[]) this.invokeMethod(m, lg, 0, 0);
		assertEquals(true, Arrays.equals(x0y0, result));

		// max,max
		boolean[] xmym = new boolean[] { true, false, true, false };// Up,Down,Left,Right
		result = (boolean[]) this.invokeMethod(m, lg, lg.getFieldWidth() - 1,
				lg.getFieldHeight() - 1);
		assertEquals(true, Arrays.equals(xmym, result));

		// max,0
		boolean[] xmy0 = new boolean[] { true, false, false, true };// Up,Down,Left,Right
		result = (boolean[]) this
				.invokeMethod(m, lg, lg.getFieldWidth() - 1, 0);
		assertEquals(true, Arrays.equals(xmy0, result));

		// 0,max
		boolean[] x0ym = new boolean[] { false, true, true, false };// Up,Down,Left,Right
		result = (boolean[]) this.invokeMethod(m, lg, 0,
				lg.getFieldHeight() - 1);
		assertEquals(true, Arrays.equals(x0ym, result));

		// 0,2
		boolean[] x0y2 = new boolean[] { false, true, true, true };// Up,Down,Left,Right
		result = (boolean[]) this.invokeMethod(m, lg, 0, 2);
		assertEquals(true, Arrays.equals(x0y2, result));

		// 2,0
		boolean[] x2y0 = new boolean[] { true, true, false, true };// Up,Down,Left,Right
		result = (boolean[]) this.invokeMethod(m, lg, 2, 0);
		assertEquals(true, Arrays.equals(x2y0, result));

		// 3,2
		boolean[] x3y2 = new boolean[] { true, true, true, true };// Up,Down,Left,Right
		result = (boolean[]) this.invokeMethod(m, lg, 3, 2);
		assertEquals(true, Arrays.equals(x3y2, result));

		// 1,1
		boolean[] x1y1 = new boolean[] { true, true, true, true };// Up,Down,Left,Right
		result = (boolean[]) this.invokeMethod(m, lg, 1, 1);
		assertEquals(true, Arrays.equals(x1y1, result));

		// 3,1
		boolean[] x3y1 = new boolean[] { true, true, true, true };// Up,Down,Left,Right
		result = (boolean[]) this.invokeMethod(m, lg, 3, 1);
		assertEquals(true, Arrays.equals(x3y1, result));

		// 3,3
		boolean[] x3y3 = new boolean[] { true, true, true, false };// Up,Down,Left,Right
		result = (boolean[]) this.invokeMethod(m, lg, 3, 3);
		assertEquals(true, Arrays.equals(x3y3, result));

	}

	@Test
	public void testNeighboursCount() {
		Method m = getMethod("getNeighboursCount", lg, int.class, int.class);

		assertEquals(1, this.invokeMethod(m, lg, 2, 1));
		assertEquals(0, this.invokeMethod(m, lg, 4, 3));
		assertEquals(3, this.invokeMethod(m, lg, 1, 2));
		assertEquals(2, this.invokeMethod(m, lg, 3, 1));
		
		lg.seed(1, 0);
		lg.seed(1, 1);
		lg.seed(1, 2);
		
		lg.seed(2, 0);

		lg.seed(3, 0);
		lg.seed(3, 1);
		lg.seed(3, 2);
		
		//lg.printLife();
		/*
		
		0001
		1110
		1110
		1110
		0001	
		
		*/
		
		assertEquals(8, this.invokeMethod(m, lg, 2, 1));

		assertEquals(4, this.invokeMethod(m, lg, 1, 2));

		assertEquals(3, this.invokeMethod(m, lg, 2, 3));

		assertEquals(1, this.invokeMethod(m, lg, 4, 3));
	}

	@Test
	public void testDoLifeTriplets()
	{
		int[][] aGrid = new int[][] { 
				{ 0, 0, 0, 0 }, 
				{ 0, 1, 0, 0 },
				{ 0, 1, 0, 0 }, 
				{ 0, 0, 1, 0 }, 
				{ 0, 0, 0, 0 } };
		lg.setGrid(aGrid);
		lg.doLife();
		/*
		0000
		0000
		0110
		0000
		0000
		
		 */
		Method m = getMethod("isLive", lg, int.class, int.class);
		assertEquals(true, this.invokeMethod(m, lg, 2, 1));
		assertEquals(true, this.invokeMethod(m, lg, 2, 2));
		

		aGrid = new int[][] { 
				{ 0, 0, 0, 0 }, 
				{ 0, 1, 0, 0 },
				{ 1, 0, 1, 0 }, 
				{ 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0 } };
		lg.setGrid(aGrid);
		lg.doLife();
		/*
		0000
		0100
		0100
		0000
		0000	
		*/
		m = getMethod("isLive", lg, int.class, int.class);
		assertEquals(true, this.invokeMethod(m, lg, 1, 1));
		assertEquals(true, this.invokeMethod(m, lg, 2, 1));

		
		aGrid = new int[][] { 
				{ 0, 0, 1, 0 }, 
				{ 0, 1, 0, 0 },
				{ 1, 0, 0, 0 }, 
				{ 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0 } };
		lg.setGrid(aGrid);
		lg.doLife();
		/*
		0000
		0100
		0000
		0000
		0000	
		*/
		m = getMethod("isLive", lg, int.class, int.class);
		assertEquals(true, this.invokeMethod(m, lg, 1, 1));
		
		
		aGrid = new int[][] { 
				{ 0, 0, 0, 0 }, 
				{ 0, 1, 1, 0 },
				{ 0, 1, 0, 0 }, 
				{ 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0 } };
		lg.setGrid(aGrid);
		lg.doLife();
		/*
		0000
		0110
		0110
		0000
		0000	
		*/
		m = getMethod("isLive", lg, int.class, int.class);
		assertEquals(true, this.invokeMethod(m, lg, 1, 1));
		assertEquals(true, this.invokeMethod(m, lg, 1, 2));
		assertEquals(true, this.invokeMethod(m, lg, 2, 1));
		assertEquals(true, this.invokeMethod(m, lg, 2, 2));
		lg.doLife();
		/*
		0000
		0110
		0110
		0000
		0000	
		*/
		m = getMethod("isLive", lg, int.class, int.class);
		assertEquals(true, this.invokeMethod(m, lg, 1, 1));
		assertEquals(true, this.invokeMethod(m, lg, 1, 2));
		assertEquals(true, this.invokeMethod(m, lg, 2, 1));
		assertEquals(true, this.invokeMethod(m, lg, 2, 2));

		
		

		aGrid = new int[][] { 
				{ 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0 },
				{ 0, 1, 1, 1 }, 
				{ 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0 } };
		lg.setGrid(aGrid);
//		lg.printLife();
		lg.doLife();
		/*
		0000
		0010
		0010 
		0010
		0000	
		*/
		m = getMethod("isLive", lg, int.class, int.class);
		assertEquals(true, this.invokeMethod(m, lg, 1, 2));
		assertEquals(true, this.invokeMethod(m, lg, 2, 2));
		assertEquals(true, this.invokeMethod(m, lg, 3, 2));
		
		lg.doLife();
		/*
		0000
		0000
		0111
		0000
		0000	
		*/
		m = getMethod("isLive", lg, int.class, int.class);
		assertEquals(true, this.invokeMethod(m, lg, 2, 1));
		assertEquals(true, this.invokeMethod(m, lg, 2, 2));
		assertEquals(true, this.invokeMethod(m, lg, 2, 3));
	}
}
