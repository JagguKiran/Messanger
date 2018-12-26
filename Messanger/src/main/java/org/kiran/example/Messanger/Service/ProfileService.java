package org.kiran.example.Messanger.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kiran.example.Messanger.Database.DatabaseClass;
import org.kiran.example.Messanger.Model.Profile;

public class ProfileService {
	private static Map<String,Profile> profiles=DatabaseClass.getProfiles();
	public ProfileService()
	{
		profiles.put("kiran",new Profile(1,"kiran","uppin","kishan"));
		profiles.put("avinish", new Profile(2,"avinish","bhimalli","ravan"));
		profiles.put("binti", new Profile(3,"binti","mukta","bhimalli"));
	}
	public List<Profile> getAllProfiles()
	{
		return new ArrayList<>(profiles.values());
	}
	public Profile getProfile(String name)
	{
		return profiles.get(name);
	}
	public Profile removeProfile(String name)
	{
		
		return profiles.remove(name);
	}
	public Profile addProfile(Profile p)
	{
		p.setId((profiles.size()+1));
	
		profiles.put(p.getProfileName(), p);
		
		return p;
	}
	public Profile updateProfile(Profile p)
	{
		if(p.getProfileName().isEmpty())
			return null;
		profiles.put(p.getFirstName(), p);
		return p;
	}
}
