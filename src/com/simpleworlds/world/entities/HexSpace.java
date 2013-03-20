package com.simpleworlds.world.entities;

import com.simpleworlds.data.ImagesData;
import com.simpleworlds.data.ImagesData.TERRAIN_TYPE;
import com.simpleworlds.display.MainWindow;
import com.simpleworlds.utils.Vec;

public class HexSpace implements DrawableInterface {

  public static final int R = 20;
  public static final float W = 2 * R;
  public static final float S = (float)Math.floor((3.0 / 2.0) * R);
  public static final float H = (float)Math.floor(Math.sqrt(3) * R);

  public TERRAIN_TYPE terrainType;
  public ResourceEntity resource;
  public StructureEntity structure;
  public UnitEntity unit;
  public EffectEntity effect;
  public Vec worldPos;
  public boolean visible = false;

  public HexSpace(Vec world, TERRAIN_TYPE t) {
    worldPos = world;
    terrainType = t;
  }

  public void draw() {
    Vec screenPos = MainWindow.worldToScreen(worldPos);
    if (visible) {
      ImagesData.drawTerrainAt(terrainType, screenPos);
      if (resource != null) {
        resource.drawAtScreenPos(screenPos);
      }
      if (structure != null) {
        structure.drawAtScreenPos(screenPos);
      }
      if (unit != null) {
        unit.drawAtScreenPos(screenPos);
      }
      if (effect != null) {
        effect.drawAtScreenPos(screenPos);
      }
    } else {
      ImagesData.drawTerrainAt(TERRAIN_TYPE.UNKNOWN, screenPos);
    }
  }
}
