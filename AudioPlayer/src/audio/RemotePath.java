package audio;

public enum RemotePath {
    SERVER("http://localhost:8080"),
    FILE("/file"),
    DOWNLOAD("/download"),
    LIST("/list");

    private String name;

    RemotePath(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
