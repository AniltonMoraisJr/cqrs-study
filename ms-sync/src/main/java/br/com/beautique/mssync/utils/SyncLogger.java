package br.com.beautique.mssync.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncLogger {

  private static final Logger logger = LoggerFactory.getLogger(SyncLogger.class);

  public static void info(String message, Object... args){
    logger.info(message, args);
  }

  public static void error(String message, Object... args){
    logger.error(message, args);
  }

  public static void trace(String message, Object... args){
    logger.trace(message, args);
  }


}
