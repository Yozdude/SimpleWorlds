package com.simpleworlds.world.entities;

import java.util.ArrayList;
import java.util.List;

import com.simpleworlds.data.ImagesData;
import com.simpleworlds.data.ImagesData.RESOURCE_TYPE;
import com.simpleworlds.utils.Vec;

public class ResourceEntity extends GameEntity {
  public RESOURCE_TYPE resourceType;

  // TODO: Add variables to deal with how far along this resource is, and if it is ready to be harvested

  public ResourceEntity(RESOURCE_TYPE type) {
    resourceType = type;
  }

  public void drawAtScreenPos(Vec screen) {
    ImagesData.drawAt(resourceType, screen);
  }

  public List<EntityAction> getEntityActions() {
    ArrayList<EntityAction> actions = new ArrayList<EntityAction>();

    return actions;
  }
}
