Exercise 1.34
=============

With the function:

    (defn f [g] (g 2))

Upon calling:

    (f f)

The chain of operations will proceed like this:

    (f f)
      = (f 2)
      = (2 2)

Upon which the application will attempt to call the number 2 as a function, which will cause it to throw an exception.
