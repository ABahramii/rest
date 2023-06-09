package server.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DuplicateRecordExceptionMapper implements ExceptionMapper<DuplicateRecordException> {

    @Override
    public Response toResponse(DuplicateRecordException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .header("Exception", "Duplicate Record Exception")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
