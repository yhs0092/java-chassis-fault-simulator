package com.github.yhs0092.javachassis.demo.consumer.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.rest.common.RestSchema;

import com.github.yhs0092.javachassis.demo.provider.service.ProviderService;

@RestSchema(schemaId = "demo")
@Path("consumer/v0")
public class ConsumerService {
  @RpcReference(microserviceName = "provider", schemaId = "demo")
  private ProviderService providerService;

  @Path("get")
  @GET
  public String get(@QueryParam("q") String q) {
    return providerService.get(q);
  }
}
