package com.github.yhs0092.javachassis.demo.common.fault.extension;

import org.apache.servicecomb.common.rest.filter.HttpClientFilter;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.foundation.vertx.http.HttpServletRequestEx;
import org.apache.servicecomb.foundation.vertx.http.HttpServletResponseEx;
import org.apache.servicecomb.swagger.invocation.Response;

import com.github.yhs0092.javachassis.demo.common.fault.simulation.FaultSimulationManager;
import com.github.yhs0092.javachassis.demo.common.fault.simulation.FramePosition;

public class FaultInjectionHttpClientFilter implements HttpClientFilter {

  @Override
  public int getOrder() {
    return 0;
  }

  @Override
  public void beforeSendRequest(Invocation invocation, HttpServletRequestEx httpServletRequestEx) {
    FaultSimulationManager.detectAndSimulate(invocation, FramePosition.CF_BF);
  }

  @Override
  public Response afterReceiveResponse(Invocation invocation, HttpServletResponseEx httpServletResponseEx) {
    FaultSimulationManager.detectAndSimulate(invocation, FramePosition.CF_AF);
    return null;
  }
}
