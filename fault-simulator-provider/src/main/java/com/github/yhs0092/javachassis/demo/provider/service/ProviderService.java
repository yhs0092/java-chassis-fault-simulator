package com.github.yhs0092.javachassis.demo.provider.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.apache.servicecomb.provider.rest.common.RestSchema;

@RestSchema(schemaId = "demo")
@Path("provider/v0")
public class ProviderService {
  @Path("get")
  @GET
  public String get(@QueryParam("q") String q) {
    try {
      Thread.sleep(1500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return q;
  }
}
