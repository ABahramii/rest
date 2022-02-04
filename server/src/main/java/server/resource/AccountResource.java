package server.resource;

import server.entity.BankAccount;
import server.service.BankAccountService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/accounts")
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
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(bankAccount, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @GET
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
        return Response.created(URI.create("/accounts/" + ba.getId())).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateBankAccount(@PathParam("id") Long id, BankAccount updateBankAccount) {
        BankAccount bankAccount = bankAccountService.findById(id);
        if (bankAccount == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        bankAccount.setOwner(updateBankAccount.getOwner());
        bankAccount.setBalance(updateBankAccount.getBalance());
        bankAccount.setAccNumber(updateBankAccount.getAccNumber());

        bankAccountService.update(bankAccount);
    }

    @DELETE
    @Path("{id}")
    public Response deleteBankAccount(@PathParam("id") Long id) {
        BankAccount bankAccount = bankAccountService.findById(id);
        if (bankAccount == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        bankAccountService.delete(bankAccount);
        return Response.noContent().build();
    }
}