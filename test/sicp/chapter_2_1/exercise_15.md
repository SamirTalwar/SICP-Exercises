Exercise 2.15
=============

From Exercise 2.14:

    user=> (def a (Interval/create {:lower 99, :upper 101}))
    #'user/a
    user=> (def b (Interval/create {:lower 150, :upper 160}))
    #'user/b
    user=> (/ a a)
    #user.Interval{:lower 99/101, :upper 101/99}
    user=> (par1 a a)
    #user.Interval{:lower 9801/202, :upper 10201/198}
    user=> (par2 a a)
    #user.Interval{:lower 99/2, :upper 101/2}

Running all this shows us that each operation on an interval introduces uncertainty. Even dividing an interval by itself does not produce the interval `(1 -> 1)`, but instead an approximation of this, with error relative to the original interval. We can also see that the `par1` operation has more uncertainty than the `par2` operation, and we can conclude that this is because it possesses more operations, which lead to more compounded uncertainty.

Eva is right. `par2` does not repeat the intervals, and therefore possesses fewer operations and produces less uncertainty. It is therefore "better".
