package com.test.zjj.m_osc.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/7/4.
 */
public class NewsList implements Parcelable{
    private String author;
    private int authorid;
    private int commentCount;
    private int id;
    private String pubDate;
    private String title;
    private int type;

    public NewsList() {
    }

    public NewsList(String author, int authorid, int commentCount, int id, String pubDate, String title, int type) {
        this.author = author;
        this.authorid = authorid;
        this.commentCount = commentCount;
        this.id = id;
        this.pubDate = pubDate;
        this.title = title;
        this.type = type;
    }

    protected NewsList(Parcel in) {
        author = in.readString();
        authorid = in.readInt();
        commentCount = in.readInt();
        id = in.readInt();
        pubDate = in.readString();
        title = in.readString();
        type = in.readInt();
    }

    public static final Creator<NewsList> CREATOR = new Creator<NewsList>() {
        @Override
        public NewsList createFromParcel(Parcel in) {
            return new NewsList(in);
        }

        @Override
        public NewsList[] newArray(int size) {
            return new NewsList[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAuthorid() {
        return authorid;
    }

    public void setAuthorid(int authorid) {
        this.authorid = authorid;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "NewsList{" +
                "author='" + author + '\'' +
                ", authorid=" + authorid +
                ", commentCount=" + commentCount +
                ", id=" + id +
                ", pubDate='" + pubDate + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeInt(authorid);
        dest.writeInt(commentCount);
        dest.writeInt(id);
        dest.writeString(pubDate);
        dest.writeString(title);
        dest.writeInt(type);
    }
}
