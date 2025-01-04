package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.acme.model.Vente;
import org.acme.service.VenteService;
import jakarta.ws.rs.client.Entity;
import org.eclipse.microprofile.reactive.messaging.Incoming;

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
    @Path("/publish")
    public Response publishOrder(Vente vente) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:3500/v1.0/publish/pubsub/ventes");
        Response response = target.request().post(Entity.entity(vente, MediaType.APPLICATION_JSON));
        return Response.ok("Vente publié avec succès").build();
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


    @POST
    @Path("/order")
    @Consumes(MediaType.APPLICATION_JSON)
    public void handleOrder(String orderJson) {
        System.out.println("Order received in SaleService: " + orderJson);
        processOrder(orderJson);
    }

   // @Incoming("venteTopic")
    public void subscribeOrder(String orderJson) {
        System.out.println("Order received via Pub/Sub in SaleService: " + orderJson);
        processOrder(orderJson);
    }

    private void processOrder(String orderJson) {
        System.out.println("Processing order: " + orderJson);
    }
}
