package com.github.yhs0092.javachassis.demo.common.fault.extension;

import org.apache.servicecomb.common.rest.filter.HttpServerFilter;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.foundation.vertx.http.HttpServletRequestEx;
import org.apache.servicecomb.foundation.vertx.http.HttpServletResponseEx;
import org.apache.servicecomb.swagger.invocation.Response;

import com.github.yhs0092.javachassis.demo.common.fault.simulation.FaultSimulationManager;
import com.github.yhs0092.javachassis.demo.common.fault.simulation.FramePosition;

public class FaultInjectionHttpServerFilter implements HttpServerFilter {

  @Override
  public int getOrder() {
    return 0;
  }

  @Override
  public Response afterReceiveRequest(Invocation invocation, HttpServletRequestEx httpServletRequestEx) {
    FaultSimulationManager.detectAndSimulate(invocation, FramePosition.SF_AF);
    return null;
  }

  @Override
  public void beforeSendResponse(Invocation invocation, HttpServletResponseEx responseEx) {
    FaultSimulationManager.detectAndSimulate(invocation, FramePosition.SF_BF);
  }
}
