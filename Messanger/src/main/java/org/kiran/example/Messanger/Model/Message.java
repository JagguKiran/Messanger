package org.kiran.example.Messanger.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Message {

	private long id;
	private String message;
	private Date created;
	private String auther;
	private List<Link> links=new ArrayList<>();
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuther() {
		return auther;
	}
	public void setAuther(String auther) {
		this.auther = auther;
	}
	public Message(long id, String message, String auther) {
		super();
		this.id = id;
		this.message = message;
		this.created = new Date();
		this.auther = auther;
	}
	public Message()
	{
		
	}
	public void addLink(String uri,String rel)
	{
		Link l=new Link();
		l.setLink(uri);
		l.setRel(rel);
		links.add(l);
	}
	public String toString()
	{
		return this.message+":"+this.auther;
	}
}
