# README for the Imageine Program #

## README ##

This README details the Imageine program - an Image Processing program created for Northeastern University’s Object-Oriented Design course: CS-3500. The Imageine program is able to load files of various types which include PPM, PNG, JPEG, BMP, and others to conduct various operations on each pixel of the image and save the outcome of the operations conducted on the image as a new file of any type. This README, in specific, contains specifications and descriptions of the code written for part two of the assignment. 

The Imageine program utilizes the Model-View-Controller design pattern to instantiate an instance of the program. 

In particular, the model represents an image-file using a 2-D array of Pixels - a class created that holds RGB (Red, Green, Blue) values of color - which are stored in a hashmap of String to Pixel. We decided to create a Pixel class as it allows us to conduct operations on each Pixel of a given image enabling more flexible, specific, and unique operations to be supported especially for future reusability. We decided to stick with the Model-View-Controller design for part two of the assignment as it allowed us to retains the same functionality supported by the assignment one code, while offering the ability to add support and functionalities for other file-types that are non-PPM through extending the design. In specific, we extended assignment one's ImageManipulatorModel interface as a BetterImageManipulatorModel interface implemented in the GeneralImageManipulatorModel class which now has the ability to perform operations on any file-types. We have this new compatibility as our save and load methods in GeneralImageManipulatorModel now uses two new utility methods 'readGeneral' and 'writeGeneral' which uses the ImageIO library to read and write various file-types (in assignment one we had readPPM and writePPM which only supported PPM file types). This new model interface also supports two new functionalities which are transform and filter that allow us to apply supplied transformation and/or filter matrixes to our images respectively. The transform method currently supports the ability to transform images into greyscale or sepia, and the filter methods support the ability to blur and sharpen an image. 


The view enables users to read operations that have been inputted into the program’s controller as text denoting commands that have been inputted and acted upon by the user and the program respectively. The view is currently limited to text descriptions of commands; in the future, we hope to implement the ability for users to see the loaded and operate the program using a GUI. For the second part of the assignment, because of our flexible design, we did not need to alter the view.

Lastly, the controller utilizes a scanner to read user input and conduct the specified operations (decided upon by switch-case statements) while outputting the appropriate view for each case of operations. For the second part of the assignment, we did *not* change our ImageManipulatorController interface. We updated our implementation of this interface to support our new model interface - BetterImageManipulatorModel. 


In order to run the program, a user should run the main method supplied in the ImageManipulator class or input program arguments in the class’ run configuration. In running the main method, the user will see a welcome message along with instructions describing how a user can see a list of potential commands by typing in “help” into the command-line. The commands offered by our program are as follows:

	- load [file-path] [file-name] -> loads a file at the path 'file-path' as 'file-name'.
	- save [file-path] [file-name] -> saves a file 'file-name' to the location 'file-path'.
	- greyscale [component] [image-name] [dest-image-name] -> creates a grayscale version of 'image-name' called 'dest-image-name' based on the supplied component.
	- vertical-flip [image-name] [dest-image-name] -> creates a vertically flipped version of 'image-name' called 'dest-image-name'.
	- horizontal-flip [image-name] [dest-image-name] -> creates a horizontally flipped version of 'image-name' called 'dest-image-name'.
	- brighten increment [image-name] [dest-image-name] -> creates a brightened version of 'image-name' called 'dest-image-name', where '
	- filter [filter-type] [image-name] [dest-image-name] -> creates a filtered version of 'image-name' called 'dest-image-name' using the filter 'filter-type'.
	- transform [transform-type] [image-name] [dest-image-name] -> creates a transformed version of 'image-name' called 'dest-image-name' using the transform 'transform-type'.
 
To use the program, the user must enter the commands written above into the command-line with the respective items substituted within the square-brackets. To use the operations, a user must first load an image using the load command (the image must be within the same package as the program's code. Afterwards, the user is able to use the loaded file’s file-name to conduct operations on. The user is able to load and operate on multiple images throughout one instance of the program. However, if a user loads or saves two images with the same file-name, the most recently loaded image will override the previously loaded image. 

*** Sample script console/command-line inputs ***
	1. load "Monster.ppm" "example" brighten 10 "example" "exampleBrighten" save "saveExampleBrighten" "exampleBrighten"
		- This loads a PPM-image text-file called "Monster.ppm" as "example". It then brightens the loaded "example" file by an increment of 10 and stores it as "exampleBrighten". Lastly, it saves the brightened image ("exampleBrighten") as a new PPM-image file named "saveExampleBrighten". 

	2. Load "koala.ppm" "koalaImage" greyscale "red" "koalaImage" "redKoala" save "saveRedKoala" "redKoala" 
		- This loads a PPM-image called "koala.ppm" as "koalaImage". It then greyscales the image with a red component and stores it as "redKoala". Finally, it saves the grayscale image ("redKoala") as a new PPM-image file named "saveRedKoala". 



** Class/Interface Design **
	1. Model
		- Pixel Class: A class that represents a singular pixel in an image and holds Red-Green-Blue color values that when combined are able to make various pixel colors to compose a whole image. Assignment two's implementation of the pixel class has been updated to clamp values of pixels to be between 0-255.  
		- ImageManipulatorModel Interface: Holds operations that are offered by the model for an ImageManipulator. These include loading an image, saving an image, and operations that manipulate the content of an image. 
		- AbstractImageManipulatorModel Class: An abstract class that implements the ImageManipulatorModel as it contains operation for an abstracted model object such as a different image file type. An abstract class was used to allow code reusability/reduce code duplication for future additional features such as more operations and the support of non-PPM image types.
		- PPMImageManipulatorModel Class: A class that extends the AbstractImageManipulatorModel class as it is a model object which potentially shares non-private/non-unique methods with other models. This class contains method code that is unique to a PPM-Image. In specific, the ability to load and save a PPM-image text file.
		- BetterImageManipulatorModel Interface: Holds operations that are offered by the model for an ImageManipulator that now supports most file-types that include non-PPM file-types. These include loading an image, saving an image, and operations that manipulate the content of an image. In specific, these operations (on-top of those previously supported by assignment one's implementation) now include the ability to apply transformations and filters on an image.
		- GeneralImageManipulatorModel Class: A class that implements the BetterImageManipulatorModel interface and supports its new methods (filter and transform) as well as the operations previously defined in the old ImageManipulatorModel interface. 
	2. View
		- ImageManipulatorView Interface: Contains operations that are offered by the view for an ImageManipulator. In specific, the ability to render a message of type String to some output. 
		- ImageManipulatorTextView Class: This class implements the ImageManipulatorView as it is a view object which holds the code for operations that outputs a text-based view describing the actions and operations that have been conducted in an instance of the program. This class takes in an appendable which denotes the output destination of the text-based view.
		- CorruptibleImageManipulatorTextView Class: An instance of the text view class that has been corrupted and throws an IOException. This class is used for testing and to represent how methods in the ImageManipulatorView throws an IOException given the transmission of a message to the data destination (appendable) fails. 
	3. Controller
		- ImageManipulatorController Interface: Contains the operations that are offered by the controller for the Imageine program. In specific, it contains the method that allows the program to run an instance of the program. 
		- ImageManipulatorControllerImpl Class: This class implements the ImageManipulatorController as it is the implementation (written out code) of the controller interface. This class takes in a model, view, and readable user inputs as arguments for the constructor. Using these three arguments, this class outputs a certain view and model based on the user input that has been parsed through the scanner item. 
	4. Main
		- ImageManipulator Class: Contains the main method that allows users to run the Imageine program and supply arguments through the command-line as represented by an array of String.

** Method/Constructor Design **
	1. Model
		- Pixel class:
			- Constructor: Takes in integer values that represent Red-Blue-Green color values. The constructor throws an IllegalArgumentException if any of the values are negative. 
			- Brighten: Takes in an integer value denoting the increment to brighten the Pixel by. If the supplied integer is positive, each of the pixel's fields are incremented by the integer value with a maximum of 255. If the integer value is negative, each of the pixel's fields are decreased by the integer amount with a minimum of 0.
			- greyscale: Take a component and utilizes a switch case to set the values of a Pixels field according to the various operations. 
			- equals: Overrides equals to more specifically compare if two pixels represent the same thing. 
			- hashCode: Overrides hashCode by hashing the three fields of the Pixel.
		- ImageManipulatorModel Interface:
			- loadImage: Takes in a fileName and a destName as arguments that signifies what file is being loaded and how it should be referred to in the hashmap of files respectively. In particular, the loadImage reads the supplied image such that it can be represented in some manner using the Pixel class. This method throws an error if the filePath supplied doesn't point to any file.
			- saveImage: Takes in a destPath and a fileName as argument which denotes what name the file is to be saved as, and the file within the hashmap of files that is being saved respectively. It throws an IOException if the file to be saved can't be transmitted to the output destination. 
			- greyScale: Takes in a component type, fileName, and destName as arguments. Applies greyscale of type component to the image denoted by fileName and stores it in the hashmap of files as the destName. Throws an IllegalArgumentException if the file being operated on can't be found.
	 		- verticalFlip: Takes in a fileName and a destName. Vertically flips the imaged denoted by filename and stores the resulting image in the hashmap of files as the destName. Throws an IllegalArgumentException if the file being operated on doesn't exist.
			- horizontalFlip: Takes in a fileName and a destName. Horizontally flips the image denoted by filename and stores the resulting image in the hashmap of files as the destName. Throws an IllegalArgumentException if the file being operated on doesn't exist.
			- brighten: Takes in an integer increment, a fileName and a destName. Brightens the image denoted by fileName by the integer increment and saves the resulting image in the hashmap of files as the destName. Throws an IllegalArgumentException if the file being operated on doesn't exist.
		- AbstractImageManipulatorModel class:
			- loadImage: Allows an image of some abstracted type to be loaded. However, it's an abstract method denoting that each model has different underlying code that allows the program to read the image and translate it as Pixels.
			- saveImage: Allows an image that has been operated on, or loaded into the program to be saved as some file with the supplied destPath. However, it's an abstract method denoting that each model has different underlying code that allows the program to read the image being operated on and write it as some image file. 
			- greyscale: For each pixel in the image, apply the greyscale component operation on the pixel. Afterwards, put the new image in the hashmap of files. 
			- verticalFlip: For each pixel in the image, flip their column position but keep the row position same. Afterwards, put the new image in the hashmap of files.
			- horizontalFlip: For each pixel in the image, flip their row position but keep the column position same. Afterwards, put the new image in the hashmap of files.
			- brighten: For each pixel in the image, brighten by the supplied increment. Afterwards, put the new image in the hashmap of files. 
		- PPMImageManipulatorModel class: 
			- Constructor: Takes in no fields and sets files as a new HashMap mapping String to a 2-D array of Pixels. 
			- loadImage: Read the PPM image and put it in the hashmap of files.
			- saveImage: Gets the PPM image and writes it as a new PPM file.
		- BetterImageManipulatorModel interface:
			- filter: Creates a filtered version of the image called destName by applying the given filter.
			- transform: Creates a transformed version of the image called destName by applying the given transform.
		- GeneralImageManipulatorModel class:
			- loadImage: Takes in a fileName and a destName as arguments that signifies what file is being loaded and how it should be referred to in the hashmap of files respectively. In particular, the loadImage reads the supplied image such that it can be represented in some manner using the Pixel class. This method throws an error if the filePath supplied doesn't point to any file.
			- saveImage: Takes in a destPath and a fileName as argument which denotes what name the file is to be saved as, and the file within the hashmap of files that is being saved respectively. It throws an IOException if the file to be saved can't be transmitted to the output destination. 
			- filter: Applies the supplied filter matrix to the supplied image.
			- transform: Applies the supplied transformation matrix to the supplied image.

	2. View
		- ImageManipulatorView Interface:
			- renderMessage: Takes in a message of type String and renders it to the appendable. Throws an IOException if the message can't be transmitted to the appendable.
		- ImageManipulatorTextView class:
			- Constructor: Takes in an appendable as output and throws an IllegalArgumentException if the appendable is null.
			- renderMessage: Renders the supplied message to the output.
	3. Controller
		- ImageManipulatorController Interface:
			- launchProgram: Launches an instance of the program.
		- ImageManipulatorControllerImpl class:
			- Constructor: Takes in an ImageManipulatorModel, ImageManipulatorView, and a Readable. Throws an IllegalArgumentException if any of these arguments are null. The controller outputs the correct model and view based on the input.
			- launchProgram: Launches the program and runs through the program based on the users input. There is a switch-case block that handles each possible command that the program can handle. Within each of the case, we take the respective arguments and supply it to the methods that will conduct the operation on the image. 
	4. Main
		- ImageManipulator class:
			- Main: Instantiates the program and allows the user to interact with the program. Our new updated main can now also take in a -file file-path as an arg.


** Acknowledgements **

This program was able to be created with the guidance provided by the Professors and Teaching Assistants of Northeastern University’s Object-Oriented Design course. 

** Install **

After downloading or cloning the project files from this repository, open the program and navigate to the ImageManipulator class. Load your own images into the program package (or stick with the ones we’ve supplied) and run the main method. You should be greeted with a welcome message along with instructions to view all the commands offered by our program!

** License **
Sudarpo, Jonathan. “Monster.ppm” 
Shutterstock. n.d. Koala Bear Climbing On Tree. Retrieved June 9, 2022 (https://www.shutterstock.com/image-photo/koala-bear-climbing-on-tree-1087953089?irclickid=VX8RJ8xB8xyIT0l2t-RrFXUXUkD2sPROD233zs0&irgwc=1&utm_medium=Affiliate&utm_campaign=TinEye&utm_source=77643&utm_term=). "Koala.ppm" 
KindPng. n.d. Watermelon Pixel Art, HD Png Download. Retrieved June 17, 2022 (https://www.kindpng.com/imgv/iiwwTTw_watermelon-pixel-art-hd-png-download/). "watermelon.png" 




