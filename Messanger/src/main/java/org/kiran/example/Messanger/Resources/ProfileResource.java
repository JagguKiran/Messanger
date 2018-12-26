package org.kiran.example.Messanger.Resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.kiran.example.Messanger.Model.Profile;
import org.kiran.example.Messanger.Service.ProfileService;
@Path("/profiles")
public class ProfileResource {

	ProfileService ps=new ProfileService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Profile> getAllProfiles()
	{
		return ps.getAllProfiles();
	}
	@GET
	@Path("/{profileName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Profile getProfile(@PathParam("profileName") String firstName)
	{
		return ps.getProfile(firstName);
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Profile addProfile(Profile p)
	{
		return ps.addProfile(p);
	}
	@DELETE
	@Path("/{profileId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Profile deleteProfile(@PathParam("profileId") String profileName)
	{
	
		return ps.removeProfile(profileName);
	}
	@PUT
	@Path("/{profileId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Profile updateProfile(@PathParam("profileId") String name,Profile p)
	{
		return ps.updateProfile(p);
	}
}
