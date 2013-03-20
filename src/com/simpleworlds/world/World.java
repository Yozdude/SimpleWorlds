package com.simpleworlds.world;

import java.util.ArrayList;
import java.util.List;

import com.simpleworlds.display.MainWindow;
import com.simpleworlds.utils.Vec;
import com.simpleworlds.world.entities.HexSpace;
import com.simpleworlds.world.entities.Nation;

public class World {
  public HexSpace hexes[][];

  public List<Nation> nations;

  public World() {
    nations = new ArrayList<Nation>();
  }

  public void draw() {
    if (hexes != null) {
      for (int i=0; i<hexes.length; i++) {
        for (int j=0; j<hexes[0].length; j++) {
          hexes[i][j].draw();
        }
      }
    }
  }

  public void step() {

  }

  public List<HexSpace> getNeighbors(int x, int y) {
    ArrayList<HexSpace> neighbors = new ArrayList<HexSpace>();
    if (x % 2 != 0) {
      for (int i=x-1; i<=x+1; i++) {
        for (int j=y; j<=y+1; j++) {
          if (getHex(i, j) != null) {
            neighbors.add(getHex(i, j));
          }
        }
      }
      if (getHex(x, y-1) != null) {
        neighbors.add(getHex(x, y-1));
      }
    } else {
      for (int i=x-1; i<=x+1; i++) {
        for (int j=y-1; j<=y; j++) {
          if (getHex(i, j) != null) {
            neighbors.add(getHex(i, j));
          }
        }
      }
      if (getHex(x, y+1) != null) {
        neighbors.add(getHex(x, y+1));
      }
    }
    return neighbors;
  }

  public List<HexSpace> getNeighbors(Vec v) {
    return getNeighbors(v.x, v.y);
  }

  public List<HexSpace> getNeighbors(HexSpace hex) {
    return getNeighbors(MainWindow.worldToGrid(hex.worldPos));
  }

  public HexSpace getHex(int x, int y) {
    if (x < 0 || x >= hexes.length || y < 0 || y >= hexes[0].length) {
      return null;
    } else {
      return hexes[x][y];
    }
  }
}
