package server.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

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
