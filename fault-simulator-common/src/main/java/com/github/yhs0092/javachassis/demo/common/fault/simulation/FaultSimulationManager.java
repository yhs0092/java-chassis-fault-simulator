package com.github.yhs0092.javachassis.demo.common.fault.simulation;

import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.serviceregistry.RegistryUtils;

public class FaultSimulationManager {
  static FaultSimulator faultSimulator = new FaultSimulator();

  static String svc = RegistryUtils.getMicroservice().getServiceName();

  public static void detectAndSimulate(Invocation invocation, FramePosition position) {
    for (Object arg : invocation.getArgs()) {
      if (arg instanceof FaultSimulationOrder) {
        faultSimulator.consumeFaultSimulationItem(svc, position, (FaultSimulationOrder) arg);
        break;
      }
    }
  }

  public static void simulate(FaultSimulationOrder order, FramePosition position) {
    faultSimulator.consumeFaultSimulationItem(svc, position, order);
  }
}
