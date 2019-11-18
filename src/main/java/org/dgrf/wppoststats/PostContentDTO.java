/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.wppoststats;


/**
 *
 * @author dgrfiv
 */
public class PostContentDTO {
    private String postContent;
    private String title;
    private int postId;
    private String postDate;
    
    private String compiledBy;
    private int wordCount;

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }


    public String getCompiledBy() {
        return compiledBy;
    }

    public void setCompiledBy(String compiledBy) {
        this.compiledBy = compiledBy;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    
}
