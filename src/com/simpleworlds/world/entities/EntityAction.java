package com.simpleworlds.world.entities;

import com.simpleworlds.data.ImagesData.ACTION_TYPE;

public class EntityAction {
  public ACTION_TYPE actionType;

  public boolean enabled = true;

  public EntityAction(ACTION_TYPE type) {
    actionType = type;
  }
}
