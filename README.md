CS304 Project: Progress Report
=======

## Group Info

* Group Name: **Snappat**
* Member:

| Member |    ID    |
| ----- | ------ |
| 赵博弘 | 11612706 |
| 王泽林 | 11612715 |
| 陈德缘 | 11611310 |
|  任涛  | 11612717 |
|  王森  | 11612110 |
| 李可明 | 11612126 |

## Snappat: version 1.0

### Features

We built and implemented 3 features in this iteration's submission, those are:

* Login
* Object Recognition
* Back-end Data Structure Construction and Storing

We chose those 3 features as our starting step for the reason that it will help us obtaining more smooth and steady developing progress(rhythm) in the following iterations.

#### Feature1: Login

* Priority: **Most Important**
* Dependency: **Everything** depends on **Login**
* Dev degree of difficulty: **Medium**
* Test degree of difficulty:

Our Android app Snappat is mainly a social oriented software, thus for the whole progress users are using our app (story), an indispensable feature: login is the first step and the gateway to the colorful world in the app.

####Feature2: Object Recognition

* Priority: **Most Important**
* Dependency: Main feature of Puzzle Solving depends on **Object Recognition**
* Dev degree of difficulty: **Hard**
* Test degree of difficulty: 

Object Recognition is the main feature used on Puzzle Solving function whenever it's creating or solving a puzzle. For current stage, we have embedded the TensorFlow Lite library to our source and used the demo model to get basic recognition for several kinds of common object in our daily life.

#### Feature3: Back-end Data Structure Construction and Storing

- Priority: **Most Important**
- Dependency: Users' **data** relies on **Back-end**
- Dev degree of difficulty: **Medium**
- Test degree of difficulty: 

Back-end is the infrastructure of the application. We use PHP as back-end server to maintain the database of information which include users' info, puzzle details, solving history and messages. PHP server serve as a handler to make do with request and response between back-end and terminal(devices). The data structure shows as below:

##### Users

![User](https://i.loli.net/2019/04/20/5cba843062264.png)

##### Puzzles

```json
[
  {
    id:10,													// ID of the puzzle
    userid:0,												// User's ID to whom the puzzle belongs
    hint:text, 											// Corresponding hint of the puzzle
    treasure:'some important msg',	// Message embedded with the puzzle
    src:"",													// Image url of the puzzle
    key:{},													// Solution to the puzzle, represented by a dictionary
    																// containing key-value pairs
    sdate:'2019-12-10 22:10:03', 		// Date which puzzle released
    edate:"2019-12-11 10:10:00", 		// Date which puzzle expired
    coins: 100,											// Bonus the user will obtain when sovled the puzzle
    decoder:[1], 										// User's id who solved the puzzle
    favor:0, 												// Count of likes for the puzzle
    comment:[], 										// Comment of the puzzle
    view:[], 												// Users' id who have view the puzzle
  }
  ,...
]
```

##### History

```json
[
  {
    id:9,												// the id of puzzle which had been solved
    userid:1,										// the id of user which the puzzle belongs to
    date:"2019-12-10 22:10:03", // cracking date of the puzzle
  }
  ,...
]
```

##### Message

```json
[
  {
    content:"",	// the content of message
    user:"",		// sender
    type:1,			//消息类型,可选值有3: 1表示有人favor了你发布的mystery; 2表示有人comment了你的mystery; 3表示有人解开了你的mystery
    read:1, 		//是否已阅,1为已阅,0为未读
  }
  ,...
]
```

### Features Testing Scenarios



### Features Implementation

#### Feature: Login

![Login](https://i.loli.net/2019/04/20/5cbb196f74151.png)

#### Feature: Object Recognition

![Recognition](https://i.loli.net/2019/04/20/5cbb1a39b62d8.png)

#### Feature: Back-end



### Program Debugging and Cleanliness

#### Google Code Style

> com.sustech.snappet.utils is not a source directory, and we currently haven't used it.
>
> So it won't be covered by the code style check. 

All Java files passed the Google Code Style check.

#### FindBugs

> We met problems with plugin FindBugs itself. So we can't generate any result for this part.

#### PMD

> 706 PMD rule violations were found. See the report at: snappat/tools/pmd.html

No error detected.

### Javadoc

Finished, see the html files in root directory.

### JUnit Test

Finished, for all public methods.

## Schedule

> The plan for the following development

| Week No. | Plan                                |
| -------- | ----------------------------------- |
| Week 10  | Take photos                         |
| Week 11  | Set personal information            |
| Week 12  | Leave comments                      |
| Week 13  | Collections & Likes                 |
| Week 14  | Achievement system & Ranking system |
| Week 15  | Follow users &  Message box         |

## Code Review Group

Monday: 4.20-6.10pm (Instructor: Hu Chun Feng) 

TA E (5 groups), TA F (5 groups) 