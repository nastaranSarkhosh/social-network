
import java.util.ArrayList;
import java.util.Scanner;
public class menu {
    static Profile profile=new Profile();
    static Profile search=new Profile();
    static Scanner input = new Scanner(System.in);

    static public void mainMenu() {
        int option =-1;
        do {
            System.out.println("1-Login 2-Sign in  3-Exit");
            option = input.nextInt();
            if (option == 1)
                loginManu();
            else if (option == 2)
                signInManu();

        } while (option != 3);
    }

    static public void loginManu() {
        System.out.println("Username:");
        String username = input.next();
        System.out.println("Password:");
        String pass = input.next();
        Profile profile = SocialNetwork.login(username, pass);
        if (profile == null) {
            System.out.println("Password or username is wrong");
            return;
        }
        profileManu(profile);
    }

    static public void signInManu() {
        System.out.println("Username:");
        String username = input.next();
        if (!SocialNetwork.search(username)) {
            System.out.println("Password:");
            String pass = input.next();
            SocialNetwork.signIn(username, pass);
        } else
            System.out.println("This username has been taken");
    }

    static public void profileManu(Profile profile) {
        int option = -1;
        do {
            showProfile(profile);
            System.out.println(".1.New post ..... .2.Search ..... .3.Setting ..... .4.Delete post ..... .5.Suggestion ..... .6.Show follower ..... .7.Show following ..... .0.Exit");
            option = input.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Text:");
                    input.nextLine();
                    String text=input.nextLine();
                    profile.getPosts().add(new Post(profile.getUsername(),text));
                    break;
                case 2:
                    Search(profile);
                    break;
                case 3:
                    if(profileSetting(profile)==false)
                        option=0;
                    break;
                case 4:
                    deletePost(profile);
                    break;
                case 5:
                    suggestion2(profile);
                    break;
                case 6:
                    show_FF(2,profile);
                    break;
                case 7:
                    show_FF(1,profile);
                    break;
            }
        } while (option != 0);

    }
    public static boolean profileSetting(Profile profile){
        System.out.println(".1.Change bio\n.2.Change password\n.3.Delete account");
        int option=input.nextInt();
        switch (option){
            case 1:
                System.out.println("New bio:");
                input.nextLine();
                String text=input.nextLine();
                profile.setBio(text);
                break;
            case 2:
                System.out.println("New password:");
                profile.setPass(input.next());
                break;
            case 3:
                deleteAccount(profile);
                return false;

        }
        return true;
    }
    public static void show_FF(int option,Profile profile){
        switch (option){
            case 1:
                System.out.println(profile.getFollowing().toString());
                break;
            case 2:
                System.out.println(profile.getFollowers().toString());
                break;
        }
    }
    static public void suggestion(Profile profile) {
        PriorityQueue priorityQueue=SocialNetwork.suggestion(profile);
        if(priorityQueue.size==0) {
            System.out.println("No one was found");
            return;
        }
        ArrayList<String> arrayList=new ArrayList<>();
        System.out.println("Which one do you want to follow? If you give up, enter the number 7");
        int num=0;
        while (num<6 && priorityQueue.size>0){
            String user=priorityQueue.removeMin();
            arrayList.add(user);
            System.out.println((num+1)+" : "+arrayList.get(num));
            num++;
        }
        int option=input.nextInt();
        if(option<=6){
            profile.getFollowing().add(arrayList.get(option-1));
            SocialNetwork.profileMap.get(arrayList.get(option-1)).getFollowers().add(profile.getUsername());
        }
    }
    static public void suggestion2(Profile profile) {
        Graph.insert();
        PriorityQueue priorityQueue=Graph.ProbabilityRecognition(profile);
        if(priorityQueue!=null) {
            if (priorityQueue.size == 0) {
                System.out.println("No one was found");
                return;
            }
            ArrayList<String> arrayList = new ArrayList<>();
            System.out.println("Which one do you want to follow? If you give up, enter the number 7");
            int num = 0;
            while (num < 6 && priorityQueue.size > 0) {
                String user = priorityQueue.removeMin();
                arrayList.add(user);
                System.out.println((num + 1) + " : " + arrayList.get(num));
                num++;
            }
            int option = input.nextInt();
            if (option <= 6) {
                profile.getFollowing().add(arrayList.get(option - 1));
                SocialNetwork.profileMap.get(arrayList.get(option-1)).getFollowers().add(profile.getUsername());
            }
        }
    }
    static public void showProfile(Profile profile) {
        System.out.println(profile.toString());
        for (int i = 0; i < profile.getPosts().size(); i++)
            System.out.println(i + " : " + profile.getPosts().get(i).toString());
        System.out.println("------------------------------------------------");

    }
    public static void Search(Profile profile){
        boolean tf=false;
        System.out.println("Please enter :::::");
        search=SocialNetwork.searchID(input.next(),profile);
        if (search==null){
            System.out.println("Nothing found");
            return;
        }else {
            for (int i = 0; i < profile.getFollowing().size(); i++) {
                if (search.getUsername().compareTo((String) profile.getFollowing().get(i))==0){
                    menu.showProfile(search);
                    tf=true;
                    break;
                }
            }
            if (!tf){
                System.out.println("You don't have it in your following.\n.1.Request ... .2.Deny");
                int option=input.nextInt();
                if (option==1){
                    profile.getFollowing().add(search.getUsername());
                    SocialNetwork.profileMap.get(search.getUsername()).getFollowers().add(profile.getUsername());
                }
            }
        }
    }
    public static void deletePost(Profile profile){
        for (int i = 0; i < profile.getPosts().size(); i++) {
            System.out.println((i+1)+" : "+profile.getPosts().get(i).toString());;
        }
        System.out.println("Which post do you want to delete:");
        int post=input.nextInt();
        if(post>0 &&post<=profile.getPosts().size())
            profile.getPosts().remove(post-1);

    }
    public static void deleteAccount(Profile profile){
        while (!SocialNetwork.profileMap.get(profile.getUsername()).getFollowers().isEmpty()) {
            String str=SocialNetwork.profileMap.get(profile.getUsername()).getFollowers().remove(0);
            for (int i = 0; i < SocialNetwork.profileMap.get(str).getFollowing().size(); i++) {
                if(SocialNetwork.profileMap.get(str).getFollowing().get(i).compareTo(profile.getUsername())==0){
                    SocialNetwork.profileMap.get(str).getFollowing().remove(i);
                    break;
                }
            }
        }
        while (!SocialNetwork.profileMap.get(profile.getUsername()).getFollowing().isEmpty()) {
            String str=SocialNetwork.profileMap.get(profile.getUsername()).getFollowing().remove(0);
            for (int i = 0; i < SocialNetwork.profileMap.get(str).getFollowers().size(); i++) {
                if(SocialNetwork.profileMap.get(str).getFollowers().get(i).compareTo(profile.getUsername())==0){
                    SocialNetwork.profileMap.get(str).getFollowers().remove(i);
                    break;
                }
            }
        }
        SocialNetwork.profileMap.remove(profile.getUsername());

    }

}
