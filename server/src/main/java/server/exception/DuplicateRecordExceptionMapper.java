package server.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

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
