package com.reallyfun.server.service;

import com.reallyfun.server.entity.RelGameTag;
import com.reallyfun.server.entity.Tag;

public interface ITagService {
    void insertTag (String content);
    void deleteTag (Integer id);
    void bindGameTag (Integer gameId, Integer tagId);
    void unbindGameTag (Integer gameId, Integer tagId);
}
