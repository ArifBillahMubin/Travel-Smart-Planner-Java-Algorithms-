# Travel Smart Planner (Java Algorithms)

Travel Smart Planner is a Java-based console application that helps users efficiently plan their travel routes across major cities in Bangladesh. It offers features like shortest route finding, budget-friendly trip planning, and multi-city travel optimization.

## Features
- **Find Shortest Route**: Uses Dijkstra's Algorithm to find the shortest path between two cities.
- **Plan Trip Within Budget**: Implements a greedy algorithm to suggest travel options within a given budget.
- **Multi-City Travel Plan**: Uses a divide-and-conquer approach to suggest an efficient multi-city travel plan.

## Cities Covered
1. Rajshahi
2. Sylhet
3. Chittagong
4. Barishal
5. Khulna
6. Dhaka

## Installation & Usage
### Prerequisites
- Java Development Kit (JDK) installed

### Run the Project
1. Clone the repository:
   ```sh
   git clone https://github.com/ArifBillahMubin/TravelSmartPlanner.git
   ```
2. Navigate to the project folder:
   ```sh
   cd TravelSmartPlanner
   ```
3. Compile and run the Java program:
   ```sh
   javac TravelSmartPlanner2.java
   java TravelSmartPlanner2
   ```

## How to Use
1. Run the program and select an option from the menu:
   - **Find Shortest Route**: Enter the source and destination city numbers.
   - **Plan Trip Within Budget**: Enter the starting city and budget.
   - **Multi-City Travel Plan**: Enter the cities you wish to visit.
2. Follow on-screen instructions to get results.

## Example
```
=== Travel Smart Planner ===
1. Find Shortest Route
2. Plan Trip Within Budget
3. Multi-City Travel Plan
4. Exit
Enter your choice: 1
Enter source city number: 0
Enter destination city number: 5
Shortest distance: 2
Path: Rajshahi -> Dhaka
```

## Algorithms Used
- **Dijkstra's Algorithm**: Finds the shortest route between cities.
- **Greedy Algorithm**: Helps plan trips within a specified budget.
- **Divide and Conquer**: Optimizes multi-city travel planning.

## Contributing
Feel free to fork this project and contribute improvements!

## License
This project is open-source and available under the MIT License.

