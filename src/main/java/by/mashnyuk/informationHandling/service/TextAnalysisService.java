package by.mashnyuk.informationHandling.service;
import by.mashnyuk.informationHandling.entity.*;

import java.util.*;
import java.util.stream.Collectors;


public class TextAnalysisService {
    private static final String VOWELS = "aeiouAEIOUаеёиоуыэюяАЕЁИОУЫЭЮЯ";

    public Text sortParagraphsBySentenceCount(Text text) {
        List<TextComponent> paragraphs = new ArrayList<>(text.getChildren());
        paragraphs.sort(Comparator.comparingInt(p -> ((Paragraph)p).getChildren().size()));

        Text sortedText = new Text();
        paragraphs.forEach(sortedText::add);
        return sortedText;
    }

    public List<Sentence> findSentencesWithLongestWord(Text text) {
        List<Sentence> result = new ArrayList<>();
        int maxLength = 0;

        for (TextComponent paragraph : text.getChildren()) {
            for (TextComponent sentence : paragraph.getChildren()) {
                int currentMax = findLongestWordLength((Sentence)sentence);

                if (currentMax > maxLength) {
                    maxLength = currentMax;
                    result.clear();
                    result.add((Sentence)sentence);
                } else if (currentMax == maxLength) {
                    result.add((Sentence)sentence);
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
                if (countWords((Sentence)sentence) >= minWordCount) {
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
        Map<Sentence, int[]> result = new HashMap<>();

        for (TextComponent paragraph : text.getChildren()) {
            for (TextComponent sentence : paragraph.getChildren()) {
                int[] counts = new int[2];
                countLetters((Sentence)sentence, counts);
                result.put((Sentence)sentence, counts);
            }
        }

        return result;
    }

    private int countWords(Sentence sentence) {
        int count = 0;
        for (TextComponent lexeme : sentence.getChildren()) {
            for (TextComponent component : lexeme.getChildren()) {
                if (component instanceof Word) {
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
                    maxLength = Math.max(maxLength, component.getText().length());
                }
            }
        }
        return maxLength;
    }

    private void collectWords(TextComponent component, Map<String, Integer> wordCount) {
        if (component instanceof Word) {
            String word = component.getText().toLowerCase();
            wordCount.merge(word, 1, Integer::sum);
        } else {
            for (TextComponent child : component.getChildren()) {
                collectWords(child, wordCount);
            }
        }
    }

    private void countLetters(Sentence sentence, int[] counts) {
        for (TextComponent lexeme : sentence.getChildren()) {
            for (TextComponent component : lexeme.getChildren()) {
                if (component instanceof Word) {
                    for (char c : component.getText().toCharArray()) {
                        if (Character.isLetter(c)) {
                            if (VOWELS.indexOf(c) >= 0) {
                                counts[0]++;
                            } else {
                                counts[1]++;
                            }
                        }
                    }
                }
            }
        }
    }
}