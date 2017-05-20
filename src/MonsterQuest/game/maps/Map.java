package MonsterQuest.game.maps;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.ValidityException;


/**
 * This is to be inherited by all classes that do maps : TODO
 * 
 * @author Zyun
 */
public class Map {

    /**
     *
     */
    public Map() {
        //Open map file
        File map = new File(System.getProperty("user.dir") + "/data/maps/map0x0000.xml");
        Builder buildMap = new Builder();
        
        try {
            Document docMap = buildMap.build(map);
            Element root = docMap.getRootElement();
            Elements childElements = root.getChildElements();
           for (int i = 0; i < childElements.size(); i++){
                Element maps = childElements.get(i);
                Elements layers = maps.getChildElements()
           }
        } catch (ParsingException pe) {
            
        } catch (IOException ex) {
            
        }
    }
}
