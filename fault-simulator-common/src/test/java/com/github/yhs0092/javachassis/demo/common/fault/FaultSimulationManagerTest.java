package com.github.yhs0092.javachassis.demo.common.fault;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

public class FaultSimulationManagerTest {

  private FaultSimulationManager faultSimulationManager = new FaultSimulationManager("testSvc", FramePosition.BIZ);

  @Test
  public void consumeFaultSimulationItem_delay() {
    FaultSimulationItem faultSimulationItem =
        createFaultSimulationItem("testSvc", FramePosition.BIZ, FaultType.DELAY, "1000");

    long start = System.currentTimeMillis();
    faultSimulationManager.consumeFaultSimulationItem(createFaultSimulationOrder(faultSimulationItem));
    long actualDelay = System.currentTimeMillis() - start;
    Assert.assertTrue("actualDelay = " + actualDelay, actualDelay > 990);
  }

  @Test
  public void consumeFaultSimulationItem_exception() {
    FaultSimulationOrder faultSimulationOrder = createFaultSimulationOrder(
        createFaultSimulationItem("testSvc", FramePosition.CF, FaultType.EXCEPTION, "other error"),
        createFaultSimulationItem("testSvc2", FramePosition.BIZ, FaultType.EXCEPTION, "other error"),
        createFaultSimulationItem("testSvc", FramePosition.BIZ, FaultType.EXCEPTION, "test error")
    );

    try {
      faultSimulationManager.consumeFaultSimulationItem(faultSimulationOrder);
      fail("an exception is expected!");
    } catch (Exception e) {
      Assert.assertEquals(RuntimeException.class, e.getClass());
      Assert.assertEquals("test error", e.getMessage());
    }
  }

  @Test
  public void consumeFaultSimulationItem_empty() {
    FaultSimulationOrder faultSimulationOrder = createFaultSimulationOrder();
    faultSimulationManager.consumeFaultSimulationItem(faultSimulationOrder);
  }

  private FaultSimulationOrder createFaultSimulationOrder(FaultSimulationItem... faultSimulationItems) {
    FaultSimulationOrder faultSimulationOrder = new FaultSimulationOrder();
    faultSimulationOrder.setFaultSimulationItemList(Lists.newArrayList(faultSimulationItems));
    return faultSimulationOrder;
  }

  private FaultSimulationItem createFaultSimulationItem(String svc, FramePosition position,
      FaultType faultType, String faultDetails) {
    FaultSimulationItem faultSimulationItem = new FaultSimulationItem();
    faultSimulationItem.setSvc(svc);
    faultSimulationItem.setPosition(position);
    faultSimulationItem.setFaultType(faultType);
    faultSimulationItem.setFaultDetails(faultDetails);
    return faultSimulationItem;
  }
}