package com.simpleworlds.utils;

import java.util.Random;

import com.simpleworlds.data.ImagesData;
import com.simpleworlds.data.ImagesData.RESOURCE_TYPE;
import com.simpleworlds.data.ImagesData.STRUCTURE_TYPE;
import com.simpleworlds.data.ImagesData.TERRAIN_TYPE;
import com.simpleworlds.data.ImagesData.UNIT_TYPE;
import com.simpleworlds.display.MainWindow;
import com.simpleworlds.world.World;
import com.simpleworlds.world.entities.HexSpace;
import com.simpleworlds.world.entities.Nation;
import com.simpleworlds.world.entities.ResourceEntity;
import com.simpleworlds.world.entities.StructureEntity;
import com.simpleworlds.world.entities.UnitEntity;

public class WorldGenerator {
  public static World generateRandomWorld(int width, int height) {
    World world = new World();

    world.hexes = new HexSpace[width][height];
    for (int i=0; i<width; i++) {
      for (int j=0; j<height; j++) {
        TERRAIN_TYPE t = TERRAIN_TYPE.values()[1 + (int)(Math.random()*(ImagesData.TERRAIN_IMAGE.length-1))];
        world.hexes[i][j] = new HexSpace(MainWindow.gridToWorld(new Vec(i, j)), t);
        if (Math.random() >= 0.95) {
          if (t.equals(TERRAIN_TYPE.SALTWATER) || t.equals(TERRAIN_TYPE.FRESHWATER)) {
        	world.hexes[i][j].resource = new ResourceEntity(RESOURCE_TYPE.FISH);
          } else {
        	RESOURCE_TYPE rt = RESOURCE_TYPE.values()[(int)(Math.random()*ImagesData.RESOURCE_IMAGE.length)];
        	if (!rt.equals(RESOURCE_TYPE.FISH)) {
              world.hexes[i][j].resource = new ResourceEntity(rt);
            }
          }
        }
        if (Math.random() >= 0.95 && !t.equals(TERRAIN_TYPE.SALTWATER) && !t.equals(TERRAIN_TYPE.FRESHWATER)) {
          world.hexes[i][j].structure = new StructureEntity(STRUCTURE_TYPE.values()[(int)(Math.random()*ImagesData.STRUCTURE_IMAGE.length)]);
        }
        if (Math.random() >= 0.95 && !t.equals(TERRAIN_TYPE.SALTWATER) && !t.equals(TERRAIN_TYPE.FRESHWATER)) {
          world.hexes[i][j].unit = new UnitEntity(UNIT_TYPE.values()[(int)(Math.random()*ImagesData.UNIT_IMAGE.length)]);
        }
      }
    }

    Random random = new Random();
    double x = width/2.0 + random.nextGaussian() * width/4.0;
    x = Math.min(Math.max(Math.round(x), 0), width-1);
    double y = height/2.0 + random.nextGaussian() * height/4.0;
    y = Math.min(Math.max(Math.round(y), 0), height-1);
    Vec start = new Vec((int)x, (int)y);
    world.getHex(start.x, start.y).structure = new StructureEntity(STRUCTURE_TYPE.TOWN);
    for (HexSpace hex : world.getNeighbors(start)) {
      hex.visible = true;
    }

    for (int i=0; i<3; i++) {
      world.nations.add(new Nation());
    }

    return world;
  }
}
