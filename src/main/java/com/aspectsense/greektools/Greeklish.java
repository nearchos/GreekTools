package com.aspectsense.greektools;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Implements ISO 843 / ΕΛΟΤ 743 for the transliteration of Greek text to the Latin alphabet.
 *
 * @see <a href="https://en.wikipedia.org/wiki/ISO_843#1997_edition">ISO 843 on Wikipedia</a>
 * @see <a href="http://www.geonoma.gov.cy/index.php/typopoiisi/metagrafi-ellnikou-alfavitou">
 *     Metagrafi ellinikou alfavitou</a>
 *
 * @author Nearchos Paspallis
 * Created: 29-Mar-20
 */
public class Greeklish {

    private static final Map<String, String> mappingBase = new HashMap<>();
    private static final Map<String, String> mappingWithAccents = new HashMap<>();
    private static final Map<String, String> exceptions = new HashMap<>();
    private static final Map<String, String[]> exceptionsNotes_1_2 = new HashMap<>();
    private static final Map<String, String> exceptionsWithAccents = new HashMap<>();
    private static final Map<String, String[]> exceptionsWithAccentsNotes_1_2 = new HashMap<>();
    private static final Map<String, String> exceptionsAllCaps = new HashMap<>();

    static {
        mappingBase.put("Α", "A");
        mappingBase.put("Ά", "A");
        mappingBase.put("α", "a");
        mappingBase.put("ά", "a");
        mappingBase.put("Β", "V");
        mappingBase.put("β", "v");
        mappingBase.put("Γ", "G");
        mappingBase.put("γ", "g");
        mappingBase.put("Δ", "D");
        mappingBase.put("δ", "d");
        mappingBase.put("Ε", "E");
        mappingBase.put("Έ", "E");
        mappingBase.put("ε", "e");
        mappingBase.put("έ", "e");
        mappingBase.put("Ζ", "Z");
        mappingBase.put("ζ", "z");
        mappingBase.put("Η", "I");
        mappingBase.put("Ή", "I");
        mappingBase.put("η", "i");
        mappingBase.put("ή", "i");
        mappingBase.put("Θ", "Th");
        mappingBase.put("θ", "th");
        mappingBase.put("Ι", "I");
        mappingBase.put("Ί", "I");
        mappingBase.put("Ϊ", "I");
        mappingBase.put("ι", "i");
        mappingBase.put("ί", "i");
        mappingBase.put("ϊ", "i");
        mappingBase.put("ΐ", "i");
        mappingBase.put("Κ", "K");
        mappingBase.put("κ", "k");
        mappingBase.put("Λ", "L");
        mappingBase.put("λ", "l");
        mappingBase.put("Μ", "M");
        mappingBase.put("μ", "m");
        mappingBase.put("Ν", "N");
        mappingBase.put("ν", "n");
        mappingBase.put("Ξ", "X");
        mappingBase.put("ξ", "x");
        mappingBase.put("Ο", "O");
        mappingBase.put("Ό", "O");
        mappingBase.put("ο", "o");
        mappingBase.put("ό", "o");
        mappingBase.put("Π", "P");
        mappingBase.put("π", "p");
        mappingBase.put("Ρ", "R");
        mappingBase.put("ρ", "r");
        mappingBase.put("Σ", "S");
        mappingBase.put("σ", "s");
        mappingBase.put("ς", "s");
        mappingBase.put("Τ", "T");
        mappingBase.put("τ", "t");
        mappingBase.put("Υ", "Y");
        mappingBase.put("Ύ", "Y");
        mappingBase.put("Ϋ", "Y");
        mappingBase.put("υ", "y");
        mappingBase.put("ύ", "y");
        mappingBase.put("ϋ", "y");
        mappingBase.put("ΰ", "y");
        mappingBase.put("Φ", "F");
        mappingBase.put("φ", "f");
        mappingBase.put("Χ", "Ch");
        mappingBase.put("χ", "ch");
        mappingBase.put("Ψ", "Ps");
        mappingBase.put("ψ", "ps");
        mappingBase.put("Ω", "O");
        mappingBase.put("Ώ", "O");
        mappingBase.put("ω", "o");
        mappingBase.put("ώ", "o");

        mappingBase.put(";", "?");

        mappingWithAccents.put("Ά", "Á");
        mappingWithAccents.put("ά", "á");
        mappingWithAccents.put("Έ", "É");
        mappingWithAccents.put("έ", "é");
        mappingWithAccents.put("Η", "Ī");
        mappingWithAccents.put("Ή", "Ī́");
        mappingWithAccents.put("η", "ī");
        mappingWithAccents.put("ή", "ī́");
        mappingWithAccents.put("Ί", "Í");
        mappingWithAccents.put("Ϊ", "Ï");
        mappingWithAccents.put("ί", "í");
        mappingWithAccents.put("ϊ", "ï");
        mappingWithAccents.put("ΐ", "ḯ");
        mappingWithAccents.put("Ό", "Ó");
        mappingWithAccents.put("ό", "ó");
        mappingWithAccents.put("Ύ", "Ý");
        mappingWithAccents.put("Ϋ", "Ÿ");
        mappingWithAccents.put("ύ", "ý");
        mappingWithAccents.put("ϋ", "ÿ");
        mappingWithAccents.put("ΰ", "ÿ́");
        mappingWithAccents.put("Ω", "Ō");
        mappingWithAccents.put("Ώ", "Ṓ");
        mappingWithAccents.put("ω", "ō");
        mappingWithAccents.put("ώ", "ṓ");

        exceptionsNotes_1_2.put("ΑΥ", new String [] {"AV", "AF"});
        exceptionsNotes_1_2.put("Αυ", new String [] {"Av", "Af"});
        exceptionsNotes_1_2.put("αυ", new String [] {"av", "af"});
        exceptionsNotes_1_2.put("ΑΎ", new String [] {"AV", "AF"});
        exceptionsNotes_1_2.put("Αύ", new String [] {"Av", "Af"});
        exceptionsNotes_1_2.put("αύ", new String [] {"av", "af"});
        exceptionsNotes_1_2.put("ΕΥ", new String [] {"EV", "EF"});
        exceptionsNotes_1_2.put("Ευ", new String [] {"Ev", "Ef"});
        exceptionsNotes_1_2.put("ευ", new String [] {"ev", "ef"});
        exceptionsNotes_1_2.put("ΕΎ", new String [] {"EV", "EF"});
        exceptionsNotes_1_2.put("Εύ", new String [] {"Ev", "Ef"});
        exceptionsNotes_1_2.put("εύ", new String [] {"ev", "ef"});

        exceptions.put("ΟΥ", "OU");
        exceptions.put("Ου", "Ou");
        exceptions.put("ου", "ou");
        exceptions.put("ΟΎ", "OU");
        exceptions.put("Ού", "Ou");
        exceptions.put("ού", "ou");

        exceptions.put("ΓΓ", "NG");
        exceptions.put("Γγ", "Ng");
        exceptions.put("γγ", "ng");
        exceptions.put("ΓΚ", "GK");
        exceptions.put("Γκ", "Gk");
        exceptions.put("γκ", "gk");
        exceptions.put("ΓΞ", "NX");
        exceptions.put("Γξ", "Nx");
        exceptions.put("γξ", "nx");
        exceptions.put("ΓΧ", "NCH");
        exceptions.put("Γχ", "Nch");
        exceptions.put("γχ", "nch");
        exceptions.put("ΝΤ", "NT");
        exceptions.put("Ντ", "Nt");
        exceptions.put("ντ", "nt");

        exceptionsWithAccentsNotes_1_2.put("ΑΎ", new String [] {"ÁV", "ÁF"});
        exceptionsWithAccentsNotes_1_2.put("Αύ", new String [] {"Áv", "Áf"});
        exceptionsWithAccentsNotes_1_2.put("αύ", new String [] {"áv", "áf"});
        exceptionsWithAccentsNotes_1_2.put("ΕΎ", new String [] {"ÉV", "ÉF"});
        exceptionsWithAccentsNotes_1_2.put("Εύ", new String [] {"Év", "Éf"});
        exceptionsWithAccentsNotes_1_2.put("εύ", new String [] {"év", "éf"});

        exceptionsWithAccents.put("ΟΎ", "OÚ");
        exceptionsWithAccents.put("Ού", "Oú");
        exceptionsWithAccents.put("ού", "oú");

        exceptionsAllCaps.put("Θ", "TH");
        exceptionsAllCaps.put("Χ", "CH");
        exceptionsAllCaps.put("Ψ", "PS");
    }

    private static final String ALL_GREEK_UPPERCASE_LETTERS = "ΑΆΒΓΔΕΈΖΗΉΘΙΊΪΚΛΜΝΞΟΌΠΡΣΤΥΎΫΦΧΨΩΏ";
    private static final String ALL_GREEK_LOWERCASE_LETTERS = "αάβγδεέζηήθιίϊΐκλμνξοόπρσςτυύϋΰφχψωώ";
    private static final String ALL_GREEK_LETTERS = ALL_GREEK_UPPERCASE_LETTERS + ALL_GREEK_LOWERCASE_LETTERS;
    private static final String ALL_GREEK_VOWELS = "ΑαΆάΕεΈέΗηΉήΙιΊίΪϊΐΟοΌόΥυΎύΫϋΰΩωΏώ";
    private static final String NOTE_1_GREEK_CONSONANTS = "ΒβΓγΔδΖζΛλΜμΝνΡρ";
    private static final String NOTE_2_GREEK_CONSONANTS = "ΘθΚκΞξΠπΣσΤτΦφΧχΨψ";

    private static boolean checkNote1(final char c) {
        return ALL_GREEK_VOWELS.indexOf(c) != -1 || NOTE_1_GREEK_CONSONANTS.indexOf(c) != -1;
    }

    /**
     * Converts a text given in Greek into Greeklish (i.e. the equivalent text expressed in the Latin character set).
     * It ignores accents, i.e. "Κόσμος" is converted to "Kosmos".
     *
     * @param greek the original text, expressed in the Greek character set
     * @return the converted text, expressed in the Latin character set
     * @see #toGreeklish(String, boolean)
     */
    public static String toGreeklish(final String greek) {
        return toGreeklish(greek, false);
    }

    /**
     * Converts a text given in Greek into Greeklish (i.e. the equivalent text expressed in the Latin character set).
     * If withAccents is set to true, it converts accents as well, i.e. "Κόσμος" is converted to "Kósmos".
     *
     * @see #toGreeklish(String)
     *
     * @param greek the original text, expressed in the Greek character set
     * @return the converted text, expressed in the Latin character set
     */
    public static String toGreeklish(final String greek, final boolean withAccents) {
        final StringBuilder stringBuilder = new StringBuilder();
        final StringTokenizer stringTokenizer = new StringTokenizer(greek, " ");
        while(stringTokenizer.hasMoreTokens()) {
            final String word = stringTokenizer.nextToken();
            stringBuilder.append(toGreeklishWord(word, withAccents)).append(" ");
        }
        return stringBuilder.toString().trim();
    }

    /**
     * Converts a word given in Greek into Greeklish (i.e. the equivalent word expressed in the Latin character set).
     * If withAccents is set to true, it converts accents as well, i.e. "Κόσμος" is converted to "Kósmos".
     *
     * @see #toGreeklish(String)
     *
     * @param greekWord the original word, expressed in the Greek character set
     * @return the converted text, expressed in the Latin character set
     */
    public static String toGreeklishWord(final String greekWord, final boolean withAccent) {
        String greeklishWord = greekWord;
        // handle words starting with ΜΠ, Μπ, μπ
        {
            if(greeklishWord.startsWith("ΜΠ")) greeklishWord = greeklishWord.replace("ΜΠ", "B");
            else if(greeklishWord.startsWith("Μπ")) greeklishWord = greeklishWord.replace("Μπ", "B");
            else if(greeklishWord.startsWith("μπ")) greeklishWord = greeklishWord.replace("μπ", "b");
        }
        // first handle exceptional cases under notes 1, 2 - with accents ...
        if(withAccent) {
            final Set<String> exceptionKeys = exceptionsWithAccentsNotes_1_2.keySet();
            for(final String exceptionKey : exceptionKeys) {
                final String [] exceptionValues = exceptionsWithAccentsNotes_1_2.get(exceptionKey);
                assert exceptionValues != null;
                int index;
                while((index = greeklishWord.indexOf(exceptionKey)) != -1) {
                    final boolean endOfWord = index == greeklishWord.length() - exceptionKey.length() - 1;
                    final boolean note1 = !endOfWord && checkNote1(greeklishWord.charAt(index + exceptionKey.length()));
                    greeklishWord = greeklishWord.replaceAll(exceptionKey, note1 ? exceptionValues[0] : exceptionValues[1]);
                }
            }
        }
        // ... and then handle base cases
        {
            final Set<String> exceptionKeys = exceptionsNotes_1_2.keySet();
            for(final String exceptionKey : exceptionKeys) {
                final String [] exceptionValues = exceptionsNotes_1_2.get(exceptionKey);
                assert exceptionValues != null;
                int index;
                while((index = greeklishWord.indexOf(exceptionKey)) != -1) {
                    final boolean endOfWord = index == greeklishWord.length() - exceptionKey.length() - 1;
                    final boolean note1 = !endOfWord && checkNote1(greeklishWord.charAt(index + exceptionKey.length()));
                    greeklishWord = greeklishWord.replaceAll(exceptionKey, note1 ? exceptionValues[0] : exceptionValues[1]);
                }
            }
        }
        // next handle exceptional cases - with accents ...
        if(withAccent) {
            final Set<String> exceptionKeys = exceptionsWithAccents.keySet();
            for(final String exceptionKey : exceptionKeys) {
                final String exceptionValue = exceptionsWithAccents.get(exceptionKey);
                assert exceptionValue != null;
                greeklishWord = greeklishWord.replaceAll(exceptionKey, exceptionValue);
            }
        }
        // ... and then handle base cases
        {
            final Set<String> exceptionKeys = exceptions.keySet();
            for(final String exceptionKey : exceptionKeys) {
                final String exceptionValue = exceptions.get(exceptionKey);
                assert exceptionValue != null;
                greeklishWord = greeklishWord.replaceAll(exceptionKey, exceptionValue);
            }
        }
        // next handle special letters if all caps
        final boolean isAllCaps = isGreekWordInAllCaps(greekWord);
        if(isAllCaps) {
            final Set<String> exceptionAllCapsKeys = exceptionsAllCaps.keySet();
            for(final String exceptionKey : exceptionAllCapsKeys) {
                final String exceptionValue = exceptionsAllCaps.get(exceptionKey);
                assert exceptionValue != null;
                greeklishWord = greeklishWord.replaceAll(exceptionKey, exceptionValue);
            }
        }
        // finally handle all other letters - with accents ...
        if(withAccent) {
            final Set<String> mappingKeys = mappingWithAccents.keySet();
            for(final String mappingKey : mappingKeys) {
                final String mappingValue = mappingWithAccents.get(mappingKey);
                assert mappingValue != null;
                greeklishWord = greeklishWord.replaceAll(mappingKey, mappingValue);
            }
        }
        // ... and then all base cases
        {
            final Set<String> mappingKeys = mappingBase.keySet();
            for(final String mappingKey : mappingKeys) {
                final String mappingValue = mappingBase.get(mappingKey);
                assert mappingValue != null;
                greeklishWord = greeklishWord.replaceAll(mappingKey, mappingValue);
            }
        }

        return greeklishWord;
    }

    /**
     * Checks if a given Greek word is in all-capital letters.
     *
     * @param greekWord must be a Greek word
     * @return true if and only if a given Greek word is in all-capital letters
     */
    public static boolean isGreekWordInAllCaps(final String greekWord) {
        final char [] characters = greekWord.toCharArray();
        for(final char c : characters) {
            if(ALL_GREEK_LOWERCASE_LETTERS.indexOf(c) != -1) return false;
        }
        return true;
    }

    private static final Map<Character,Character> ACCENTS_TO_PLAIN_GREEK_LOWERCASE = new HashMap<>();
    static {
        ACCENTS_TO_PLAIN_GREEK_LOWERCASE.put('ά', 'α');
        ACCENTS_TO_PLAIN_GREEK_LOWERCASE.put('έ', 'ε');
        ACCENTS_TO_PLAIN_GREEK_LOWERCASE.put('ή', 'η');
        ACCENTS_TO_PLAIN_GREEK_LOWERCASE.put('ί', 'ι');
        ACCENTS_TO_PLAIN_GREEK_LOWERCASE.put('ϊ', 'ι');
        ACCENTS_TO_PLAIN_GREEK_LOWERCASE.put('ΐ', 'ι');
        ACCENTS_TO_PLAIN_GREEK_LOWERCASE.put('ό', 'ο');
        ACCENTS_TO_PLAIN_GREEK_LOWERCASE.put('ύ', 'υ');
        ACCENTS_TO_PLAIN_GREEK_LOWERCASE.put('ϋ', 'υ');
        ACCENTS_TO_PLAIN_GREEK_LOWERCASE.put('ΰ', 'υ');
        ACCENTS_TO_PLAIN_GREEK_LOWERCASE.put('ώ', 'ω');
    }

    private static final Map<Character,Character> ACCENTS_TO_PLAIN_GREEK_UPPERCASE = new HashMap<>();
    static {
        ACCENTS_TO_PLAIN_GREEK_UPPERCASE.put('Ά', 'Α');
        ACCENTS_TO_PLAIN_GREEK_UPPERCASE.put('Έ', 'Ε');
        ACCENTS_TO_PLAIN_GREEK_UPPERCASE.put('Ή', 'Η');
        ACCENTS_TO_PLAIN_GREEK_UPPERCASE.put('Ί', 'Ι');
        ACCENTS_TO_PLAIN_GREEK_UPPERCASE.put('Ϊ', 'Ι');
        ACCENTS_TO_PLAIN_GREEK_UPPERCASE.put('Ό', 'Ο');
        ACCENTS_TO_PLAIN_GREEK_UPPERCASE.put('Ύ', 'Υ');
        ACCENTS_TO_PLAIN_GREEK_UPPERCASE.put('Ϋ', 'Υ');
        ACCENTS_TO_PLAIN_GREEK_UPPERCASE.put('Ώ', 'Ω');
    }

    private static final Map<Character,Character> ACCENTS_TO_PLAIN_GREEK = new HashMap<>();
    static {
        ACCENTS_TO_PLAIN_GREEK.putAll(ACCENTS_TO_PLAIN_GREEK_LOWERCASE);
        ACCENTS_TO_PLAIN_GREEK.putAll(ACCENTS_TO_PLAIN_GREEK_UPPERCASE);
    }

    /**
     * Converts a given lower-case text in Greek, into the equivalent, simplified version where accents are removed.
     * For example, "κόσμος" is converted to "κοσμος".
     *
     * This can be useful when you are searching in text.
     *
     * @param greekLowercaseTextWithAccents the lower-case text in Greek, which includes accents
     * @return the equivalent text in Greek, without accents
     */
    public static String removeAccentsLowercase(final String greekLowercaseTextWithAccents) {
        final StringBuilder greekLowercaseTextWithoutAccents = new StringBuilder();
        for(final char c : greekLowercaseTextWithAccents.toCharArray()) {
            final Character cWithoutAccent = ACCENTS_TO_PLAIN_GREEK_LOWERCASE.get(c);
            greekLowercaseTextWithoutAccents.append(cWithoutAccent == null ? c : cWithoutAccent);
        }
        return greekLowercaseTextWithoutAccents.toString();
    }

    /**
     * Converts a given upper-case text in Greek, into the equivalent, simplified version where accents are removed.
     * For example, "ΈΝΑΣ ΚΌΣΜΟΣ" is converted to "ΕΝΑΣ ΚΟΣΜΟΣ".
     *
     * This can be useful when you are searching in text.
     *
     * @param greekUppercaseTextWithAccents the upper-case text in Greek, which includes accents
     * @return the equivalent text in Greek, without accents
     */
    public static String removeAccentsUppercase(final String greekUppercaseTextWithAccents) {
        final StringBuilder greekUppercaseTextWithoutAccents = new StringBuilder();
        for(final char c : greekUppercaseTextWithAccents.toCharArray()) {
            final Character cWithoutAccent = ACCENTS_TO_PLAIN_GREEK_UPPERCASE.get(c);
            greekUppercaseTextWithoutAccents.append(cWithoutAccent == null ? c : cWithoutAccent);
        }
        return greekUppercaseTextWithoutAccents.toString();
    }

    /**
     * Converts a given any-case text in Greek, into the equivalent, simplified version where accents are removed.
     * For example, "Ένας Κόσμος" is converted to "Ενας Κοσμος".
     *
     * This can be useful when you are searching in text.
     *
     * @param greekTextWithAccents the any-case text in Greek, which includes accents
     * @return the equivalent text in Greek, without accents
     */
    public static String removeAccents(final String greekTextWithAccents) {
        final StringBuilder greekTextWithoutAccents = new StringBuilder();
        for(final char c : greekTextWithAccents.toCharArray()) {
            final Character cWithoutAccent = ACCENTS_TO_PLAIN_GREEK.get(c);
            greekTextWithoutAccents.append(cWithoutAccent == null ? c : cWithoutAccent);
        }
        return greekTextWithoutAccents.toString();
    }

    /**
     * Simply checks if the given text contains at least one character from the Greek character set.
     *
     * @param text the text to be checked
     * @return true if and only if the given text contains at least one character from the Greek character set
     */
    public static boolean containsGreek(final String text) {
        for(final char c : text.toCharArray()) {
            if(ALL_GREEK_LETTERS.indexOf(c) > -1) return true;
        }
        return false;
    }
}