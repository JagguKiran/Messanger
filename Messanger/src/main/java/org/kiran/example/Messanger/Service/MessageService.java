package org.kiran.example.Messanger.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.kiran.example.Messanger.DataException.DataNotFoundException;
import org.kiran.example.Messanger.Database.DatabaseClass;
import org.kiran.example.Messanger.Model.ErrorMessage;
import org.kiran.example.Messanger.Model.Message;
//import org.kiran.example.Messanger.Model.Profile;

public class MessageService {

	
	private static Map<Long,Message> messages=DatabaseClass.getMessages();
	
	public MessageService()
	{
		messages.put(1L, new Message(1, "JAVA","kiran"));
		messages.put(2L, new Message(2, "JAVASCRIPT","BINTI"));
	}
	public List<Message> getAllMessages()
	{
		return new ArrayList<Message>(messages.values());
	}
	public Message getMessage(long id)
	{
		Message msg = messages.get(id);
		if(msg==null)
		{
			throw new DataNotFoundException("Invalid Id found "+id+" Please Check...");
		}
		return msg;
	}
	public Message addMessage(Message message)
	{
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	public Message deleteMessage(long id)
	{
		Message m=messages.remove(id);
		if(m==null)
		{
			ErrorMessage em=new ErrorMessage("Not Possible To Delete",404,"Try it Latere");
			Response response=Response.status(Status.NOT_FOUND).entity(em).type(MediaType.APPLICATION_JSON).build();
			throw new NotFoundException(response);
		}
		return m;
	}
	public Message updateMessage(Message message)
	{
		if(message.getId()<=0)
		{
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	public List<Message> getAllMessageOfYear(int year)
	{
		List<Message> al=new ArrayList<>();
		Calendar cal=Calendar.getInstance();
		for(Message m:messages.values())
		{
			cal.setTime(m.getCreated());
			if(cal.get(Calendar.YEAR)==year)
			{
				al.add(m);
			}
		}
		return al;	
	}
	public List<Message> getAllMessagePagination(int start, int size)
	{
		List<Message> al=new ArrayList<Message>(messages.values());
		if((size+start)>al.size())
			return new ArrayList<Message>();
		return al.subList(start, start+size);
	}
}
