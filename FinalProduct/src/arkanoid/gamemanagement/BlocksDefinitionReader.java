/**
 * This code was written by Itamar Laredo.
 * Date: 13/9/2020
 * All rights reserved.
 * Contact by email: Laredo93i@gmail.com
 */
package arkanoid.gamemanagement;

import arkanoid.gamepieces.Block;
import arkanoid.gameutilities.BlocksFromSymbolsFactory;
import arkanoid.gameutilities.ColorsParser;
import arkanoid.interfaces.BlockCreator;
import arkanoid.shapes.Rectangle;
import arkanoid.shapes.Point;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;

/**
 * The purpose of the class is to extract the relevant information
 * from the text file and convert it to the properties
 * of the blocks of the game.
 */
public class BlocksDefinitionReader {

    /**
     * This method reading the file and returning the information needed.
     * @param reader reader for the file
     * @return BlocksFromSymbolsFactory Object.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {

        // a BlocksFromSymbolsFactory should have two maps - for spacers and BlockCreators.
        Map<String, Integer> spacerWidths = new Hashtable<>();
        Map<String, BlockCreator> blockCreators = new Hashtable<>();

        ColorsParser cp = new ColorsParser();
        Dictionary<String, String> ddef = new Hashtable<>();

        // Read the content.
        char[] charRead = new char[8 * 1024];
        StringBuilder stringBuilder = new StringBuilder();
        int numRead;

        try {
            while ((numRead = reader.read(charRead, 0, charRead.length)) != -1) {
                stringBuilder.append(charRead, 0, numRead);
            }
        } catch (IOException ioe) {
            System.out.println("Error: IOException caught in BlocksDefinitionReader class");
        }

        // Split to lines and parse line-by-line.
        String[] lines = stringBuilder.toString().split("\n");

        for (String line : lines) {
            if (line.startsWith("default")) {
                String[] words = line.split(" ");
                for (String w : words) {
                    if (w.contains(":")) {
                        String[] split = w.split(":");
                        ddef.put(split[0], split[1]);
                    }
                }
            } else if (line.startsWith("bdef")) {
                String[] words = line.split(" ");
                String symbol = null;
                String fill = ddef.get("stroke");
                String height = ddef.get("height");
                String width = ddef.get("width");
                for (String w : words) {
                    if (w.startsWith("symbol")) {
                        String[] split = w.split(":");
                        symbol = split[1];
                    } else if (w.startsWith("fill")) {
                        String[] split = w.split(":");
                        fill = split[1];
                    } else if (w.startsWith("height")) {
                        String[] split = w.split(":");
                        height = split[1];
                    } else if (w.startsWith("width")) {
                        String[] split = w.split(":");
                        width = split[1];
                    }
                }
                // Converting the data to block creator
                if (symbol != null) {
                    double numWidth = Integer.parseInt(width);
                    double numHeight = Integer.parseInt(height);
                    Color color = null;
                    Image img = null;
                    BlockCreator bc = null;
                    String input = fill.substring(fill.indexOf("(") + 1, fill.indexOf(")"));
                    if (fill.startsWith("color")) {
                        color = cp.colorFromString(input);
                        Color finalColor = color;
                        bc = new BlockCreator() {
                            @Override
                            public Block create(int xpos, int ypos) {
                                Rectangle rect = new Rectangle(new Point(xpos, ypos), numWidth, numHeight);
                                return new Block(rect, finalColor);
                            }
                        };
                    } else {
                        try {
                            img = ImageIO.read(new File(input));
                            Image finalImg = img;
                            bc = new BlockCreator() {
                                @Override
                                public Block create(int xpos, int ypos) {
                                    Rectangle rect = new Rectangle(new Point(xpos, ypos), numWidth, numHeight);
                                    return new Block(rect, finalImg);
                                }
                            };
                        } catch (IOException e) {
                            System.out.println("Error: IOException caught in BlocksDefinitionReader class (2)");
                        }
                    }
                    blockCreators.put(symbol, bc);
                }
            } else if (line.startsWith("sdef")) {
                String[] words = line.split(" ");
                String symbol = null;
                String width = null;
                for (String w : words) {
                    if (w.startsWith("symbol")) {
                        String[] split = w.split(":");
                        symbol = split[1];
                    } else if (w.startsWith("width")) {
                        String[] split = w.split(":");
                        width = split[1];
                    }
                }
                if (symbol != null && width != null) {
                    spacerWidths.put(symbol, 50);
                }
            }
        }
        return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
    }
}
