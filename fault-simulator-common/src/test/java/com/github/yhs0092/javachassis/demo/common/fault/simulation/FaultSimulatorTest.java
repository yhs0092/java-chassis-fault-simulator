package com.github.yhs0092.javachassis.demo.common.fault.simulation;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

public class FaultSimulatorTest {

  private FaultSimulator faultSimulator = new FaultSimulator();

  @Test
  public void consumeFaultSimulationItem_delay() {
    FaultSimulationItem faultSimulationItem =
        createFaultSimulationItem("testSvc", FramePosition.BIZ_AF, FaultType.DELAY, "1000");

    long start = System.currentTimeMillis();
    faultSimulator
        .consumeFaultSimulationItem("testSvc", FramePosition.BIZ_AF, createFaultSimulationOrder(faultSimulationItem));
    long actualDelay = System.currentTimeMillis() - start;
    Assert.assertTrue("actualDelay = " + actualDelay, actualDelay > 990);
  }

  @Test
  public void consumeFaultSimulationItem_exception() {
    FaultSimulationOrder faultSimulationOrder = createFaultSimulationOrder(
        createFaultSimulationItem("testSvc", FramePosition.CF_AF, FaultType.EXCEPTION, "other error"),
        createFaultSimulationItem("testSvc2", FramePosition.BIZ_AF, FaultType.EXCEPTION, "other error"),
        createFaultSimulationItem("testSvc", FramePosition.BIZ_AF, FaultType.EXCEPTION, "test error")
    );

    try {
      faultSimulator.consumeFaultSimulationItem("testSvc", FramePosition.BIZ_AF, faultSimulationOrder);
      fail("an exception is expected!");
    } catch (Exception e) {
      Assert.assertEquals(RuntimeException.class, e.getClass());
      Assert.assertEquals("test error", e.getMessage());
    }
  }

  @Test
  public void consumeFaultSimulationItem_empty() {
    FaultSimulationOrder faultSimulationOrder = createFaultSimulationOrder();
    faultSimulator.consumeFaultSimulationItem("testSvc", FramePosition.BIZ_AF, faultSimulationOrder);
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