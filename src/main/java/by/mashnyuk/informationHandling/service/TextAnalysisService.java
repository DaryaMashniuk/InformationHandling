package by.mashnyuk.informationHandling.service;

import by.mashnyuk.informationHandling.entity.*;

import java.util.*;
import java.util.stream.Collectors;

public class TextAnalysisService {
    private static final String VOWELS = "aeiouAEIOUаеёиоуыэюяАЕЁИОУЫЭЮЯ";

    public Text sortParagraphsBySentenceCount(Text text) {
        List<Paragraph> paragraphs = text.getChildren().stream()
                .filter(p -> p instanceof Paragraph)
                .map(p -> (Paragraph) p)
                .collect(Collectors.toList());

        paragraphs.sort(Comparator.comparingInt(p -> p.getChildren().size()));

        Text sortedText = new Text();
        paragraphs.forEach(sortedText::add);

        return sortedText;
    }

    public List<Sentence> findSentencesWithLongestWord(Text text) {
        int maxLength = 0;
        List<Sentence> result = new ArrayList<>();

        for (TextComponent paragraph : text.getChildren()) {
            for (TextComponent sentence : paragraph.getChildren()) {
                int currentMax = findLongestWordLength((Sentence) sentence);

                if (currentMax > maxLength) {
                    maxLength = currentMax;
                    result.clear();
                    result.add((Sentence) sentence);
                } else if (currentMax == maxLength) {
                    result.add((Sentence) sentence);
                }
            }
        }

        return result;
    }

    public Text removeShortSentences(Text text, int minWordCount) {
        Text result = new Text();

        for (TextComponent paragraph : text.getChildren()) {
            Paragraph newParagraph = new Paragraph();
            for (TextComponent sentence : paragraph.getChildren()) {
                int wordCount = countWords((Sentence) sentence);
                if (wordCount >= minWordCount) {
                    newParagraph.add(sentence);
                }
            }
            if (!newParagraph.getChildren().isEmpty()) {
                result.add(newParagraph);
            }
        }

        return result;
    }

    public Map<String, Integer> countDuplicateWords(Text text) {
        Map<String, Integer> wordCount = new HashMap<>();
        collectWords(text, wordCount);

        return wordCount.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Sentence, int[]> countVowelsAndConsonants(Text text) {
        Map<Sentence, int[]> result = new LinkedHashMap<>();

        for (TextComponent paragraph : text.getChildren()) {
            for (TextComponent sentence : paragraph.getChildren()) {
                int[] counts = new int[2];
                String sentenceText = ((Sentence) sentence).getText();

                for (char c : sentenceText.toCharArray()) {
                    if (Character.isLetter(c)) {
                        if (VOWELS.indexOf(c) >= 0) {
                            counts[0]++;
                        } else {
                            counts[1]++;
                        }
                    }
                }

                result.put((Sentence) sentence, counts);
            }
        }

        return result;
    }

    private int countWords(Sentence sentence) {
        int count = 0;
        for (TextComponent lexeme : sentence.getChildren()) {
            for (TextComponent component : lexeme.getChildren()) {
                if (component instanceof Word || component instanceof Expression) {
                    count++;
                }
            }
        }
        return count;
    }

    private int findLongestWordLength(Sentence sentence) {
        int maxLength = 0;
        for (TextComponent lexeme : sentence.getChildren()) {
            for (TextComponent component : lexeme.getChildren()) {
                if (component instanceof Word) {
                    String word = component.getText().replaceAll("[^a-zA-Zа-яА-ЯёЁ]", "");
                    maxLength = Math.max(maxLength, word.length());
                }
            }
        }
        return maxLength;
    }

    private void collectWords(TextComponent component, Map<String, Integer> wordCount) {
        if (component instanceof Word) {
            String word = component.getText().toLowerCase();
            wordCount.merge(word, 1, Integer::sum);
        } else if (!(component instanceof Expression)) {
            for (TextComponent child : component.getChildren()) {
                collectWords(child, wordCount);
            }
        }
    }
}
