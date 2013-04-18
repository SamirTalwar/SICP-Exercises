Exercise 2.4
============

    (defn cons [x y]
      (fn [m] (m x y)))
    (defn car [z]
      (z (fn [p q] p)))

Expanding and reducing `(car (cons x y))` produces:

    (car (cons x y))
     = (car (fn [m] (m x y)))
     = ((fn [m] (m x y)) (fn [p q] p))
     = ((fn [p q] p) x y)
     = x

The corresponding definition of `cdr` is:

    (defn cdr [z]
      (z (fn [p q] q)))

Expanding and reducing `(cdr (cons x y))` produces:

    (cdr (cons x y))
     = (cdr (fn [m] (m x y)))
     = ((fn [m] (m x y)) (fn [p q] q))
     = ((fn [p q] q) x y)
     = y
