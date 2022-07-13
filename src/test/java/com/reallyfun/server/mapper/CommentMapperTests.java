package com.reallyfun.server.mapper;

import com.reallyfun.server.BaseTests;
import com.reallyfun.server.entity.Comment;
import com.reallyfun.server.entity.Like;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CommentMapperTests extends BaseTests {
    @Autowired(required = false)
    ICommentMapper commentMapper;

    private List<Comment> commentList;

    private Boolean generate() {
        if (commentList == null) {
            System.out.println("GENERATE");
            commentList = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                Comment comment = new Comment();
                comment.setUserId(randint(1, 10));
                comment.setGameId(randint(1, 10));
                comment.setContent(randstr());
                comment.setMoment(randdate());
                comment.setLikeCount(0);
                comment.setReplyCount(0);
                commentList.add(comment);
            }
            return true;
        }
        return false;
    }

    @Test
    public void insert() {
        generate();
        System.out.println("INSERT");
        for (int i = 0; i < commentList.size(); i++) {
            Comment comment = commentList.get(i);
            int j = randint(-1, i - 1);
            if (j >= 0) {
                Comment c = commentList.get(j);
                comment.setDirectReplyId(c.getId());
                if (c.getRootReplyId() != null) {
                    comment.setRootReplyId(c.getRootReplyId());
                } else {
                    comment.setRootReplyId(c.getId());
                }
            }
            comment.createBy(comment.getUserId());
            Integer result = commentMapper.insert(comment);
            assert result == 1;
        }
    }

    @Test
    public void deleteById() {
        if (generate()) {
            insert();
        }
        System.out.println("DELETE");
        Integer id = commentList.get(0).getId();
        System.out.println("DEL ID = " + id);
        Integer result = commentMapper.deleteById(id);
        assert result == 1;
    }

    private List<Like> likeList;

    private Boolean generateLike() {
        if (likeList == null) {
            System.out.println("GENERATE LIKE");
            likeList = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                Like like = new Like();
                like.setUserId(randint(1, 10));
                like.setCommentId(randint(1, 20));
                likeList.add(like);
            }
            return true;
        }
        return false;
    }

    @Test
    public void insertLike() {
        generateLike();
        System.out.println("INSERT LIKE");
        for (Like like : likeList) {
            like.createBy(like.getUserId());
            Integer result = commentMapper.insertLike(like);
            assert result == 1;
        }
    }

    @Test
    public void deleteLikeByIds() {
        if (generateLike()) {
            insertLike();
        }
        System.out.println("DELETE LIKE");
        Integer uid = likeList.get(0).getUserId();
        Integer cid = likeList.get(0).getCommentId();
        System.out.println("DEL UID = " + uid + " CID = " + cid);
        Integer result = commentMapper.deleteLikeByIds(uid, cid);
        assert result == 1;
    }

    @Test
    public void updateLikeCountById() {
        if (generate()) {
            insert();
        }
        System.out.println("UPDATE LIKE COUNT");
        for (Comment comment : commentList) {
            Integer result = commentMapper.updateLikeCountById(comment.getId());
            assert result == 1;
        }
    }

    @Test
    public void updateReplyCountById() {
        if (generate()) {
            insert();
        }
        System.out.println("UPDATE REPLY COUNT");
        for (Comment comment : commentList) {
            Integer result = commentMapper.updateReplyCountById(comment.getId());
            assert result == 1;
        }
    }

    private void test(Integer testId, Object[] args) {
        System.out.println("-------- TEST " + testId + " --------");
        List<Comment> result = commentMapper.findAllOfRange(
                (String) args[0], (Boolean) args[1], (Integer) args[2],
                (Integer) args[3], (Integer) args[4], (Integer) args[5],
                (Boolean) args[6], (Integer) args[7], (Integer) args[8]
        );
        assert result != null;
        Integer cnt = commentMapper.count(
                (Integer) args[2],
                (Integer) args[3], (Integer) args[4], (Integer) args[5],
                (Boolean) args[6]
        );
        assert cnt != null;
        System.out.println("cnt = " + cnt);
        for (Comment comment : result) {
            System.out.println(comment);
        }
    }

    @Test
    public void findAndCount() {
        if (generate()) {
            insert();
        }
        System.out.println("FIND");
        System.out.println("commentList.get(0) = " + commentList.get(0));
        Object[][] argsList = {
                {null, null, null, null, null, null, null, 1, 5},
                {"moment", false, null, null, null, null, null, 1, 5},
                {"moment", true, null, null, null, null, null, 1, 5},
                {null, null, commentList.get(0).getUserId(), null, null, null, null, 1, 5},
                {null, null, null, commentList.get(0).getGameId(), null, null, null, 1, 5},
                {null, null, null, null, commentList.get(0).getId(), null, null, 1, 5},
                {null, null, null, null, null, commentList.get(0).getUserId(), null, 1, 5},
                {null, null, null, null, null, null, true, 1, 5},
        };
        for (int i = 0; i < argsList.length; i++) {
            Object[] args = argsList[i];
            test(i, args);
        }
    }
}
