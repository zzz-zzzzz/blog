package com.tsu.zzz.utils;

import com.tsu.zzz.pojo.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentUtil {

    private List<Comment> concentrateComments = new ArrayList<>();

    public void concentrate(Comment comment) {
        List<Comment> replyComments = comment.getReplyComments();
        if (replyComments != null && replyComments.size() > 0) {
            for (int i = 0; i < replyComments.size(); i++) {
                Comment replyComment = replyComments.get(i);
                this.concentrateComments.add(replyComment);
                concentrate(replyComment);
            }
        }
        return;
    }

    public List<Comment> getConcentrateComments() {
        return concentrateComments;
    }

    public void clear() {
        this.concentrateComments = new ArrayList<>();
    }

    public static void main(String[] args) {
        Comment comment01 = new Comment();
        comment01.setId(1L);
        Comment comment02 = new Comment();
        comment02.setId(2L);
        Comment comment03 = new Comment();
        comment03.setId(3L);
        Comment comment04 = new Comment();
        comment04.setId(4L);
        ArrayList<Comment> comments01 = new ArrayList<>();
        comments01.add(comment02);
        comment01.setReplyComments(comments01);
        ArrayList<Comment> comments02 = new ArrayList<>();
        comments02.add(comment03);
        comment02.setReplyComments(comments02);
        ArrayList<Comment> comments03 = new ArrayList<>();
        comments03.add(comment04);
        comment03.setReplyComments(comments03);
        CommentUtil commentUtil = new CommentUtil();
        commentUtil.concentrate(comment01);
        System.out.println();
    }
}
