Exercise 1.15
=============

Definition of `sine`
--------------------

    (defn abs [x]
      (if (>= x 0)
        x
        (- x)))
    (defn cube [x] (* x x x))
    (defn p [x] (- (* 3 x) (* 4 (cube x))))
    (defn sine [angle]
      (if (not (> (abs angle) 0.1))
        angle
        (p (sine (/ angle 3.0)))))

Application of `p`
------------------

    (sine 12.15)
    (p (sine 4.05))
    (p (p (sine 1.35)))
    (p (p (p (sine 0.45))))
    (p (p (p (p (sine 0.15)))))
    (p (p (p (p (p (sine 0.05))))))
    (p (p (p (p (p 0.05)))))

`p` is applied 5 times.

Growth
------

The order of growth in both space and number of steps in linear with respect to *a* when `(sine a)` is evaluated.

Order of growth: *Θ(a)*
