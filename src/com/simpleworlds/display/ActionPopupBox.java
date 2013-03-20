package com.simpleworlds.display;

import java.util.List;

import com.simpleworlds.data.MetaData;
import com.simpleworlds.utils.Vec;
import com.simpleworlds.world.entities.EntityAction;

public class ActionPopupBox extends PopupBox {

  public ActionPopupBox(Vec pos, List<EntityAction> actions) {
    super(pos);
    width = 100;
    height = 60 * actions.size();
  }

  public void draw() {
    Vec screenPos = MainWindow.worldToScreen(worldPos);
    MetaData.mainWindow.fill(255);
    MetaData.mainWindow.rect(screenPos.x, screenPos.y, width, height);
  }

}
