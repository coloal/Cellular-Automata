# Cellular-Automata
## How does this works and what is it?
Parallel implementation of 1D Cellular automata in Java 8, and a GUI(done with the Swing library) to observe them.

To compile and then execute:
  javac Simulatore.java
  java Simulatore
  
Once we got the program running, we can adjust some parameters in order to obtain the cellular automata requiered. We have the following parameters:
  - Semilla, SemillaX, SemillaW and SemillaY which are all seeds for the random generator. The random generator is used to initialize randomly the pane.
  -We also have a list of different random generators such as the methods of Fishman & Moore, the Randu metho and others.
  - fTransicion, is the transition function. To consult some well-knowns transition functions: http://mathworld.wolfram.com/ElementaryCellularAutomaton.html
  - Ncelulas, is the number of cells used in every generation(being 640 the maximum).
  - Nestados, is the number of posible states.
  - condicionFrontera, is the frontier condition. Being 0 a void frontier and any number greater than 0 would set a cylindric frontier.
  - Nvecinos, is the neighborhood.
  
 ![setting window](https://github.com/coloal/Cellular-Automata/blob/master/SettingsWindow.png)
 
 Now we can see the output of the first epoch, after pushing the botton "DIBUJAR", everytime we push this botton we create 640 new generations(since every row is a generation).
 
 ![Display window](https://github.com/coloal/Cellular-Automata/blob/master/ExampleCellularAutomata.png)
 
 We also have a very sample graphical display of the evolution of the Hamming distance(line in red) and the spatial entropy(line in black) of the cellular automata, and also a display of the numeric value of the seventh cell entropy in function of time.

![Statics window](https://github.com/coloal/Cellular-Automata/blob/master/StatsCA.png)

## Some well-known cellular automatas
