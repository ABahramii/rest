package server.resource;

import server.entity.BankAccount;
import server.service.BankAccountService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/account")
@RequestScoped
public class AccountResource {

    @Inject
    private BankAccountService bankAccountService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAccountById(@PathParam("id") Long id) {
        BankAccount bankAccount = bankAccountService.findById(id);
        if (bankAccount == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(bankAccount, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @GET
    @Path("/BankAccounts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllBankAccounts() {
        return Response.ok(bankAccountService.findAll()).build();
    }

    @GET
    @Path("/findAccount/{accNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAccount(@PathParam("accNumber") String accNumber) {
        BankAccount bankAccount = bankAccountService.findAccountByAccNumber(accNumber);
        if (bankAccount == null) {
            return Response.noContent().build();
        }
        return Response.ok(bankAccount, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBankAccount(BankAccount bankAccount) {
        BankAccount ba = bankAccountService.create(bankAccount);
        if (ba == null) {
            return Response.serverError().build();
        }
        return Response.ok(ba, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBankAccount(BankAccount bankAccount) {
        BankAccount ba = bankAccountService.update(bankAccount);
        if (ba == null) {
            return Response.serverError().build();
        }
        return Response.ok(bankAccount, MediaType.APPLICATION_JSON_TYPE).build();
    }
}