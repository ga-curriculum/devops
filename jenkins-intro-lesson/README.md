---
Title: Intro to Jenkins
Type: Morning Exercise
Duration: "1:00"
Author:
    Name: Alex De Marco
---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Intro to Jenkins

## Opening

Who doesn't want to save time and money? You can use **continuous integration** (CI) and **continuous delivery** (CD) to build, test, and deploy software safely and efficiently, ultimately delivering value to your users more quickly.

Generally, companies begin with continuous integration (CI). Continuous delivery goes a step further than CI by automating the deployment of releases, including infrastructure and configuration changes. Continuous deployment, which we won't cover in too much depth here, goes a step further than continuous delivery by deploying every change to production automatically.

----

## Continuous Integration 

![](https://ga-instruction.s3.amazonaws.com/assets/continuous-integration/CImeme.png)

Don't be Disaster Girl. You can avoid disasters with continuous integration, which means that individual developers integrate their work into a main repository multiple times a day. That way, you can catch integration bugs early and collaborate more quickly. _Integrating work from multiple developers_ involves, at a minimum, building and testing the application whenever a change is made by a developer (i.e., continuously). 

### Commit Small Changes Often

This is one of the cardinal rules of CI. Developers should commit to the mainline branch often (at least every day) and changes should be as small as possible so that, when a change _breaks the build_, anyone can quickly pinpoint the specific issue. 

With many changes happening all of the time, a fast build process is critical so that developers get feedback about broken builds right away.

### How Will Continuous Integration Save Us Time and Money?

As your mom always said, the early bird catches the worm. Finding bugs earlier in the **software development life cycle** (SDLC) is **much** less costly than finding bugs later.

![](https://www.isixsigma.com/wp-content/uploads/images/stories/migrated/graphics/604a.gif) 

*Relative Costs to Fix Software Defects (Source: IBM Systems Sciences Institute)*

### Build and Test Your Application Automatically With a Build Pipeline

So, how do we find bugs early? By testing early and often. You really don't want to have to think too much about *when* and *how* to run tests. Most modern software development teams would rather run tests automatically when there's any kind of change to the code, specifically in the mainline branch where all developers integrate their code for the next release. 

![](https://ga-instruction.s3.amazonaws.com/assets/continuous-integration/automate-all-the-things-small.jpeg)

A **build pipeline** is the automated process that watches for changes. When a change is detected, the build pipeline builds the application, tests it, and possibly does other stuff, too. Pipelines can have stages, and each stage can have steps. Steps in your pipeline can execute Maven, Gradle, shell scripts, and more. 

> **Let's discuss**: What do you think are three characteristics of an ideal build pipeline? 

1. __Fast__: You want your build pipeline to give you near-instant feedback on whether your tests pass or fail. This is so that you can fix problems right away while you are actively thinking and working on the feature.
1. __Repeatable__: You always want to run the same process to build and test your application, and you always want to run that process in the same build environment. That will reduce the number of things that change from one build to the next so that, when bugs come up and you need to troubleshoot, there's less to worry about. 
1. __Reliable__: You want to set up your build pipeline and tests so that, if there's a failure, it's because of the application being built and tested rather than a problem in your test environment or the build pipeline itself. Common issues that lead to "false positives" include bad data in your database that wasn't cleaned up from the last test run or some other kind of contention for a shared resource (such as files on a shared file system).

----

## Create a Build Pipeline With Jenkins

The most popular tool for building Java-based CI pipelines is [Jenkins](https://jenkins.io/). Jenkins is an open-source automation server that can be used to automate all sorts of tasks related to building, testing, and delivering or deploying software.

Let's do a bit of Jenkins research. With a partner, take a look at the [Jenkins documentation](https://jenkins.io/doc/) (or other Jenkins resources). Answer the following questions:

- How does a Jenkins pipeline work?
- What is a Jenkinsfile?
- What's the difference between a declarative and scripted Jenkins pipeline?

> Did the docs raise any other questions for you or leave you wanting more? Jot down those questions and we'll discuss them as a class!

### Jenkins Plugins

A core feature of Jenkins are [**plugins**](https://plugins.jenkins.io/), which allow you to extend Jenkins' basic functionality. 

Jenkins plugins come in all shapes, sizes, and purposes. With a partner, research the following Jenkins plugins:

- Global Build Stats Plugin
- Job Generator Plugin
- Performance Plugin

Want to make sure your code is high quality? Jenkins pipelines will also often include plugins such as [SonarQube](https://www.sonarqube.org/) and [Find Bugs](http://findbugs.sourceforge.net/) that scan code for bad coding practices and security vulnerabilities.

### Moving to Nexus

Once your application is built (usually in the first stage of your pipeline), your application artifact (e.g., an rpm, war/jar, ear file, or image) will be deployed to a repository such as [Nexus](https://www.sonatype.com/nexus-repository-sonatype). By uploading the artifact to Nexus, the next stages in the pipeline can actually run on different servers and pull the artifact(s) from the repo to operate on them. Repositories such as Nexus also offer other features for dependency management and audit controls.

![](https://help.sonatype.com/repomanager2/files/5411442/5411443/1/1508787001638/using_dependencies.png)

*View a Component’s Dependencies in Nexus From [Inspecting the Component Dependencies | Sonatype Nexus Documentation](https://help.sonatype.com/repomanager2/using-the-user-interface/inspecting-the-component-dependencies)*

-----

## CI's Best Friend: CD

![](https://ga-instruction.s3.amazonaws.com/assets/continuous-integration/deliver-continuouslysmall.png)

Continuous delivery (CD) is an approach that builds on continuous integration. CD ensures that our code is always in a deployable state, even with thousands of developers making changes regularly. Teams produce software in short cycles so they can release the software whenever necessary. CD helps teams save time and money and makes delivering changes less risky.

### Development, Staging, and Production Environments

In most enterprises, there are several different environments where a new version of the application (a release candidate) needs to be built, tested, and/or reviewed before deployment to the production environment.
  
1. __Build__: Oftentimes, you'll see a build environment where the code is compiled and application artifacts are constructed (e.g., an rpm, war file, or image). Sometimes, unit tests will run here, too. Once built, the artifact is pushed to a repository and should never change.  
1. __Test__: The artifact would then be pulled from the repository and installed in a test environment for integration testing, performance testing, and other testing purposes. 
1. __Stage__: A staging environment is the last environment before promoting the artifact to production. That's where more tests can be run (automated or not) and/or human users may perform some kind of acceptance testing. 
  
In CI/CD, this whole process (including deployments from build to test to stage and (optionally) to prod) should be automated by your build pipeline. Here's an example of what that pipeline might look like in Jenkins:
 
 ![](https://raw.githubusercontent.com/marcingrzejszczak/jenkins-pipeline/master/docs/jenkins/pipeline_finished.png)
 
*Full Pipeline View From [Spring Cloud Microservice Java Pipeline](https://github.com/marcingrzejszczak/jenkins-pipeline/blob/master/README.adoc)*
