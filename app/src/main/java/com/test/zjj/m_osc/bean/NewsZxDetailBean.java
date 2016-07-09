package com.test.zjj.m_osc.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/4.
 */
public class NewsZxDetailBean {

    /**
     * author : oschina
     * id : 74883
     * authorid : 1
     * title : Riot v2.5.0 发布，JavaScript 的 MVP 框架
     * body : <style type='text/css'>pre {white-space:pre-wrap;word-wrap:break-word;}</style><p>Riot v2.5.0 发布了，Riot.js是一个客户端模型-视图-呈现(MVP)框架并且它非常轻量级甚至小于1kb.尽管他的大小令人难以置信，所有它能构建的有如下：一个模板引擎，路由，甚至是库和一个严格的并具有组织的MVP模式。当模型数据变化时视图也会自动更新。</p>
     <p>本次发布主要改进如下：</p>
     <p>下载地址：<a target="_blank" href="http://riotjs.com/download/" rel="nofollow">http://riotjs.com/download/</a><br></p>
     * pubDate : 2016-07-04 07:10:41
     * favorite : 0
     * url : http://www.oschina.net/news/74883/riot-v2-5-0
     * relativies : [{"title":"ECharts 3.2.1 发布，JavaScript 图表组件","url":"http://www.oschina.net/news/74968/echarts-3-2-1"},{"title":"LokiJS 1.4.1 发布，JavaScript 数据库","url":"http://www.oschina.net/news/74945/lokijs-1-4"},{"title":"ECharts 3.2.0 发布，JavaScript 图表组件","url":"http://www.oschina.net/news/74823/echarts-3-2-0"},{"title":"Eclipse Neon 发布，关注 JavaScript 和 PHP","url":"http://www.oschina.net/news/74563/eclipse-neon"},{"title":" 全栈 JavaScript 程序员的崛起","url":"http://www.oschina.net/news/74112/javascript-full-stack-programmer"},{"title":"V8 5.2 发布，JavaScript 解析引擎","url":"http://www.oschina.net/news/74020/v-8-5-2"},{"title":"Howler.js \u2014\u2014 JavaScript 音频库","url":"http://www.oschina.net/p/howlerjs"},{"title":"SonarQube JavaScript 2.13 和 SonarQube Java 3.14 发布 ","url":"http://www.oschina.net/news/73877/sonarqube-javascript-2-13"},{"title":"Riot.js v3.0.0-alpha.4 发布，JavaScript 的 MVP 框架","url":"http://www.oschina.net/news/73687/riot-js-v3-0-0-alpha-4"},{"title":"Riot v2.4.1 发布，JavaScript 的 MVP 框架","url":"http://www.oschina.net/news/73655/riot-v-2-4-1"}]
     * commentCount : 0
     * notice : {"referCount":0,"replyCount":0,"msgCount":0,"fansCount":0}
     */

    private String author;
    private int id;
    private int authorid;
    private String title;
    private String body;
    private String pubDate;
    private int favorite;
    private String url;
    private int commentCount;
    /**
     * referCount : 0
     * replyCount : 0
     * msgCount : 0
     * fansCount : 0
     */

    private NoticeBean notice;
    /**
     * title : ECharts 3.2.1 发布，JavaScript 图表组件
     * url : http://www.oschina.net/news/74968/echarts-3-2-1
     */

    private List<RelativiesBean> relativies;

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

    public int getAuthorid() {
        return authorid;
    }

    public void setAuthorid(int authorid) {
        this.authorid = authorid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public NoticeBean getNotice() {
        return notice;
    }

    public void setNotice(NoticeBean notice) {
        this.notice = notice;
    }

    public List<RelativiesBean> getRelativies() {
        return relativies;
    }

    public void setRelativies(List<RelativiesBean> relativies) {
        this.relativies = relativies;
    }

    public static class NoticeBean {
        private int referCount;
        private int replyCount;
        private int msgCount;
        private int fansCount;

        public int getReferCount() {
            return referCount;
        }

        public void setReferCount(int referCount) {
            this.referCount = referCount;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public int getMsgCount() {
            return msgCount;
        }

        public void setMsgCount(int msgCount) {
            this.msgCount = msgCount;
        }

        public int getFansCount() {
            return fansCount;
        }

        public void setFansCount(int fansCount) {
            this.fansCount = fansCount;
        }
    }

    public static class RelativiesBean {
        private String title;
        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
