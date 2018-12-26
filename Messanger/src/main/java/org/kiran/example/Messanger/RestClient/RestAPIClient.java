package org.kiran.example.Messanger.RestClient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import javax.ws.rs.core.Response;

import javax.ws.rs.client.Invocation.Builder;
import org.kiran.example.Messanger.Model.Message;

public class RestAPIClient {
	public static void main(String[] args)
	{
		Client client=ClientBuilder.newClient();
		WebTarget target=client.target("http://localhost:8080/Messanger/webapi/messages/1");
		Builder builder=target.request();
		Response response=builder.get();
		Message msg=response.readEntity(Message.class);
		
		Message msg1=new Message(5,"Hello World","KiranKumar");
		Response resp=client.target("http://localhost:8080/Messanger/webapi/messages")
				.request().post(Entity.json(msg1));
		System.out.println(resp.readEntity(Message.class));
	}
}
