package com.qxst.e.meatballstartup.util;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.analysis.CosineTextSimilarity;
import org.apdplat.word.analysis.TextSimilarity;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.apdplat.word.segmentation.Word;
import org.apdplat.word.segmentation.impl.MaximumMatching;
import org.apdplat.word.tagging.AntonymTagging;
import org.apdplat.word.tagging.SynonymTagging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordUtil {
    /**
     * 参考网址：https://github.com/ysc/word
     * 对文本进行分词时，可显式指定特定的分词算法，如：
     * WordSegmenter.seg("APDPlat应用级产品开发平台", SegmentationAlgorithm.BidirectionalMaximumMatching);
     *
     * SegmentationAlgorithm的可选类型为：
     * 正向最大匹配算法：MaximumMatching
     * 逆向最大匹配算法：ReverseMaximumMatching
     * 正向最小匹配算法：MinimumMatching
     * 逆向最小匹配算法：ReverseMinimumMatching
     * 双向最大匹配算法：BidirectionalMaximumMatching
     * 双向最小匹配算法：BidirectionalMinimumMatching
     * 双向最大最小匹配算法：BidirectionalMaximumMinimumMatching
     * 全切分算法：FullSegmentation
     * 最少分词算法：MinimalWordCount
     * 最大Ngram分值算法：MaxNgramScore
     * @param text
     * @return
     */
    public static Map<String, String> segMore(String text) {
        Map<String, String> map = new HashMap<>();
        /*for(SegmentationAlgorithm segmentationAlgorithm : SegmentationAlgorithm.values()){
            map.put(segmentationAlgorithm.getDes(), seg(text, segmentationAlgorithm));
        }*/
        map.put("ReverseMaximumMatching", seg(text, SegmentationAlgorithm.ReverseMaximumMatching));
        return map;
    }
    private static String seg(String text, SegmentationAlgorithm segmentationAlgorithm) {
        StringBuilder result = new StringBuilder();
        List<Word> words = WordSegmenter.segWithStopWords(text, segmentationAlgorithm);
        //同义标注
        //SynonymTagging.process(words);
        //System.out.println("同义词标注后： = [" + words + "]");
        //反义标注
        //AntonymTagging.process(words);
        for(Word word : words){
            result.append(word.getText()).append(" ");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        //分词
        //Map map = segMore("楚离陌千方百计为无情找回记忆");
        //System.out.println("args = [" + map + "]");

        //相似度算法
        /*String text1 = "我爱购物";
        String text2 = "我爱读书";
        String text3 = "他是黑客";
        TextSimilarity textSimilarity = new CosineTextSimilarity();
        double score1pk1 = textSimilarity.similarScore(text1, text1);
        double score1pk2 = textSimilarity.similarScore(text1, text2);
        double score1pk3 = textSimilarity.similarScore(text1, text3);
        double score2pk2 = textSimilarity.similarScore(text2, text2);
        double score2pk3 = textSimilarity.similarScore(text2, text3);
        double score3pk3 = textSimilarity.similarScore(text3, text3);
        System.out.println(text1+" 和 "+text1+" 的相似度分值："+score1pk1);
        System.out.println(text1+" 和 "+text2+" 的相似度分值："+score1pk2);
        System.out.println(text1+" 和 "+text3+" 的相似度分值："+score1pk3);
        System.out.println(text2+" 和 "+text2+" 的相似度分值："+score2pk2);
        System.out.println(text2+" 和 "+text3+" 的相似度分值："+score2pk3);
        System.out.println(text3+" 和 "+text3+" 的相似度分值："+score3pk3);*/

        List<Word> words = WordSegmenter.segWithStopWords("楚离陌千方百计为无情找回记忆");
        System.out.println(words);
        SynonymTagging.process(words);
        System.out.println(words);
    }

}
