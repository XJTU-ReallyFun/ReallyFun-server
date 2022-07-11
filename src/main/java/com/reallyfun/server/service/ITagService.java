package com.reallyfun.server.service;

public interface ITagService {
    void insertTag (String content);
    void deleteTag (Integer id);
    void bindGameTag (Integer gameId, Integer tagId);
    void unbindGameTag (Integer gameId, Integer tagId);
}
