package es.iesptocruz.francisco.agendajsprepasodsw.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/test")
public class Test {
    @GET
    public Response ok(){
        return Response.status(200).entity("ok").build();
    }
 }
