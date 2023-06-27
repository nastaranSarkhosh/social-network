import java.util.ArrayList;

public class Graph {
    static ArrayList<ArrayList> Graph=new ArrayList<>();
    static ArrayList<String>user=new ArrayList<>();
    public static void insert(){
        Graph.removeAll(Graph);
        user.removeAll(user);
        for(var listName:SocialNetwork.profileMap.keySet()){
            Graph.add(SocialNetwork.profileMap.get(listName).getFollowing());
            user.add(listName);
        }
    }
    public static PriorityQueue ProbabilityRecognition(Profile profile){
        PriorityQueue priorityQueue=new PriorityQueue();
        if(profile.getFollowing().size()==0) {
            menu.suggestion(profile);
            return null;
        }
        else {
            int similar=0;
            int different=0;
            Graph.remove(profile.getFollowing());
            user.remove(profile.getUsername());
            delete(profile);
            for (int i=0;i<Graph.size();i++) {
                similar = 0;
                different = 0;
                if (Graph.get(i).size() == 0) {
                    priorityQueue.insert(0, user.get(i));
                } else {
                    similar = numSimilar(profile, i);
                    different = profile.getFollowing().size() - similar;
                    different = different + (Graph.get(i).size() - similar);
                    if (different == 0 && similar > 0)
                        priorityQueue.insert(1, user.get(i));
                    else
                        priorityQueue.insert(similar / different, user.get(i));
                }
            }
        }
        return priorityQueue;
    }
    public static int numSimilar(Profile profile ,int i){
        int similar=0;
        for (int j=0;j<profile.getFollowing().size();j++){
            if (Graph.get(i).contains(profile.getFollowing().get(j)))
                similar++;
        }
        return similar;
    }
    public static int search(String user1){
        for (int i=0;i<user.size();i++){
            if(user.get(i).compareTo(user1)==0)
                return i;
        }
        return -1;
    }
    public static void delete(Profile profile){
        for (int i=0;i<profile.getFollowing().size();i++){
            int index =search(profile.getFollowing().get(i));
            if(index!=-1) {
                Graph.remove(index);
                user.remove(index);
            }
        }
    }

}
