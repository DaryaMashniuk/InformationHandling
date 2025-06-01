package by.mashnyuk.InformationHandling.service;
import by.mashnyuk.InformationHandling.entity.*;

import java.util.*;
import java.util.stream.Collectors;

public class TextAnalysisService {
    private final ExpressionEvaluationService expressionService;

    public TextAnalysisService(ExpressionEvaluationService expressionService) {
        this.expressionService = expressionService;
    }

    public Text sortParagraphsBySentenceCount(Text text) {
        List<TextComponent> paragraphs = new ArrayList<>(text.getChildren());
        paragraphs.sort(Comparator.comparingInt(p -> countSentences((Paragraph) p)));

        Text sortedText = new Text();
        paragraphs.forEach(sortedText.getChildren()::add);
        return sortedText;
    }

    public List<Sentence> findSentencesWithLongestWord(Text text) {
        List<Sentence> result = new ArrayList<>();
        int maxLength = 0;

        for (TextComponent paragraph : text.getChildren()) {
            for (TextComponent sentence : paragraph.getChildren()) {
                int longest = findLongestWordLength((Sentence) sentence);

                if (longest > maxLength) {
                    maxLength = longest;
                    result.clear();
                    result.add((Sentence) sentence);
                } else if (longest == maxLength) {
                    result.add((Sentence) sentence);
                }
            }
        }

        return result;
    }

    public Text removeShortSentences(Text text, int minWordCount) {
        Text newText = new Text();

        for (TextComponent paragraph : text.getChildren()) {
            Paragraph newParagraph = new Paragraph();

            for (TextComponent sentence : paragraph.getChildren()) {
                if (countWords((Sentence) sentence) >= minWordCount) {
                    newParagraph.getChildren().add(sentence);
                }
            }

            if (!newParagraph.getChildren().isEmpty()) {
                newText.getChildren().add(newParagraph);
            }
        }

        return newText;
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
        String vowels = "aeiouAEIOUаеёиоуыэюяАЕЁИОУЫЭЮЯ";

        for (TextComponent paragraph : text.getChildren()) {
            for (TextComponent sentence : paragraph.getChildren()) {
                int[] counts = new int[2];
                countLetters((Sentence) sentence, vowels, counts);
                result.put((Sentence) sentence, counts);
            }
        }

        return result;
    }

    private int countSentences(Paragraph paragraph) {
        return paragraph.getChildren().size();
    }

    private int countWords(Sentence sentence) {
        int count = 0;
        for (TextComponent component : sentence.getChildren()) {
            if (component instanceof Word) {
                count++;
            } else if (component instanceof Lexeme) {
                count += component.getChildren().stream()
                        .filter(c -> c instanceof Word)
                        .count();
            }
        }
        return count;
    }

    private int findLongestWordLength(Sentence sentence) {
        int maxLength = 0;
        for (TextComponent component : sentence.getChildren()) {
            if (component instanceof Word) {
                maxLength = Math.max(maxLength, component.getText().length());
            } else if (component instanceof Lexeme) {
                for (TextComponent child : component.getChildren()) {
                    if (child instanceof Word) {
                        maxLength = Math.max(maxLength, child.getText().length());
                    }
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
            component.getChildren().forEach(c -> collectWords(c, wordCount));
        }
    }

    private void countLetters(Sentence sentence, String vowels, int[] counts) {
        for (TextComponent component : sentence.getChildren()) {
            if (component instanceof Symbol) {
                char c = component.getText().charAt(0);
                if (Character.isLetter(c)) {
                    if (vowels.indexOf(c) >= 0) {
                        counts[0]++;
                    } else {
                        counts[1]++;
                    }
                }
            } else {
                countLetters(component, vowels, counts);
            }
        }
    }

    private void countLetters(TextComponent component, String vowels, int[] counts) {
        if (component instanceof Symbol) {
            char c = component.getText().charAt(0);
            if (Character.isLetter(c)) {
                if (vowels.indexOf(c) >= 0) {
                    counts[0]++;
                } else {
                    counts[1]++;
                }
            }
        } else {
            component.getChildren().forEach(c -> countLetters(c, vowels, counts));
        }
    }
}
