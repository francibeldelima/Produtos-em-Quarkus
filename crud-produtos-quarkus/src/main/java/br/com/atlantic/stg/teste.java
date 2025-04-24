package br.com.atlantic.stg;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/ok")
public class teste {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "ok";
    }
}
