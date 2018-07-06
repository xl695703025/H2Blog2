package com.yuxia.blog.service;

import java.util.List;

import com.yuxia.blog.entity.Tag;

public interface TagService {

	public List<Tag> getTagList(String userName);
}
