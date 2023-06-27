public class Post {
    String username;
    String postText;

    public Post(String username, String postText) {
        this.username = username;
        this.postText = postText;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postText='" + postText + '\'' +
                '}';
    }
}
