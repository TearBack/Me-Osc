package com.test.zjj.m_osc.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/7/6.
 */
public class TweetListBean implements Parcelable{
    private String author;
    private int id;
    private String portrait;
    private int authorid;
    private String body;
    private String pubDate;
    private int commentCount;
    private String imgBig;
    private String imgSmall;

    public TweetListBean() {
    }

    protected TweetListBean(Parcel in) {
        author = in.readString();
        id = in.readInt();
        portrait = in.readString();
        authorid = in.readInt();
        body = in.readString();
        pubDate = in.readString();
        commentCount = in.readInt();
        imgBig = in.readString();
        imgSmall = in.readString();
    }

    public static final Creator<TweetListBean> CREATOR = new Creator<TweetListBean>() {
        @Override
        public TweetListBean createFromParcel(Parcel in) {
            return new TweetListBean(in);
        }

        @Override
        public TweetListBean[] newArray(int size) {
            return new TweetListBean[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public int getAuthorid() {
        return authorid;
    }

    public void setAuthorid(int authorid) {
        this.authorid = authorid;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getImgBig() {
        return imgBig;
    }

    public void setImgBig(String imgBig) {
        this.imgBig = imgBig;
    }

    public String getImgSmall() {
        return imgSmall;
    }

    public void setImgSmall(String imgSmall) {
        this.imgSmall = imgSmall;
    }

    @Override
    public String toString() {
        return "TweetListBean{" +
                "author='" + author + '\'' +
                ", id=" + id +
                ", portrait='" + portrait + '\'' +
                ", authorid=" + authorid +
                ", body='" + body + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", commentCount=" + commentCount +
                ", imgBig='" + imgBig + '\'' +
                ", imgSmall='" + imgSmall + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeInt(id);
        dest.writeString(portrait);
        dest.writeInt(authorid);
        dest.writeString(body);
        dest.writeString(pubDate);
        dest.writeInt(commentCount);
        dest.writeString(imgBig);
        dest.writeString(imgSmall);
    }
}
