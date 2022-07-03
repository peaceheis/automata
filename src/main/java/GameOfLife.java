import java.awt.image.BufferedImage;
import java.net.MalformedURLException;

public class GameOfLife implements Simulation {
    static final int PIXEL_SIZE = 4;
    public boolean[][] pixels;
    private final int width;
    private final int height;
    private final int tickLength;

    public GameOfLife(boolean[][] pixels, int width, int height, int tickLength) {
        this.pixels = pixels;
        this.width = width;
        this.height = height;
        this.tickLength = tickLength;
    }

    @Override
    public BufferedImage tick() {
        boolean[][] pixelCopy = this.pixels.clone(); // a copy is needed to make sure we don't tamper with the live pixel array - all changes should be applied at once in the live version.
        for(int y = 0; y < this.height; y++) {
            for(int x = 0; x < this.width; x++) {
                // fill a square with top left corner at (x,y) - the length size is PIXEL_SIZE. this is so pixels show up more.
                int count = getLiveNeighborCount(x, y);
                if(count == 3) {
                    pixelCopy[y][x] = true; // cells with 3 neighbors stay living or come alive.
                } else if(count == 2) {
                    if (this.pixels[y][x]) {
                        pixelCopy[y][x] = true; // living cells with 2 neighbors live.
                    }
                } else {
                    pixelCopy[y][x] = false; // otherwise, the cell dies / stays dead.
                }
            }
        }

        // when done, update pixel data.
        this.pixels = pixelCopy.clone();
        return Simulation.scaleBools(pixelCopy, PIXEL_SIZE);
    }

    int getLiveNeighborCount(int x, int y) {
        int count = 0;
        count += booleanToInt(this.pixels[wrap(y-1, this.height)][wrap(x-1, this.width)]);
        count += booleanToInt(this.pixels[wrap(y-1, this.height)][x]);
        count += booleanToInt(this.pixels[wrap(y-1, this.height)][wrap(x+1, this.width)]);
        count += booleanToInt(this.pixels[y][wrap(x-1, this.width)]);
        // skip the actual point
        count += booleanToInt(this.pixels[y][wrap(x+1, this.width)]);
        count += booleanToInt(this.pixels[wrap(y+1, this.height)][wrap(x-1, this.width)]);
        count += booleanToInt(this.pixels[wrap(y+1, this.height)][x]);
        count += booleanToInt(this.pixels[y][wrap(x+1, this.width)]);
        return count;
    }
    
    int wrap(int toWrap, int size) {
        if(toWrap == size) {
            return 0;
        } else if(toWrap < 0 ) {
            return size-1;
        } else {
            return toWrap;
        }
    }

    int booleanToInt(boolean bool) {
        return bool ? 1 : 0; // this is a ternary statement for those who don't know - it reads, if boolean is true, return 1, otherwise return 0.
    }

    public static void makeVideo(String fileName) throws MalformedURLException {


    }

    public static void main() {

    }
}


