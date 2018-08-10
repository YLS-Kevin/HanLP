/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/05/2014/5/17 13:25</create-date>
 *
 * <copyright file="WordResult.java" company="上海林原信息科技有限公司">
 * Copyright (c) 2003-2014, 上海林原信息科技有限公司. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信息科技有限公司 to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.seg.common;

import java.util.List;
import java.util.Objects;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.utility.LexiconUtility;

/**
 * 一个单词，用户可以直接访问此单词的全部属性
 * @author hankcs
 */
public class Term implements Comparable<Term>
{
	
	/**
	 * 词类型
	 */
	public List<String> type;
	
    /**
     * 词语
     */
    public String word;
    
    /**
     * 主词
     */
    public String trueWord;

    /**
     * 词性
     */
    public Nature nature;
    
    /**
     * 词频
     */
    public int frequency;

    /**
     * 在文本中的起始位置（需开启分词器的offset选项）
     */
    public int offset;

    /**
     * 构造一个单词
     * @param word 词语
     * @param nature 词性
     */
    public Term(String word, Nature nature)
    {
        this.word = word;
        this.nature = nature;
    }
    
    /**
     * 构造一个单词
     * @param word 词语
     * @param nature 词性
     * @param frequency 词频
     * @param type 词类型
     */
    public Term(String word, Nature nature, int frequency, List<String> type, String trueWord)
    {
        this.word = word;
        this.nature = nature;
        this.frequency = frequency;
        this.type = type;
        this.trueWord = trueWord;
    }
    
    /**
     * 构造一个单词
     * @param word 词语
     * @param nature 词性
     * @param frequency 词频
     * @param type 词类型
     * @param offset 在文本中的起始位置
     */
    public Term(String word, Nature nature, int frequency, List<String> type, int offset, String trueWord)
    {
        this.word = word;
        this.nature = nature;
        this.frequency = frequency;
        this.type = type;
        this.offset = offset;
        this.trueWord = trueWord;
    }

    @Override
    public String toString()
    {
        if (HanLP.Config.ShowTermNature)
            return word + "/" + nature;
        return word;
    }

    /**
     * 长度
     * @return
     */
    public int length()
    {
        return word.length();
    }

    /**
     * 获取本词语在HanLP词库中的频次
     * @return 频次，0代表这是个OOV
     */
    public int getFrequency()
    {
        return LexiconUtility.getFrequency(word);
    }


    /**
     * 判断Term是否相等
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Term)
        {
            Term term = (Term)obj;
            if (this.nature == term.nature && this.word.equals(term.word))
            {
                return true;
            }
        }
        return super.equals(obj);
    }
    
    @Override
	public int hashCode() {
		return Objects.hash(this.word, this.nature);
	}
    
    @Override
	public int compareTo(Term o) {// 降序排序
		// 按照规定：如果 this 比 o 小，则返回一个负数，如果 this 比 o 大，则返回正数，否则返回0
		return -(this.frequency - o.frequency);
	}
}
