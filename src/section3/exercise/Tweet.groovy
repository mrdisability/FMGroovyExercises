package section3.exercise

import groovy.transform.ToString

@ToString
class Tweet {
    // text, author, likes
    private String text;
    private String author;
    private Integer likes = 0;

    Tweet(String text, String author) {
        this.text = text;
        this.author = author;
    }

    String getText() {
        return text;
    }

    void setText(String text) {
        this.text = text;
    }

    String getAuthor() {
        return author;
    }

    void setAuthor(String author) {
        this.author = author;
    }

    Integer getLikes() {
        return likes;
    }

    void setLikes(Integer likes) {
        this.likes = likes;
    }

    void addLike() {
        this.likes += 1;
    }
}

//Tweet secondTweet = new Tweet("Second Tweet", "Ioane");
//println secondTweet;
