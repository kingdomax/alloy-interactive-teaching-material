# alloy-interactive-teaching-material

The interactive teaching application is designed to introduce the fundamentals of Alloy language. 
With this application, learner will gain a solid understanding of Alloy's key concepts through a series of unit lessons.
Each lesson includes examples, practice exercises, and informative feedback to help you master the material.
Plus, user can easily pick up where you left off and learn at your own pace.

## Project Setup
1. Install OpenJDK/JDK 
2. Clone the [repository](https://github.com/kingdomax/alloy-interactive-teaching-material.git)
3. Open in any IDE of your choice (e.g. Eclipse, VS Code, etc.)
4. Run `app\src\main\java\alloy\interactive\teaching\material\App.java`

## Commandline Instruction
To run the application, you have two options:

1) Walkthrough each unit lesson - This is a traditional approach that allows you to navigate and learn through your desired lesson. Upon completion of the lesson, you will be prompted with a syntax example and end up with an exercise to test your knowledge of the lesson. It is worthwhile to note that each lesson's sections will be saved as .txt or .als files in the same directory as the application.  
You can run the application in this scenario using the below command:  
`java -jar alloy-interactive-teaching-material.jar`

2) Jump to a specific lesson's section - You can quickly access a particular lesson's section by simply instructing the application with the below command synopsis:  
`java -jar alloy-interactive-teaching-material.jar --lesson <lesson-number> --part <explanation||example||exercise||exercise-solution||exercise-submit> [--partParam <solution-file.als||instance.file.xml>]`  
```
java -jar alloy-interactive-teaching-material.jar --lesson 2 --part explanation                      // This command instructs the application to display only the explanation section from lesson2.
java -jar alloy-interactive-teaching-material.jar --lesson 2 --part exercise                         // This command instructs the application to display only the exercise section from lesson2.
java -jar alloy-interactive-teaching-material.jar --lesson 2 --part exercise-solution                // This command instructs the application to display only the exercise's solution from lesson2.
java -jar alloy-interactive-teaching-material.jar --lesson 2 --part exercise-submit my-solution.als  // This command instructs the alloy analyzer to validate "my-solution.als" model and display the feedback output.
java -jar alloy-interactive-teaching-material.jar --lesson 2 --part exercise-submit my-instance.xml  // This command instructs the application to visualize the instance from "my-instance.xml" in GUI mode.
```

## Unit Lessons
Application covers 3 unit lessons, each focusing on a specific topic.
Each lesson also includes specific tasks to help you reinforce your understanding of the material.
1) Signatures, Relations, Multiplicities
2) Facts, Functions, Predicates
3) All types of operators, Quantifiers
