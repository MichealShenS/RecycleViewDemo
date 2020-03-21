package com.ssq.recyclerviewdemo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : Mr.Shen
 * Date : 2020/3/21 8:04
 * Description :
 */
public class ChapterInfo extends BaseInfo {

    public String name;
    public int chapterIndex;

    public List<SectionInfo> sectionInfos = new ArrayList<>();

}
