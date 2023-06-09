package server.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import server.entity.BankAccount;
import server.exception.DuplicateRecordException;
import server.exception.SaveRecordException;
import server.service.BankAccountService;

import java.net.URI;

@Path("/accounts")
@ApplicationScoped
public class AccountResource {

    @Inject
    private BankAccountService bankAccountService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAccountById(@PathParam("id") Long id) {
        BankAccount bankAccount = bankAccountService.findById(id);
        if (bankAccount == null) {
            throw new NotFoundException();
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBankAccount(BankAccount bankAccount) throws SaveRecordException, DuplicateRecordException {
        BankAccount ba = bankAccountService.create(bankAccount);
        return Response.created(URI.create("/accounts/" + ba.getId())).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateBankAccount(@PathParam("id") Long id, BankAccount updateBankAccount) throws DuplicateRecordException {
        BankAccount bankAccount = bankAccountService.findById(id);
        if (bankAccount == null) {
            throw new NotFoundException();
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
            throw new NotFoundException();
        }
        bankAccountService.delete(bankAccount);
        return Response.noContent().build();
    }
}