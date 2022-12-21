# AnnotationProcessor practice used java

execution step   
> (use cmd)
> 1. move to '{priject location}\src' direcotry.
> 2. compile the processor class.
> ```
> > javac -source 1.8 -target 1.8 .\example\processor\ClassBuilder.java, .\example\processor\ClassBuilderIgnore.java, .\example\processor\ClassBuilderProcessor.java
> ```
> 3. compile the sample class.
> ```
> > javac -source 1.8 -target 1.8 -processor example.processor.ClassBuilderProcessor -processorpath .\ .\example\processor\Sample.java, .\example\processor\Sample2.java
> ```

you can find the builder class.