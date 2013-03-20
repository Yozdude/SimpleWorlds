package com.simpleworlds.data;

import com.simpleworlds.display.MainWindow;

public class MetaData {
  public static MainWindow mainWindow;

  public static void initialize(MainWindow w) {
    mainWindow = w;

    ImagesData.initialize();
  }
}
