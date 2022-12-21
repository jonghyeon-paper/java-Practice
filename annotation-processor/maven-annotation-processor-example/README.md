# AnnotationProcessor practice used maven

execution step   
> (use eclipse IDE)
> 1. project import... -> maven - Existing Maven Projects -> select project location.
> 2. right-click on the 'maven-annotation-processor-example' project in the project explorer. select Run As - (2)Maven Build...
> 3. Enter 'clean install' in 'goals'.
> 4. click 'Run'.

> (use cmd)
> 1. maven download.
> 2. execute the following command.
> ```
> > mvn clean install
> (if 'maven' is not registered in the environment variable)
> > mvn clean install -f {project path}
> ```

you can find the builder class in the 'generated-sources' directory of the 'annotation-sample' project.