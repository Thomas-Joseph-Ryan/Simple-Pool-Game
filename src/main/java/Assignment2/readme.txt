Place config file in the resources folder and ensure it is named config.json.

Run the program using gradle run

The specified methods from the specs are seperated into folders, namely BallBehaviours (STRATEGY),
BallBuilders(BUILDER) and ConfigReaders(FACTORY). However dependencies and creation for these classes are
not limited to those specific folders for example BallManager uses BallBehaviours to create the balls, however BallManager
is not in the BallBehaviour folder. Also there are dependencies between these folders for example, the BallBehaviours are
given to each ball by the director class in the BallBuilders.

Thats pretty much it, hope that you enjoy how I implemented things!