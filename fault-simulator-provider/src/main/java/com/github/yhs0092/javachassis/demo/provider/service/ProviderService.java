package com.github.yhs0092.javachassis.demo.provider.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.apache.servicecomb.provider.rest.common.RestSchema;

import com.github.yhs0092.javachassis.demo.common.fault.simulation.FaultSimulationManager;
import com.github.yhs0092.javachassis.demo.common.fault.simulation.FaultSimulationOrder;
import com.github.yhs0092.javachassis.demo.common.fault.simulation.FramePosition;

@RestSchema(schemaId = "demo")
@Path("provider/v0")
public class ProviderService {
  @Path("simulate")
  @POST
  public String simulate(FaultSimulationOrder faultSimulationOrder) {
    FaultSimulationManager.simulate(faultSimulationOrder, FramePosition.BIZ_BF);
    return "OK";
  }
}
