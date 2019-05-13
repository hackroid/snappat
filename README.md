# Snappat

## Group Members

| Member |    ID    |
| :----: | :------: |
| 赵博弘 | 11612706 |
| 王泽林 | 11612715 |
| 陈德缘 | 11611310 |
|  任涛  | 11612717 |
|  王森  | 11612110 |
| 李可明 | 11612126 |

## Group name: **Snappat**

   It's a compound word created by sur team.

   To introduce our project, here we explain our group name first.

   > Snap: a snapshot.
   >
   > Pat: draw attention to something by tapping it gently.

   Our project is a novel photo social application by solving puzzles.

   * **A puzzle is a photo with labels.** Users can public a puzzle by taking a photo, and our application will perform **object detection**, thus attaching several labels to this photo.
   * Once a puzzle has been published, users can **solve this puzzle** accidentally by taking similar photos with same labels. Also, object detection is used in labeling the photos.
   * For example, when a user takes a photo with five computer in it, another user can solve this "puzzle" by taking another photo with five computers.

## Group Formation

> Why do your group select this project? Explain the reason in terms of the criteria below:

### Usefulness

>  What is your target audiences/users for the app?

Our application targets at **young people** who like taking photos, exploring the mysteries and having a desire to meet strangers. By using Snappat, young people can enjoy the process of publishing their own photos with puzzles and they can expect others to solve those puzzles. What's more, when taking photos, there may be serendipities to solve a "mystery" and meet its creator.

### Use of framework

>  How important is the framework in the app? For regular users, how likely that the framework will be invoked?

We use [Tensorflow Lite](https://www.tensorflow.org/lite)([repo](https://github.com/tensorflow)) as our kernel framework. This key technology is used by our most important feature: Puzzle.

An instance of puzzle consist of (which our framework get involved):

* Initialization

  When user is creating a puzzle, he/she usually seem to have some fantastic ideas of the things around  him/her. Then the user use our app to take photo on these stuff, and followed by the action, our framework could present a well recognization to get these target into camera aperture.

* revelation

  In the discovery page user will tend to looking for some puzzles he/she maybe interested in.  When the user click on that favored one, TF framework step in and start to recognize the things in the scene with the shift of user's motion. Whenever the recognition achieve the goals of the puzzle, at any moment, the user will reach the answer and  get into the answer page.

### Novelty

* Q: List at least 3 keywords to summarize the purpose of your app.

  A: PHOTOGRAPHY, SOCIAL, PUZZLE

* Q: Perform a search in [GitHub](https://github.com/). Do similar apps that you propose exist?

  A: No, our app is quite original and we hope to implement it well.

* Q: What is the novelty of your project?

  * Q: What is the novelty of your project

    * better design

      Our app is define a new way of social intercourse. People  come into contact with others by puzzle. Someone take photos and publish puzzle with tips, others solve it by take similar photos which contain necessary elements in some positions, they can also commit this puzzle.  People publish and solve puzzles by some karma and get familiar with others in this process.

    * unique features

      It’s not only a new social model but also a entertainment method.

      It contain newest image segmentation and image recognition technology.

      it has fresh UI and abundant logic.

Q:  Is it easy to test the app? Does it require specific location to
be set? Does it require special inputs/data? Does it require special hardware?

A: Yes, it's easy to test this app since it mainly uses phone's camera and requires no special inputs or hardware.

### Diversity of team members

First iteration:

| Name   | Role                   |
| ------ | ---------------------- |
| 王森   | Leader & Developer     |
| 任涛   | Developer              |
| 李可明 | Developer              |
| 陈德缘 | Developer              |
| 赵博弘 | Designer & Developer   |
| 王泽林 | Tester & Documentation |

### List at least 10 features supported in your App.

1. Sign in and Log in
2. Take photos
3. Leave comments
4. Collections
5. Ranking system
6. Achievement system
7. Share the link
8. Set personal information
9. Follow users
10. Likes
11. Message box

### Design the main screen of your app

![屏幕快照 2019-03-30 下午1.59.07](https://ws4.sinaimg.cn/large/006tKfTcgy1g1ks7jkt4uj313c0u04d4.jpg)

![屏幕快照 2019-03-30 下午1.59.23](https://ws4.sinaimg.cn/large/006tKfTcgy1g1ks7ol63vj31830u0gxt.jpg)

![屏幕快照 2019-03-30 下午1.59.50](https://ws3.sinaimg.cn/large/006tKfTcgy1g1ks7tjcz8j30xh0u0tlk.jpg)
