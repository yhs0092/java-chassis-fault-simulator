package com.github.yhs0092.javachassis.demo.common.fault.simulation;

import java.util.List;

public class FaultSimulationOrder {
  private List<FaultSimulationItem> faultSimulationItemList;

  public List<FaultSimulationItem> getFaultSimulationItemList() {
    return faultSimulationItemList;
  }

  public void setFaultSimulationItemList(
      List<FaultSimulationItem> faultSimulationItemList) {
    this.faultSimulationItemList = faultSimulationItemList;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("FaultSimulationOrder{");
    sb.append("faultSimulationItemList=").append(faultSimulationItemList);
    sb.append('}');
    return sb.toString();
  }
}
