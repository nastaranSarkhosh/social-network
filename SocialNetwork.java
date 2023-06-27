import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SocialNetwork {
    static int ID=0;
    static Map<String,Profile> profileMap=new HashMap<>();
    static public Profile login(String username,String password){
        Profile profile=profileMap.get(username);
        if(profile==null)
            return null;
        if(profile.getPass().compareTo(password)==0)
            return profile;
        return null;
    }
    static public boolean search(String username){
        Profile profile=profileMap.get(username);
        if(profile==null)
            return false;
        return true;
    }
    static public void signIn(String username,String password){
        profileMap.put(username,new Profile(username,password,ID));
        ID++;
    }

    static public PriorityQueue suggestion(Profile profile){
        PriorityQueue priorityQueue=new PriorityQueue();
        for(var listName:SocialNetwork.profileMap.keySet()){
            if(profile.getFollowing().contains(listName)!=true){
                if(profile.getUsername()!=listName )
                    priorityQueue.insert(profileMap.get(listName).getID(),listName);
            }
        }
        return priorityQueue;
    }

    public static Profile searchID(String ID,Profile profile){
        return SocialNetwork.profileMap.get(ID);
    }
    public static int maxID(){
        int max=0;
        for(var listName:SocialNetwork.profileMap.keySet()){
            if(max<profileMap.get(listName).getID())
                max=profileMap.get(listName).getID();
        }
            return max;
    }
}
