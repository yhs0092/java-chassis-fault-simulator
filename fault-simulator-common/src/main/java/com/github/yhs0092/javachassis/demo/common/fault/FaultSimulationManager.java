package com.github.yhs0092.javachassis.demo.common.fault;

public class FaultSimulationManager {

  private final String svc;

  private final FramePosition position;

  public FaultSimulationManager(String svc, FramePosition position) {
    this.svc = svc;
    this.position = position;
  }

  public void consumeFaultSimulationItem(FaultSimulationOrder faultSimulationOrder) {
    if (null == faultSimulationOrder.getFaultSimulationItemList()
        || faultSimulationOrder.getFaultSimulationItemList().isEmpty()) {
      return;
    }
    for (FaultSimulationItem faultSimulationItem : faultSimulationOrder.getFaultSimulationItemList()) {
      if (svc.equals(faultSimulationItem.getSvc())
          && position.equals(faultSimulationItem.getPosition())) {
        switch (faultSimulationItem.getFaultType()) {
          case DELAY:
            try {
              Thread.sleep(Long.valueOf(faultSimulationItem.getFaultDetails()));
              break;
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          case EXCEPTION:
            throw new RuntimeException(faultSimulationItem.getFaultDetails());
        }
      }
    }
  }
}
