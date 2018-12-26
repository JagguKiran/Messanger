package org.kiran.example.Messanger.Resources;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.internal.util.Base64;

public class SecurityFilter implements ContainerRequestFilter
{
	private static final String HEADERS_KEY="authorization";
	private static final String HEADER_PREFIX="Basic";
	private static final String URL_PREFIX="secured";
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if(requestContext.getUriInfo().getPath().contains(URL_PREFIX))
		{
			List<String> authHeader=requestContext.getHeaders().get(HEADERS_KEY);
			if(authHeader!=null || authHeader.size()>0)
			{
				String authToken=authHeader.get(0);
				authToken=authToken.replaceFirst(HEADER_PREFIX, "");
				String decodeString=Base64.decodeAsString(authToken);
				StringTokenizer st=new StringTokenizer(decodeString,":");
				String username=st.nextToken();
				String password=st.nextToken();
				if(username.equals("kiran")&&password.equals("uppin"))
				{
					return;
				}
			}
			Response unauthorized=Response.status(Response.Status.UNAUTHORIZED).entity("Not Secured...please check").build(); 
			requestContext.abortWith(unauthorized);
		}
		
	}
	

}

