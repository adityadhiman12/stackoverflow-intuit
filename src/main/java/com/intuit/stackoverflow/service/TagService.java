package com.intuit.stackoverflow.service;

import com.intuit.stackoverflow.model.Tag;

public interface TagService {
    Tag removeTag(Integer id);
    Tag addTag(Tag tag);
}
