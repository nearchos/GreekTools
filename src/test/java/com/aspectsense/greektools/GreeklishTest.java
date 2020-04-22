package com.aspectsense.greektools;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Testing various functionalities of the library.
 *
 * @author Nearchos Paspallis
 * Created: 29-Mar-20
 */
public class GreeklishTest {

    private static final String [] GREEK_WORDS = {
            "Νέαρχος",
            "ΝΈΑΡΧΟΣ",
            "Ουρανός",
            "ΟΥΡΑΝΌΣ",
            "Συλλαβή",
            "ΣΥΛΛΑΒΉ",
            "Διαλυτικά",
            "ΔΙΑΛΥΤΙΚΆ",
            "Άυλος",
            "ΆΥΛΟΣ",
            "Αυλός",
            "ΑΥΛΌΣ",
            "Φαΐ",
            "ΦΑΪ",
            "ΞΎΠΝΗΜΑ",
            "Ξύπνημα",
            "ΔΙΎΛΙΣΗ",
            "Διύλιση",
            "ΑΓΓΑΡΕΊΑ",
            "Αγγαρεία",
            "ΜΠΙΦΤΈΚΙΑ",
            "Μπιφτέκια",
            "ΕΥΓΝΩΜΟΣΎΝΗ",
            "Ευγνωμοσύνη",
            "ΕΥΘΕΊΑ",
            "Ευθεία",
            "Αυτή είναι μια δοκιμαστική πρόταση. Είναι καλή;",
    };

    private static final String [] EXPECTED_GREEKLISH_WORDS_SIMPLIFIED = {
            "Nearchos",
            "NEARCHOS",
            "Ouranos",
            "OURANOS",
            "Syllavi",
            "SYLLAVI",
            "Dialytika",
            "DIALYTIKA",
            "Aylos",
            "AYLOS",
            "Avlos",
            "AVLOS",
            "Fai",
            "FAI",
            "XYPNIMA",
            "Xypnima",
            "DIYLISI",
            "Diylisi",
            "ANGAREIA",
            "Angareia",
            "BIFTEKIA",
            "Biftekia",
            "EVGNOMOSYNI",
            "Evgnomosyni",
            "EFTHEIA",
            "Eftheia",
            "Afti einai mia dokimastiki protasi. Einai kali?",
    };

    private static final String [] EXPECTED_GREEKLISH_WORDS_WITH_ACCENTS = {
            "Néarchos",
            "NÉARCHOS",
            "Ouranós",
            "OURANÓS",
            "Syllavī́",
            "SYLLAVĪ́",
            "Dialytiká",
            "DIALYTIKÁ",
            "Áylos",
            "ÁYLOS",
            "Avlós",
            "AVLÓS",
            "Faḯ",
            "FAÏ",
            "XÝPNĪMA",
            "Xýpnīma",
            "DIÝLISĪ",
            "Diýlisī",
            "ANGAREÍA",
            "Angareía",
            "BIFTÉKIA",
            "Biftékia",
            "EVGNŌMOSÝNĪ",
            "Evgnōmosýnī",
            "EFTHEÍA",
            "Eftheía",
            "Aftī́ eínai mia dokimastikī́ prótasī. Eínai kalī́?",
    };

    @Test
    public void testWords() {
        boolean succeed = true;
        for(int i = 0; i < GREEK_WORDS.length; i++) {
            final String greekWord = GREEK_WORDS[i];
            final String greeklishWord = Greeklish.toGreeklish(greekWord);
            final boolean match = greeklishWord.equals(EXPECTED_GREEKLISH_WORDS_SIMPLIFIED[i]);
            System.out.println(greekWord + " -> " + greeklishWord + " [" + EXPECTED_GREEKLISH_WORDS_SIMPLIFIED[i] + "] " + (match ? "✓" : "✗"));
            succeed &= match;
        }
        assert succeed;
    }

    @Test
    public void testWordsWithAccents() {
        boolean succeed = true;
        for(int i = 0; i < GREEK_WORDS.length; i++) {
            final String greekWord = GREEK_WORDS[i];
            final String greeklishWord = Greeklish.toGreeklish(greekWord, true);
            final boolean match = greeklishWord.equals(EXPECTED_GREEKLISH_WORDS_WITH_ACCENTS[i]);
            System.out.println(greekWord + " -> " + greeklishWord + " [" + EXPECTED_GREEKLISH_WORDS_WITH_ACCENTS[i] + "] " + (match ? "✓" : "✗"));
            succeed &= match;
        }
        assert succeed;
    }

    @Test
    public void testRemoveLowercaseAccents() {
        final Map<String,String> testTexts = new HashMap<>();
        testTexts.put("γειά σου κόσμε! ελπίζω να είναι όλα καλά.", "γεια σου κοσμε! ελπιζω να ειναι ολα καλα.");
        testTexts.put("Η διήθιση είναι ένα ασυνήθιστο ρήμα και το ύψιλον ένα ασυνήθιστο γράμμα.", "Η διηθιση ειναι ενα ασυνηθιστο ρημα και το υψιλον ενα ασυνηθιστο γραμμα.");
        testTexts.put("Τα αϊδόνια δεν σε αφήνουνε να κοιμηθείς στις Πλάτρες", "Τα αιδονια δεν σε αφηνουνε να κοιμηθεις στις Πλατρες");
        testTexts.put("Έξω από την πόλη βρίσκονται τα δέντρα", "Έξω απο την πολη βρισκονται τα δεντρα");

        boolean succeed = true;
        for(final String lowercaseTextWithAccents : testTexts.keySet()) {
            final String lowercaseTextWithoutAccents = Greeklish.removeAccentsLowercase(lowercaseTextWithAccents);
            final boolean match = lowercaseTextWithoutAccents.equals(testTexts.get(lowercaseTextWithAccents));
            System.out.println(lowercaseTextWithAccents + " -> " + lowercaseTextWithoutAccents + " [" + testTexts.get(lowercaseTextWithAccents) + "] " + (match ? "✓" : "✗"));
            succeed &= match;
        }
        assert succeed;
    }

    @Test
    public void testRemoveUppercaseAccents() {
        final Map<String,String> testTexts = new HashMap<>();
        testTexts.put("ΓΕΙΆ ΣΟΥ ΚΌΣΜΕ! ΕΛΠΊΖΩ ΝΑ ΕΊΝΑΙ ΌΛΑ ΚΑΛΆ.", "ΓΕΙΑ ΣΟΥ ΚΟΣΜΕ! ΕΛΠΙΖΩ ΝΑ ΕΙΝΑΙ ΟΛΑ ΚΑΛΑ.");
        testTexts.put("Η ΔΙΉΘΙΣΗ ΕΊΝΑΙ ΈΝΑ ΑΣΥΝΉΘΙΣΤΟ ΡΉΜΑ ΚΑΙ ΤΟ ΎΨΙΛΟΝ ΈΝΑ ΑΣΥΝΉΘΙΣΤΟ ΓΡΆΜΜΑ.", "Η ΔΙΗΘΙΣΗ ΕΙΝΑΙ ΕΝΑ ΑΣΥΝΗΘΙΣΤΟ ΡΗΜΑ ΚΑΙ ΤΟ ΥΨΙΛΟΝ ΕΝΑ ΑΣΥΝΗΘΙΣΤΟ ΓΡΑΜΜΑ.");
        testTexts.put("ΤΑ ΑΪΔΌΝΙΑ ΔΕΝ ΣΕ ΑΦΉΝΟΥΝΕ ΝΑ ΚΟΙΜΗΘΕΊΣ ΣΤΙΣ ΠΛΆΤΡΕΣ", "ΤΑ ΑΙΔΟΝΙΑ ΔΕΝ ΣΕ ΑΦΗΝΟΥΝΕ ΝΑ ΚΟΙΜΗΘΕΙΣ ΣΤΙΣ ΠΛΑΤΡΕΣ");
        testTexts.put("ΈΞΩ ΑΠΌ ΤΗΝ ΠΌΛΗ ΒΡΊΣΚΟΝΤΑΙ ΤΑ ΔΈΝΤΡΑ", "ΕΞΩ ΑΠΟ ΤΗΝ ΠΟΛΗ ΒΡΙΣΚΟΝΤΑΙ ΤΑ ΔΕΝΤΡΑ");

        boolean succeed = true;
        for(final String uppercaseTextWithAccents : testTexts.keySet()) {
            final String uppercaseTextWithoutAccents = Greeklish.removeAccentsUppercase(uppercaseTextWithAccents);
            final boolean match = uppercaseTextWithoutAccents.equals(testTexts.get(uppercaseTextWithAccents));
            System.out.println(uppercaseTextWithAccents + " -> " + uppercaseTextWithoutAccents + " [" + testTexts.get(uppercaseTextWithAccents) + "] " + (match ? "✓" : "✗"));
            succeed &= match;
        }
        assert succeed;
    }

    @Test
    public void testRemoveAccents() {
        final Map<String,String> testTexts = new HashMap<>();
        testTexts.put("ΓΕΙΆ ΣΟΥ ΚΌΣΜΕ! ΕΛΠΊΖΩ ΝΑ ΕΊΝΑΙ ΌΛΑ ΚΑΛΆ.", "ΓΕΙΑ ΣΟΥ ΚΟΣΜΕ! ΕΛΠΙΖΩ ΝΑ ΕΙΝΑΙ ΟΛΑ ΚΑΛΑ.");
        testTexts.put("Η ΔΙΉΘΙΣΗ ΕΊΝΑΙ ΈΝΑ ΑΣΥΝΉΘΙΣΤΟ ΡΉΜΑ ΚΑΙ ΤΟ ΎΨΙΛΟΝ ΈΝΑ ΑΣΥΝΉΘΙΣΤΟ ΓΡΆΜΜΑ.", "Η ΔΙΗΘΙΣΗ ΕΙΝΑΙ ΕΝΑ ΑΣΥΝΗΘΙΣΤΟ ΡΗΜΑ ΚΑΙ ΤΟ ΥΨΙΛΟΝ ΕΝΑ ΑΣΥΝΗΘΙΣΤΟ ΓΡΑΜΜΑ.");
        testTexts.put("ΤΑ ΑΪΔΌΝΙΑ ΔΕΝ ΣΕ ΑΦΉΝΟΥΝΕ ΝΑ ΚΟΙΜΗΘΕΊΣ ΣΤΙΣ ΠΛΆΤΡΕΣ", "ΤΑ ΑΙΔΟΝΙΑ ΔΕΝ ΣΕ ΑΦΗΝΟΥΝΕ ΝΑ ΚΟΙΜΗΘΕΙΣ ΣΤΙΣ ΠΛΑΤΡΕΣ");
        testTexts.put("ΈΞΩ ΑΠΌ ΤΗΝ ΠΌΛΗ ΒΡΊΣΚΟΝΤΑΙ ΤΑ ΔΈΝΤΡΑ", "ΕΞΩ ΑΠΟ ΤΗΝ ΠΟΛΗ ΒΡΙΣΚΟΝΤΑΙ ΤΑ ΔΕΝΤΡΑ");
        testTexts.put("γειά σου κόσμε! ελπίζω να είναι όλα καλά.", "γεια σου κοσμε! ελπιζω να ειναι ολα καλα.");
        testTexts.put("Η διήθιση είναι ένα ασυνήθιστο ρήμα και το ύψιλον ένα ασυνήθιστο γράμμα.", "Η διηθιση ειναι ενα ασυνηθιστο ρημα και το υψιλον ενα ασυνηθιστο γραμμα.");
        testTexts.put("Τα αϊδόνια δεν σε αφήνουνε να κοιμηθείς στις Πλάτρες", "Τα αιδονια δεν σε αφηνουνε να κοιμηθεις στις Πλατρες");
        testTexts.put("Έξω από την πόλη βρίσκονται τα δέντρα", "Εξω απο την πολη βρισκονται τα δεντρα");

        boolean succeed = true;
        for(final String textWithAccents : testTexts.keySet()) {
            final String textWithoutAccents = Greeklish.removeAccents(textWithAccents);
            final boolean match = textWithoutAccents.equals(testTexts.get(textWithAccents));
            System.out.println(textWithAccents + " -> " + textWithoutAccents + " [" + testTexts.get(textWithAccents) + "] " + (match ? "✓" : "✗"));
            succeed &= match;
        }
        assert succeed;
    }

    @Test
    public void testContainsGreek() {
        final Map<String,Boolean> testTexts = new HashMap<>();
        testTexts.put("γειά σου κόσμε! ελπίζω να είναι όλα καλά.", true);
        testTexts.put("ΈΞΩ ΑΠΌ ΤΗΝ ΠΌΛΗ ΒΡΊΣΚΟΝΤΑΙ ΤΑ ΔΈΝΤΡΑ", true);
        testTexts.put("Mixed sentence with some English και μερικά Ελληνικά", true);
        testTexts.put("Purely non-Greek sentence", false);
        testTexts.put("Something", false);
        testTexts.put("", false); // empty text

        boolean succeed = true;
        for(final String text : testTexts.keySet()) {
            final boolean containsGreek = Greeklish.containsGreek(text);
            final Boolean expected = testTexts.get(text);
            final boolean match = expected != null && expected == containsGreek;
            System.out.println(text + " -> " + containsGreek + " [" + expected + "] " + (match ? "✓" : "✗"));
            succeed &= match;
        }
        assert succeed;
    }
}