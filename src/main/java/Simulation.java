import java.awt.image.BufferedImage;

public interface Simulation {
    BufferedImage tick();

    static BufferedImage scaleBools(boolean[][] toScale, int pixelSize) {
        BufferedImage img = new BufferedImage(toScale[0].length*pixelSize, toScale.length*pixelSize, BufferedImage.TYPE_INT_RGB);
        // set pixel data
        for(int y = 0; y < toScale.length; y++) {
            for(int x = 0; x < toScale[y].length; x++) {
                for (int yScaler = 0; yScaler < pixelSize; yScaler++) {
                    for (int xScaler = 0; xScaler < pixelSize; xScaler++) {
                        if (toScale[y][x]) {
                            img.setRGB(x * pixelSize + xScaler, y * pixelSize + yScaler, 0xFFFFFF);
                        } else {
                            img.setRGB(x * pixelSize + xScaler, y * pixelSize + yScaler, 0x000000);
                        }
                    }
                }
            }
        }
        return img;
    }
}
