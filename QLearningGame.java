import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QLearningGame extends JPanel {
    private static final int Grid_Size = 5;
    private static final int Cell_Size = 100;
    private static final int Num_Actions = 4;
    private static final double Learning_Rate = 0.1;
    private static final double Discount_Factor = 0.9;
    private static final double Epsilon = 0.1;
    private static final int Training_Episodes = 5000;
    
    private int[][][] QTable = new int[Grid_Size][Grid_Size][Num_Actions];
    private int agentX = 0, agentY = 0;
    private final int goalX = 4, goalY = 4;
    private final int[][] obstacles = {{1, 1}, {2, 2}, {3, 3}};
    private final Random random = new Random();
    private final List<Point> path = new ArrayList<>();

    public QLearningGame() {
        // Pre-train the agent before visualization
        trainAgent();
        
        // Timer to continuously move the agent at a faster speed
        Timer timer = new Timer(600, e -> updateGame());
        timer.start();
    }
    
    private void trainAgent() {
        for (int episode = 0; episode < Training_Episodes; episode++) {
            int x = 0, y = 0;
            while (x != goalX || y != goalY) {
                int action = chooseAction(x, y);
                int newX = x, newY = y;
                
                switch (action) {
                    case 0:
                        newX = Math.max(0, x - 1);
                        break;
                    case 1:
                        newX = Math.min(Grid_Size - 1, x + 1);
                        break;
                    case 2:
                        newY = Math.max(0, y - 1);
                        break;
                    case 3:
                        newY = Math.min(Grid_Size - 1, y + 1);
                        break;
                }
                
                int reward = -1;
                for (int[] obstacle : obstacles) {
                    if (newX == obstacle[0] && newY == obstacle[1]) {
                        reward = -5;
                    }
                }
                if (newX == goalX && newY == goalY) {
                    reward = 10;
                }
                
                int bestFutureQ = Math.max(QTable[newX][newY][0], Math.max(QTable[newX][newY][1],
                        Math.max(QTable[newX][newY][2], QTable[newX][newY][3])));
                
                QTable[x][y][action] += Learning_Rate * (reward + Discount_Factor * bestFutureQ - QTable[x][y][action]);
                
                x = newX;
                y = newY;
            }
        }
    }

    private int chooseAction(int x, int y) {
        if (random.nextDouble() < Epsilon) {
            return random.nextInt(Num_Actions); // Exploration
        } else {
            int bestAction = 0;
            int bestValue = QTable[x][y][0];
            for (int i = 1; i < Num_Actions; i++) {
                if (QTable[x][y][i] > bestValue) {
                    bestValue = QTable[x][y][i];
                    bestAction = i;
                }
            }
            return bestAction; // Exploitation
        }
    }

    private void updateGame() {
        int action = chooseAction(agentX, agentY);
        int newX = agentX, newY = agentY;
        
        switch (action) {
            case 0:
                newX = Math.max(0, agentX - 1);
                break;
            case 1:
                newX = Math.min(Grid_Size - 1, agentX + 1);
                break;
            case 2:
                newY = Math.max(0, agentY - 1);
                break;
            case 3:
                newY = Math.min(Grid_Size - 1, agentY + 1);
                break;
        }
        
        agentX = newX;
        agentY = newY;
        path.add(new Point(agentY, agentX)); // Store agent's path
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw grid background with grid lines
        g.setColor(new Color(220, 220, 220)); // Light grey
        g.fillRect(0, 0, Grid_Size * Cell_Size, Grid_Size * Cell_Size);
        g.setColor(Color.BLACK);
        for (int i = 0; i <= Grid_Size; i++) {
            g.drawLine(i * Cell_Size, 0, i * Cell_Size, Grid_Size * Cell_Size);
            g.drawLine(0, i * Cell_Size, Grid_Size * Cell_Size, i * Cell_Size);
        }
        
        // Draw obstacles in dark red
        g.setColor(new Color(139, 0, 0));
        for (int[] obstacle : obstacles) {
            g.fillRect(obstacle[1] * Cell_Size, obstacle[0] * Cell_Size, Cell_Size, Cell_Size);
        }
        
        // Draw goal in dark green
        g.setColor(new Color(0, 100, 0));
        g.fillRect(goalY * Cell_Size, goalX * Cell_Size, Cell_Size, Cell_Size);
        
        // Draw path
        g.setColor(new Color(255, 215, 0)); // Yellow for path
        for (Point p : path) {
            g.fillOval(p.x * Cell_Size + 40, p.y * Cell_Size + 40, 20, 20);
        }
        
        // Draw agent as a stickman
        g.setColor(Color.BLUE);
        int centerX = agentY * Cell_Size + Cell_Size / 2;
        int centerY = agentX * Cell_Size + Cell_Size / 2;
        g.drawOval(centerX - 10, centerY - 20, 20, 20); // Head
        g.drawLine(centerX, centerY, centerX, centerY + 20); // Body
        g.drawLine(centerX, centerY + 20, centerX - 10, centerY + 40); // Left Leg
        g.drawLine(centerX, centerY + 20, centerX + 10, centerY + 40); // Right Leg
        g.drawLine(centerX, centerY + 10, centerX - 10, centerY); // Left Arm
        g.drawLine(centerX, centerY + 10, centerX + 10, centerY); // Right Arm
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Q-learning Agent Game");
        QLearningGame game = new QLearningGame();
        frame.add(game);
        frame.setSize(Grid_Size * Cell_Size, Grid_Size * Cell_Size);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
