package org.kiran.example.Messanger.DataException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.kiran.example.Messanger.Model.ErrorMessage;


@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>
{

	@Override
	public Response toResponse(DataNotFoundException exp) 
	{
		ErrorMessage em=new ErrorMessage(exp.getMessage(),404,"Try it Next time");
		return Response.status(Status.NOT_FOUND).entity(em).type(MediaType.APPLICATION_JSON).build();
	}
	
}
