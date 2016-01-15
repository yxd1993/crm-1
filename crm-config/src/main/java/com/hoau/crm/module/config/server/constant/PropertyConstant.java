package com.hoau.crm.module.config.server.constant;


public class PropertyConstant
{
  private static final String[] fileNames = { "/config.properties" };
  private static PropertyManager propertyManager = PropertyManager.load(fileNames, PropertyConstant.class);
  public static final String DC_URL = propertyManager.getString("DC_URL");
  public static final String DOWNLOAD_DIR = propertyManager.getString("DOWNLOAD_DIR");
}
