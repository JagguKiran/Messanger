package org.kiran.example.Messanger.Resources;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.kiran.example.Messanger.Model.Message;
import org.kiran.example.Messanger.Service.MessageService;
@Path("/annotations")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectDemoClass 
{
	@GET
	public String test(@MatrixParam("param") String m,@HeaderParam("sirName") String l)
	{
		return "kiran "+m+" "+l;
	}
	@GET
	@Path("context")
	public String getParamUsingContext(@Context UriInfo uriInfo,@Context HttpHeaders header)
	{
		return "Header = "+header.getRequestHeaders().toString();
	}
}
