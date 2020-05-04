package iwona.pl.modol12project.model;
public class Image {

    private Long id;
    private String url;
    private String content;

    public Image() {
    }

    public Image(String url, String content) {
//        this.id = id;
        this.url = url;
        this.content = content;
    }

    public Image(long id, String url, String content) {
        this.id = id;
        this.url = url;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public String setContent(String content) {
        this.content = content;
        return content;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
