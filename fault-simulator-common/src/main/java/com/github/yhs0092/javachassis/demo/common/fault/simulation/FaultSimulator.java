package com.github.yhs0092.javachassis.demo.common.fault.simulation;

public class FaultSimulator {
  FaultSimulator() {
  }

  void consumeFaultSimulationItem(String svc, FramePosition position,
      FaultSimulationOrder faultSimulationOrder) {
    if (null == faultSimulationOrder.getFaultSimulationItemList()
        || faultSimulationOrder.getFaultSimulationItemList().isEmpty()) {
      return;
    }
    for (FaultSimulationItem faultSimulationItem : faultSimulationOrder.getFaultSimulationItemList()) {
      if (itemMatched(svc, position, faultSimulationItem)) {
        simulate(faultSimulationItem);
      }
    }
  }

  private void simulate(FaultSimulationItem faultSimulationItem) {
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

  private boolean itemMatched(String svc, FramePosition position, FaultSimulationItem faultSimulationItem) {
    return svc.equals(faultSimulationItem.getSvc())
        && position.equals(faultSimulationItem.getPosition());
  }
}
