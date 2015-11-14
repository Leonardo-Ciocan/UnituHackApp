package com.uhapp.uhapp;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Post")
public class Post  extends ParseObject{

    public int getPage(){
        return getInt("page");
    }

    public void setPage(int page){
        put("page",page);
    }

    public String getText(){
        return getString("text");
    }

    public void setText(String text){
        put("text",text);
    }

    public ParseUser getUser(){
        return getParseUser("user");
    }

    public void setUser(ParseUser user){
        put("user",user);
    }

    public int getLikes(){
        return getInt("likes");
    }

    public int getType(){
        return getInt("type");
    }

    public void setType(int type){
        put("type",type);
    }

}
