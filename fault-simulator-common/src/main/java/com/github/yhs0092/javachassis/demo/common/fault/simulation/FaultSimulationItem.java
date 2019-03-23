package com.github.yhs0092.javachassis.demo.common.fault.simulation;

public class FaultSimulationItem {
  private String svc;

  private FramePosition position;

  private FaultType faultType;

  private String faultDetails;

  public String getSvc() {
    return svc;
  }

  public void setSvc(String svc) {
    this.svc = svc;
  }

  public FramePosition getPosition() {
    return position;
  }

  public void setPosition(FramePosition position) {
    this.position = position;
  }

  public FaultType getFaultType() {
    return faultType;
  }

  public void setFaultType(FaultType faultType) {
    this.faultType = faultType;
  }

  public String getFaultDetails() {
    return faultDetails;
  }

  public void setFaultDetails(String faultDetails) {
    this.faultDetails = faultDetails;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("FaultSimulationItem{");
    sb.append("svc='").append(svc).append('\'');
    sb.append(", position=").append(position);
    sb.append(", faultType='").append(faultType).append('\'');
    sb.append(", faultDetails=").append(faultDetails);
    sb.append('}');
    return sb.toString();
  }
}
