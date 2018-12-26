package org.kiran.example.Messanger.Database;

import java.util.HashMap;
import java.util.Map;

import org.kiran.example.Messanger.Model.Message;
import org.kiran.example.Messanger.Model.Profile;

public class DatabaseClass {
	
	private static Map<Long,Message> messages=new HashMap<>();
	private static Map<String,Profile> profiles=new HashMap<>();
	public static Map<Long,Message> getMessages()
	{
		return messages;
	}
	public static Map<String,Profile> getProfiles()
	{
		return profiles;
	}

}
