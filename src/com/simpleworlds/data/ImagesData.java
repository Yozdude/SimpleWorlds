package com.simpleworlds.data;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

import com.simpleworlds.utils.Vec;
import com.simpleworlds.world.entities.HexSpace;

public class ImagesData {

  public static enum TERRAIN_TYPE {UNKNOWN, GRASSLAND, FOREST, MOUNTAIN, SAND, SALTWATER, FRESHWATER, FARMLAND};
  public static PImage TERRAIN_IMAGE[] = new PImage[TERRAIN_TYPE.values().length];

  public static enum RESOURCE_TYPE {GRAIN, FISH, WOOD, HIDE, ORE, METAL};
  public static PImage RESOURCE_IMAGE[] = new PImage[RESOURCE_TYPE.values().length];

  public static enum STRUCTURE_TYPE {SETTLEMENT, TOWN, CITY};
  public static PImage STRUCTURE_IMAGE[] = new PImage[STRUCTURE_TYPE.values().length];

  public static enum UNIT_TYPE {GROUND, RANGED, RIDER, SIEGE, BOAT};
  public static PImage UNIT_IMAGE[] = new PImage[UNIT_TYPE.values().length];

  public static enum EFFECT_TYPE {VORTEX};
  public static PImage EFFECT_IMAGE[] = new PImage[EFFECT_TYPE.values().length];

  public static enum ACTION_TYPE {EXPLORE};
  public static PImage ACTION_IMAGE[] = new PImage[ACTION_TYPE.values().length];

  public static PImage selectedImage;

  public static void initialize() {
    for (int i=0; i<TERRAIN_TYPE.values().length; i++) {
      TERRAIN_IMAGE[i] = MetaData.mainWindow.loadImage("/images/terrain/" + TERRAIN_TYPE.values()[i].toString() + ".png");
    }

    for (int i=0; i<RESOURCE_TYPE.values().length; i++) {
      RESOURCE_IMAGE[i] = MetaData.mainWindow.loadImage("/images/resources/" + RESOURCE_TYPE.values()[i].toString() + ".png");
    }

    for (int i=0; i<STRUCTURE_TYPE.values().length; i++) {
      STRUCTURE_IMAGE[i] = MetaData.mainWindow.loadImage("/images/structures/" + STRUCTURE_TYPE.values()[i].toString() + ".png");
    }

    for (int i=0; i<UNIT_TYPE.values().length; i++) {
      UNIT_IMAGE[i] = MetaData.mainWindow.loadImage("/images/units/" + UNIT_TYPE.values()[i].toString() + ".png");
    }
/*
    for (int i=0; i<EFFECT_TYPE.values().length; i++) {
      EFFECT_TYPE[i] = MetaData.mainWindow.loadImage("/images/effects/" + EFFECT_TYPE.values()[i].toString() + ".png");
    }
*/
/*
    for (int i=0; i<STRUCTURE_TYPE.values().length; i++) {
      PGraphics g = MetaData.mainWindow.createGraphics(HexSpace.R * 2, HexSpace.R * 2, PApplet.OPENGL);
      g.beginDraw();
      g.background(g.color(255, 0));
      g.strokeWeight(2);
      g.ellipseMode(PApplet.CENTER);
      g.fill(g.color(150, 150, 150));
      if (i == 0) {
        g.stroke(g.color(0, 0, 255));
      } else if (i == 1) {
        g.stroke(g.color(0, 255, 0));
      } else if (i == 2) {
        g.stroke(g.color(255, 0, 0));
      }
      g.ellipse(HexSpace.R, HexSpace.R, HexSpace.R, HexSpace.R);
      g.endDraw();
      STRUCTURE_IMAGE[i] = g.get();
    }

    for (int i=0; i<UNIT_TYPE.values().length; i++) {
      PGraphics g = MetaData.mainWindow.createGraphics(HexSpace.R * 2, HexSpace.R * 2, PApplet.OPENGL);
      g.beginDraw();
      g.background(g.color(255, 0));
      g.stroke(g.color(0));
      g.strokeWeight(2);
      g.rectMode(PApplet.CENTER);
      if (i == 0) {
          g.fill(g.color(0, 0, 255));
      } else if (i == 1) {
        g.fill(g.color(0, 255, 0));
      } else if (i == 2) {
        g.fill(g.color(255, 0, 0));
      } else if (i == 3) {
        g.fill(g.color(200, 200, 200));
      } else if (i == 4) {
        g.fill(g.color(100, 100, 100));
      }
      g.rect(HexSpace.R, HexSpace.R, 0.75f * HexSpace.R, 1.25f * HexSpace.R);
      g.endDraw();
      UNIT_IMAGE[i] = g.get();
    }
*/
    for (int i=0; i<EFFECT_TYPE.values().length; i++) {
      PGraphics g = MetaData.mainWindow.createGraphics(HexSpace.R * 2, HexSpace.R * 2, PApplet.OPENGL);
      g.beginDraw();
      g.background(g.color(255, 0));
      g.stroke(g.color(255));
      g.strokeWeight(2);
      g.ellipseMode(PApplet.CENTER);
      if (i == 0) {
          g.fill(g.color(100, 100, 255));
      }
      g.ellipse(HexSpace.R, HexSpace.R, HexSpace.R, HexSpace.R);
      g.endDraw();
      EFFECT_IMAGE[i] = g.get();
    }

    for (int i=0; i<EFFECT_TYPE.values().length; i++) {
      PGraphics g = MetaData.mainWindow.createGraphics(HexSpace.R * 2, HexSpace.R * 2, PApplet.OPENGL);
      g.beginDraw();
      g.background(g.color(255, 0));
      g.stroke(g.color(255));
      g.strokeWeight(2);
      g.ellipseMode(PApplet.CENTER);
      if (i == 0) {
          g.fill(g.color(100, 100, 255));
      }
      g.ellipse(HexSpace.R, HexSpace.R, HexSpace.R, HexSpace.R);
      g.endDraw();
      EFFECT_IMAGE[i] = g.get();
    }

    for (int i=0; i<ACTION_TYPE.values().length; i++) {
      PGraphics g = MetaData.mainWindow.createGraphics(HexSpace.R * 2, HexSpace.R * 2, PApplet.OPENGL);
      g.beginDraw();
      g.background(g.color(255, 0));
      g.stroke(g.color(255));
      g.strokeWeight(2);
      g.ellipseMode(PApplet.CENTER);
      g.fill(10 * i);
      g.ellipse(HexSpace.R, HexSpace.R, 2 * HexSpace.R, 2 * HexSpace.R);
      g.endDraw();
      ACTION_IMAGE[i] = g.get();
    }

    selectedImage = MetaData.mainWindow.loadImage("/images/misc/SELECTED.png");
  }

  // Terrain
  public static void drawTerrainAt(TERRAIN_TYPE type, Vec screenPos) {
    MetaData.mainWindow.image(TERRAIN_IMAGE[type.ordinal()], screenPos.x, screenPos.y);
  }

  public static void drawAt(TERRAIN_TYPE type, Vec screenPos) {
    ImagesData.drawTerrainAt(type, screenPos);
  }

  // Resources
  public static void drawResourceAt(RESOURCE_TYPE type, Vec screenPos) {
    MetaData.mainWindow.image(RESOURCE_IMAGE[type.ordinal()], screenPos.x, screenPos.y);
  }

  public static void drawAt(RESOURCE_TYPE type, Vec screenPos) {
    ImagesData.drawResourceAt(type, screenPos);
  }

  // Structures
  public static void drawStructureAt(STRUCTURE_TYPE type, Vec screenPos) {
    MetaData.mainWindow.image(STRUCTURE_IMAGE[type.ordinal()], screenPos.x, screenPos.y);
  }

  public static void drawAt(STRUCTURE_TYPE type, Vec screenPos) {
    ImagesData.drawStructureAt(type, screenPos);
  }

  // Units
  public static void drawUnitAt(UNIT_TYPE type, Vec screenPos) {
    MetaData.mainWindow.image(UNIT_IMAGE[type.ordinal()], screenPos.x, screenPos.y);
  }

  public static void drawAt(UNIT_TYPE type, Vec screenPos) {
    ImagesData.drawUnitAt(type, screenPos);
  }

  // Effects
  public static void drawEffectAt(EFFECT_TYPE type, Vec screenPos) {
    MetaData.mainWindow.image(EFFECT_IMAGE[type.ordinal()], screenPos.x, screenPos.y);
  }

  public static void drawAt(EFFECT_TYPE type, Vec screenPos) {
    ImagesData.drawEffectAt(type, screenPos);
  }

  // Actions
  public static void drawActionAt(ACTION_TYPE type, Vec screenPos) {
    MetaData.mainWindow.image(ACTION_IMAGE[type.ordinal()], screenPos.x, screenPos.y);
  }

  public static void drawAt(ACTION_TYPE type, Vec screenPos) {
    ImagesData.drawActionAt(type, screenPos);
  }
}
