package com.github.yhs0092.javachassis.demo.common.fault.extension;

import org.apache.servicecomb.core.Handler;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.swagger.invocation.AsyncResponse;
import org.apache.servicecomb.swagger.invocation.InvocationType;

import com.github.yhs0092.javachassis.demo.common.fault.simulation.FaultSimulationManager;
import com.github.yhs0092.javachassis.demo.common.fault.simulation.FramePosition;

public class FaultInjectionHandler implements Handler {
  @Override
  public void handle(Invocation invocation, AsyncResponse asyncResponse) throws Exception {
    FaultSimulationManager.detectAndSimulate(invocation,
        invocation.getInvocationType().equals(InvocationType.PRODUCER)
            ? FramePosition.PH_NEXT : FramePosition.CH_NEXT);

    invocation.next(response -> {
      FaultSimulationManager.detectAndSimulate(invocation,
          invocation.getInvocationType().equals(InvocationType.PRODUCER)
              ? FramePosition.PH_RSP : FramePosition.CH_RSP);
      asyncResponse.complete(response);
    });
  }
}
