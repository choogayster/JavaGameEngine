package sample.old;

import java.applet.Applet;
import java.awt.*;
import java.util.Random;

public class Renderer extends Applet {
	
	static int[][] tilemap;
	static int rows;
	static int columns;
	
	@Override
	public void init() {
		setSize(800, 480);
		setBackground(Color.BLACK);
		createTilemap();
	}
	
	private void createTilemap() {
		tilemap = new int [50][30];
		rows = tilemap.length;
		columns = tilemap[1].length;
		Random r = new Random();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				tilemap[i][j] = r.nextInt(5);
			}
		}
	}

	@Override 
	public void paint(Graphics g) {
		for (int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				int mod_i = 16*i;
				int mod_j = 16*j;
				switch(tilemap[i][j]) {
				case 0:
					g.setColor(Color.RED);
					g.fillRect(mod_i, mod_j, 16, 16);
					break;
				case 1:
					g.setColor(Color.BLUE);
					g.fillRect(mod_i, mod_j, 16, 16);
					break;
				case 2:
					g.setColor(Color.YELLOW);
					g.fillRect(mod_i, mod_j, 16, 16);
					break;
				case 3:
					g.setColor(Color.WHITE);
					g.fillRect(mod_i, mod_j, 16, 16);
					break;
				case 4:
					g.setColor(Color.GREEN);
					g.fillRect(mod_i, mod_j, 16, 16);
					break;
				}
			}
		}
	}
}
