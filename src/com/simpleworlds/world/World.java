package com.simpleworlds.world;

import java.util.ArrayList;
import java.util.List;

import com.simpleworlds.data.ImagesData;
import com.simpleworlds.data.ImagesData.RESOURCE_TYPE;
import com.simpleworlds.data.ImagesData.STRUCTURE_TYPE;
import com.simpleworlds.data.ImagesData.TERRAIN_TYPE;
import com.simpleworlds.display.MainWindow;
import com.simpleworlds.utils.Vec;
import com.simpleworlds.world.entities.HexSpace;
import com.simpleworlds.world.entities.Nation;
import com.simpleworlds.world.entities.ResourceEntity;
import com.simpleworlds.world.entities.StructureEntity;

public class World {
  public HexSpace hexes[][];

  public List<Nation> nations;

  public World() {
    nations = new ArrayList<Nation>();
  }

  public void generateRandomWorld(int width, int height) {
    hexes = new HexSpace[width][height];
    for (int i=0; i<width; i++) {
      for (int j=0; j<height; j++) {
        hexes[i][j] = new HexSpace(MainWindow.gridToWorld(new Vec(i, j)), TERRAIN_TYPE.values()[1 + (int)(Math.random()*(ImagesData.TERRAIN_IMAGE.length-1))]);
        if (Math.random() >= 0.7) {
          hexes[i][j].resource = new ResourceEntity(RESOURCE_TYPE.values()[(int)(Math.random()*ImagesData.RESOURCE_IMAGE.length)]);
        }
      }
    }

    Vec start = new Vec((int)Math.floor(Math.random() * hexes.length), (int)Math.floor(Math.random() * hexes[0].length));
    getHex(start.x, start.y).structure = new StructureEntity(STRUCTURE_TYPE.TOWN);
    for (HexSpace hex : getNeighbors(start)) {
      hex.visible = true;
    }

    for (int i=0; i<3; i++) {
      nations.add(new Nation());
    }
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
