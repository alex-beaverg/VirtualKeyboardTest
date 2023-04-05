package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class MapCreator {
    private final Logger logger = LogManager.getRootLogger();

    public HashMap<Character, Character> createEnglishShiftMap() {
        logger.info("Was created english Shift Map");
        return new HashMap<>() {{
            put('~', '`'); put('!', '1'); put('@', '2'); put('#', '3'); put('$', '4'); put('%', '5'); put('^', '6');
            put('&', '7'); put('*', '8'); put('(', '9'); put(')', '0'); put('_', '-'); put('+', '='); put('{', '[');
            put('}', ']'); put(':', ';'); put('<', ','); put('>', '.'); put('?', '/'); put('Q', 'q'); put('W', 'w');
            put('E', 'e'); put('R', 'r'); put('T', 't'); put('Y', 'y'); put('U', 'u'); put('I', 'i'); put('O', 'o');
            put('P', 'p'); put('A', 'a'); put('S', 's'); put('D', 'd'); put('F', 'f'); put('G', 'g'); put('H', 'h');
            put('J', 'j'); put('K', 'k'); put('L', 'l'); put('Z', 'z'); put('X', 'x'); put('C', 'c'); put('V', 'v');
            put('B', 'b'); put('N', 'n'); put('M', 'm'); put('"', '\''); put('|', '\\');
        }};
    }

    public HashMap<Character, Character> createRussianShiftMap() {
        logger.info("Was created russian Shift Map");
        return new HashMap<>() {{
            put('Ё', 'ё'); put('Й', 'й'); put('Ц', 'ц'); put('У', 'у'); put('К', 'к'); put('Е', 'е'); put('Н', 'н');
            put('Г', 'г'); put('Ш', 'ш'); put('Щ', 'щ'); put('З', 'з'); put('Х', 'х'); put('Ъ', 'ъ'); put('Ф', 'ф');
            put('Ы', 'ы'); put('В', 'в'); put('А', 'а'); put('П', 'п'); put('Р', 'р'); put('О', 'о'); put('Л', 'л');
            put('Д', 'д'); put('Ж', 'ж'); put('Э', 'э'); put('Я', 'я'); put('Ч', 'ч'); put('С', 'с'); put('М', 'м');
            put('И', 'и'); put('Т', 'т'); put('Ь', 'ь'); put('Б', 'б'); put('Ю', 'ю');
        }};
    }

    public HashMap<Character, Character> createEnRuMap() {
        logger.info("Was created EN/RU Map");
        return new HashMap<>() {{
            put('ё', '`'); put('й', 'q'); put('ц', 'w'); put('у', 'e'); put('к', 'r'); put('е', 't'); put('н', 'y');
            put('г', 'u'); put('ш', 'i'); put('щ', 'o'); put('з', 'p'); put('х', '['); put('ъ', ']'); put('ф', 'a');
            put('ы', 's'); put('в', 'd'); put('а', 'f'); put('п', 'g'); put('р', 'h'); put('о', 'j'); put('л', 'k');
            put('д', 'l'); put('ж', ';'); put('э', '\''); put('я', 'z'); put('ч', 'x'); put('с', 'c'); put('м', 'v');
            put('и', 'b'); put('т', 'n'); put('ь', 'm'); put('б', ','); put('ю', '.');
        }};
    }
}
