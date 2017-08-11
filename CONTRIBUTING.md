How To Contribute
=================
Please help, it takes quite a bunch of time to make it... If you want, you can 
just test it, and I'll be happy. Please see in the open projects if 
you want to code the thing.

To get the code, clone the repo:
`git clone https://github.com/EhWhoAmI/Monster-Quest`

### Ideas
If you want to add an idea, go to issues, type a title and description, amd add the
idea label to it. Be specific, or it will not pass. Your idea might pass, but it
is not guaranteed. Also, your idea might be subject to balancing, for better and more
fair gameplay. Btw, if you add images, there will have an even higher chance.

### Art
We always welcome graphics artists, but we follow the following rules(It's one, actually):
 - Use .png formats.
That's all.

### Code conventions
We just follow a few code conventions, they are all in overview.html in the src folder.
They are here, for simplicity:

- Spacing: 4 spaces. A good IDE should be able to do this.

  - Naming Conventions:

    - We follow the java naming conventions. Link is here.

    - Please make all the variables and method names clear. Please, or else we might spend quite a bit of time trying to refractor this.

 - Please, document your code with comments.

   - This one
     `// whatever you want to put`

    - or this one
      `/* whatever you want to put */`

    - Lastly the javadoc ones
      `/** Document */`

 - Please, no stuff like this:
    `someVariable.someMethod().anotherMethod().aRandomVariable.toString()`
    - No more than 3 fullstops will be allowed
  - Packages: how to place them and how to create them
    - Packages are created based on what the set package does.
    - For example, the package:
      `MonsterQuest.game.player`
    - Is in charge for the player behaviour, and basically, anything to do with the player.
    - Make as much packages as necessary, but do not do unnecessary packages. Make packages made for just one general task, then name classes to do name the specific task.
  - Logging
    - Please log often, to show how the program is going
    - To enable logging, add `-d` in the command line arguments. To log in files, add `-l` in command line arguments.
    - Add an import at the import part like this, it must be a static import
      `static import MonsterQuest.MonsterQuestMain.systemLog`
    - Logging can be used like this; use the systemLog variable contained there.
        `systemLog.log("Blah blah blah");`
    - There is only one restriction on where to log: In animations. That will clog up the processer.