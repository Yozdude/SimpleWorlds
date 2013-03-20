/**
 * NOTICE: This software  source code and any of  its derivatives are the
 * confidential  and  proprietary   information  of  Vecna  Technologies,
 * Inc. (such source  and its derivatives are hereinafter  referred to as
 * "Confidential Information"). The  Confidential Information is intended
 * to be  used exclusively by  individuals or entities that  have entered
 * into either  a non-disclosure agreement or license  agreement (or both
 * of  these agreements,  if  applicable) with  Vecna Technologies,  Inc.
 * ("Vecna")   regarding  the  use   of  the   Confidential  Information.
 * Furthermore,  the  Confidential  Information  shall be  used  only  in
 * accordance  with   the  terms   of  such  license   or  non-disclosure
 * agreements.   All  parties using  the  Confidential Information  shall
 * verify that their  intended use of the Confidential  Information is in
 * compliance  with and  not in  violation of  any applicable  license or
 * non-disclosure  agreements.  Unless expressly  authorized by  Vecna in
 * writing, the Confidential Information  shall not be printed, retained,
 * copied, or  otherwise disseminated,  in part or  whole.  Additionally,
 * any party using the Confidential  Information shall be held liable for
 * any and  all damages incurred  by Vecna due  to any disclosure  of the
 * Confidential  Information (including  accidental disclosure).   In the
 * event that  the applicable  non-disclosure or license  agreements with
 * Vecna  have  expired, or  if  none  currently  exists, all  copies  of
 * Confidential Information in your  possession, whether in electronic or
 * printed  form, shall be  destroyed or  returned to  Vecna immediately.
 * Vecna  makes no  representations  or warranties  hereby regarding  the
 * suitability  of  the   Confidential  Information,  either  express  or
 * implied,  including  but not  limited  to  the  implied warranties  of
 * merchantability,    fitness    for    a   particular    purpose,    or
 * non-infringement. Vecna  shall not be liable for  any damages suffered
 * by  licensee as  a result  of  using, modifying  or distributing  this
 * Confidential Information.  Please email [info@vecnatech.com]  with any
 * questions regarding the use of the Confidential Information.
 */

package com.simpleworlds.display;

import processing.core.PApplet;

import com.simpleworlds.data.ImagesData;
import com.simpleworlds.data.MetaData;
import com.simpleworlds.utils.Vec;
import com.simpleworlds.utils.WorldGenerator;
import com.simpleworlds.world.World;
import com.simpleworlds.world.entities.HexSpace;

public class MainWindow extends PApplet {
  private static final long serialVersionUID = 1L;

  static Vec screenOffset = new Vec(0, 0);

  World world;

  Vec selected = null;

  ActionPopupBox actionPopup;

  public static void main(String args[]) {
    PApplet.main(new String[] { "--present", "com.simpleworlds.display.MainWindow" });
  }

  public void setup() {
    size(800, 800, OPENGL);

    MetaData.initialize(this);

    world = WorldGenerator.generateRandomWorld(20, 20);

    ellipseMode(CENTER);
  }

  public void draw() {
    background(0);

    world.draw();

    if (selected != null) {
      Vec world = worldToScreen(gridToWorld(selected));
      image(ImagesData.selectedImage, world.x, world.y);
    }

    if (actionPopup != null) {
      actionPopup.draw();
    }
  }

  public void mouseDragged() {
    mousePressed();
  }

  public void mousePressed() {
    if (mouseButton == LEFT) {
      if (actionPopup != null && actionPopup.pointInPopup(mouseX, mouseY)) {

      } else {
        selected = worldToGrid(screenToWorld(new Vec(mouseX, mouseY)));
        actionPopup = null;
      }
    } else if (mouseButton == RIGHT) {
      selected = worldToGrid(screenToWorld(new Vec(mouseX, mouseY)));
      actionPopup = new ActionPopupBox(gridToWorld(selected), world.getHex(selected.x, selected.y).getHexActions());
    } else if (mouseButton == CENTER) {
      screenOffset.addLocal(mouseX - pmouseX, mouseY - pmouseY);
    }
  }

  public void mouseReleased() {

  }

  public static Vec gridToWorld(Vec grid) {
    return new Vec(Math.round(grid.x * HexSpace.S), Math.round(grid.y * HexSpace.H + (grid.x % 2) * (HexSpace.H / 2)));
  }

  public static Vec worldToGrid(Vec world) {
    int it = (int)Math.floor(world.x / HexSpace.S);
    int jt = (int)Math.floor((world.y - (it % 2) * (HexSpace.H / 2.0)) / HexSpace.H);
    int xt = Math.round(world.x - it * HexSpace.S);
    int yt = (int)Math.round((world.y - (it % 2) * (HexSpace.H / 2.0)) - jt * HexSpace.H);
    int i = it - 1;
    if (xt > HexSpace.R * Math.abs(0.5 * (yt / HexSpace.H))) {
      i = it;
    }
    int dj = 0;
    if (yt > HexSpace.H / 2.0) {
      dj = 1;
    }
    int j = jt - (i % 2) + dj;
    if (xt > HexSpace.R * Math.abs(0.5 - (yt / HexSpace.H))){
      j = jt;
    }
    return new Vec(i, j);
  }

  public static Vec worldToScreen(Vec world) {
    return world.add(screenOffset);
  }

  public static Vec screenToWorld(Vec screen) {
    return screen.sub(screenOffset);
  }
}
