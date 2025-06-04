package by.mashnyuk.informationHandling.service;

import by.mashnyuk.informationHandling.entity.impl.Sentence;
import by.mashnyuk.informationHandling.entity.impl.Text;

import java.util.List;
import java.util.Map;

public interface TextAnalysisService {
    Text sortParagraphsBySentenceCount(Text text);

    List<Sentence> findSentencesWithLongestWord(Text text);

    Text removeShortSentences(Text text, int minWordCount);

    Map<String, Integer> countDuplicateWords(Text text);

    Map<Sentence, int[]> countVowelsAndConsonants(Text text);
}
