package server.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;


@Provider
public class SaveRecordExceptionMapper implements ExceptionMapper<SaveRecordException> {

    @Override
    public Response toResponse(SaveRecordException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .header("Exception", "Save Record Exception")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
