import java.io.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.Scanner;

public class finalProject {
    public static void main(String[] args) {
        File file2=new File(".\\Profile.txt");
        File file3=new File(".\\Post.txt");
        File file4=new File(".\\follow.txt");
        try {
            readProfile(file2);
            readPost(file3);
            readFollow(file4);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        menu.mainMenu();
        try {
            writeProfile(file2);
            writePost(file3);
            writeFollow(file4);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
    public static void writeProfile(File file) throws IOException {
        file.createNewFile();
        FileOutputStream fileOutputStream=new FileOutputStream(file,false);
        Formatter formatter=new Formatter(fileOutputStream);
        for(var listName:SocialNetwork.profileMap.keySet()){
            Profile profile=SocialNetwork.profileMap.get(listName);
            formatter.format("%s %s %d %s \n",profile.getUsername(),profile.getPass(),profile.ID,profile.getBio());
        }
        formatter.flush();
    }
    public static void writeFollow(File file) throws IOException {
        file.createNewFile();
        FileOutputStream fileOutputStream=new FileOutputStream(file,false);
        Formatter formatter=new Formatter(fileOutputStream);
        for(var listName:SocialNetwork.profileMap.keySet()){
            Profile profile=SocialNetwork.profileMap.get(listName);
            formatter.format("%s %d ",profile.getUsername(),1);
            for (int i=0;i<profile.getFollowers().size();i++)
                formatter.format(" %s",profile.getFollowers().get(i));
            formatter.format("\n");
            formatter.format("%s %d ",profile.getUsername(),2);
            for (int i=0;i<profile.getFollowing().size();i++)
                formatter.format(" %s",profile.getFollowing().get(i));
            formatter.format("\n");
        }
        formatter.flush();
    }
    public static void writePost(File file) throws IOException {
        file.createNewFile();
        FileOutputStream fileOutputStream=new FileOutputStream(file,false);
        Formatter formatter=new Formatter(fileOutputStream);
        for(var listName:SocialNetwork.profileMap.keySet()){
            Profile profile=SocialNetwork.profileMap.get(listName);
            for (int i=0;i<profile.getPosts().size();i++)
            formatter.format("%s %s \n",profile.getUsername(),profile.getPosts().get(i).postText);
        }
        formatter.flush();
    }
    public static void readPost(File file2) throws IOException {
        file2.createNewFile();
        FileInputStream fileInputStream=new FileInputStream(file2);
        Scanner scanner=new Scanner(fileInputStream);
        while (scanner.hasNext()) {
            String user = scanner.next();
            String text= scanner.nextLine();
            Profile profile=SocialNetwork.profileMap.get(user);
            profile.getPosts().add(new Post(user,text));
        }
    }
    public static void readProfile(File file2) throws IOException {
        file2.createNewFile();
        FileInputStream fileInputStream=new FileInputStream(file2);
        Scanner scanner=new Scanner(fileInputStream);
        int id=0;
        while (scanner.hasNext()) {
            String user = scanner.next();
            String pass = scanner.next();
            id = scanner.nextInt();
            String bio= scanner.nextLine();
            SocialNetwork.profileMap.put(user,new Profile(user,pass,id));
        }
        SocialNetwork.ID=SocialNetwork.maxID()+1;
    }
    public static void readFollow(File file2) throws IOException {
        file2.createNewFile();
        FileInputStream fileInputStream=new FileInputStream(file2);
        Scanner scanner=new Scanner(fileInputStream);
        while (scanner.hasNext()) {
            String user = scanner.next();
            int follow = scanner.nextInt();
            String follow2= scanner.nextLine();
            String []follow3=follow2.split(" ");
            Profile profile=SocialNetwork.profileMap.get(user);
            if(follow==1){
                for(int i=0;i<follow3.length;i++)
                    if(follow3[i].compareTo("")!=0)
                        profile.getFollowers().add(follow3[i]);
            }else {
                for(int i=0;i<follow3.length;i++)
                    if(follow3[i].compareTo("")!=0)
                        profile.getFollowing().add(follow3[i]);
            }
        }

    }
}
