package net.callofdroidy.pictureoftheday.model;

public class NasaPicture {
    private String date;
    private String explanation;
    private String hdurl;
    private String media_type;
    private String service_version;
    private String title;
    private String url;

    public NasaPicture(String date, String explanation, String hdurl, String media_type, String service_version, String title, String url){
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.media_type = media_type;
        this.service_version = service_version;
        this.title = title;
        this.url = url;
    }

    public String getDate(){
        return date;
    }

    public String getExplanation(){
        return explanation;
    }

    public String getHdurl(){
        return hdurl;
    }

    public String getMedia_type(){
        return media_type;
    }

    public String getService_version(){
        return service_version;
    }

    public String getTitle(){
        return title;
    }

    public String getUrl(){
        return url;
    }
}
