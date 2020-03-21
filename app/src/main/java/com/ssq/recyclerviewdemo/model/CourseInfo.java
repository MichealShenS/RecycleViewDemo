package com.ssq.recyclerviewdemo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : Mr.Shen
 * Date : 2020/3/21 8:04
 * Description :
 */
public class CourseInfo extends BaseInfo {
    public int id;
    public String name;

    public List<ChapterInfo> chapterInfos = new ArrayList<>();
}
