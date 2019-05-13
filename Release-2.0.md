# CS304 Project: Progress Report

## Group Info

- Group Name: **Snappat**
- Member:

| Member | ID       |
| ------ | -------- |
| 赵博弘 | 11612706 |
| 王泽林 | 11612715 |
| 陈德缘 | 11611310 |
| 任涛   | 11612717 |
| 王森   | 11612110 |
| 李可明 | 11612126 |



## Snappat: version 2.0

### Questions

- We had received suggestions and this time will change tag correct name “version 2.0”.

  However, Last we had finish nearly all javadoc, there must be some mistakes.

- We add more 4 feature and will explain them later

- We had made 71 commits after first version.

- Yes, we had finish features with expected number.



### Features

We built and implemented 3 features in this iteration's submission, those are:

- Better structure
- Take photos
- Fantastic Home Page
- SMS verification

The biggest advance is we change our structure to a formal google MVC structure and make convenient to future work. We also finish home page, take photos and some other parts.

#### Feature1: Better structure

- Priority: **Most Important**
- Dependency: **Everything** depends on **this new structure**
- Dev degree of difficulty: **Medium**
- Test degree of difficulty:

This MVC structure is more easy and brief to test, however, some time some associate classes may cause other error.

#### Feature2: Take photos

- Priority: **second Important**
- Dependency: the basic of this app is depending on **Object Recognition**
- Dev degree of difficulty: **Hard**
- Test degree of difficulty: 

Most code are just UI change and it’s hard to do logic test and we do some UI test, it is **middle** hard.

#### Feature3: Fantastic Home Page

- Priority: **Most Important**
- Dependency: User main interaction page, the most important part of our app and its UI.
- Dev degree of difficulty: **Medium**
- Test degree of difficulty: 

There are lots to test and It’s our most important part of this APP, there are lots logic and UI test to do, and it is **hard**

#### Feature4: SMS verification

- Priority: **middle Important**
- Dependency: all users will login with SMS code and it is really convenient for people.
- Dev degree of difficulty: **Medium**
- Test degree of difficulty: 

These code are relate to http and server request and the test is really**hard**



### Features Testing Scenarios

#### Login 

- postive:
  login correctly, change activity and show login successfully
- negative
  - If phone number is not in correct format:
    	Toast show 'wrong phone number' 
  - If verify code is not in correct format:
    	Toast show 'wrong phone number' 
- SMS verification
  - send verification code
  - judgement verification code

#### Object Recognition

This part import lots of google's code, and there are also some places to improve and finish. This part will test later.

#### Server

- ##### Login

  - ##### Positive: login correctly, return user info

  - ##### Negative: wrong phone or wrong verity code, return failed

- ##### getMysteryList

  - Positive: with right cookie, it can return your mystery list.

  - Negative: wrong cookie or no cookie, it will return failed.

- getHistroyList

  - Positive:with right cookie, it can return your history list. 

  - Negative:wrong cookie or no cookie, it will return failed.

- and so on



### Features Implementation

- #### Feature: MVC structure


![1557756096626](https://i.loli.net/2019/05/13/5cd9806c2454f78963.png)

- #### Feature: Take photos


![1557758947762](https://i.loli.net/2019/05/13/5cd984134769c69480.png)

- #### Feature: Home Page

![1557756773002](https://i.loli.net/2019/05/13/5cd980a8812fe99620.png)

- #### Feature: SMS verification

![1557756773001](https://i.loli.net/2019/05/13/5cd980a13fe5375177.png)

### Program Debugging and Cleanliness

- #### Google Code Style


> com.sustech.snappet.utils is not a source directory, and we currently haven't used it.
>
> So it won't be covered by the code style check. 

​	All Java files passed the Google Code Style check.

- #### FindBugs


> We met problems with plugin FindBugs itself. So we can't generate any result for this part.

- #### PMD


> 706 PMD rule violations were found. See the report at: snappat/tools/pmd.html

No error detected.

- ### Javadoc


Finished, see the html files in ./Doc.

- ### JUnit Test


Finished, for most public methods, except some method have not be implemented till now or some library file from google.



## Schedule

| Week No. | Plan                                |
| -------- | ----------------------------------- |
| Week 13  | Collections & Likes                 |
| Week 14  | Achievement system & Ranking system |
| Week 15  | Follow users &  Message box         |

