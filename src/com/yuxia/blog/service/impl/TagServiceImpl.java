package com.yuxia.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuxia.blog.entity.Tag;
import com.yuxia.blog.mapper.TagMapper;
import com.yuxia.blog.service.TagService;
@Service("tagService")
public class TagServiceImpl implements TagService {

	@Autowired
	private  TagMapper tagMapper;
	@Override
	public List<Tag> getTagList(String userName) {
		return tagMapper.selectTagUserName(userName);
	}
	@Override
	public int delTagById(Integer tagId) {
		return tagMapper.delTagById(tagId);
	}
	@Override
	public int addTag(String userName, String tagName, String tagDescription) {
		Tag tag=tagMapper.selectTagByTagName(userName, tagName);
		if(tag==null){
			return tagMapper.addTag(userName, tagName, tagDescription);
		}
		else{
			return 0;
		}
	}
	@Override
	public int updateTag(Integer tagId, String tagName, String tagDescription) {
		Tag tag=tagMapper.selectTagByTagId(tagId);
		tag.setTagName(tagName);
		tag.setTagDescription(tagDescription);
		return tagMapper.updateTagByTagId(tag);
	}
}
