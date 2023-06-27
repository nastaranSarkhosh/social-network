import java.util.ArrayList;

public class Profile {
    private ArrayList<String> followers=new ArrayList<>();
    private ArrayList<String> following=new ArrayList<>();
    private ArrayList<Post> posts=new ArrayList<>();
    private String username;
    private String pass;
    private String bio;
    int ID;

    public Profile(String username, String pass, int ID) {
        this.username = username;
        this.pass = pass;
        this.ID = ID;
    }
    public Profile(){
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    public void newPost(String text){
        posts.add(new Post(username,text));
    }

    @Override
    public String toString() {
        return "Profile{" +
                "username='" + username + '\'' +
                ", bio='" + bio + '\'' +
                ", ID=" + ID +
                '}';
    }

    public ArrayList<String> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<String> followers) {
        this.followers = followers;
    }

    public ArrayList<String> getFollowing() {
        return following;
    }

    public void setFollowing(ArrayList<String> following) {
        this.following = following;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public String getBio() {
        return bio;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
