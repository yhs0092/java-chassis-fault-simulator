package com.github.yhs0092.javachassis.demo.common.fault.simulation;

public enum FramePosition {
  /**
   * HttpServerFilter#afterReceiveRequest
   */
  SF_AF,
  /**
   * HttpServerFilter#beforeSendResponse
   */
  SF_BF,
  /**
   * HttpClientFilter#beforeSendRequest
   */
  CF_BF,
  /**
   * HttpClientFilter#afterReceiveResponse
   */
  CF_AF,
  /**
   * Provider Handler
   */
  PH_NEXT,
  PH_RSP,
  /**
   * Consumer Handler
   */
  CH_NEXT,
  CH_RSP,
  /**
   * business logic
   */
  BIZ_AF,
  BIZ_BF
}
