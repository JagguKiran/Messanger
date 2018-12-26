package org.kiran.example.Messanger.Resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.kiran.example.Messanger.DataException.DataNotFoundException;
import org.kiran.example.Messanger.Model.Message;
import org.kiran.example.Messanger.Model.MessageFilterBean;
import org.kiran.example.Messanger.Model.Profile;
import org.kiran.example.Messanger.Service.MessageService;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService ms=new MessageService();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getJsonFormatAllMessages(@BeanParam MessageFilterBean bean)
	{
		if(bean.getYear()>0) {
			return ms.getAllMessageOfYear(bean.getYear());
		}
		if(bean.getSize()>=bean.getStart()&&bean.getSize()>0)
		{
			return ms.getAllMessagePagination(bean.getStart(), bean.getSize());
		}
		return ms.getAllMessages();
	}
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Message> getXMLFormatAllMessages(@BeanParam MessageFilterBean bean)
	{
		if(bean.getYear()>0) {
			return ms.getAllMessageOfYear(bean.getYear());
		}
		if(bean.getSize()>=bean.getStart()&&bean.getSize()>0)
		{
			return ms.getAllMessagePagination(bean.getStart(), bean.getSize());
		}
		return ms.getAllMessages();
	}
	@Path("/{messageId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Message getJsonFormatMessage(@PathParam("messageId") long id,@Context UriInfo uriInfo)
	{
		Message m=ms.getMessage(id);
		m.addLink(getUriForMessage(uriInfo,m), "self");
		m.addLink(getUriForProfile(uriInfo,m), "profile");
		m.addLink(getUriForComment(uriInfo,m), "comment");
		return m;
	}
	@Path("/{messageId}")
	@GET
	@Produces(MediaType.TEXT_XML)
	public Message getXMLFormatMessage(@PathParam("messageId") long id,@Context UriInfo uriInfo)
	{
		Message m=ms.getMessage(id);
		m.addLink(getUriForMessage(uriInfo,m), "self");
		m.addLink(getUriForProfile(uriInfo,m), "profile");
		m.addLink(getUriForComment(uriInfo,m), "comment");
		return m;
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addJsonMessage(Message m,@Context UriInfo uriInfo)
	{
		Message msg=ms.addMessage(m);
		String newId=String.valueOf(m.getId());
		URI uri=uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri).entity(msg).build();
	}
	@POST
	@Produces(MediaType.TEXT_XML)
	@Consumes(MediaType.TEXT_XML)
	public Response addXMLMessage(Message m,@Context UriInfo uriInfo)
	{
		Message msg=ms.addMessage(m);
		String newId=String.valueOf(m.getId());
		URI uri=uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri).entity(msg).build();
	}
	@Path("/{messageId}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Message deleteMessage(@PathParam("messageId") long id)
	{
		return ms.deleteMessage(id);
	}
	@Path("/{messageId}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id, Message message)
	{
		message.setId(id);
		return ms.updateMessage(message);
	}
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource()
	{
		return new CommentResource();
	}
	private String getUriForMessage(UriInfo uriInfo,Message m)
	{
		return uriInfo.getBaseUriBuilder().path(MessageResource.class).path(Long.toString(m.getId())).build().toString();
	}
	private String getUriForProfile(UriInfo uriInfo,Message m)
	{
		return uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(m.getAuther()).build().toString();
	}
	private String getUriForComment(UriInfo uriInfo,Message m)
	{
		return uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class,"getCommentResource")
				.resolveTemplate("messageId", m.getId())
				.path(CommentResource.class).build().toString();
	}
}
