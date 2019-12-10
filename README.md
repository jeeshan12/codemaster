# code master TestVagarant



## Added Page factory Implementation

```sh
If you use the PageFactory, you can assume that the fields are initialised. If you don't use the PageFactory, then NullPointerExceptions will be thrown if you make the assumption that the fields are already initialised
```

```sh
Introduced seperate page constants and page classes . This will bring more readability and usablity for the users. Instead of changing the locator in the test class , just change the locators in the corresponding page constant class.
```

## Reusability of the methods

```sh
Created a sepearte util SeleniumUtil for basic UI opeartions. This will bring more reusbaility for the user who is writing the tests. 
```

## Data Parameterization

```sh
Instead of hard coding ,using TestNG data provider concept for parameterizing the test data . Test data is present as a part of JSON file . Each test should have a seperate JSON test data file.
```
```sh
Using properties file for test configuration. Using spring for loading the properties file.
```
 
## WebDriver handling for specific browser

```sh
BrowserFactory class returns WebDriver reference for specific browser passed from the properties file.
```

## Driver handling

```sh
TestBase class is created to get the driver reference returned from the BrowserFactory class. Each test should extend TestBase.
In this way we can avoid handling the driver in the test class.
```

## Avoided hard wait and flakiness in the test

```sh
Awaitility is introduced to avoid any hard waits and flakiness in the tests. This will be useful in handling AJAX calls also.
```

## WebDriverManager
```sh
Managing the executables will become very difficult as you need to update the drivers manually as soon as new verison of browser gets released. WebDriverManager handles the automatic configuration of the executables for you . Depending upon the machine you are executing your test ,WDM downloads the compatible driver version for your browser. There is no need to specifically define the platform, it is smart enough to identify the platform you  are executing your tests.Hence reducing your code to big extent. You can also define the architecture and restrict the WDM to use the specific version for the driver also.
```


## Paralle execution

```sh
Needless to say parallel execution is possible when you execute your tests from testNG.xml
```