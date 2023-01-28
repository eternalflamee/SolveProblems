//It's time to try to implement a hierarchy of classes of a certain kind and solve a specific problem.

//Imagine you are making a comment filtering system on some web portal, be it news, video hosting,
// or maybe even for an online learning system :). You want to filter comments by different criteria,
// be able to easily add new filters and modify old ones. Let's say we will filter spam,
// comments with negative content and comments that are too long.

//Spam will be filtered by the presence of the specified keywords in the text. Negative content
// will be determined by the presence of one of the three emoticons - :( =( :|. Too long comments will be
// determined based on this number - the maximum length of the comment.

//You have decided to abstract the filter in the form of the following interface:
//
//interface TextAnalyzer {
//  Label processText(String text);
//}
//Label – type-enumeration, which contains labels with which we will mark the text:
//
//enum Label {
//  SPAM, NEGATIVE_TEXT, TOO_LONG, OK
//}

//Next, you need to implement three classes that implement this interface: SpamAnalyzer,
// NegativeTextAnalyzer and TooLongTextAnalyzer. SpamAnalyzer should be constructed from an array
// of strings with keywords. An object of this class must keep this array of strings in its state
// in the private keywords field. NegativeTextAnalyzer should be constructed by the default constructor.
// The TooLongTextAnalyzer should be constructed from an int with the maximum allowable comment length.
// An object of this class must keep this number in its state in the private maxLength field.

//Surely you have already noticed that SpamAnalyzer and NegativeTextAnalyzer are similar in many ways:
// they both check the text for the presence of any keywords (in the case of spam, we get them from the constructor,
// in the case of negative text, we know a set of sad emoticons in advance) and if one of the keywords is found,
// they return Label (SPAM and NEGATIVE_TEXT, respectively), and if nothing was found, return OK.
// Let's abstract this logic into an abstract KeywordAnalyzer class as follows:

// Let's highlight two abstract methods getKeywords and getLabel, one of which will return a set of keywords,
// and the second a label with which to mark positive positives. We don't need to show these methods to class consumers,
// so we'll leave access to them only for heirs. We implement processText in such a way that it depends
// only on getKeywords and getLabel. Let's make SpamAnalyzer and NegativeTextAnalyzer inheritors of KeywordAnalyzer
// and implement abstract methods.

//The final touch is to write the checkLabels method, which will return a label for a comment on a set of text analyzers.
// checkLabels should return the first non-OK label in the order of a given set of analyzers, and OK if all analyzers returned OK.
// Please use the most open access modifier for all classes. As a result, implement the KeywordAnalyzer, SpamAnalyzer,
// NegativeTextAnalyzer and TooLongTextAnalyzer classes and the checkLabels method. TextAnalyzer and Label are already connected,
// you will not need extra imports.

abstract class KeywordAnalyzer implements TextAnalyzer {
    protected abstract String[] getKeywords();
    protected abstract Label getLabel();

    public Label processText(String text) {
        String[] keywords = getKeywords();
        for (String keyword : keywords) {
            if (text.contains(keyword)) {
                return getLabel();
            }
        }
        return Label.OK;
    }

}

class SpamAnalyzer extends KeywordAnalyzer implements TextAnalyzer {
    private String[] keywords;

    public SpamAnalyzer(String[] strings) {
        this.keywords = strings;
    }

    @Override
    protected String[] getKeywords() {
        return keywords;
    }

    @Override
    protected Label getLabel() {
        return Label.SPAM;
    }
}

class NegativeTextAnalyzer extends KeywordAnalyzer implements TextAnalyzer {
    private final String[] KEYWORDS = {":(", "=(", ":|"};

    @Override
    protected String[] getKeywords() {
        return KEYWORDS;
    }

    @Override
    protected Label getLabel() {
        return Label.NEGATIVE_TEXT;
    }
}

class TooLongTextAnalyzer implements TextAnalyzer {
    private int maxLength;

    public TooLongTextAnalyzer(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public Label processText(String text) {
        if (text.length() > maxLength)
            return Label.TOO_LONG;
        else
            return Label.OK;
    }
}

public Label checkLabels(TextAnalyzer[] analyzers, String text) {
    for (TextAnalyzer analyzer : analyzers)
        if (analyzer.processText(text) != Label.OK)
            return analyzer.processText(text);
    return Label.OK;
}

