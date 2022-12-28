# AnnotationProcessor practice used gradle

execution step
> (use eclipse IDE)
> 1. project import... -> gradle - Existing Gradle Project -> select project location.
> 2. [tab] Gradle Tasks -> gradle-annotation-processor-example - build - build -> 'double click' or 'right click' and Create Gradle Run Configuration...   
> [If right click and Create Gradle Run Configuration is selected, proceed with steps 3 and 4.]
> 3. Check the 'build' value in gradle tasks.
> 4. click 'Run'.
> 5. right click on the 'annotation-sample' project. select Gradle - Refresh Gradle Project.

> (use cmd)
> 1. move to 'gradle-annotation-processor-example' project directory.
> 2. execute the following command.
> ```
> > .\gradlew clean build
> (each build)
> > .\gradlew :annotation-processor:clean build -x test
> > .\gradlew :annotation-sample:clean build -x test
> 
> ```

you can find the builder class in the 'generated-sources' directory of the 'annotation-sample' project.