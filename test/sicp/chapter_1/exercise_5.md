Exercise 1.5
============

    (defn p (p))
    (defn test [x y]
      (if (= x 0) 0 y))

Then he evaluates the expression:

    (test 0 (p))

Applicative-order evaluation will evaluate the arguments first. It will evaluate `(p)`, which will in turn evaluate `(p)`, which will evaluate `(p)`, and so on. It will never return.

Normal-order evaluation will expand first:

    (test 0 (p))
    (if (= x 0) 0 (p))

Then reduce:

    (if true 0 (p))

`if` will only evaluate the case that it needs in order to return the correct result. It therefore evaluates the true branch:

    0
