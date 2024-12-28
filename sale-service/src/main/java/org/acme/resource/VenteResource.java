package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.acme.model.Vente;
import org.acme.service.VenteService;


@Path("/ventes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VenteResource {

    @Inject
    VenteService venteService;

    @GET
    public List<Vente> getAllVentes() {
        return venteService.findAll();
    }

    @GET
    @Path("/{id}")
    public Vente getVenteById(@PathParam("id") Long id) {
        return venteService.findById(id);
    }

    @POST
    public Response createVente(Vente vente) {
        Vente createdVente = venteService.create(vente);
        return Response.status(Response.Status.CREATED).entity(createdVente).build();
    }

    @PUT
    @Path("/{id}")
    public Vente updateVente(@PathParam("id") Long id, Vente vente) {
        return venteService.update(id, vente);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteVente(@PathParam("id") Long id) {
        venteService.delete(id);
        return Response.noContent().build();
    }
}
