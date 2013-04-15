Exercise 1.6
============

    (defn new-if [predicate then-clause else-clause]
      (cond predicate then-clause
            :else else-clause))

The issue with this is that calling it can result in infinite recursion. Applicative ordering states that the `predicate`, `then-clause` and `else-clause` are all evaluated before `new-if` is called, so recursive functions using `new-if` will explode.
