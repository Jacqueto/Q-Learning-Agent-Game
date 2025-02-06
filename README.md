# Q-Learning Agent Game (Java)

## Overview
This is a **Q-learning AI agent** that learns to navigate a **5x5 grid** using **reinforcement learning**. The agent improves its movements over time by optimizing its path to the goal while avoiding obstacles.

## Features
**Q-learning algorithm** for AI-based pathfinding.  
**Java Swing visualization** – Watch the agent move in real-time.  
**Path tracking** – The agent leaves a trail to show its learning process.  
**Customizable environment** – Modify grid size, obstacles, and learning parameters.  
**Faster execution** – The agent moves smoothly every 300ms.  

## 🛠 Tech Stack
- **Language:** Java
- **Libraries:** Swing (for UI visualization)
- **Algorithm:** Q-learning (Reinforcement Learning)

## 🔧 Installation & Running the Game
1. **Clone this repository:**
   ```bash
   git clone https://github.com/your-username/Q-Learning-Agent-Game.git
   cd Q-Learning-Agent-Game
   ```
2. **Compile the Java code:**
   ```bash
   javac QLearningGame.java
   ```
3. **Run the game:**
   ```bash
   java QLearningGame
   ```

## How It Works
1. **Training Phase:** The agent starts without knowledge and explores the grid while updating its **Q-table*
2. **Reward System:**
   - **+10** for reaching the goal
   - **-5** for hitting an obstacle
   - **-1** for every move to encourage efficiency
3. **Learning Mechanism:** The agent chooses the best action using an **ε-greedy policy**, balancing exploration and exploitation.

## Future Improvements
🔹 Add different difficulty levels (larger grids).  
🔹 Improve the agent's **learning speed**.  
🔹 Implement a **graphical dashboard** to visualize Q-values.  

## Contribute
Want to improve this project? Feel free to **fork this repository** and submit a pull request!

## License
This project is licensed under the **MIT License** – free to use and modify.

## 🔗 Connect With Me
💼 **LinkedIn:** [Jacqueline Chiazor](https://www.linkedin.com/in/Jacquelinechiazor/)  
👨‍💻 **GitHub:** [Jacqueto](https://github.com/Jacqueto)  
📧 **Email:** Jacquechiazor@gmail.om  

🚀 *Happy Coding!* 🎮

