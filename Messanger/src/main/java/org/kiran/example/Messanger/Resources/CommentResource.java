package org.kiran.example.Messanger.Resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public class CommentResource {

	@GET
	public String test()
	{
		return "kirankumar";
	}
	@GET
	@Path("/{commentId}")
	public String commentId(@PathParam("commentId") String str,@PathParam("messageId") String str1)
	{
		return "uppin kiran "+str1+":"+str;
	}
}
