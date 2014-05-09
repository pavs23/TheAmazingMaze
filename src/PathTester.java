import java.util.ArrayList;
public class PathTester {
    public static void main (String[] args) {
        Maze newMaze = new Maze(5,5);

        int mazeArray[][] = newMaze.getMazeArray();
        for (int i = 0; i < mazeArray[0].length; i++) {
            for (int j = 0; j < mazeArray.length; j++) {
                System.out.print(mazeArray[j][i] + " ");
            }
            System.out.println("");
        }
        
        ArrayList<Coordinate> path = newMaze.getHint(new Coordinate(0,1));
        for (Coordinate each : path) {
            System.out.println (each.getX() + ", " + each.getY());
        }

        /*
        MazeGenerator newMaze = new MazeGenerator(5,5);
        
        int mazeArray[][] = newMaze.generateMazeArray();
        for (int i = 0; i < mazeArray.length; i++) {
            for (int j = 0; j < mazeArray[0].length; j++) {
                System.out.print(mazeArray[i][j] + " ");
            }
            System.out.println("");
        }
        
        Coordinate start = new Coordinate(1, 1);
        Coordinate finish = new Coordinate(7, 9);
        ArrayList<Coordinate> path = newMaze.findPath(start, finish);
        for (Coordinate each : path) {
            System.out.println (each.getX() + ", " + each.getY());
        }
        */
        
    }
}
