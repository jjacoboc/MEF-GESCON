/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.lucene;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author proyectosbeta
 */
public class StopWords {
    // Objetos de clase.
    /*
     * Aca se incluyen los StopWords de la clase SpanishAnalyzer que trae Apache
     * Lucene con las etiquetas html y demas cosas que no sirven para la busqueda.
     */
    private static String[] s_stopWords = {"mucho", "tuviese", "míos", "seremos", 
        "del", "serán", "vuestros", "hube", "tus", "desde", "habríamos", "les", 
        "está", "estuvierais", "hubieras", "durante", "otras", "mí", "habido", 
        "esté", "estarán", "fuera", "algunas", "tendrías", "estarás", "habréis", 
        "al", "tengan", "quien", "son", "habrían", "tengas", "habrías", "estar", 
        "estas", "sobre", "estaréis", "unos", "nosotros", "tuvieron", "mía", 
        "estuviste", "estarían", "seáis", "habíamos", "todo", "tiene", "estarías", 
        "tuyas", "tenemos", "hubiéramos", "mío", "sus", "se", "algo", "tendrán", 
        "mis", "por", "serías", "sea", "estuviéramos", "ella", "vuestras", "entre", 
        "tendrás", "a", "su", "teniendo", "esas", "fuiste", "tuvieseis", "o", "te", 
        "ti", "habrán", "tendremos", "tuve", "y", "habrás", "hubimos", "fueran", 
        "tu", "fuese", "tendré", "estáis", "sois", "hasta", "suyos", "seré", "fueras", 
        "nosotras", "tuvieran", "tendrían", "tuvieras", "estaban", "han", "habidas", 
        "tuviésemos", "estabas", "has", "fueron", "tenían", "estuve", "hay", "tendrá", 
        "tenías", "él", "el", "estuvo", "en", "habéis", "poco", "es", "estés", 
        "habían", "estén", "tengáis", "habías", "serás", "hubisteis", "otros", 
        "hubiera", "que", "vosotros", "seréis", "era", "suyas", "e", "tuviesen", 
        "también", "hayan", "nuestra", "tuvieses", "hayas", "otra", "antes", 
        "vuestra", "sin", "sí", "fueseis", "nuestro", "esta", "más", "eras", 
        "contra", "otro", "hubiese", "vuestro", "tenidos", "una", "estada", 
        "cuando", "estuvisteis", "todos", "estuvieron", "muchos", "pero", "hemos", 
        "uno", "habidos", "habrá", "ha", "tuvo", "tuvimos", "he", "estuviesen", 
        "habré", "donde", "tú", "tienen", "ya", "fueses", "estemos", "tienes", 
        "tenéis", "como", "esos", "estad", "fuisteis", "habremos", "yo", "nuestros", 
        "seamos", "estaba", "siendo", "seríamos", "fuimos", "estos", "será", "fuesen", 
        "estadas", "tendríais", "hayáis", "tuviéramos", "estuvimos", "somos", 
        "estábamos", "teníais", "hubieses", "estuvieran", "hubo", "un", "estuvieras", 
        "estaremos", "qué", "habíais", "tuya", "hubiésemos", "fuerais", "hubieseis", 
        "nos", "los", "estéis", "seríais", "tuvierais", "tuyo", "estuviésemos", 
        "quienes", "había", "ante", "no", "hubieron", "sido", "nuestras", "estado", 
        "tenida", "tenido", "nada", "estuviera", "esa", "sería", "la", "fue", "ese", 
        "le", "serían", "tened", "habida", "estuvieses", "estuvieseis", "teníamos", 
        "eso", "con", "lo", "vosotras", "tuviera", "fui", "tuviste", "estará", 
        "estaría", "erais", "porque", "hubiste", "estaré", "estuviese", "estamos", 
        "ellas", "me", "eran", "de", "mi", "soy", "las", "este", "cual", "estaríamos", 
        "tenga", "esto", "hubieran", "hayamos", "están", "mías", "estás", "ni", 
        "tendríamos", "tendría", "haya", "tengo", "estabais", "fuésemos", "habríais", 
        "estados", "tenidas", "muy", "éramos", "seas", "eres", "algunos", "tuvisteis", 
        "tanto", "habría", "ellos", "para", "suya", "os", "fuéramos", "tendréis", 
        "hubierais", "hubiesen", "estoy", "tenía", "habiendo", "estaríais", "tengamos", 
        "tuyos", "suyo", "estando", "sean", "sitio.css", "mediacalcd", "www.infomedical.cl",
        "meta", "center", "you", "bottom", "set", "cccccc", "html", "g", "bottom", "by",
        "b.htm", "br", "cajit", "becaus", "boundri", "c", "c.htm", "addressed",
        "address", "b", "all", "border", "action", "includ", "activ", "siti",
        "codeoutsidehtmlislocked", "contenid", "cellpadding", "class", "colspan", 
        "confirm", "boundari", "as", "cajit", "cellspacing", "color", "afaramacolo.asp", 
        "_includ", "activ", "_siti",  "align", "a.htm", "84", "becaus", "body", 
        "7px", "acci", "896", "charset", "3344413", "_blank", "2390", "2315203", 
        "804", "_imag", "0", "11", "alt", "100", "10px", "8859", "562", "3331719", 
        "3", "1", "all.htm", "666666", "1999", "1px", "11px", "1.1", "6", 
        "200.63.97.72", "137", "content", "http", "htm", "file", "from", "head",
        "head", "href", "fax", "font", "form2", "g.htm", "h.htm", "hr", "doctyp",
        "cfe6ff", "div", "d", "domain", "f", "fon", "doctitl", "form", "fals",
        "function", "e.htm", "h", "height", "home", "d.htm", "css", "cajit",
        "22", "bgcolor", "cum", "contenid", "2", "boundari", "default.asp",
        "eacut", "editor", "dtd", "f.htm", "siti", "activ", "equiv", "for",
        "email", "becaus", "includ", "i" 
    
    };
    
    private HashSet<String> stopWords;

    // Constructor predeterminado.
    public StopWords() {
        stopWords = new HashSet<String>(s_stopWords.length);

        for(String stopWord : s_stopWords) {
            stopWords.add(stopWord);
        }
    } // Fin del constructor por predeterminado.
    
    /*
     * Metodo publico para saber si la palabra es un stopWord.
     */
    public boolean isStopWord(String word) {
        return stopWords.contains(word.toLowerCase());
    } // Fin del metodo publico isStopWord.
 
    /*
     * Metodo publico que devuelve todos los stopWords.
     */
    public Set<?> getS_stopWords(){
        return stopWords;
    } // Fin del metodo publico getS_stopWords.
} // Fin de la clase StopWords.