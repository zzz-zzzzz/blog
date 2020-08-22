package com.tsu.zzz.service.impl;

import com.tsu.zzz.dao.CommentMapper;
import com.tsu.zzz.pojo.Comment;
import com.tsu.zzz.pojo.User;
import com.tsu.zzz.service.CommentService;
import com.tsu.zzz.utils.CommentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.xml.ws.ServiceMode;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private HttpSession session;

    @Value("jpg.default.comment")
    private String defaultCommentJpg;


    @Override
    @Transactional
    public void save(Comment comment) {
        comment.setCreateTime(new Date());
        User loginUser = (User)session.getAttribute("loginUser");
        if(loginUser==null){
            comment.setAvatar(defaultCommentJpg);
            comment.setAdminComment(false);
        }else {
            comment.setAvatar(loginUser.getAvatar());
            comment.setAdminComment(true);
            comment.setNickname(loginUser.getNickname());
        }
        commentMapper.save(comment);
    }

    @Override
    public List<Comment> findByBlogId(Long blogId) {
        List<Comment> commentList = commentMapper.findByBlogIdAndParentId(blogId, -1L);
        CommentUtil commentUtil = new CommentUtil();
        for (Comment comment : commentList) {
            commentUtil.concentrate(comment);
            comment.setReplyComments(commentUtil.getConcentrateComments());
            commentUtil.clear();
        }
        return commentList;
    }
}
